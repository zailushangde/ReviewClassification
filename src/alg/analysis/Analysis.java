/** 
 * a class to analysis the MEDIAN and IQR for the category
 */
package alg.analysis;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import util.reader.DatasetReader;

public class Analysis {

	private DatasetReader reader;
	/**
	 * Constructor - create a new Analysis object
	 * @param reader - the DatasetReader object
	 */
	public Analysis(DatasetReader reader) {
		super();
		this.reader = reader;
	}
	
	/**
	 *  to generate the results for the Median and IQR
	 * @param reader
	 */
	public void generateResults() 
	{
		for (double i = 1; i <= reader.getRatings().size(); i ++)
		{
			System.out.println("\n*****The summary for the " + i + " star*****");
			computeNum(reader.getRatings().get(i));
			computeMedianIQR(reader.getRatings().get(i));
		}
	}
	/**
	 * @param rating all of the reviews rating 
	 */
	private void computeMedianIQR(List<Double> rating) {
		double[] values = new double[rating.size()];
		// convert rating into array
		for(int i=0; i<rating.size(); ++i){
			values[i] = rating.get(i);
		}
		
		Percentile percentile = new Percentile();
		double median = percentile.evaluate(values, 50);
		double IQR = percentile.evaluate(values, 75) - percentile.evaluate(values, 25);
		System.out.println("median: " + median);
		System.out.println("IQR: " + IQR);
		for (int p = 25; p <= 75; p+=25)
			System.out.println(p + "th percentile:  " + percentile.evaluate(values, p));
	}

	
	/**
	 * calculate the number of helpfulness and unusefulness reviews
	 * @param rating
	 */
	private void computeNum(List<Double> rating) {
		int yes=0;
		int no=0;
		
		for(Double helpfulness : rating){
			// increment count of helpfulness
			if(helpfulness>=0.75)yes++;
			else no++;
		}
		
		System.out.println("helpful: " + yes + "  unhelpful: " + no);
	}
}
