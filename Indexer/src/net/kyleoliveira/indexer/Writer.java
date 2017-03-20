/*   Author(s): Oliveira, Kyle
 *       Title: Indexer, Writer Class
 *     License: Creative Commons Attribution-Share Alike 3.0 United States
 *              License. See http://kyleoliveira.net/portfolio.html
 */

package net.kyleoliveira.indexer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Contains methods for writing results to a text file.
 *
 * @author <a href="mailto:kcoliveira@ucdavis.edu">Kyle Oliveira</a>
 */
public final class Writer {

    /**
     * Constructor. Private so the class stays static.
     * 
     */
    Writer() {}


    /**
     * Writes the given TreeMap to standard out.
     *
     * @param words TreeMap to print.
     */
    static public void WriteToOut(TreeMap<String, ArrayList<Integer> > words) {
        System.out.println("-- Result:");

        Iterator iterator = words.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ArrayList<Integer>> entry = (Map.Entry<String, ArrayList<Integer>>) iterator.next();
            System.out.print(entry.getKey());
            StringBuilder builder = new StringBuilder();
            for(Integer i : ((ArrayList<Integer>)entry.getValue())) {
                builder.append(" ");
                builder.append(i);
                builder.append(",");
            }
            builder.replace(builder.lastIndexOf(","), builder.length(),  "");
            builder.append("\n");

            System.out.print(builder.toString());
        }
    }

    /**
     * Writes the given TreeMap to a file.
     *
     * @param words TreeMap to print.
     * @param outPath Path to write the file out to.
     */
    static public void WriteToFile(TreeMap<String, ArrayList<Integer> > words, String outPath) {
        System.out.println("-- Writing result to " + outPath);

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(outPath));
            Iterator iterator = words.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, ArrayList<Integer>> entry = (Map.Entry<String, ArrayList<Integer>>) iterator.next();
                out.write(entry.getKey());
                StringBuilder builder = new StringBuilder();
                for(Integer i : ((ArrayList<Integer>)entry.getValue())) {
                    builder.append(" ");
                    builder.append(i);
                    builder.append(",");
                }
                builder.replace(builder.lastIndexOf(","), builder.length(),  "");
                builder.append("\n");

                out.write(builder.toString());
            }
            out.close();
        } catch (IOException e) {
            System.err.println("File output error! Cause:");
            e.printStackTrace(System.err);
        }
    }
}
