import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author gershon
 *
 */
public class TextAnalyze {

	/**
	 * @param words
	 * @return
	 */
	public static LinkedList<WordCount> radixSort(LinkedList<WordCount> words) {

		// create array of 27 linked Lists
		ArrayList<LinkedList<WordCount>> buckets = new ArrayList<LinkedList<WordCount>>(27);
		for (int alpha = 0; alpha < 28; alpha++) {
			buckets.add(new LinkedList<WordCount>());
		}

		// loops through from last letter to the first letter
		for (int i = maxLength(words) - 1; i >= 0; i--) {

			// put words in buckets with respect to letter #i
			Iterator<WordCount> it = words.iterator();

			while (it.hasNext()) {
				// current word we are on using iterator
				WordCount current = it.next();

				// get the current word as a string
				String str = current.getWord().toLowerCase();

				// what bucket should current word go to?
				int bucketNum = 0;
				if (str.length() > i) {
					bucketNum = str.charAt(i) - 'a' + 1;
				}
				LinkedList<WordCount> bucket = buckets.get(bucketNum);

				// Add 'current' word to the bucket.
				// If the first word in the bucket is the same, just by increase its count
				WordCount firstWord = (bucket.getFirst() == null) ? null : bucket.getFirst().getElement(); // can be
																											// null
				if ((firstWord != null) && (str.compareTo(firstWord.getWord()) == 0)) {
					firstWord.setCount(firstWord.getCount() + 1);
				} else {
					buckets.get(bucketNum).addToFront(current);
				}

			}

			// merge all lists back into one using append
			words = new LinkedList<WordCount>();
			for (int bucketNum = 0; bucketNum < 28; bucketNum++) {
				LinkedList<WordCount> bucket = buckets.get(bucketNum);
				bucket.reverse();
				if (!bucket.isEmpty()) {
					// System.out.println(bucketNum);

					// LinkedList.printList2(bucket);
					words.append(bucket);

				}
			}
			// LinkedList.printList2(words);

		}

		return words;
	}

	/**
	 * loop over all elements to find max length
	 * 
	 * @param words
	 * @return
	 */
	public static int maxLength(LinkedList<WordCount> words) {
		Iterator<WordCount> it = words.iterator();
		// loop over all elements to find max length
		int maxLength = 0;
		while (it.hasNext()) {
			WordCount current = it.next();
			String str = current.getWord();
			if (str.length() > maxLength) {
				maxLength = str.length();
			}
		}
		return maxLength;

	}

	public static LinkedList<WordCount> loadFile(String fileName) throws IOException {
		BufferedReader br = null;

		try {
			// buffer reader
			br = new BufferedReader(new FileReader(fileName));

			// the linked list we are going to input the new values
			LinkedList<WordCount> words = new LinkedList<>();

			// string to which we will input our conversion of the code
			String line = null;
			String[] lineWords;
			while ((line = br.readLine()) != null) {

				lineWords = line.split("\\s");
				for (String word : lineWords) {
					words.addToEnd(new WordCount(word));
				}
			}
			return words;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException(" File " + fileName + "cannot be read ");

		} finally {
			br.close();
		}
	}

	public static void writeData(String fileName, LinkedList<WordCount> list) throws IOException {
		BufferedWriter words = null;
		try {
			FileWriter writer = new FileWriter(fileName + ".txt");
			words = new BufferedWriter(writer);
			Iterator<WordCount> it = list.iterator();
			int totalCount = 0;
			for (int j = 0; j < list.length(); j++) {
				totalCount += it.next().getCount();
			}
			Iterator<WordCount> it2 = list.iterator();
			for (int i = 0; i < list.length(); i++) {
				WordCount current = it2.next();
				double percent = (current.getCount() / totalCount);
				words.write(current.getWord() + current.getCount() + percent);
				words.write(" \n\n");
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
			System.out.println("file problem");
		} finally {
			words.close();
		}

	}

	public static void main(String args[]) {
		try {

			LinkedList<WordCount> input = loadFile("C:\\eclipse-workspace\\Project4\\src\\test.txt");
			LinkedList.printList2(input);

			// System.out.println(TextAnalyze.maxLength(input));

			LinkedList<WordCount> output = TextAnalyze.radixSort(input);
			writeData("gershongilmantest3", output);
			LinkedList.printList2(output);
		} catch (IOException e) {

		} finally {

		}
	}
}