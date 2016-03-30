public class NodePool<T> extends ObjectPool<Node<T>> { // The data element of a Node has type T
  // Constructor invokes the constructor of the parent class.
  // Throws IllegalArgumentException if maxSize < 1
  NodePool(int maxSize) throws IllegalArgumentException {
    super(maxSize);

    if (maxSize < 1) {
      throw new IllegalArgumentException();
    } 
  }

  // Default constructor
  // Calls NodePool(int maxSize) constructor
  NodePool() throws IllegalArgumentException {
    this(DEFAULTMAXSIZE);
  }
 
  // Returns a newly constructed Node with data type T. 
  // Called when an object is requested from an empty pool.
  // Target Complexity: O(1)
  protected Node<T> create() {
	  Node<T> node = new Node<T>();
	  return node;
  }
 
  // Resets values of x to their initial values. 
  // Called when an empty Node is released back to the pool.
  // Empty nodes are released to the NodePool by methods clear() 
  // and compress() in class ArrayLinkedList, as described below.
  // Target Complexity: O(1)
  protected Node<T> reset(Node<T> x) {
	  // resets node in Node class method
	  x.reset();
    return x;
  }
}