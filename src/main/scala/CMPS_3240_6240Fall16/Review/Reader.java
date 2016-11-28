/**
 * Peter Riser, Wilson Jeter
 *
 * Reader Class
 *
 *
 */

package CMPS_3240_6240Fall16.Review;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Created by peterhriser on 10/4/16.
 *
 * PLEASE READ ALL MY COMMENTS
 */
public class Reader {
    /**
     * This is the constructor. To use, just call ArrayList whateverArray = new Reader(//filename without type extension ie file not file.txt).docs.tolist
     * Put File in our Data Folder, any txt or html file can be read
     * example code:z
     * val trainData = new Reader("Train").docs.toList
     * val testData = new Reader("Test").docs.toList
     * 'NOTE THE DOCS TO LIST MAY NOTBE NECESSARY, MY CODE ALREADY RETURNS A LIST
     *
    * */
    public ArrayList<Document> docs;
    public Reader(int start, int end, String label){
        docs = pull(start,end,label);
    }

    public int getResponseCode(URL u) throws MalformedURLException, IOException {

        HttpURLConnection huc =  (HttpURLConnection)  u.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return huc.getResponseCode();
    }

    public String Write(String filename){
        Path file = Paths.get("./data/peterriser/" + filename + ".txt");

        String contents = "";
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                contents = contents + "\n" + line;

            }
            return contents;
        } catch (IOException x) {
            System.out.println("Error");
            System.err.println(x);
        }
        return null;
    }

    /**
     * input: filename WITH NO FILE TYPE ADDED TO END, as long as it exists somewhere i the database or offline as a html or txt, just use name without file type.
     * output: String containing all text from the file in question
     * info: This will take an html or txt file, convert its contents to a string and then return it to be used.
     * Note that it will create a file if a file identical to the url does not exist.
     *
     * To test it, use the data types from the movie reviews folder in my data folder and use the existing files if you do not
     * want to generate more
     *
     * file name format:
     * the files range from 0, to 42799 (the number of total movie reviews. This function allows you to pull a single review from that collection
     *
     *
    **/
    public String ReadReview(String filename){

        //initialize a few method wide variables
        URL url;
        File newFile;
        //removes the file type if its either html or txt
        //Here is the read in from URL command
        try {
            newFile = new File("./data/peterriser/" + filename + ".txt");
            if (!newFile.exists()) {
                //CHECKS IF IT IS A MOVIE REVIEW, MAKE THE FILENAME in the format above, if it is something else make it 6 characters or greater
                if (filename.length() <6) {
                    if (filename.length() == 4) {

                        url = new URL("http://www.imdb.com/reviews/" + filename.substring(0, 2) + "/" + filename + ".html");
                    }
                    else {

                        url = new URL("http://www.imdb.com/reviews/" + filename.substring(0, 3) + "/" + filename + ".html");
                    }
                    newFile.createNewFile();
                    if (this.getResponseCode(url) == 200) {
                        FileUtils.copyURLToFile(url, newFile);
                    } else {
                        System.out.println("Broken URL at " + filename);
                    }
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("I GOT ERROR" + e);

            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I GOT ERROR" + e);

            e.printStackTrace();
        }
        return Write(filename);

    }
    public ArrayList FilterWords(String line){
        String[] words = line.split("\\s+");
        ArrayList<String> wordArray = new ArrayList<>();

        for (String word:words){
            if (word.length() >= 3 && word.substring(0) != "<"){
                wordArray.add(word);
            }
        }
        return wordArray;
    }
    /**
     *
     * @param filename, a filename with file type (only two relevant files, will soon be one file anyway)
     * @return retArray, an arrayList
     *
     * works similarly but without pulling from internet, so much less can go wrong
     */

    public ArrayList ReadWords(String filename){
        ArrayList<String> retArray = new ArrayList<>();
        Path file = Paths.get("./data/peterriser/" + filename);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        ArrayList<String> SepLines = FilterWords(line);
                        for (int i = 0; i < SepLines.size(); i++) {
                            retArray.add(SepLines.get(i));
                        }
                    }

            return retArray;
        } catch (IOException x) {
            System.out.println("Error");
            System.err.println(x);
        }
        return null;
    }

    /**
     *
     */
    public ArrayList pull(int start, int numDocs,String label){
        ArrayList<Document> docs = new ArrayList<>();
        for (int i =  start; i <=  numDocs; i++) {
            String index = Integer.toString(i);

            try {
                File newFile = new File("./src/main/scala/CMPS_3240_6240Fall16/Review/Data2/" + index + ".txt");
                Document newDoc = new Document( newFile , label);
                docs.add(newDoc);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        return docs;
        /**
        if (start > 2){
            int num = start;
            for (int i =  num; i <= num + numDocs; i++) {
                String index = Integer.toString(i);
                articleArray.add(ReadWords(this.ReadReview(index + ".txt")));
            }
        }
         **/

    }


     /**testcode
    public static void main(String[] args) {
        Reader reader = new Reader();
        String testReviewReader = reader.ReadReview("42555.txt");
        //THIS WILL GENERATE A TON OF DOCUMENTS, USE ONLY TO TEST MASE USAGE
        //ArrayList testPullHundred = reader.pullHundred("42555");
        System.out.println("WordReader: " + "\n" + "Review Reader: " + testReviewReader);
    }
    **/


}
