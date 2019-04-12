import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class to represent a linked list of nodes.
 */
public class LinkedList<T> implements Iterable<T> {
	/** the first node of the list, or null if the list is empty */
	private LLNode<T> firstNode;

	/**
	 * Creates an initially empty linked list
	 */
	public LinkedList() {
		firstNode = null;
	}

	/**
	 * Returns the first node.
	 */
	protected LLNode<T> getFirst() {
		return firstNode;
	}

	/**
	 * Changes the first node.
	 * 
	 * @param node the first node of the new linked list
	 */
	protected void setFirst(LLNode<T> node) {
		this.firstNode = node;
	}

	/**
	 * Add an element to the front of the linked list
	 * 
	 * @param element the element to add
	 */
	public void addToFront(T element) {
		setFirst(new LLNode<T>(element, getFirst()));
	}

	/**
	 * Return whether the list is empty
	 * 
	 * @return true if the list is empty
	 */
	public boolean isEmpty() {
		return (getFirst() == null);
	}

	/**
	 * Returns the length of the linked list
	 * 
	 * @return the number of nodes in the list
	 */
	public int length() {
		int count = 0; // counts number of nodes seen
		LLNode<T> nodeptr = getFirst();
		while (nodeptr != null) {
			count++;
			nodeptr = nodeptr.getNext();
		}
		return count;
	}

	/**
	 * Remove and return the element at the front of the list
	 * 
	 * @return the first element of the list
	 * @throws NoSuchElementException if there is no such element
	 */
	public T removeFromFront() {
		if (isEmpty())
			throw new NoSuchElementException();
		else {
			T save = getFirst().getElement();
			setFirst(getFirst().getNext());
			return save;
		}
	}

	/**
	 * Add an element to the very end of the list
	 * 
	 * @param element the element to add to the end of the list
	 */
	public void addToEnd(T element) {
		if (isEmpty())
			addToFront(element);
		else {
			getLast().setNext(new LLNode<T>(element, null));
		}
	}

	/**
	 * Print the contents of the list to the console.
	 * 
	 * @param list the list to print
	 */
	public static <T> void printList1(LinkedList<T> list) {
		LLNode<T> nodeptr = list.getFirst();
		while (nodeptr != null) {
			System.out.print(nodeptr.getElement().toString() + " ");
			nodeptr = nodeptr.getNext();
		}
		System.out.println();
	}

	/**
	 * Print the contents of the list to the console.
	 * 
	 * @param list the list to print
	 */
	public static <T> void printList2(LinkedList<T> list) {
		// this loop is using the iterator instead of the nodes directly
		Iterator<T> it = list.iterator();
		while (it.hasNext())
			System.out.print(it.next().toString() + " ");
		System.out.println();
	}

	/**
	 * Returns an iterator that will run through the linked list contents in order
	 * 
	 * @return the iterator for this linked list
	 */
	public Iterator<T> iterator() {
		return new LinkedListIterator<T>(this.getFirst());
	}

	public LLNode<T> getLast() {
		LLNode<T> last = getFirst();
		for (; last != null && last.getNext() != null; last = last.getNext()) {
		}
		return last;
	}

	/**
	 * combining two linked lists
	 * 
	 * @param input a Linked List that we want to connect to another list
	 */
	public void append(LinkedList<T> input) {
		LLNode<T> last = getLast();
		if (last == null) {
			setFirst(input.getFirst());
		} else {
			last.setNext(input.getFirst());
		}
		// make input list empty
		input.setFirst(null);
	}

	/**
	 * 
	 */
	public void reverse() {

		LLNode<T> reversed = null; // nothing is reversed yet!
		LLNode<T> list_to_do = getFirst(); // still need to reverse everything :-)

		while (list_to_do != null) {
			LLNode<T> temp = list_to_do;
			list_to_do = list_to_do.getNext();

			temp.setNext(reversed);
			reversed = temp;
			setFirst(reversed);
		}
	}

	public static void main(String args[]) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.addToEnd(i);
			list2.addToEnd(i + 5);
		}
//		printList2(list);
//		printList2(list2);
//		list.append(list2);
//		printList2(list);
//		printList2(list2);
		list2.reverse();
		printList2(list2);
	}
}
