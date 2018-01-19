
//LinkedListPlus class. It extends A4LList and adds extra functionality with a few other methods


public class LinkedListPlus<T> extends A4LList<T> 
{
	// A no arg constructor that just makes an empty list
	public LinkedListPlus() 
	{
		super.clear();
	
	}
	
	// Generates a new list that is a copy of the argument list (copying all of the nodes inside the old list)
	public LinkedListPlus(LinkedListPlus<T> oldList)
	{
			Node firstInOldList = oldList.firstNode;										// grabs first node in oldList
			
			firstNode = new Node(firstInOldList.getData())	;	   							// copies first node of old list to new first node
			numberOfEntries++;
			Node oldListNext = firstInOldList.getNextNode();								// grabs the second node in oldList		
			Node newListNext = new Node(oldListNext.getData()); 			 				// grabs second node in oldList, which is equal to oldListNext
			firstNode.setNextNode(newListNext);												// sets second node to node after first
			oldListNext = oldListNext.getNextNode();										// grabs a third node in old list
			numberOfEntries++;
		
			// This while loop will set the a new node in the newList equal to the oldListNext
			// as long as the oldListNext node is not equal to null
			while(oldListNext != null)
			{												
				newListNext.setNextNode(new Node(oldListNext.getData())); 					// the next node to the next old node
				newListNext = newListNext.getNextNode();									// shift to next node in new list
				oldListNext = oldListNext.getNextNode();									// shift to next node in old list
				numberOfEntries++;

			}
			
			
	}

	// This method will shift the list by num places to the left	
	public void leftShift(int num)
	{
		if(num >= numberOfEntries )
		{
			super.clear();
		}
		else if(num <= 0)
		{
			// Do nothing
		}
		else
		{
			
			for(int i = 0; i < num; i++)
			{
				firstNode = firstNode.getNextNode();
				numberOfEntries--;
			}
			
		}
	}
	
	// This method will shift the list by num places to the right
	public void rightShift(int num)
	{
		if(num >= numberOfEntries )
		{
			super.clear();
		}
		else if(num <= 0)
		{
			// Do nothing
		}
		else
		{	
			
			Node newTailNode = firstNode;
			
			// this for makes the new tail node = to entry at numEntries - num
			for(int i = 1; i <= (numberOfEntries - num) - 1; i ++)
			{
				newTailNode = newTailNode.getNextNode();
			}
			
			newTailNode.setNextNode(null);   //set the node after the new tail equal to null
			numberOfEntries -= num;
			
		}
	}
	
	// This method will still shift the contents of the list num places to the left, 
	// but rather than removing nodes from the list, it will change their 
	// ordering in a cyclic way
	public void leftRotate(int num)
	{
		if(num == 0)
		{
			// Do nothing
		}
		else if( num > 0 && num < numberOfEntries)
		{
			Node lastNode = firstNode;
			
			// this for loop will grab the last node in the list
			for(int i = 1; i <= numberOfEntries - 1  ; i++)
			{
				lastNode = lastNode.getNextNode();
			}
			
			lastNode.setNextNode(firstNode);		// This sets the last node to point to the first node, momentarily making the list circular
			
			Node newEnd = firstNode;
			
			// This for loop will grab the node at position num. This node will be made the new end of the list
			for(int i = 0; i < num - 1; i++ )
			{
				newEnd = newEnd.getNextNode();
			}
			
			firstNode = newEnd.getNextNode();		// Set new head to be the num + 1 node
			newEnd.setNextNode(null);				// Set the new end's next node to be null
			
		}
		else if(num > numberOfEntries)
		{
			while(num > numberOfEntries)
			{
				num -= numberOfEntries;
			}
			
			leftRotate(num);
		}
		else if(num < 0)
		{
			num = num * (-1);
			rightRotate(num);
			
		}
	}
	
	// This method will still shift the contents of the list num places to the right, 
	// but rather than removing nodes from the list, it will change their 
	// ordering in a cyclic way
	public void rightRotate(int num)
	{
		if(num == 0)
		{
			// Do nothing
		}
		else if( num > 0 && num < numberOfEntries)
		{
			Node lastNode = firstNode;
			
			// this for loop will grab the last node in the list
			for(int i = 1; i <= numberOfEntries - 1  ; i++)
			{
				lastNode = lastNode.getNextNode();
			}
			
			lastNode.setNextNode(firstNode);		// This sets the last node to point to the first node, momentarily making the list circular
			
			Node newEnd = firstNode;
			
			// This for loop will grab the node at position numberOfEntries - num - 1. This node will be made the new end
			for(int i = 0; i < (numberOfEntries - num) - 1 ; i++ )
			{
				newEnd = newEnd.getNextNode();
			}
			
			firstNode = newEnd.getNextNode();		// Set new head 
			newEnd.setNextNode(null);				// Set the new end's next node to be null
			
			
		}
		else if(num > numberOfEntries)
		{
			while(num > numberOfEntries)
			{
				num -= numberOfEntries;
			}
			
			rightRotate(num);
		}
		else if(num < 0)
		{
			num = num * (-1);
			leftRotate(num);
			
		}
	}
	
	
	// This method will reverse the nodes in the list
	public void reverse()
	{
		LinkedListPlus<T> temp = new LinkedListPlus<T>();					// a temporary list to hold the reversed list
		
		temp.firstNode = new Node(firstNode.getData());						// copy firstNode data to temp.firstNode
	
		if(firstNode.getNextNode() != null)
		{
		
			Node nextOldList = firstNode.getNextNode();							// get the next node in the old list
			Node nextNewList = new Node(nextOldList.getData());					// make a new node with the old list data in it
		
		
			// this while loop will take the data in the old new list, and add it to the front of the temp list, thus reversing the list
			while(nextOldList != null)
			{
				nextNewList.setNextNode(temp.firstNode);
				temp.firstNode = nextNewList;
				nextOldList = nextOldList.getNextNode();
				if(nextOldList != null)
					nextNewList = new Node(nextOldList.getData());
		
			}
		
			firstNode = temp.firstNode;										// now make the temp list the new list by making firstNode point to temp.firstNode
		}
		else
			firstNode = temp.firstNode;
			
	}
	
	// Returns a string representation of the list associated with the object
	public String toString()
	{
		String stringList = "";
		Node currNode = firstNode;
		
		while(currNode != null)
		{
			stringList += currNode.getData() + " ";
			currNode = currNode.getNextNode();
		}
				
		return stringList;
	}
	
}
