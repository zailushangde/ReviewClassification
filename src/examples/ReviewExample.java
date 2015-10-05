/**
 * An example class to read in a review dataset
 * 
 * Michael O'Mahony
 * 20/02/2013
 */

package examples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import util.Review;
import util.reader.DatasetReader;

public class ReviewExample 
{
	public final static String EOL = System.getProperty("line.separator");
	
	public static void main(String[] args)
	{
		//String filename = "Digital Camera.txt"; // set the dataset filename
		String filename = "Printer.txt"; // set the dataset filename
		DatasetReader reader = new DatasetReader(filename); // create an instance of the DatasetReader class
		ArrayList<Review> reviews = reader.getReviews(); // get all reviews and store in an ArrayList
		
		System.out.println("total # reviews: " + reviews.size()); // print the number of reviews in the ArrayList
		System.out.println("total # products: " + reader.getProductIds().size());
		
		System.out.println("\nthe 3rd review:\n" + reviews.get(2).toString()); 	// print the 3rd review
																				// - note the <br /> tag in the review text...
																				// - treat two (or more) consecutive <br /> tags as a new paragraph
		
		// tip - how to replace all <br /> tags with line separators: 
		System.out.println("\nreview text with \"<br />\" tags:\n" + reviews.get(2).getReviewText());		
		System.out.println("\nreview text with \"<br />\" tags replaced by line separators:\n" + reviews.get(2).getReviewText().replaceAll("<br />", EOL));
	}
}
