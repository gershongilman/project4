/**
 * @author Gershon Gilman 
 * Working through a textfile and counting the words
 */
public class WordCount {

	private int count = 1;

	private String word = "1";

	/**
	 * the Constructor
	 * 
	 * @param input the string that we will count
	 * @param count the number of words
	 */
	public WordCount(String word) {
		this.count = 1;
		this.word = word;
	}

	/**
	 * returns the word count
	 * @return the word count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * set the count of the word/ change the count
	 * @param counter the change of the words
	 */
	public void setCount(int counter) {
		count = counter;
	}

	/**
	 * returns the word based on count
	 * @return
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * sets the word to the user input
	 * @param input the string that is inputted
	 */
	public void setWord(String input) {
		this.word = input;
	}
	
	@Override
	public String toString() {
		return "(" + getWord() + "," + getCount() + ")";
	}
}
