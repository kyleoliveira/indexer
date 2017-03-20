/*   Author(s): Oliveira, Kyle
 *       Title: Indexer, Main Class
 *     License: Creative Commons Attribution-Share Alike 3.0 United States
 *              License. See http://kyleoliveira.net/portfolio.html
 */

package net.kyleoliveira.indexer;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Given a text file composed of space-delimited words and newlines, produces
 * an alphabetical index of each word with each line number that it occurs on.
 * Does not account for special characters other than space and newline, so do
 * not include them.
 *
 * @author <a href="mailto:kcoliveira@ucdavis.edu">Kyle Oliveira</a>
 */
public class Main {

    /**
     * Starts the application.
     *
     * @param args the command line arguments. Expects just the path to the file
     *             to be indexed.
     */
    public static void main(String[] args) {
        File file = ValidateInput(args);

        if(file != null && file.exists() && file.canRead()) {
            // TODO: Read file into data structure / flat file / database, parsing into words.
            TreeMap<String, ArrayList<Integer> > words = Reader.Read(file);

            // Write out of storage, in the appropriate order, writing into a new file.
            if(args.length == 2) {
                Writer.WriteToFile(words, args[1]);
            } else {
                Writer.WriteToOut(words);
            }
            System.out.println("-- Done.");
        }
        else {
            System.err.println("-- Incorrect input! File is either nonexistent or unavailable.");
            System.exit(1);
        }
    }

    /**
     * Validates the user input and returns a File object to the specified File.
     * 
     * @param input args array
     * @return File object if given a valid filename as arg[0]
     */
    static private File ValidateInput(String[] input){
        // Identify the file we're working with and whatever special arguments we're using.
        if(input.length < 1 || input.length > 2) {
            System.err.println("-- Incorrect input! Please supply one file to open as your first argument.");
            System.err.println("-- If you wish to output the results, the second argument must be the file to output to.");
            System.err.println("-- You supplied these arguments:");
            for(int i = 0; i < input.length; i++){
                System.err.println("-- arg[" + i + "]: " + input[i]);
            }
            System.exit(1);
        }

        return new File(input[0]);
    }

}
