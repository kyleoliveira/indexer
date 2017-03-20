/*   Author(s): Oliveira, Kyle
 *       Title: Indexer, Reader Class
 *     License: Creative Commons Attribution-Share Alike 3.0 United States
 *              License. See http://kyleoliveira.net/portfolio.html
 */

package net.kyleoliveira.indexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Contains methods for reading from a text file containing space-delimited
 * words and newlines. Parses the data into a given data structure / file /
 * database along with the indexing information for each word.
 *
 * @author <a href="mailto:kcoliveira@ucdavis.edu">Kyle Oliveira</a>
 */
public final class Reader {
    
    /**
     * Constructor. Private so the class stays static.
     * 
     */
    Reader(){}

    static public TreeMap<String, ArrayList<Integer> > Read(File file) {
        TreeMap<String, ArrayList<Integer> > words = new TreeMap<String, ArrayList<Integer> >();

        try {
            System.out.println("-- Reading words from " + file.getPath());
            BufferedReader in = new BufferedReader(new FileReader(file));
            int lineNum = 0;
            String str;
            while ((str = in.readLine()) != null) {
                lineNum++;
                ParseLine(words, str, lineNum);
            }
            in.close();
        } catch (IOException e) {
            System.err.println("File read error. Cause:");
            e.printStackTrace(System.err);
        }

        return words;
    }

    /**
     * Parses a single line of words into our TreeMap.
     *
     * @param words TreeMap of words read from the document.
     * @param line Current line of words to parse.
     * @param lineNum Current line number.
     */
    static void ParseLine(TreeMap<String, ArrayList<Integer> > words, String line, int lineNum) {
        StringTokenizer tokenizer = new StringTokenizer(CleanLine(line), " ");

        while(tokenizer.hasMoreTokens()) {
            // Tokenize the  line and store the words
            String token = tokenizer.nextToken().toLowerCase();
            if(words.containsKey(token)) {
                // If the word is already there, add it the line number to the list
                // if the line number isn't in the list.
                if(!words.get(token).contains(lineNum))
                    words.get(token).add(lineNum);
            }
            else
            {
                // If the word isn't there, add the new word and the associated line number
                ArrayList<Integer> lineNums = new ArrayList<Integer>();
                lineNums.add(lineNum);
                words.put(token, lineNums);
            }
        }
    }

    /**
     * Applies a basic filter to the line that should remove all instances of
     * <code>,'"!.?():</code><br />
     * Note that it also converts the string "'s" to " is" so that contractions
     * based on is count as two words (the base and the word "is). It also
     * converts contractions of "it" (i.e. "'t") to the word "it".
     *
     * @param line Line to be cleaned
     * @return the cleaned line
     */
    static String CleanLine(String line) {
        StringBuilder cleanedLine = new StringBuilder(line);

        // Remove ,
        while(cleanedLine.indexOf(",") != -1)
        {
            cleanedLine.deleteCharAt(cleanedLine.indexOf(","));
        }

        // Remove '
        while(cleanedLine.indexOf("\'") != -1)
        {
            int indexOfMark = cleanedLine.indexOf("\'");
            if(cleanedLine.charAt(indexOfMark + 1) == 's')
            {
                cleanedLine.replace(indexOfMark, indexOfMark + 1, " i");
            }
            else if(cleanedLine.charAt(indexOfMark + 1) != ' ')
            {
                cleanedLine.deleteCharAt(indexOfMark);
                cleanedLine.insert(indexOfMark, "i");
            }
            else {
                cleanedLine.deleteCharAt(indexOfMark);
            }
        }

        // Remove "
        while(cleanedLine.indexOf("\"") != -1)
        {
            cleanedLine.deleteCharAt(cleanedLine.indexOf("\""));
        }

        // Remove !
        while(cleanedLine.indexOf("!") != -1)
        {
            cleanedLine.deleteCharAt(cleanedLine.indexOf("!"));
        }

        // Remove .
        while(cleanedLine.indexOf(".") != -1)
        {
            cleanedLine.deleteCharAt(cleanedLine.indexOf("."));
        }

        // Remove ?
        while(cleanedLine.indexOf("?") != -1)
        {
            cleanedLine.deleteCharAt(cleanedLine.indexOf("?"));
        }

        // Remove (
        while(cleanedLine.indexOf("(") != -1)
        {
            cleanedLine.deleteCharAt(cleanedLine.indexOf("("));
        }

        // Remove )
        while(cleanedLine.indexOf(")") != -1)
        {
            cleanedLine.deleteCharAt(cleanedLine.indexOf(")"));
        }

        // Remove :
        while(cleanedLine.indexOf(":") != -1)
        {
            cleanedLine.deleteCharAt(cleanedLine.indexOf(":"));
        }

        return cleanedLine.toString();
    }

}
