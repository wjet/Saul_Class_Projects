/**
 * 
 */
package CMPS_3240_6240Fall16.Review;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Peter Riser
 * 
 */
public class Document {

	private final String label;
	private final List<String> words;
	private String guid;
	private final  List<String> buzzwords= Arrays.asList("rating", "percent", "grade", "stars");
    private final  List<String> buzzOwned = new ArrayList<>();
	private String grade = "";
	/**
	 * Create a new document
	 * 
	 * @throws java.io.IOException
	 */
	public Document(File file, String label) throws IOException {
		this.label = label;
		BufferedReader reader = new BufferedReader(new FileReader(file));
        this.guid=file.getName();
		words = new ArrayList<>();
		String line;

		int isGrade = 0;

		while ((line = reader.readLine()) != null) {
			for (String word : line.split("\\s+")) {

				if (word.length() > 2) {
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

	public Document(File file) throws IOException {
		this(file, "unknown");
	}

	public Document(List<String> words) {

		this(words, "unknown");
	}

	public void setGUID(String guid) {
		this.guid = guid;
	}
	
	public String getGUID(){
		return this.guid;
	}
	

	public Document(List<String> words, String label) {
		this.words = words;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

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
