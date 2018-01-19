// Spring 2014


// SimpleDB<T> interface


// Since SimpleDB<T> is an interface, there is no implicit requirement for
// the physical storage of the actual data.  However, for Assignment 1 you
// are required to use a Java array as your underlying data in MyDB<T>
// class.  You MAY NOT use ArrayList, Vector or any predefined collection
// class for your MyDB<T> data.  However, you may want to use some
// additional instance variables to keep track of the data effectively.

public interface SimpleDB<T>
{
	// Add a new item to the SimpleDB in the next available location.  If
	// all goes well, return true.
	
	public boolean addItem(T item);
	
	// Find, remove and return the item matching the argument in the SimpleDB.  Use the
	// equals() method as defined in type T for comparisons.  If the item is not
	// found, return null.  If more than one instance of the item exists, only delete
	// one occurrence.
	public T removeItem(T item);
	
	// Find and return the item matching the argument in the SimpleDB.  Use the equals()
	// method as defined in type T for the comparisons.  If the item is not found, return
	// null.  If more than one instance of the item exists, return the first one that
	// is found.
	public T findItem(T item);
	
	// Return true if the SimpleDB is full, and false otherwise.  Since we are
	// resizing our SimpleDB when necessary, this method should always return true.
	public boolean isFull();
	
	// Return true if the SimpleDB is empty, and false otherwise.
	public boolean isEmpty();
	
	// Return the number of items currently in the SimpleDB.
	public int size();

	// Reset the SimpleDB to empty status by reinitializing the variables
	// appropriately.
	public void clear();
}
