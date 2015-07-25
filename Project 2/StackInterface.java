//CS445      Assignment 2    Ramirez T,TH 2:30-3:45
//This is the stack interface to implement the linked stack


public interface StackInterface<T>
{
    //Adds a new entry to the top of the stack.
    public void push(T newEntry);
    
    //removes and returns the top entry of the stack
    public T pop();
    
    //retrieves the top entry of the stack
    public T peek();
    
    //checks to see whether the stack is empty
    public boolean isEmpty();
    
    //clears the stack of any entries
    public void clear();
    
}
