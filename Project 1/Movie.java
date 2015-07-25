// CS 0445 Spring 2014
// Ramirez T TH 2:30 - 3:45
// Assignment 1 Movie Class


import java.io.*;

public class Movie implements Comparable<Movie>, Serializable
{   
    private String title, dateOfRelease, runningTime; //The different part of the movie objects
    
    //constructor that allows user to initialize the three variables in one statement
    public Movie(String title, String dateOfRelease, String runningTime)
    {
       this.title = title;
       this.dateOfRelease = dateOfRelease;
       this.runningTime = runningTime;
    }
    
    //a no arg constructor for if the user wants to initialize the variables later
    public Movie()
    {
        title = null;
        dateOfRelease = null;
        runningTime = null;
    }
    
    //this methods sets the title of the movie object
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    //this method sets the release date of the movie object in YYYY/MM/DD format
    public void setDateOfRelease(String dateOfRelease)
    {
        this.dateOfRelease = dateOfRelease;
    }
    
    //this method sets the running time of the movie object in Min,sec format(EX: 120,33 --> 120 minutes and 33 seconds))
    public void setRunningTime(String runningTime)
    {   
        this.runningTime = runningTime;
    }
    
    //this method returns to the user the title of the movie
    public String getTitle()
    {
        return this.title;
    }
    
    //this method returns to the user the relese date of the movie
    public String getDateOfRelease()
    {
        return this.dateOfRelease;
    }
    
    //this  method returns to the user the running time of the movie
    public String getRunningTime()
    {
        return this.runningTime;
    }
    
    //implementation of the Comparable interface, compares based on title
    public int compareTo(Movie MovieToCompare)
    {
        int conditionTitle = this.title.compareTo(MovieToCompare.title);
        int conditionDate = this.dateOfRelease.compareTo(MovieToCompare.dateOfRelease);
        int conditionTime = this.runningTime.compareTo(MovieToCompare.runningTime);
        
        if(conditionTitle > 0)
            return 1;
        else if(conditionTitle < 0 )
            return -1;
        else if(conditionTitle == 0)
        {
            if(conditionDate > 0)
                return 1;
            else if(conditionDate < 0)
                return -1;
            else if(conditionDate == 0)
            {
                if(conditionTime > 0)
                    return 1;
                else if(conditionTime < 0)
                    return -1;
                else if(conditionTime == 0)
                    return 0;
            }
        } 
        
        return 0;
    }
    
    //a to string method that returns a string representation of a movie object
    public String toString()
    {
        return "\nMovie: " + this.title + " \nDate of Release: " + this.dateOfRelease + " \nRunning Time(minutes,seconds): " + this.runningTime + "\n";
    }
    
    public boolean equals(Object MovieToCompare)
    {
        Movie theMovie = (Movie)MovieToCompare;
        if(theMovie.getTitle().equals(title))
        {
            if(theMovie.getDateOfRelease().equals(dateOfRelease))
            {
                if(theMovie.getRunningTime().equals(runningTime))
                {
                    return true;
                }
            }
        }
        
        //DEBUG
        //System.out.println("This is the value of the equals: ");
        //System.out.println("Comparing titles: " + theMovie.getTitle().equals(title) );
        //System.out.println("Comparing dates: " + theMovie.getRunnin().equals(dateOfRelease) );
        //System.out.println("Comparing times: " + theMovie.
        return false;
    }  
}
