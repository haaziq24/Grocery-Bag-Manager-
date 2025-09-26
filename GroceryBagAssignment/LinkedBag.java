
/**
 * LinkedBag.java - Link-based implementation of the Bag ADT
 */
public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;     
    private int numEntries;

    private class Node
    {
        private T data;
        private Node next;

        private Node(T dataPortion)
        {
            data = dataPortion;
            next = null;
        }
    }

    //constructor
    public LinkedBag()
    {
        firstNode = null;
        numEntries = 0;
    }

    //interface methods

    //Gets the current number of entries in this bag
    // @return The integer number of entries currently in the bag
    public int getCurrentSize()
    {
        return numEntries;
    }

    //Sees whether the bag is empty
    // @return TRUE if the bag is empty, FALSE otherwise
    public boolean isEmpty()
    {
        return (numEntries == 0);
    }

    //Adds a new entry to this bag
    // @param newEntry - the object to be added as a new entry
    // @return TRUE if add was successful, FALSE otherwise
    public boolean add(T newEntry)
    {
        //create a new node
        Node newNode = new Node(newEntry);

        //attach new node to the first node
        newNode.next = firstNode;

        //reassign first node to be the new node
        firstNode = newNode;

        numEntries++;
        return true;
    }

    //Retrieves all entries that are in this bag
    // @return A newly allocated array of all the entries in this bag
    //  NOTE:  If the bag is empty, the returned array is empty
    public T[] toArray()
    {
        //allocate new result array
        T[] result = (T[]) new Object[numEntries];
        int index = 0;
        Node curr = firstNode;      //start at first node

        //traverse our list, copying current data into array
        while (curr != null)
        {
            //copy over
            result[index] = curr.data;
            //hop to next node
            curr = curr.next;
            index++;
        }

        return result;
    }

    //Remove (general, unspecified version) an item
    //Removes one unspecified entry from this bag, if possible
    // @return either the removed entry (if successful), or NULL
    public T remove()
    {
        T result = null;

        if (!isEmpty())
        {
            result = firstNode.data;
            firstNode = firstNode.next;     //cut out 1st node 
            numEntries--;
        }

        return result;
    }

    //getNodeContaining(T anEntry) - returns a Node object containing given entry
    //  @param anEntry - the entry to be found
    //  @return a Node reference where anEntry is found or NULL, if not found
    private Node getNodeContaining(T anEntry)
    {
        Node curr = firstNode;      //start at first node

        //traverse our list, copying current data into array
        while (curr != null)
        {
            //check if current data equals anEntry
            if (curr.data.equals(anEntry))
                return curr;        //found it, so return the current node
            //hop to next node
            curr = curr.next;
        }

        return null;
    }

    //Remove (specific version)
    //Removes one occurrence of a given entry from the bag, if possible
    // @param anEntry - the entry to be removed
    // @return TRUE if successful, FALSE otherwise
    public boolean remove(T anEntry)
    {
        Node p = getNodeContaining(anEntry);

        if (p == null)          //anEntry was not found, so just return false
            return false;

        //copy firstNode's data into p
        p.data = firstNode.data;

        //remove the first node
        remove();

        return true;
    }

    //Removes all entries from this bag
    public void clear()
    {
        firstNode = null;
        numEntries = 0;

    }

    //Counts the number of times a given entry appears in this bag
    // @param anEntry - the entry to be counted
    // @return the number of times anEntry appears in the bag
    public int getFrequencyOf(T anEntry)
    {
        Node curr = firstNode;      //start at first node
        int result = 0;

        //traverse our list, copying current data into array
        while (curr != null)
        {
            //check if current data equals anEntry
            if (curr.data.equals(anEntry))
                result++;        //found it, increment the counter
            //hop to next node
            curr = curr.next;
        }

        return result;
    }

    //Tests whether this bag contains a given entry
    // @param anEntry - the entry to find
    // @return TRUE if the bag contains anEntry, FALSE otherwise
    public boolean contains(T anEntry)
    {
        Node p = getNodeContaining(anEntry);
        
        if (p == null)
            return false;
        else
            return true;
    }

}
