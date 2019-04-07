/**
 * @Gershon Gilman
 * A class to represent a linked list of nodes.
 */
public class LinkedList<T> {
  /** the first node of the list, or null if the list is empty */
  private LLNode<T> frontNode;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    frontNode = null;
  }
  
  /**
   * Returns the first node.
   */
  protected LLNode<T> getFront() {
    return frontNode;
  }

  /**
   * Changes the front node.
   * @param node  the node that will be the first node of the new linked list
   */
  protected void setFront(LLNode<T> node) {
    this.frontNode = node;
  }

  /**
   * Add an element to the front of the linked list
   */
  public void addToFront(T element) {
    setFront(new LLNode<T>(element, getFront()));
  }
  
  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFront() == null);
  }
  
  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int lengthSoFar = 0;
    LLNode<T> nodeptr = getFront();
    while (nodeptr != null) {
      lengthSoFar++;
      nodeptr = nodeptr.getNext();
    }
    return lengthSoFar;
  }
  
  /**
   * Returns a String representation of the list
   * @return a String representing the list
   */
  public String toString() {
    return null;
  }

  // returns the last node of the list 
  private LLNode<T> getLast() {
    for (LLNode<T> last = getFront(); last != null && last.getNext() != null; last = last.getNext()) {
    }
    return last;
  }
	
  /**
   * combining two linked lists
   * 
   */
  public void append(LinkedList<T> input) {
    LLNode>T> last = getLast();
    if (last == null) {
      setFront(input.getFront());
    }
    else {
      last.setNext(input.getFront());
    }
    // make input list empty
    input.setFront(null);
  }
 
}
