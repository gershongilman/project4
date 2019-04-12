import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 * @author gershon
 *
 */
public class TextAnalyze {

	/**
	 * 
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
				WordCount firstWord = bucket.getFirst();   // can be null
				if ((firstWord != null) && (str == firstWord.getWord()) {
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

	public static LinkedList<WordCount> loadFile(String fileName) throws FileNotFoundException {
//		File file = new File(fileName);
//		BufferedReader br = null;
//
//		try {
//
//			br = new BufferedReader(new FileReader(fileName));
//			LinkedList<WordCount> words = new LinkedList<>();
//			String line = null;
//			String[] lineWords;
//			while (br.readLine() != null) {
//				lineWords = line.split("\\s");
//				for (String word : lineWords) {
//					words.addToEnd(new WordCount(word));
//				}
//			}
//			return words;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new IOException(" File " + fileName + "cannot be read ");
//		} finally {
//			br.close();
//		}
		File file = new File(fileName);
		Scanner sc = null;
		try {
			sc = new Scanner(file);

			LinkedList<WordCount> words = new LinkedList<>();
			String line;
			String[] lineWords;

			while (sc.hasNextLine()) {
				line = sc.nextLine();
				lineWords = line.split("\\s");
				for (String word : lineWords) {
					words.addToEnd(new WordCount(word));
				}
			}
			return words;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileNotFoundException(" File " + fileName + "cannot be read ");
		} finally {
			sc.close();
		}
	}

//	public static void writeData(String fileName, LinkedList<WordCount> list) throws IOException {
//		PrintStream output = new PrintStream(new FileOutputStream(fileName));
//		for (int i = 0; i < list.length(); i++) {
//
//			output.print(list.getWord());
//			output.print(list.getCount());
//		}
//	}

	public static void main(String args[]) {
		try {

			LinkedList<WordCount> input = loadFile("C:\\eclipse-workspace\\Project4\\src\\test.txt");
			LinkedList.printList2(input);

			System.out.println(TextAnalyze.maxLength(input));
			LinkedList<WordCount> output = TextAnalyze.radixSort(input);
			LinkedList.printList2(output);
		} catch (FileNotFoundException e) {

		} finally {

		}
	}
}
