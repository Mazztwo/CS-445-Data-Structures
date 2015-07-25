	
// CS 445 Ramirez T,TH 230-345
// Assignment 4
// ReallyLongInt class. Extenrs the LinkedListPlus and adds more methods. Also uses comparable

import java.util.*;

public class ReallyLongInt extends LinkedListPlus<Integer> implements Comparable<ReallyLongInt> 
{

	// Default constructor
	public ReallyLongInt()
	{
		super();
	}

	// Note that we are adding the digits here in the FRONT. This is more efficient
	// (no traversal is necessary) and results in the LEAST significant digit first
	// in the list.
	public ReallyLongInt(String s)
	{
		super();
		char c;
		int digit;
		// Iterate through the String, getting each character and converting it into
		// an int.  Then make an Integer and add at the front of the list.
		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);
			if (('0' <= c) && (c <= '9'))
			{
				digit = c - '0';
				this.add(1, new Integer(digit));
			}
		}
	}
	// Simple call to super to copy the nodes from the argument ReallyLongInt into a
	// new one.
	public ReallyLongInt(ReallyLongInt rightOp)
	{
		super(rightOp);
	}
		
	// Method to put digits of number into a String.  Since the numbers are
	// stored "backward" (least significant digit first) we first reverse the
	// number, then traverse it to add the digits to a StringBuilder, then
	// reverse it again.  This seems like a lot of work, but accessing the list
	// from back to front using repeated calls to the getEntry() method is actually
	// much more work -- we will discuss this in lecture after break.
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if (numberOfEntries > 0)
		{
			this.reverse();
			for (Node curr = firstNode; curr != null; curr = curr.next)
			{
				sb.append(curr.data);
			}
			this.reverse();
		}
		return sb.toString();
	}

	// Returns a NEW ReallyLongInt that is the sum of the current ReallyLongInt and the parameter ReallyLongInt, without altering the original values
	public ReallyLongInt add(ReallyLongInt rightOp)
	{
		int carry = 0;							// Holds the carry
		StringBuilder sum = new StringBuilder();						// Holds the sum that will be turned into a ReallyLongInt via the constructor at the end
		int singleDigitSum = 0;					// Holds the sum of the single digits that the add is currently on
		
		singleDigitSum = firstNode.getData() + rightOp.firstNode.getData();
		
		if(singleDigitSum < 10) 
		{
			sum.append(Integer.toString(singleDigitSum));
		}
		else
		{
			carry = ((singleDigitSum) / (10));
			sum.append(Integer.toString(singleDigitSum - 10));
		}
		
		Node next = firstNode.getNextNode();
		Node nextRightOp = rightOp.firstNode.getNextNode();
		
		while(next != null || nextRightOp != null)
		{
			if(next == null)
			{
				singleDigitSum = carry + nextRightOp.getData();
				
				if(singleDigitSum < 10)
				{
					sum.insert(0, Integer.toString(singleDigitSum));
					nextRightOp = nextRightOp.getNextNode();
				}
				else
				{
					carry = ((singleDigitSum) / (10));
					
					while(singleDigitSum >= 10)
					{
						singleDigitSum -= 10;
					}
					
					sum.insert(0, Integer.toString(singleDigitSum));
					nextRightOp = nextRightOp.getNextNode();
				}
			}
			else if (nextRightOp == null )
			{
				singleDigitSum = carry + next.getData();
				
				if(singleDigitSum < 10)
				{
					sum.insert(0, Integer.toString(singleDigitSum));
					next = next.getNextNode();
				}
				else
				{
					carry = ((singleDigitSum) / (10));
					
					while(singleDigitSum >= 10)
					{
						singleDigitSum -= 10;
					}
					
					sum.insert(0, Integer.toString(singleDigitSum));
					next = next.getNextNode();
				}
			}
			else
			{
				singleDigitSum = carry + next.getData() + nextRightOp.getData();	

				if(singleDigitSum < 10)
				{
					sum.insert(0, Integer.toString(singleDigitSum));
					next = next.getNextNode();
					nextRightOp = nextRightOp.getNextNode();
				}
				else
				{
					carry = ((singleDigitSum) / (10));
					
					while(singleDigitSum >= 10)
					{
						singleDigitSum -= 10;
					}
					
					sum.insert(0, Integer.toString(singleDigitSum));
					next = next.getNextNode();
					nextRightOp = nextRightOp.getNextNode();
				}
			}	
		}
		sum.insert(0, carry);
		return new ReallyLongInt(sum.toString());	
	}
	
	// Multiply the current ReallyLongInt by 10^num	
	public void multTenToThe(int num)
	{
		if(num == 0)
		{
			// do nothing
		}
		else
		{
			while(num != 0)
			{
				
				Node temp = new Node(new Integer(0));
				temp.setNextNode(firstNode);
				firstNode = temp;
				numberOfEntries++;
				num--;
			}		
		}
	}

	// Divide the current ReallyLongInt by 10^num
	public void divTenToThe(int num)
	{	
		if(num >= numberOfEntries)
		{
			firstNode = new Node(new Integer(0));
			numberOfEntries = 0;
		}
		else
		{
			leftShift(num);

		}
	}
	
	// this method checks to see if two ReallyLongInts are equal
	public boolean equals(Object rightOp)
		{
			ReallyLongInt rightSide = (ReallyLongInt)rightOp;
			
			int comparison = this.compareTo(rightSide);
			
			if(comparison == 0)
				return true;
			else
				return false;
				
		}
	
	// implemented to satisfy the comparable interface
	public int compareTo(ReallyLongInt rightOp) 
	{
		if(numberOfEntries > rightOp.numberOfEntries)
		{
			return 1;
		}
		else if ( numberOfEntries < rightOp.numberOfEntries)
		{
			return -1;
		}
		else
		{
			for(int i = numberOfEntries; i > 0; i--)
			{
				int comparison = getEntry(i).compareTo(rightOp.getEntry(i));
				
				if(comparison == 1)
				{
					return 1;
				}
				else if(comparison == -1)
				{
					return -1;
				}
			}
			
			return 0;
		}
		
	}

}
