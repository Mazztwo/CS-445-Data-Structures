// MyDB Class


import java.util.Arrays;
import java.io.*;

public class MyDB<T> implements SimpleDB<T>, Reverser, Sorter, SaveRestore
{
    private T[] data;                   //underlying datastructure used to store data
    private int num_Elements = 0;       //Used for the addItem and removeItem methods. Tells the method how many items have been added 
    
    //This is the no arg constructor that initializes to a defult of 15
    public MyDB()
    {
        data = (T[]) new Object[15];
    }    

    //Initialize the underlying array
    public MyDB(int size)
    {
        data = (T[]) new Object[size];
    }
    
    //adds an item to the DB array. If item placed in array.length index, DB array is doubled
    public boolean addItem(T item)
    {
        if(num_Elements == data.length)
        {
            T[] temp = (T[]) new Object[2 * data.length];
            data = ensureCapacity(temp, 0);
            System.out.println("The database size has been doubled to a capacity of  " + data.length + ".");
        }
        
        data[num_Elements] = item;
        num_Elements++;
        return true;
    }
    
    //this private inner methods recursevly calls itself to add elements from the old data array to the new doubled data array
    private T[] ensureCapacity(T[] temp, int start)
    {

        if(start < num_Elements)
        {
            temp[start] = data[start];
            ensureCapacity(temp, start + 1);
        }
   
        return temp;  
    }
   
    //removes an item from the DB array and returns that item if it is found. else returns null
    public T removeItem(T item)
    {
        T foundItem = null;
        
        foundItem = remove(item, 0);
        
        if(foundItem != null)
        {
            num_Elements--;
            int nullIndex = findIndexOfNull(0);
            shiftData(nullIndex);
        }
    
        return foundItem;
    }
    
    // a method used to shift data over after something has been removed from somewhere in the middle
    private void shiftData(int start)
    {
        if(start < num_Elements)
        {
            if(data[start] == null && data[start + 1] == null)
            {
                start = num_Elements;
            }
            data[start] = data[start + 1];
            data[start + 1] = null;
            shiftData(start + 1);
        }
        
        data[data.length -1] = null;
       
    }
    
    // finds the index of the null after an item is removed form the data
    private int findIndexOfNull(int start)
    {
        if(start < data.length)
        {
            if(data[start] == null)
            {
                return start;
            }
            else
            {
                return findIndexOfNull(start + 1);
            }
        }
        
        return -1 ; // This should never happen and is just here to satisfy the compiler
    }
  
    
    // removes an item if the item exists, return null if the item to remove is not found
    private T remove(T item, int start)
    {
        if(start < num_Elements)
        {
            if(item.equals(data[start]))
            {
                data[start] = null;
                return item;
            }
            else
            {
                return remove(item, start + 1);
            }
        }
      
        return null;
    }
 
    //finds and retuns an item from the DB array. If not found, returns null
    public T findItem(T item)
    {  
      return findAnItem(item, 0);   
    }
     
    //finds an item in the data, return null if the item is not found. To be used for the findItem() method above
    private T findAnItem(T item, int start)
    {
        if(start < num_Elements)
        {
            if(data[start] != null)
            {
                if(data[start].equals(item))
                {
                    return  item;
                }
                else
                {
                    return findAnItem(item, start + 1);
                }
            }
        
        }
        return null;
        
    }
    
    //Since resizing occurs automatically, this method always returns false
    public boolean isFull()
    {
        return false;
    }
    
    //loops through array to see if all elements are null. If an index is found that doesn't equal null, then returns false
    public boolean isEmpty()
    {
        return checkIfIsEmpty(0);
    }

    // used to check if the data[] is empty. If it is empty, returns true, else false. To be used with isEmpty() method above
    private boolean checkIfIsEmpty(int start)
    {
        if(start < num_Elements)
        {
            if(data[start] == null)
            {
                return checkIfIsEmpty(start + 1);
            }
         
            return false;
        }
        
        return true;
    
    }
    
    //returns the number of items in the database
    public int size()
    {
        return findSize(0);
    }
    
    // this method finds the size of the data[]. To be used with the size() method above
    private int findSize(int start)
    {
        if(start < num_Elements)
        {
            if(data[start] != null)
            {
                return  findSize(start + 1);
            }
            
            return start;
            
        }
        
        return start;
    }
    
    //changes all data in the array to null
    public void clear()
    {
        clearData(0);
    }
    
    //this method clears all the data[]. Makes everything equal to null. To be used with the clear() method above
    private void clearData(int start)
    {
        if(start < data.length)
        {
            if(data[start] == null)
            {
                //do nothing --> means data is cleared
            }    
            else if(data[start] != null)
            {
                data[start] = null;
                num_Elements--;
                clearData(start + 1);  
            }
        }    
    }
    
    //a toString method to display a string representation of the DataBase
    public String toString()
    {
        StringBuilder temp = new StringBuilder();
        temp.append("Here is the DB: \n");
        return toStringRec(temp, 0);
    }

    //this method returns the string version of data[]. To be used with toString() method above
    private String toStringRec(StringBuilder b, int ind)
    {
        if(ind < num_Elements)
        {
            b.append(data[ind]);
            b.append(" ");
            return toStringRec(b, ind + 1);
        }
        
        return b.toString();
        
    }
    
    //This method is part of the Reverser interface and reverses the order of the data in the database
    public void reverse()
    {
        int numItems = this.size();
        reverseData(0, numItems);
       
    }
    
    // this method reverses data[]. To be used with reverse() method above
    private void reverseData(int start, int numItems)
    {
        T temp = null;
        
        if(start < data.length)
        {
            
            if(start != (numItems - 1))
            {    
                if(! (start > (numItems -1 )))
                {
                    temp = data[start];
                    data[start] = data[numItems - 1];
                    data[numItems - 1] = temp;
            
                    reverseData(start + 1, numItems - 1);
                }
            }
        }
    }
    
    //This method trims null items from the DB and sorts the data according to comparable
    public void sort()
    {
        T[] temp = (T[]) new Object[this.size()];
        data = ensureCapacity(temp, 0);
        Arrays.sort(data);
    }
    
    //writes the db to a file with the specified file name
    public boolean saveToFile(String fileName)
    { 
      try
       {
          FileOutputStream outStream = new FileOutputStream(fileName);
          ObjectOutputStream objectOutput = new ObjectOutputStream(outStream);
          
          return saveDBToFile(objectOutput, 0);
  
       }
       
      catch(IOException e)
      {
          System.out.println("There was an error and the file could not be created");
           return false;
       }
    }    
    
    //this method loops through the db and saves each element in a file. To be used with saveToFile() method above.
    private boolean saveDBToFile(ObjectOutputStream objectOutput, int start)
    {
        try
        {
            if(start < data.length)
            {
                objectOutput.writeObject(data[start]);
                saveDBToFile(objectOutput, start + 1);
            }
        }
        catch(IOException e)
       {
           System.out.println("There was an error and the file could not be created");
           return false;
       }
       return true;
        
    }
   
    //reads objects from specified file. Clears and overwrites current collection
    public boolean restoreFromFile(String fileName)
    {   
        this.clear();
        
        try
        {
            FileInputStream inStream = new FileInputStream(fileName);
            ObjectInputStream objectInput = new ObjectInputStream(inStream);

            return restoreDBFromFile(objectInput, 0);
        
        }
        catch(FileNotFoundException e)
        {
            System.out.println("The file does not exist.");
            return false;
        }
        catch(IOException e)
        {
            return true;
        }
    }
    
    //restores a db from a specified file by writing each object in the file to data[]. To be used with restoreFromFile() method above
    private boolean restoreDBFromFile(ObjectInputStream objectInput, int start)
    {
        try
        {
            if(start < data.length)
            {
                T objectRead = (T) objectInput.readObject();
                data[start] = objectRead;
                if(objectRead != null)
                {
                    num_Elements++;
                }
                restoreDBFromFile(objectInput, start + 1);
                
            }
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println("The file does not exist.");
            return false;
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error");
            return false;
        }
        catch(IOException e)
        {
            return true;
        }
        
        return false;// this wont ever happen, only here to satisfy the method syntax
    }
    
    // this method returns true if the data[] is sorted from low to high 
    public boolean isSorted()
    {
        return checkIfSorted(0);
    }
   
    private boolean checkIfSorted(int start)
    {
        if(start < num_Elements - 1)
        {
            Comparable curr = (Comparable)data[start];
            Comparable next = (Comparable)data[start + 1];
            
            int check = curr.compareTo(next);
            
            if(check <= 0)
            {
                return  checkIfSorted(start + 1);
            }
            else
                return false;
            
        }
    
        return true;
    }
    
    // this method calls a recursive method findMode that finds the most frequent item and how many times it appears in the data
    public void showMode()
    {
        T firstItem = data[0];
        
        int[] mode = findMode(firstItem,0,0,0,0,0);
        
        System.out.println("The mode is:    Data: " + data[mode[0]] + "       Freq: " + mode[1]);
        
        
    }
    
    // this method finds the mode of the data and returns it and the frequency with which it appears in a an array of length 2. answer[0] is the index in 
    // the data where the mode is and answer[1] is the frequency with which it occurs
    public int[] findMode(T currItem, int currItemIndexInData,int currIndex, int currItemFreq, int modeFreq, int modeIndex)
    {
        if(currIndex < num_Elements)
        {
            if(data[currIndex].equals(currItem))
            {
                currItemFreq++;
                currIndex++;
                return findMode(currItem, currItemIndexInData, currIndex, currItemFreq, modeFreq, modeIndex);

            }
            else
            {
                currIndex++;
                return findMode(currItem, currItemIndexInData, currIndex, currItemFreq, modeFreq, modeIndex); 
            }
        }    
        else if(currItemFreq > modeFreq)
        {
            modeFreq = currItemFreq;
            modeIndex = currItemIndexInData;
        }
            
        if(currItemIndexInData < num_Elements -1)
        {
            currItemIndexInData++;
            currIndex = currItemIndexInData;
            currItem = data[currIndex];
            currItemFreq = 0;
            return findMode(currItem, currItemIndexInData, currIndex, currItemFreq, modeFreq, modeIndex);
        }
         
        int[] answer = new int[2];
        answer[0] = modeIndex;
        answer[1] = modeFreq;
        return answer;
    }
}   

