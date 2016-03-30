public class Node<T> {
  protected static final int NULL = -1;
  protected int previous;
  protected int next;
  protected T data;
 
  // Constructor initializes data members. See method init().
  protected Node() {
	  init();
  }
  
 
  // Create a pretty representation of the pool
  // Format: [previous,next,data]
  // Target Complexity: O(n)
  public String toString() {
	  return ("[" + previous + "," + next + "," + data + "]");
  }
 

  // Initializes the data members. 
  // Called by the constructor and also by method reset() 
  // in class NodePool.
  // Target Complexity: O(1)
  protected void init() {
	  previous = -1;
    next = -1;
	  data = null;
  }

  public void reset() {
    init();
  }
  
  public int getPreviousIndex() {
	  return this.previous;
  }
  
  public int getNextIndex() {
	  return this.next;
  }
  
  public T getData() {
	  return this.data;
  }
  
  public void setPreviousIndex(int previous) {
	  this.previous = previous;
  }
  
  public void setNextIndex(int next) {
	  this.next = next;
  }
  
  public void setData(T data) {
	  this.data = data;
  }  
  
} 