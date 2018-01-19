// Spring 2014
// MyDB Class

import java.util.Arrays;
import java.io.*;

public class MyDB<T> implements SimpleDB<T>, Reverser, Sorter, SaveRestore 
{
    private T[] data;                   //underlying datastructure used to store data
    private int num_Elements = 0;       //Used for the addItem and removeItem methods. Tells the method how many items have been added 
    private int index_To_Shift_To;      //Used for the shift method. Tells the method which index to shift everything to
    
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
            data = Arrays.copyOf(data, 2 * data.length);
            System.out.println("The database size has been doubled to a capacity of  " + data.length + ".");
        }
        
        data[num_Elements] = item;
        num_Elements++;
        return true;

    }
    
    //removes an item from the DB array and returns that item if it is found. else returns null
    public T removeItem(T item)
    {
        for(int i = 0; i < data.length; i++ )
        {
            if(item.equals(data[i]))
            {
                data[i] = null;
                index_To_Shift_To = i;
                this.shift(index_To_Shift_To);
                num_Elements--;
                return item;
            }
        }

        return null;
    }

    //internal method to shift the database if an item is removed from a position other than the last 
    private void shift(int index)
    {
        for(int i = index; i <= data.length - 1; i++)
        {
            if(i == data.length-1)
            {
                data[i] = null; 
            }
            else
                data[i] = data[i + 1];
        }
    }
    
    //finds and retuns an item from the DB array. If not found, returns null
    public T findItem(T item)
    {
        if ( !this.isEmpty() )
        {
            for(int i = 0; i < data.length ; i++ )
            {
                if(item.equals(data[i]))
                {
                    return item;
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
        for(T x: data )
        {
            if(x == null)
                continue;
            else
                return false;   
        }

        return true;
    }

    //returns the number of items in the database
    public int size()
    {
        int items = 0;

        for(T x: data)
        {
            if(x == null)
            {   
                return items;
            }
            items++;
        }

        return items;
    }
    
    //changes all data in the array to null
    public void clear()
    {
        for(int i = 0; i < data.length; i   ++)
        {
            data[i] = null;
        }
        
    }

    //a toString method to display a string representation of the DataBase
    public String toString()
    {
        int items = 0;

        String theDB = "";

        theDB += "Here is the DB:\n";

        for(T x: data)
        {
            if(x == null)
            {   
                return theDB;
            }
            theDB += (x + " ");
            items++;
        }
        return theDB;
    }

    //This method is part of the Reverser interface and reverses the order of the data in the database
    public void reverse()
    {
        
        T temp = null;
        int j = 0;
        
        for(int i = this.size() - 1; i > j; i--)
        {           
                temp = data[j];
                data[j] = data[i];
                data[i] = temp;
                j++;
 
        }
    }
    
    //This method trims null items from the DB and sorts the data according to comparable
    public void sort()
    {
        data = Arrays.copyOf(data, this.size());
        Arrays.sort(data);
    }
    
    //writes the db to a file with the specified file name
    public boolean saveToFile(String fileName)
    { 
        try
       {
          FileOutputStream outStream = new FileOutputStream(fileName);
          ObjectOutputStream objectOutput = new ObjectOutputStream(outStream);
   
          for(T x: data)
       {
             objectOutput.writeObject(x);
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
            int counter = 0;
            
            while(true)
            {
                data[counter] = (T) objectInput.readObject();
                counter++;
                num_Elements++;
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
    }
}   

