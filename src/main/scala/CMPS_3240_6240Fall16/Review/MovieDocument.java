/**
 * 
 */
package CMPS_3240_6240Fall16.Review;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Peter Riser
 * 
 */
public class MovieDocument {

	private final String label;
	private final List<String> words;
	private String guid;
	private final  List<String> buzzwords= Arrays.asList("rating", "Rating", "RATING", "rating:", "Rating:", "RATING:", "percent", "Percent", "PERCENT", "percent:", "PERCENT:", "Percent:", "grade", "GRADE", "Grade:", "grade:", "GRADE:", "Grade:",  "stars", "Stars", "STARS", "stars:", "Stars:", "STARS:");
    private final  List<String> buzzOwned = new ArrayList<>();
	private String grade = "";
	private int netGB = 0;

/**
	 * Create a new document
	 * 
	 * @throws java.io.IOException
	 */
	public MovieDocument(File file, String label, HashMap[] lexicons) throws IOException {

		this.label = label;
		BufferedReader reader = new BufferedReader(new FileReader(file));
        this.guid=file.getName();
		words = new ArrayList<>();

		String line;
		int isGrade = 0;

		while ((line = reader.readLine()) != null) {
			for (String word : line.split("\\s+")) {

				if (word.length() > 2) {
					if (lexicons[0].containsKey(word)) netGB++;
					else if (lexicons[1].containsKey(word)) netGB--;
					words.add(word.trim());
				}
				if (buzzwords.contains(word)){
                    isGrade = 1;
                }
				else if (isGrade == 1){
					buzzOwned.add(word);
					isGrade = 0;
				}

			}

			}
		if (!buzzOwned.isEmpty()){
			grade = buzzOwned.get(0);
		}
		reader.close();
	}


	public void setGUID(String guid) {
		this.guid = guid;
	}
	
	public String getGUID(){
		return this.guid;
	}



	public MovieDocument(List<String> words, String label) {
		this.words = words;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	public int getNetGB(){return netGB;}
	public List<String> getWords() {
		return Collections.unmodifiableList(words);
	}
    public List<String> getBuzzwords(){return Collections.unmodifiableList(buzzOwned);}
	public String getGrade() {
		return grade;
	}
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return label + ", " + words;
	}

}
