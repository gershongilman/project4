import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextAnalyze {

	public static WordCount radixSort(LinkedList<WordCount> words) {
		
		//loop over all elements to find max length
		int maxLength = 0;
		LLNode<WordCount> node = words.getFirst();
		while (node != null) {
			node = node.getNext();
			maxLength= ( node.getElement().getWord().length() < maxLength) ? node.getElement().getWord().length() : maxLength;
		}
		
		// create array of 27 linked Lists
		List<LinkedList<WordCount>> backets = new ArrayList<LinkedList<WordCount>>(27);
		
		
	    return words;
	}


	
	
	public static  void writeData(String  fileName, LinkedList<WordCount> words) {
		
	}
	
	public static LinkedList<WordCount> loadFile(String fileName) throws FileNotFoundException {
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
			throw new FileNotFoundException(" File " + fileName+ "cannot be read ");
		} finally {
			sc.close();
		}

	}

}
