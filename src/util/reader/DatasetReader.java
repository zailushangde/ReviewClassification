/**
 * A class to read in and store product reviews.
 * 
 * Michael O'Mahony
 * 20/02/2013
 */

package util.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Review;
import util.ReviewInstance;

public class DatasetReader 
{
	private ArrayList<Review> reviews; // data structure to store reviews
	private Map<Double,List<Double>> ratings; // store the times for the ratings for 1, 2, 3, 4, 5
	private Map<String, Map<String, Double>> products; // store the information about the product
	private Map<String, List<Double>> productRatings; // store every ratings for every product
	public final static String NUMBEROFREVIEWS = "numberOfReviews"; // the flag to define the number of the reviews
	private final static String TOTALRATINGS = "totalratings"; // the flag to define the total ratings
	public final static String STDDEV = "standdev"; // the flag to tell the standed dev value
	public final static String MEANRATING = "meanrating"; // the flag of mean value for a product id 
	private List<ReviewInstance> reInstanceList; // store the instance of the reviews
	private Map<String, ReviewInstance> reInstanceMap;
	/**
	 * default constructor - creates a new DatasetReader object
	 */
	public DatasetReader()
	{
		reviews = new ArrayList<Review>();
		ratings = new HashMap<Double,List<Double>>();
		products = new HashMap<String, Map<String,Double>>();
		productRatings = new HashMap<String, List<Double>>();
		reInstanceList = new ArrayList<ReviewInstance>();
		reInstanceMap = new HashMap<String, ReviewInstance>();
	}
	
	/**
	 * default constructor - creates a new DatasetReader object
	 * @param filename - the name of the file containing the reviews
	 */
	public DatasetReader(final String filename)
	{
		this();
		
		readReviews(filename + ".txt");
	}
	
	/**
	 * @return the reviews
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(final ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * @return the product ids
	 */
	public Set<String> getProductIds()
	{
		Set<String> set = new HashSet<String>();
		for(Review r: reviews)
			set.add(r.getProductId());
		return set;
	}
	
	/** 
	 * reads reviews from a file
	 * @param filename - the path and filename of the file containing the reviews
 	 */
	private void readReviews(final String filename) 
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File("dataset" + File.separator + filename)));
			String line;
			
			while ((line = br.readLine()) != null) 
			{
				String[] tokens = line.split(";;");
				if(tokens.length != 12)
				{
					System.out.println("Error reading from file \"" + filename + "\"");
					System.exit(1);
				}
				
				String reviewId = tokens[0];
				String reviewerUrl = tokens[1];
				
				String productCategory = tokens[2];
				String productId = tokens[3];
				String productName = tokens[4];
				
				String reviewTitle = tokens[5];
				String reviewText = tokens[6];
				double reviewRating = Double.parseDouble(tokens[7]);
				String reviewDate = tokens[8];
				
				int posVotes = Integer.parseInt(tokens[9]);
				int totalVotes = Integer.parseInt(tokens[10]);
				double helpfulness = Double.parseDouble(tokens[11]);
				
				reviews.add(new Review(reviewId, reviewerUrl, productCategory, productId, productName, reviewTitle, reviewText, reviewRating, reviewDate, posVotes, totalVotes, helpfulness));
				
				/** stores the helpfulness of reviews rated from 1 to 5 **/			
				if (reviewRating == (int)(reviewRating))
				{
					List<Double> list = ratings.containsKey(reviewRating)? ratings.get(reviewRating): new ArrayList<Double>();
					list.add(helpfulness);
					ratings.put(reviewRating, list);
				}
				
				// update the information in for the product
				updateProduct(productId, reviewRating);
				// create a new ReviewInstance object
				ReviewInstance ri = new ReviewInstance();
				// store rating of this review
				ri.setRating(reviewRating);
				// classify whether this review is helpful or not
				ri.setReviewHelpfulness(helpfulness>=0.75 ? "helpful" : "unhelpful");
				
				// store the ReviewInstance
				this.reInstanceList.add(ri);
				reInstanceMap.put(reviewId, ri);
			}
			computeStdDevRating();
			br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}

	public Map<String, ReviewInstance> getReInstanceMap() {
		return reInstanceMap;
	}

	public void setReInstanceMap(Map<String, ReviewInstance> reInstanceMap) {
		this.reInstanceMap = reInstanceMap;
	}

	/**
	 * @return the object of ReviewInstance
	 */
	public List<ReviewInstance> getReInstanceList() {
		return reInstanceList;
	}

	/**
	 * @return the products' information
	 */
	public Map<String, Map<String, Double>> getProducts() {
		return products;
	}

	/**
	 * compute the standed Dev value
	 */
	private void computeStdDevRating() {
		for (String productId: products.keySet())
		{
			double above = 0;
			double meanValue = products.get(productId).get(TOTALRATINGS) / products.get(productId).get(NUMBEROFREVIEWS);
			for (Double score: productRatings.get(productId))
				above += Math.pow(score-meanValue, 2);
			products.get(productId).put(STDDEV, Math.sqrt(above/(products.get(productId).get(NUMBEROFREVIEWS))));
			products.get(productId).put(MEANRATING, meanValue);
		}
		
	}

	/**
	 * update the information for the product
	 * @param productId the id of the product to be updated
	 * @param reviewRating the reviews' rating 
	 */
	private void updateProduct(String productId, double reviewRating) {
		Map<String, Double> details = products.containsKey(productId)? products.get(productId): new HashMap<String, Double>();
		Double numberOfReviews = details.containsKey(NUMBEROFREVIEWS)? details.get(NUMBEROFREVIEWS): 0.0;
		Double totalRatings = details.containsKey(TOTALRATINGS)? details.get(TOTALRATINGS): 0.0;

		details.put(NUMBEROFREVIEWS, numberOfReviews + 1);
		details.put(TOTALRATINGS, totalRatings + reviewRating);
		
		List<Double> list = productRatings.containsKey(productId)? productRatings.get(productId): new ArrayList<Double>();
		list.add(reviewRating);
		
		products.put(productId, details);
		productRatings.put(productId, list);
	}

	/**
	 * @return the review' rating
	 */
	public Map<Double,List<Double>> getRatings() {
		return ratings;
	}

}
