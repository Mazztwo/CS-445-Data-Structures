//CS445     Alessio Mazzone     Assignment 2    Ramirez T,TH 2:30-3:45
//Main program of assig 2. This program will act as the store, simulating various activities
//based on the file inputed through the commandprompt

import java.io.*;
import java.util.Scanner;

public class Assig2
{
  public static void main(String[] args)throws IOException
  {
    int DAY = 0;//global clock for the day
    double laborCost = 0.0;//holds a counter for the labor cost. Incremented every time there is a push
    double potatoCost = 0.0;//holds the total cost for all potatoes added to stack
    double totalCost = laborCost + potatoCost;//adds the labor cost plus the cost of each box moved
    
    //The four OVERALL variables below will hold the total cost of everything throghout the entire simulation
    //as opposed to just the current shipment
    double laborCostOVERALL = 0.0;
    double potatoCostOVERALL = 0.0;
    int shipmentBoxesOVERALL = 0;//holds the number of boxes for shipments
    
     int boxesAddedToStack = 0;//This holds the boxes for the most recent shipment
    
    System.out.println("Starting simulation on Day " + DAY + "\n");  
    
    LinkedStack<Spud> mainStack = new LinkedStack<Spud>();//holds the main stack of spuds
    LinkedStack<Spud> tempStack = new LinkedStack<Spud>();//temp stack of spuds for moving
    Spud boxDelivered = null; 
    
    File theFile = new File(args[0]);//grabs the file name from the command line
    Scanner inputFile = new Scanner(theFile);//Opens the command line specified file
    
    String command = inputFile.nextLine();

    while(! command.equals("quit"))
    { 
        
      if(command.equals("receive"))
      {
          int boxesToProcess = Integer.parseInt(inputFile.nextLine());
          boxesAddedToStack = boxesToProcess;
          
          //Initializes these variables to 0 for each shipment that is received
          //This is to keep track of the most recent shipment vs overall numebrs
          laborCost = 0.0;
          potatoCost = 0.0;
          totalCost = 0.0;
          totalCost = 0.0;
         
          System.out.println("Processing shipment of " + boxesToProcess + " boxes of potatoes!"); 
        
          String[] Box = null;
          
          for(;boxesToProcess > 0; boxesToProcess--)
          {
              String receivedBox = inputFile.nextLine();
              Box = receivedBox.split(":");
              boxDelivered = new Spud(Box[0], Integer.parseInt(Box[1]), Double.parseDouble(Box[2]));
              potatoCost += boxDelivered.getCost();
              
              //If the box received is already expired, it will be thrown away
              if(boxDelivered.getExpirationDate() < DAY)
              {
                  System.out.println(boxDelivered + " is already expired! It will be thrown away!");
                  boxesAddedToStack--;
                  potatoCost -= boxDelivered.getCost();
              }
              
              //CASE 1: If both the main and temp stacks are empty, place box on temp automatically
              else if(mainStack.isEmpty() && tempStack.isEmpty())
              { 
                  tempStack.push(boxDelivered);
                  laborCost += 1.0;
                 
              }
              
              //CASE 2: If the main stack is empty and the temp stack is not)
              else if(mainStack.isEmpty() && !(tempStack.isEmpty()))
              {
                  Spud TOP_TEMP = tempStack.peek();
                  
                  if(boxDelivered.getExpirationDate() > TOP_TEMP.getExpirationDate())
                  {
                      mainStack.push(boxDelivered);
                      laborCost += 1.0;
                      
                      while(TOP_TEMP != null)
                      {
                        tempStack.pop();
                        mainStack.push(TOP_TEMP);
                        laborCost += 1.0;
                        TOP_TEMP = tempStack.peek();               
                     }                   
                  }
                  else if(boxDelivered.getExpirationDate() <= TOP_TEMP.getExpirationDate())
                  {
                      tempStack.pop();
                      mainStack.push(TOP_TEMP);
                      laborCost += 1.0;
                      
                      TOP_TEMP = tempStack.peek();
                      
                      while(TOP_TEMP != null)
                      {
                          if(boxDelivered.getExpirationDate() <= TOP_TEMP.getExpirationDate())
                          {
                              tempStack.pop();
                              mainStack.push(TOP_TEMP);
                              laborCost += 1.0;
                      
                              TOP_TEMP = tempStack.peek();
                          }
                          else if(boxDelivered.getExpirationDate() > TOP_TEMP.getExpirationDate())
                          {
                               mainStack.push(boxDelivered);
                               laborCost += 1.0;
                               
                               TOP_TEMP = tempStack.peek();
                               
                               while(TOP_TEMP != null)
                               {
                                   tempStack.pop();
                                   mainStack.push(TOP_TEMP);
                                   laborCost += 1.0;
                                   TOP_TEMP = tempStack.peek();               
                                } 
                              
                          }
                      }
                      
                      if(TOP_TEMP == null)
                      {
                          mainStack.push(boxDelivered);
                          laborCost += 1.0;
                      }
                      
                  }
              }      
              //CASE 3: If the main stack has items, but the temp stack does not
              else if(!(mainStack.isEmpty()) && tempStack.isEmpty())
              {
                  Spud TOP_MAIN = mainStack.peek();
                  Spud TOP_TEMP = tempStack.peek();
                  
                  if(boxDelivered.getExpirationDate() > TOP_MAIN.getExpirationDate())
                  {
                      mainStack.pop();
                      tempStack.push(TOP_MAIN);
                      laborCost += 1.0;
                      
                      TOP_MAIN = mainStack.peek();
                      
                      while(TOP_MAIN != null)
                      {
                          if(boxDelivered.getExpirationDate() > TOP_MAIN.getExpirationDate())
                          {
                              mainStack.pop();
                              tempStack.push(TOP_MAIN);
                              laborCost += 1.0;
                      
                              TOP_MAIN = mainStack.peek();
                              if(TOP_MAIN == null)
                                tempStack.push(boxDelivered);
                          }
                          else if(boxDelivered.getExpirationDate() <= TOP_MAIN.getExpirationDate())
                          {
                              TOP_MAIN = null;
                              mainStack.push(boxDelivered);
                              laborCost += 1.0;
                              
                              TOP_TEMP = tempStack.peek();
                              
                              while(TOP_TEMP != null)
                              {
                                  tempStack.pop();
                                  mainStack.push(TOP_TEMP);
                                  laborCost += 1.0;
                                  
                                  TOP_TEMP = tempStack.peek();
                              }     
                          } 
                      }
                  }
                  else if(boxDelivered.getExpirationDate() <= TOP_MAIN.getExpirationDate())
                  {
                     mainStack.push(boxDelivered);
                     laborCost += 1.0;   
                  }    
              } 
          } 
      
        System.out.println("Added " + boxesAddedToStack + " boxes to your stack!\n"); 
        
        totalCost = potatoCost + laborCost;
        potatoCostOVERALL += potatoCost;
        laborCostOVERALL += laborCost;
        shipmentBoxesOVERALL += boxesAddedToStack;
       
        command = inputFile.nextLine();  
      }     
      else if(command.equals("use"))
      {
         int boxesToUse = Integer.parseInt(inputFile.nextLine()); 
         System.out.println(boxesToUse + " boxes of potatoes are needed!");
         Spud topMain = mainStack.peek();
         
         if(topMain == null)
            System.out.println("You do not have any potatoes left. Please order more!");
         else
         {
             mainStack.pop();
             System.out.println("USING " + topMain);
             boxesToUse--;
             topMain = mainStack.peek();
             
             while(topMain != null)
             {
                 for(; boxesToUse > 0; boxesToUse--)
                 {
                     mainStack.pop();
                     System.out.println("USING " + topMain);
                     topMain = mainStack.peek();
                 }
                 
                 topMain = null;
             }
             
             
         }
         
         System.out.println("\n");
         command = inputFile.nextLine();  
      }
      else if(command.equals("display"))
      {
          if(mainStack.isEmpty())
          {   
              if(tempStack.isEmpty())
                System.out.println("No boxes in the stack. Please order more potatoes!");
              else if(!(tempStack.isEmpty()))
              {
                  Spud topOfTemp = tempStack.peek();
                  while(topOfTemp != null)
                  {
                      tempStack.pop();
                      mainStack.push(topOfTemp);
                      topOfTemp = tempStack.peek();
                  }
                  
                   System.out.println("\nHere is your stack of boxes:");
                   System.out.println(mainStack);
              }
            }
          else  
          {
              System.out.println("\nHere is your stack of boxes:");
              System.out.println(mainStack);
            }
          command = inputFile.nextLine();
      }
      else if(command.equals("skip"))
      {
          DAY++;
          System.out.println("The current day is now day " + DAY + "\n");
          
          Spud topMain = mainStack.peek();
          
          while(topMain != null)
          {
              if(DAY > topMain.getExpirationDate())
              {
                  mainStack.pop();
                  System.out.println(topMain + " has expired. It will now be removed from the stack and thrown away!\n");
                  topMain = mainStack.peek();
                  
              }
              else
                topMain = null;
          }
         
          command = inputFile.nextLine();
      }
      else if(command.equals("report"))
      {
          System.out.println("Spuds Spuds and Spuds Report:");
          System.out.println("\tMost Recent Shipment:");
          System.out.println("\t\tBoxes: " + boxesAddedToStack);
          System.out.println("\t\tPotato cost: " + potatoCost);
          System.out.println("\t\tLabor (moves): " + (int)(laborCost));
          System.out.println("\t\tLabor cost: " + laborCost);
          System.out.println("\t\t___________________________");
          System.out.println("\t\tTotal cost: " + totalCost + "\n");
          
          System.out.println("\tOverall Expenses:");
          System.out.println("\t\tBoxes: " + shipmentBoxesOVERALL);
          System.out.println("\t\tPotato cost: " + potatoCostOVERALL);
          System.out.println("\t\tLabor (moves): " + (int)(laborCostOVERALL));
          System.out.println("\t\tLabor cost: " + laborCostOVERALL);
          System.out.println("\t\t___________________________");
          System.out.println("\t\tTotal cost: " + (laborCostOVERALL + potatoCostOVERALL));
          
          command = inputFile.nextLine();
      }
    }
    
    System.out.println("\nYou have reached the end of the simulation!");
    inputFile.close();
  } 
}