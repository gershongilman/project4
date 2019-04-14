import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
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

	/**
	 * 
	 * @param fileName
	 * @param list
	 * @throws IOException
	 */
	public static void writeData(String fileName, LinkedList<WordCount> list)
			throws IOException, FileAlreadyExistsException {

		// we use words to write to the file
		BufferedWriter words = null;
		// try {

		// we check if the file we want to write to actually already exists
		if (new File(fileName).exists()) {
			System.out.println("File already exists");
			throw new FileAlreadyExistsException(fileName);
		} else {
			// we create a file writer to write the words we want
			FileWriter writer = new FileWriter(fileName, false);
			words = new BufferedWriter(writer);

			// we want to store total count to be able to do the percentage calculation
			double totalCount = 0;
			// finding the total count
			for (Iterator<WordCount> it = list.iterator(); it.hasNext();) {
				totalCount += it.next().getCount();
			}

			// doing the writing
			for (Iterator<WordCount> it2 = list.iterator(); it2.hasNext();) {
				WordCount current = it2.next();
				double percent = (current.getCount() / totalCount) * 100;
				words.write(current.getWord() + ", " + current.getCount() + ", " + (int) percent);
				words.newLine();

			}
			// }
			// } catch (IOException e) {
			// System.err.format("IOException: %s%n", e);
			// } finally {
			words.close();
		}

	}

	public static void main(String args[]) {
		try {

			LinkedList<WordCount> input = loadFile("C:\\eclipse-workspace\\Project4\\src\\test.txt");
			LinkedList.printList2(input);

			// System.out.println(TextAnalyze.maxLength(input));

			LinkedList<WordCount> output = TextAnalyze.radixSort(input);
			writeData("gershongilmantest5.txt", output);
			LinkedList.printList2(output);
		} catch (IOException e) {

		} finally {

		}
	}
}