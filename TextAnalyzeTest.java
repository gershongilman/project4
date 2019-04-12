import java.util.Iterator;

import org.junit.Test;

public class TextAnalyzeTest {

	@Test
	public void testRadixSort() {
		LinkedList<WordCount> list = new LinkedList<WordCount>();
		list.addToFront(new WordCount("hello"));
		list.addToFront(new WordCount("how"));
		list.addToFront(new WordCount("are"));
		list.addToFront(new WordCount("you"));
		list.addToFront(new WordCount("paco"));

		TextAnalyze.radixSort(list);
		Iterator<WordCount> it = list.iterator();
		assertEquals("are", list.getFirst().getElement().toString());
	}

}
