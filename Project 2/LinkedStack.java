//CS445      Assignment 2    Ramirez T,TH 2:30-3:45

//This is the linked stack in order to simulate a stack of boxes. implements the stack interface

public class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode; // references the first node in the chain
    
    public LinkedStack()
    {
        topNode = null;
    } 
	
	public void push(T newEntry)
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	} 

	public T peek()
	{
		T top = null;
		
		if (topNode != null)
			top = topNode.getData();
		
		return top;
	} 

	public T pop()
	{
	   T top = peek();
	  
	   if (topNode != null)
	      topNode = topNode.getNextNode(); 

	   return top;
	} 

	public boolean isEmpty()
	{
		return topNode == null;
	} 
	
	public void clear()
	{
		topNode = null;	
	} 
	
	public String toString()
	{
	   String stack = "|||||TOP||||||||\n";
	   Node temp = topNode;
	   
	   while(temp.getNextNode() != null)
	   {
	       stack += temp.getData() + "\n";
	       temp = temp.getNextNode();
	   }
	   
	   stack += "" + temp.getData() + "\n|||||BOTTOM|||||\n";

	   return stack;
	}
	
	

	private class Node
	{
      private T data; // entry in stack
      private Node next; // link to next node

      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;	
      }

      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;	
      }

      private T getData()
      {
         return data;
      }

      private void setData(T newData)
      {
         data = newData;
      }

      private Node getNextNode()
      {
         return next;
      } 

      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      }
	} 
} 
