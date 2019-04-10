import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextAnalyze {

	public static WordCount radixSort(WordCount count) {
		return count;
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
