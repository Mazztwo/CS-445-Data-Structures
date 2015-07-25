// CS 0445 Spring 2014
// Ramirez T TH 2:30 - 3:45
// Assignment 1 Main program to test Movie class with myDB class

import java.util.*;

public class Assig1B
{
    public static void main(String [] args)
    {
        int selection;         //Holds user's menu selection
        String fileName;       //Holds user's filename when option 1 is picked 
            
        MyDB<Movie> userDatabase = new MyDB<Movie>(); //creates the ser's database. overwritten if they load their own
        Movie duplicate; //for option 2. if the movie the user is trying to add to the DB already exists, it will be equal to this movie
        
        String movieTitle;          //holds the title for the movie
        String movieReleaseDate;     //holds the release date for the movie
        String movieRunTime;        //holds the mmovie's runTime
        
        boolean keepGoing = true; //This variable holds the condition that will allow the menu to keep running until the user wishes to exit the program
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("\nWelcome to the Movie Database Program \n");
        System.out.println("*NOTE* A database will be created for you if you chose to\nadd a movie before loading your own database\n ");
        
        while(keepGoing)
        {
            System.out.println("\nMenu:");
            System.out.println("________________________________________________\n");
            System.out.println("1. Restore database of movies from a file");
            System.out.println("2. Add a movie to your database ");
            System.out.println("3. Search for a movie in your database ");
            System.out.println("4. Remove a movie from your database ");
            System.out.println("5. Display your database in sorted order ");
            System.out.println("6. Display your database in reverse sorted order ");
            System.out.println("7. Save your database to a file ");
            System.out.println("8. Quit the Movie Database Program ");
            System.out.println("________________________________________________\n");
            System.out.print("Please select an option(1-8): ");
            
            try
            {
                selection = keyboard.nextInt();   
                keyboard.nextLine();
            }
            catch(InputMismatchException e)
            {
               System.out.print("\nERROR: Please enter an integer value from 1 to 8"); 
               selection = keyboard.nextInt();  
               keyboard.nextLine();
            }    

            if(selection == 1)
            {
                System.out.print("Please enter the filename to restore the database from: ");
                fileName = keyboard.nextLine();
                
                userDatabase.restoreFromFile(fileName);  
                
                System.out.println("\nYour database has been restored!\n");
            }
            else if(selection == 2)
            {
                Movie movieHolder = new Movie();
                
                System.out.print("Please enter the name of the movie you would like to add: ");
                movieTitle = keyboard.nextLine();
                
                System.out.print("Please enter the release date of the movie in YYYY/MM/DD format: ");
                movieReleaseDate = keyboard.nextLine();
               
                System.out.print("Please enter the runtime of the movie in min,sec format: ");
                movieRunTime = keyboard.nextLine();
                
                movieHolder.setTitle(movieTitle);
                movieHolder.setDateOfRelease(movieReleaseDate);
                movieHolder.setRunningTime(movieRunTime);
                 
                duplicate = userDatabase.findItem(movieHolder);
            
                if(duplicate == null)
                {
                    userDatabase.addItem(movieHolder);
                    System.out.println("\nYour movie was added to the database!");
                }
                else if(duplicate.equals(movieHolder))
                {
                    System.out.println("\nThe movie you are trying to add is already in your database. It will  not be added.");
                }
            }
            else if(selection == 3)
            {
                if(userDatabase.isEmpty())
                {
                    System.out.println("\nCurrently, your database is empty. Please update it!");
                }
                else
                {
                    Movie movieHolder = new Movie();
                
                    System.out.print("Please enter the name of the movie you would like to search for: ");
                    movieTitle = keyboard.nextLine();
                    
                    System.out.print("Please enter the release date of the movie in YYYY/MM/DD format: ");
                    movieReleaseDate = keyboard.nextLine();
                   
                    System.out.print("Please enter the runtime of the movie in min,sec format: ");
                    movieRunTime = keyboard.nextLine();
                    
                    movieHolder.setTitle(movieTitle);
                    movieHolder.setDateOfRelease(movieReleaseDate);
                    movieHolder.setRunningTime(movieRunTime);
                      
                    duplicate = userDatabase.findItem(movieHolder);
                    
                    if(duplicate.equals(movieHolder))
                    {
                        System.out.println("\nYour movie was found! Here it is: ");
                        System.out.println(duplicate.toString());
                    }
                    else if(duplicate.equals(null))
                    {
                        System.out.println("\nI'm sorry, but the movie you have entered was not found in your database.");
                    } 
                }
            }
            else if(selection == 4)
            {   
                if(userDatabase.isEmpty())
                {
                    System.out.println("\nCurrently, your database is empty. Please update it!");
                }
                else
                {
                    Movie movieHolder = new Movie();
                    
                    System.out.print("Please enter the name of the movie you would like to remove from your database: ");
                    movieTitle = keyboard.nextLine();
                    
                    System.out.print("Please enter the release date of the movie in YYYY/MM/DD format: ");
                    movieReleaseDate = keyboard.nextLine();
                   
                    System.out.print("Please enter the runtime of the movie in min,sec format: ");
                    movieRunTime = keyboard.nextLine();
                    
                    movieHolder.setTitle(movieTitle);
                    movieHolder.setDateOfRelease(movieReleaseDate);
                    movieHolder.setRunningTime(movieRunTime);
                      
                    duplicate = userDatabase.removeItem(movieHolder);
                    
                    if(duplicate.equals(movieHolder))
                    {
                        System.out.println("\nYour movie was found and removed! Here is the movie you removed from your database: ");
                        System.out.println(duplicate.toString());
                    }
                    else if(duplicate.equals(null))
                    {
                        System.out.println("\nI'm sorry, but the movie you have entered was not found in your database.");
                    }  
                }
            }
            else if(selection == 5)
            {   
                if(userDatabase.isEmpty())
                {
                    System.out.println("\nCurrently, your database is empty. Please update it!");
                }
                else
                {
                userDatabase.sort();
                System.out.println(userDatabase.toString());
                }
            }
            else if(selection == 6)
            {
                if(userDatabase.isEmpty())
                {
                    System.out.println("\nCurrently, your database is empty. Please update it!");
                }
                else
                {        
                    System.out.println("\nHere is your database in reverse sorted order");                    
                    userDatabase.reverse();
                    System.out.println(userDatabase.toString());
                }    
            }
            else if(selection == 7)
            {   
                if(userDatabase.isEmpty())
                {
                    System.out.println("\nCurrently, your database is empty. Please update it!");
                }
                else
                {
                    System.out.print("Please enter the file name that you would like to save your database as: ");                    
                    fileName = keyboard.nextLine();                  
                    userDatabase.saveToFile(fileName);                    
                    System.out.println("\nYour database has been saved!");
                }        
            }
            else if(selection == 8)
            {
                keepGoing = false;
            }   
        }      
    }
}
