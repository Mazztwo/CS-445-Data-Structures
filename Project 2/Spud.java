//CS445      Assignment 2    Ramirez T,TH 2:30-3:45
//This class represents a single box of potatoes as a Spud objects. It contains three data
//fields: supplier, expirationDate, and cost. 

public class Spud implements Comparable<Spud>
{
   private String supplier;         //holds the supplier company of the Spud box    
   private int expirationDate;      //holds the expiration date for the spud, EX: day 0, day 3,...     
   private double cost;             //holds the cost of the box of spuds, EX: 3.0, 16.5, 2.5
   
    //No arg constructor for Spud
    public Spud()
    {
        supplier = "No suppier specified";
        expirationDate = 0;
        cost = 0.0;   
    }
    
    //Constructor for Spud with the supplier, expiration date, and cost
    public Spud(String supplier, int expirationDate, double cost)
    {
        this.supplier = supplier;
        this.expirationDate = expirationDate;
        this.cost = cost;
    }
    
    //This sets the supplier field of the Spud object
    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }
    
    //This sets the expiration date for the Spud object
    public void setExpirationDate(int expirationDate)
    {
        this.expirationDate = expirationDate;
    }
    
    //This sets the cost for the Spud object
    public void setCost(double cost)
    {
        this.cost = cost;
    }
    
    //This retrieves the supplier of the Spud object
    public String getSupplier()
    {
        return this.supplier;
    }
    
    //This retrieves the expiration date for the Spud object
    public int getExpirationDate()
    {
        return this.expirationDate;
    
    }
    
    //This retrieves the cost of the Spud object
    public double getCost()
    {
        return this.cost;
    }
    
    //this is the method to implement the comparable interface. It compares the expiration dates of the Spud objects
    public int compareTo(Spud spud)
    {
        int otherExpirationDate = spud.getExpirationDate();
       
        if(otherExpirationDate == this.expirationDate)
            return 0;
        else if(otherExpirationDate > this.expirationDate)
            return 1;
            
        return -1;
    }
    
   public String toString()
   {
       String theBox = "BOX: Company: " + this.getSupplier() + ", Expiration Date: Day " + this.getExpirationDate() + ", Cost: " + this.getCost() ;
       

       return theBox;
       
   }
}
