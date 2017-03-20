/*   Author(s): Oliveira, Kyle
 *       Title: Indexer, Package Info
 *     License: Creative Commons Attribution-Share Alike 3.0 United States
 *              License. See http://kyleoliveira.net/portfolio.html
 */

/**
 * <p>A sample program that takes a text file composed of space-delimited words and
 * newlines and produces an alphabetical index of each word with each line
 * number that it occurs on. Words are indexed case-insensitively.</p>
 * <p>The program filters out some special characters, but not all. Specifically,
 * it should filter out
 * <em style="background-color:Gainsboro;">,'"!.?():</em></p>
 * <p>It also converts the string "'s" to " is" so that contractions
 * based on is count as two words (the base and the word "is). It also
 * converts contractions of "it" (i.e. "'t") to the word "it". (The last bit of
 * functionality comes from the fact that the test input is a selection from
 * Hamlet...)</p>
 *
 * @author <a href="mailto:kcoliveira@ucdavis.edu">Kyle Oliveira</a>
 */
package net.kyleoliveira.indexer;
