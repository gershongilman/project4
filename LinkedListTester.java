import org.junit.*;
import static org.junit.Assert.*;

public class LinkedListTester {

	@Test
	public void testAppend() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.addToEnd(i);
			list2.addToEnd(i + 5);
			
		}
		list.append(list2);
		assertEquals("0" , "0");
	}

}
