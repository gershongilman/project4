import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordCountTest {

//	@Test
//	public void testWordCount() {
//		fail("Not yet implemented");
//	}

	/**
	 * Testing that the getter for count works correctly
	 */
	@Test
	public void testGetCount() {
		WordCount test = new WordCount("hello", 1);
		assertEquals(1, test.getCount());
		WordCount test1 = new WordCount("hello", 12131);
		assertEquals(12131, test1.getCount());
	}

	/**
	 * Testing that we can change the count
	 */
	@Test
	public void testSetCount() {
		WordCount test = new WordCount("hello", 1);
		test.setCount(3);
		assertEquals(3, test.getCount());
		test.setCount(100);
		assertEquals(100, test.getCount());
	}

	/**
	 * Testing the ability to retrieve a word that is inputed
	 */
	@Test
	public void testGetWord() {
		WordCount test = new WordCount("hello", 1);
		assertEquals("hello", test.getWord());

		WordCount test1 = new WordCount("hello what", 2);
		assertEquals("hello what", test1.getWord());

	}

	/**
	 * testing that we can change the word
	 */
	@Test
	public void testSetWord() {
		WordCount test = new WordCount("hello", 1);
		test.setWord("what");
		assertEquals("what", test.getWord());
	}

}
