import java.util.*;
import java.lang.*;

class ArrayLinkedList<T> {
    protected final static int NULL = -1;      
    protected ArrayList<Node<T>> array;
    protected NodePool<T> pool;
    protected int head; // position of dummy node in array
    protected int tail; // position of tail node in array
    protected int firstEmpty; // head of the list of empty nodes
    protected int numberEmpty; // number of empty nodes
    protected int size; // number of non-empty nodes
    protected int modCount; // number of modifications made
    protected final static int NODEPOOLSIZE = 8;
 
    // Constructor to initialize the data members, increment modCount,
    // create the dummy header Node, and add it to array
    public ArrayLinkedList() {
      array = new ArrayList<Node<T>>();
      pool = new NodePool<T>();
      head = 0;
      tail = 0;
      firstEmpty = -1;
      numberEmpty = 0;
      size = 0;
      modCount = 1;
      Node<T> dummy = new Node<T>();
      array.add(dummy);

    }
 
    // Return number of non-empty nodes
    // Target Complexity: O(1)
    public int size() {
      return this.size;
    }
 
    // convenience methods for debugging and testing
    //return firstEmpty
    protected int getFirstEmpty() {
      return this.firstEmpty;
    }
    // return head
    protected int getHead() {
      return this.head;
    }
    //return tail
    protected int getTail() {
      return this.tail;
    }
    //return array
    protected ArrayList<Node<T>> getArray() {
      return array;
    }


    //Helper method for adding new node to the list
    private Node<T> getNewNode() {
      
      Node<T> freeNode;

      if (numberEmpty == 0) {
          
          freeNode = pool.allocate();
          //add node to list
          array.add(freeNode);
        } else {
          freeNode = array.get(firstEmpty);
          //used an empty node
          numberEmpty--;
          //sets the next node in the list of firstEmpty.
          firstEmpty = freeNode.getNextIndex();
          //Makes the node the last element in the list
          freeNode.setNextIndex(NULL);
        }
      return freeNode;
    }
        
    // Appends the specified element to the end of this list. 
    // Returns true.
    // If no empty Nodes are available, then get a new Node from pool.
    // Throws IllegalArgumentException if e is null.
    // Checks assertions at the start and end of its execution.
    // Target Complexity: Amortized O(1)
    public boolean add(T e) throws IllegalArgumentException {
        assert size>=0 && head==0 && numberEmpty >=0 && (numberEmpty==0  
         && firstEmpty==NULL) || (numberEmpty>0 && firstEmpty!=NULL)
          && (array.size() == size+numberEmpty+1);         
        
        if (e == null) {
          throw new IllegalArgumentException();
        }

        //Call to helper method
        Node<T> freeNode = getNewNode();


        // Getting the node of tail and setting it's next attribute to the node at index of freeNode
        array.get(tail).setNextIndex(array.indexOf(freeNode));
        // Linking the new node to the previous node
        freeNode.setPreviousIndex(tail);
        // sets tail to the index of freeNode
        tail = array.indexOf(freeNode);
        freeNode.setData(e);

        modCount++;
        size++;
 
        // check this assertion before each return statement
        assert size>0 && head==0 && numberEmpty >=0 
          && (numberEmpty==0 && firstEmpty==NULL) || (numberEmpty>0 
            && firstEmpty!=NULL)
            && (array.size() == size+numberEmpty+1);
        return true;
    }
 
    // Inserts the specified element at the specified index in this list.  
    // Shifts the element currently at that index (if any) and any 
    // subsequent elements to the right (adds one to their indices).    
    // Throws IndexOutOfBoundsException if the index is out of range 
    // (index < 0 || index > size()).
    // Throws IllegalArgumentException if e is null.
    // Can call add(T e) if index equals size, i.e., add at the end
    // Checks assertions at the start and end of its execution.
    // Target Complexity: O(n)
    public void add(int index, T e) throws IndexOutOfBoundsException, IllegalArgumentException {
       assert size>=0 && head==0 && numberEmpty >=0 
         && (numberEmpty==0 && firstEmpty==NULL) || (numberEmpty>0 
           && firstEmpty!=NULL) && (array.size() == size+numberEmpty+1);
 
        if (index < 0 || index > size()) {
          throw new IndexOutOfBoundsException();
        }

        if (e == null) {
          throw new IllegalArgumentException();
        }

        if  (index == size) {
          add(e);
          return;
        }      


        Node<T> newNode = getNewNode();

        int i = 0;

        // getting the index of next node
        // start at node right after head
        int nextNodeIndex = indexToNode(head).getNextIndex();
        // using .get(index) to find the node at given index
        Node<T> nextNode = indexToNode(nextNodeIndex);

        // Finding the node at index
        while (i != index) {
          // nextNode = nextNode.next
          nextNodeIndex = nextNode.getNextIndex();
          nextNode = indexToNode(nextNodeIndex);
          i++;
        }

        // linking newNode's previous value and next value
        newNode.setPreviousIndex(nextNode.getPreviousIndex());
        newNode.setNextIndex(nodeToIndex(nextNode));

        //set nextnode.prev.next = newNode
        Node<T> previousNode = indexToNode(nextNode.getPreviousIndex());
        previousNode.setNextIndex(nodeToIndex(newNode));

        //set nextNode.prev = newNode
        nextNode.setPreviousIndex(nodeToIndex(newNode)); 

        // put data in node
        newNode.setData(e);

        modCount++;
        //increase non-empty node size
        size++;
 
       // check this assertion before each return statement
       assert size>0 && head==0 && numberEmpty >=0 
         && (numberEmpty==0 && firstEmpty==NULL) || (numberEmpty>0 
             && firstEmpty!=NULL) && (array.size() == 
                 size+numberEmpty+1);
       return;
    }
 
    // method to find the index of a node
    int nodeToIndex(Node<T> node) { 
      return array.indexOf(node);
    }

    // method to find the node located at the given index
    Node<T> indexToNode(int index) {
      return array.get(index);
    }

    // Equivalent to add(0,e).
    // Throws IllegalArgumentException if e is null
    // Target Complexity: O(1)
    public void addFirst(T e) throws IllegalArgumentException{
      if (e == null) {
        throw new IllegalArgumentException();
      }

      add(0,e);

    }
 
    // Equivalent to add(e).
    // Throws IllegalArgumentException if e is null
    // Target Complexity: O(1)
    public void addLast(T e) throws IllegalArgumentException {
      if (e == null) {
        throw new IllegalArgumentException();
      }

      add(e);
    }
 
    // Add all of the elements (if any) in Collection c to the end 
    // of the list, one-by-one.
    // Returns true if this list changed as a result of the call.
    // Throws NullPointerException if the specified collection is null
    // Target Complexity: O(number of elements in c)
    public boolean addAll(Collection<? extends T> c) throws NullPointerException {
      boolean didChange = false;

      if (c == null) {
        throw new NullPointerException();
      }

      for (Iterator<? extends T> it = c.iterator(); it.hasNext();) {
        addLast(it.next());
        didChange = true;
      }

      return didChange;
    }
 
    // Returns true if this list contains the specified element.
    // Throws IllegalArgumentException if e is null
    // May call indexOf(e)
    // Target Complexity: O(n)
    public boolean contains(T e) throws IllegalArgumentException {
      if (e == null) {
        throw new IllegalArgumentException();
      }

      // Use this for linked list traversal. Getting the nextNode
      int nextNodeIndex = indexToNode(head).getNextIndex();
      Node<T> nextNode = indexToNode(nextNodeIndex);
      
      if (size() == 1) {
        if (nextNode.getData() == e) {
          return true;
        } else {
          return false;
        }
      }
      
      int i = 0;
      while (i < size()) {
        if (nextNode.getData() == e) {
          return true;
        }
        nextNodeIndex = nextNode.getNextIndex();
        nextNode = indexToNode(nextNodeIndex);
        i++;
      }

      return false;
    }
    
 
    // Returns the index of the first occurrence of the 
    // specified element in this list,
    // or NULL if this list does not contain the element.
    // Throws IllegalArgumentException if e is null
    // Target Complexity: O(n)
    public int indexOf(T e) throws IllegalArgumentException {
      
      if (e == null) {
        throw new IllegalArgumentException();
      }

      // Using nextNode index for traversal of linked list
      int nextNodeIndex = indexToNode(head).getNextIndex();
      Node<T> nextNode = indexToNode(nextNodeIndex);

      // Special case is size is 1. Avoiding ArrayIndexOutOfBoundsException
      if (size() == 1) {
        if (nextNode.getData() == e) {
          return 0;
        } else {
          return NULL;
        }
      }

      // If size is greater than one, traverse through the list.
      // Will return the index if e is in the list, otherwise, will
      // return -1.  
      int i = 0;
      while (i < size()) {
        if (nextNode.getData() == e) {
          return i;
        } else {
          // Getting the nextNode for iteration
          nextNodeIndex = nextNode.getNextIndex();
          nextNode = indexToNode(nextNodeIndex);
          i++;
        }
      }

      return NULL;
    }
    
   // Returns the array position of the first occurrence of 
   // the specified element in array
   // or NULL (-1) if this list does not contain the element. 
   // Note that the return value is a position in the array, 
   // not the index of an element in the list.
   // Called by remove(T e) to find the position of e in the array.
   // Throws IllegalArgumentException if e is null
   // Target Complexity: O(n)
   protected int positionOf(T e) throws IllegalArgumentException {
    
    if (e == null) {
      throw new IllegalArgumentException();
    }
      
    if (contains(e) == false) {
      return NULL;
    }

    int i;
    for (i = 0; i < array.size(); i++) {
      Node<T> node = array.get(i);
      if (node.getData() == e) {
        //System.out.println("Found e at: " + i);
        return i;
      }
    }
    //System.out.println("In positionOf: " + i);
    return NULL;
  }

    // helper method to get the node at the current index.
    // called by get() 
    protected Node<T> getNode(int index) {
      int nextNodeIndex = indexToNode(head).getNextIndex();
      Node<T> nextNode = indexToNode(nextNodeIndex);

      if (size() == 1) {
        return nextNode;
      }

      // looping through the list to get to the node at
      // specified index
      int i = 0;
      while (i < index) {
        nextNodeIndex = nextNode.getNextIndex();
        nextNode = indexToNode(nextNodeIndex);
        i++;
      }
      return nextNode;
    }
 
    // Returns the element at the specified index in this list.
    // Throws IndexOutOfBoundsException if the index is out 
    // of range (index < 0 || index >= size())
    // Target Complexity: O(n)
    public T get(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index > size()-1) {
        throw new IndexOutOfBoundsException();
      }

      return getNode(index).getData();

      // int nextNodeIndex = indexToNode(head).getNextIndex();
      // Node<T> nextNode = indexToNode(nextNodeIndex);

      // if (size() == 1) {
      //   return nextNode.getData();
      // }

      // int i = 0;
      // while (i < index) {
      //   nextNodeIndex = nextNode.getNextIndex();
      //   nextNode = indexToNode(nextNodeIndex);
      //   i++;
      // }
      // return nextNode.getData();
    }
 
    // Returns the first element in the list.
    // Throws NoSuchElementException if the list is empty
    // Target Complexity: O(1)
    public T getFirst() throws NoSuchElementException {
      if (size() == 0) {
        throw new NoSuchElementException();
      }

      int nextNodeIndex = indexToNode(head).getNextIndex();
      Node<T> nextNode = indexToNode(nextNodeIndex);
      return nextNode.getData();
    }
 
    // Returns the last element in the list
    // Throws NoSuchElementException if the list is empty
    // Target Complexity: O(1)
    public T getLast() throws NoSuchElementException {
      
      if (size() == 0) {
        throw new NoSuchElementException();
      }

      int nextNodeIndex = indexToNode(head).getNextIndex();
      Node<T> nextNode = indexToNode(nextNodeIndex);

      if (size() == 1) {
        return getFirst();
      }

      int i = 0;
      while (i < size()-1) {
        nextNodeIndex = nextNode.getNextIndex();
        nextNode = indexToNode(nextNodeIndex);
        i++;
      }

      return nextNode.getData();
    }
 
    // Remove the node at position current in the array.
    // Note that current is a position in the array, not the 
    // index of an element in the list.
    // The removed node is made empty and added to the front 
    // of the list of empty Nodes. Dummy node cannot be removed.
    // Called by remove(T e) and remove(int index) to 
    // remove the target Node.
    // Target Complexity: O(1)
    protected void removeNode(int current) {
       
       assert current > 0 && current < array.size(); 
       //assert current > 0 && current < array.size();
       
       Node<T> node = array.get(current);
       int previousNodeIndex;

       // Updating tail to point to previous element if
       // node being removed is last in the list
       if (tail == nodeToIndex(node)) {
          previousNodeIndex = node.getPreviousIndex();
          tail = previousNodeIndex;
       }
       
       previousNodeIndex = node.getPreviousIndex();
       Node<T> previousNode = indexToNode(previousNodeIndex);
       previousNode.setNextIndex(node.getNextIndex());

       // while the node is not the last in the list. 
       // Boundary check
       if (node.getNextIndex() != -1) {
        int nextNodeIndex = node.getNextIndex();
        Node<T> nextNode = indexToNode(nextNodeIndex);
        nextNode.setPreviousIndex(previousNodeIndex);
       }
       
       
        node.setPreviousIndex(NULL);
        node.setNextIndex(firstEmpty);
        node.setData(null);

        firstEmpty = current;
        numberEmpty++;
    }
 
    // Removes the first occurrence of the specified element from 
    // this list, if it is present. Returns true if this
    // list contained the specified element.
    // Throws IllegalArgumentException if e is null.
    // Checks assertions at the start and end of its execution.
    // Target Complexity: O(n) (worst case)
    public boolean remove(T e) throws IllegalArgumentException {
 
       assert size>=0 && head==0 && numberEmpty >=0
        && (numberEmpty==0 && firstEmpty==NULL) || (numberEmpty>0 
          && firstEmpty!=NULL) && (array.size() == size+numberEmpty+1);
 
       if (e == null) {
        throw new IllegalArgumentException();
       }

       boolean didRemove = false;

       int positionE = positionOf(e);

       if (positionE != NULL) {
        removeNode(positionE);
        didRemove = true;
        size--;
       }



       // check this assertion before each return statement
       assert size>=0 && head==0 && numberEmpty >=0 
         && (numberEmpty==0 && firstEmpty==NULL) || (numberEmpty>0 
          && firstEmpty!=NULL) && (array.size() == size+numberEmpty+1);
       return didRemove;
    }
 
    // Removes the element at the specified index in this list.
    // Shifts any subsequent elements to the left (subtracts one from
    // their indices). Returns the element that was removed from the 
    // list. Throws IndexOutOfBoundsException if the index is 
    // out of range (index < 0 || index >= size)
    // Checks assertions at the start and end of its execution.
    // Target Complexity: O(n)
    public T remove(int index) throws IndexOutOfBoundsException{
      assert size>=0 && head==0 && numberEmpty >=0 
        && (numberEmpty==0 && firstEmpty==NULL) || (numberEmpty>0 
          && firstEmpty!=NULL) && (array.size() == size+numberEmpty+1);
 

      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException();
       }

       T element = get(index);
       remove(element);


 
        // check this assertion before each return statement
       assert size>=0 && head==0 && numberEmpty >=0 && (numberEmpty==0 
        && firstEmpty==NULL) || (numberEmpty>0 && firstEmpty!=NULL) 
        && (array.size() == size+numberEmpty+1);
        return element; //… ;
    }
 
    // Returns the first element in the list.
    // Throws NoSuchElementException if the list is empty.
    // Equivalent to remove(0), for index 0
    // Target Complexity: O(1)
    public T removeFirst() throws NoSuchElementException {
      
      if (size() == 0) {
        throw new NoSuchElementException();
      }

      T removedData = remove(0);

      return removedData;
    }
 
    // Returns the last element in the list
    // Throws NoSuchElementException if the list is empty
    // Equivalent to remove(size-1), for index size-1
    // Target Complexity: O(1)
    public T removeLast() throws NoSuchElementException {

      if (size() == 0) {
        throw new NoSuchElementException();
      }

      T removedData = remove(size - 1);

      return removedData;
    }
 
    // Returns true if the Node at the specified position in the 
    // array is an empty node. The dummy node is never considered to be
    // an empty node.
    // Target Complexity: O(1)
    protected boolean positionIsEmpty(int position) {
      assert position > 0 && position < array.size();
      
      Node<T> node = array.get(position);

      if (node.getData() == null && position != 0) {
        return true;
      }

      return false;
    }
 
    // Returns number of empty nodes.
    // Target Complexity: O(1)
    protected int numEmpty() {
      return numberEmpty;
    }
 
    // Replaces the element at the specified position in this 
    // list with the specified element. Returns the element 
    // previously at the specified position.    
    // Throws IllegalArgumentException if e is null.
    // Throws IndexOutOfBoundsException if index is out of 
    // range (index < 0 || index >= size)
    // Target Complexity: O(n)
    public T set(int index, T e) throws IllegalArgumentException, IndexOutOfBoundsException {

      if (e == null) {
        throw new IllegalArgumentException();
      }

      if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException();
      }

      Node<T> node = getNode(index);
      T currentData = node.getData();

      node.setData(e);

      return currentData;
    }
 
    // Removes all of the elements from this list. 
    // The list will be empty after this call returns.
    // The array will only contain the dummy head node.
    // Some data members are reinitialized and all Nodes
    // are released to the node pool. modCount is incremented.
    // Target Complexity: O(n)
    public void clear() {
       assert size>=0 && head==0 && numberEmpty >=0 
        && (numberEmpty==0 && firstEmpty==NULL) || (numberEmpty>0 
        && firstEmpty!=NULL) && (array.size() == size+numberEmpty+1);
 
          Node<T> node;
          for (int i = 1; i < array.size(); i++) {
            node = array.get(i);
            pool.release(node);
          }

          // setting the getNext() of head to -1 to indicate empty list
          Node<T> nodeHead = array.get(head);
          nodeHead.setNextIndex(NULL);

          // clearing the array but keeping the dummy node
          array.clear();
          array.add(nodeHead);
          
          // reinitializing variables
          size = 0;
          numberEmpty = 0;
          head = tail = 0;
          modCount++;
          firstEmpty = -1;
        
        
       // check this assertion before each return statement
       assert size==0 && head==0 && numberEmpty==0 && firstEmpty==NULL
       && (array.size() == size+numberEmpty+1);
    }
 
    // Returns an Iterator of the elements in this list, 
    // starting at the front of the list
    // Target Complexity: O(1)
    Iterator<T> iterator() { 
      return new ArrayLinkedListIterator();
    }
 
    // Convenience debugging method to display the internal 
    // values of the list, including ArrayList array
    protected void dump() {
      System.out.println();
      System.out.println("**** dump start ****");
      System.out.println("size:" + size);
      System.out.println("number empty:" + numberEmpty);
      System.out.println("first empty:"+firstEmpty);
      System.out.println("head:" + head);
      System.out.println("tail:" + tail); 
      System.out.println("list:");
      System.out.println("array:");
      for (int i=0; i<array.size(); i++) // show all elements of array
         System.out.println(i + ": " + array.get(i));
      System.out.println("**** dump end ****");
      System.out.println();
    }
 
    // Returns a textual representation of the list, i.e., the 
    // data values of the non-empty nodes in list order.
    public String toString() {
      String st = "";

      int previousNodeIndex;
      T data;
      //probably don't need this....
      Node<T> node = array.get(head);

      // adding to the string
      for(int i = 0; i < size; i++) {
        data = get(i);
        st += data;
      }
      return st;
    }
 
    // Compress the array by releasing all of the empty nodes to the 
    // node pool.  A new array is created in which the order of the 
    // Nodes in the new array matches the order of the elements in the 
    // list. When compress completes, there are no empty nodes. Resets 
    // tail accordingly.
    // Target Complexity: O(n)
    public void compress() {

      // New list -- size to account for dummy node
      ArrayList<Node<T>> compressArray = new ArrayList<Node<T>>();

      // Establishing an iterator for traversal
      Iterator<Node<T>> it = array.iterator();

      // checking for empty nodes in the array
      // removing them

      
      while(it.hasNext()) {
        Node<T> node = it.next();
        T nodeData = node.getData();
        if (nodeData == null) {
          pool.release(node);
          it.remove();
        } 
      }

      // setting up the head node
      int nextNodeIndex = indexToNode(head).getNextIndex();
      Node<T> nextNode = indexToNode(nextNodeIndex);

      // adding to the compressArray list 
      // Getting nextNode index to align with array position
      for (int i = 0; i < size(); i++) {
        compressArray.set(i, nextNode);
        nextNodeIndex = nextNode.getNextIndex();
        nextNode = indexToNode(nextNodeIndex);
      }

      modCount++;
    }
 
    // Iterator for ArrayLinkedList. (See the description below.)
    private class ArrayLinkedListIterator implements Iterator<T> {
      // Starting index at 1 to pass the head dummy node
      private int currentIndex = 1;

      // Constructor
      // Target Complexity: O(1)
      public ArrayLinkedListIterator() {}
 
      // Returns true if the iterator can be moved to the next() element.
      public boolean hasNext() {

        if (currentIndex == -1) {
          return false;
        }

        if (currentIndex > size()) {
          return false;
        }

        return true;
      }
 
      // Move the iterator forward and return the passed-over element
      public T next() {

        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        Node<T> node = indexToNode(currentIndex);

        currentIndex = node.getNextIndex();

        return node.getData();
      }
 
      // The following operation is part of the Iterator interface 
      // but is not supported by the iterator. 
      // Throws an UnsupportedOperationException if invoked.
      public void remove() {
        throw new UnsupportedOperationException();
      }
   }
}