

// This is the pilot program for assignment 4.

import java.io.*;
import java.util.*;

public class Assig5 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // makes a file object to open a huff file
        File inputFile = new File(args[0]); 
        
        // passes the file object to the scanner to use as input
        Scanner treeFile = new Scanner(inputFile);
        
        // This creates the root of the huffman tree to use for analysis
        BinaryNode<Character> huffmanTreeRoot = new BinaryNode<Character>('!');
        
        // Consumes the first line of the huff.txt files, which is always I, indicating that the root is always an interior
        treeFile.nextLine();
        
        // The constructTree method recursively builds the huffman tree from the txt file
        huffmanTreeRoot.setLeftChild(constructTree(huffmanTreeRoot, treeFile));
        huffmanTreeRoot.setRightChild(constructTree(huffmanTreeRoot, treeFile));
        
        // This initializes an empty String array to hold all of the codes for each letter in the Huffman tree
        String[] encodingTable = new String[26];
        
        // This huffCode will hold the code that has currently been constructied while travesing the tree and is an argument for the buildEncodingTable method.
        // When you go left a node, add 0 to the code, when you go right a node, add
        // 1 to the code, and when you go back a node, remove the last character in the code. 
        StringBuilder huffCode = new StringBuilder(1);
        
        // This method will recursivly build the encoding table to use for encoding and decoding of values. 
        buildEncodingTable(huffmanTreeRoot, huffCode, encodingTable);
        
        System.out.println("\nThe Huffman Tree has been restored.\n");
        
        // Used as the looping condition for the interactive CLI
        boolean quit = false;
        Scanner input = new Scanner(System.in);
        
        // The variable available letters will contain a string with all of the possible letters for the hoffman tree
        String availableLetters ="";
        int index = 0;
      
        while(encodingTable[index] != null)
        {
            int letter = index + 65;
            availableLetters += Character.toString((char)letter);
            index++;
                        
        }
       
        
        // This while loop is the main CLI menu. Will run until option 3, quit is selected
        while(! quit)
        {
            System.out.println("Please choose from the following:");
            System.out.println("1) Encode a text string");
            System.out.println("2) Decode a Huffman string");
            System.out.println("3) Quit");
            System.out.println("\nSelection: ");
            
            int selection = input.nextInt();
            
            if(selection == 1)
            {
               System.out.println("Enter a string from the following characters:\n" + availableLetters); 
               String userInput = input.next();
               System.out.println("Huffman encoded string:");
               
               for(int i = 0; i < userInput.length(); i++)
               {
                   System.out.println(encodingTable[(int)userInput.charAt(i) - 65]); 
               }
               System.out.println();
            }
            else if(selection == 2)
            {
              System.out.println("Here is the encoding table:");
              
              printEncodingTable(encodingTable, availableLetters);
              
              System.out.println("Please enter a Huffman string (one line, no spaces):");
              
              String userInput = input.next();
              
              System.out.println("Decoded text string:");
              
              System.out.println(decode(userInput, huffmanTreeRoot));
            
            }
            else if(selection == 3)
            {
                quit = true;
                System.out.println("Good bye!");
            }
            else if(selection < 0 || selection > 3)
            {
                System.out.println("Invalind input. Please enter an integer of value 1, 2, or 3\n");
            }
            
        }
        
        // closes the file 
        treeFile.close();
        
    }

    // This method will be called when constructing a Huffman tree. It takes in a Scanner object as an argument,
    // which holds the text file for the structure of the tree. It also takes a Binary node as an argument,
    // to dynamically build the tree as the structure is read from the text file.
    private static BinaryNode<Character> constructTree(BinaryNode<Character> node, Scanner treeFile)
    {
        String line = treeFile.nextLine();
        BinaryNode<Character> root = null;
        
        if(line != null)
        {   
            if(line.charAt(0) == 'I')
            {
                root = new BinaryNode<Character>('!');
                
                if(node.getLeftChild() == null)
                    node.setLeftChild(root);  
                else if(node.getRightChild() == null)
                    node.setRightChild(root);
             
                root.setLeftChild(constructTree(root, treeFile));
                root.setRightChild(constructTree(root, treeFile));
            }
            else if(line.charAt(0) == 'L')
            {
                BinaryNode<Character> leaf = new BinaryNode<Character>(line.charAt(2));
                if(node.getLeftChild() == null )
                {
                    node.setLeftChild(leaf);
                    return leaf;
                }
                else if(node.getRightChild() == null)
                {
                    node.setRightChild(leaf);
                    return leaf;
                }
            }
        }
        return root;
    }

    // This method takes the root of the Huffman tree as an argument and recursivly traverses the entire thing. There is also a StringBuilder passed to it. 
    // Every time the pointer moves left a node, a 0 is added to code, and when the pointer moves right a node, a 1 is added to the code. When the pointer goes
    // back a node, it will remove the last character in the code. Whenever the pointer of the huffman tree reaches a leaf, it adds the code to the table of values.
    private static void buildEncodingTable(BinaryNode<Character> node, StringBuilder code, String[] table)
    {
        if(node.getLeftChild() != null)
        {
            code.append("0");
            buildEncodingTable((BinaryNode)node.getLeftChild(), code, table);
            code.deleteCharAt(code.length() - 1);
        }
        if(node.isLeaf())
        {
            Character letter = node.getData();
            int index = (int)letter - 65;
            table[index] = code.toString();  
        }
        if(node.getRightChild() != null)
        {
            code.append("1");
            buildEncodingTable((BinaryNode)node.getRightChild(), code, table);
            code.deleteCharAt(code.length() - 1);
        }     
    }  
    
    // this method takes in the encoding table which is an array of strings, and all of the available letters, and prings the tabel from it
    private static void printEncodingTable(String[] encodingTable, String availableLetters)
    {
        int tableSize = countItems(encodingTable);
        
        
        for(int i = 0; i < tableSize; i++)
        {
            Character letter = availableLetters.charAt(i);
            String code = encodingTable[i];
            
            System.out.println(availableLetters.charAt(i) + ": " + encodingTable[i]);
        }
    }
    
    // this method counts the nunber of items that are in the encoding table
    private static int countItems(String[] encodingTable)
    {
        int index = 0;
        
        while(encodingTable[index] != null)
        {
            index++;
        }
        
        return index ;
    }
    
    // this method will take a string of 0's and 1's and decode it into a readable string
    private static String decode(String code, BinaryNode huffmanTreeRoot)
    {
        StringBuilder userCode = new StringBuilder(code);
        BinaryNode curr = huffmanTreeRoot;
        StringBuilder decodedString = new StringBuilder();
        
        while(userCode.length() != 0)
        {
            if(userCode.charAt(0) == '0')
            {
                userCode.deleteCharAt(0);
                curr = (BinaryNode)curr.getLeftChild();
                
                if(curr.isLeaf())
                {
                    decodedString.append(curr.getData());
                    curr = huffmanTreeRoot;
                }
            }
            else if(userCode.charAt(0) == '1')
            {
                 userCode.deleteCharAt(0);
                curr = (BinaryNode)curr.getRightChild();
                
                if(curr.isLeaf())
                {
                    decodedString.append(curr.getData());
                    curr = huffmanTreeRoot;
                }
                
            }
        }
        return decodedString.toString();
    
    }
    
    
}
