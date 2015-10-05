/**
 * this class is to store the ReviewInstance object
 */
package util;

import java.lang.reflect.Constructor;

public class ReviewInstance {

	// attributes to store
	private String reviewHelpfulness;
	private double rating;
	private double numberOfSentences;
	private double numberOfWords;
	private double width;
	private double depth;
	private double smog;
	private double wordsPerSentence;
	private double percentComplexWords;
	private double syllablesPerWords;
	private double fleschReadingScore;
	private double fogIndex;
	private double fleschKincaid;
	

	private double numComplexWords;
	private double numberOfSentiment;
	private double popularityOfProduct;
	private double stdDevRating;
	private double meanRatingOfProduct;
	
	/**
	 * Constructor - create a new ReviewInstance object
	 */
	public ReviewInstance() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor - create a new ReviewInstance object
	 * @param reviewHelpfulness - Class label (helpful or unhelpful)
	 * @param rating - The rating from the review of every single product
	 * @param numberOfSentences - The number of sentences in the review
	 * @param width - The total number of the features in this review
	 * @param depth - The average number of words per sentence divided by the number of sentences
	 * @param smog - The Simple Measure Of Gobbledygood(SMOG) is a way of estimating the difficulty of writing
	 * @param wordsPerSentence - The mean number the words for the sentence in the review
	 * @param percentComplexWords - The percent of the complex words in a review
	 * @param syllablesPerWords - The average number of syllables for every word
	 * @param fleschReadingScore - The higher the score, the easier it is to understand the text.
	 * @param fogIndex - A simple formula for measuring readability
	 * @param fleschKincaid - This score rates text on U.S. grade school level
	 * @param numComplexWords - The number of complex words that occur in this review
	 * @param numberOfSentiment - The number of the sentiment number in this review
	 * @param popularityOfPriduct - The number of the reviews about this product in the dataset
	 * @param stdDevRating - The standard deviation of the ratings related to the product from the whole reviews
	 */
	public ReviewInstance(String reviewHelpfulness, double rating,
			double numberOfSentences, double width, double depth, double smog,
			double wordsPerSentence, double percentComplexWords,
			double syllablesPerWords, double fleschReadingScore,
			double fogIndex, double fleschKincaid, double numComplexWords,
			double numberOfSentiment, double popularityOfPriduct,
			double stdDevRating) {
		super();
		this.reviewHelpfulness = reviewHelpfulness;
		this.rating = rating;
		this.numberOfSentences = numberOfSentences;
		this.width = width;
		this.depth = depth;
		this.smog = smog;
		this.wordsPerSentence = wordsPerSentence;
		this.percentComplexWords = percentComplexWords;
		this.syllablesPerWords = syllablesPerWords;
		this.fleschReadingScore = fleschReadingScore;
		this.fogIndex = fogIndex;
		this.fleschKincaid = fleschKincaid;
		this.numComplexWords = numComplexWords;
		this.numberOfSentiment = numberOfSentiment;
		this.popularityOfProduct = popularityOfPriduct;
		this.stdDevRating = stdDevRating;
	}

	/** the following is the GET and SET methods **/
	public String getReviewHelpfulness() {
		return reviewHelpfulness;
	}

	public void setReviewHelpfulness(String reviewHelpfulness) {
		this.reviewHelpfulness = reviewHelpfulness;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getNumberOfSentences() {
		return numberOfSentences;
	}

	public void setNumberOfSentences(double numberOfSentences) {
		this.numberOfSentences = numberOfSentences;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getMeanRatingOfProduct() {
		return meanRatingOfProduct;
	}
	
	public double getNumberOfWords() {
		return numberOfWords;
	}

	public void setNumberOfWords(double numberOfWords) {
		this.numberOfWords = numberOfWords;
	}

	public void setMeanRatingOfProduct(double meanRatingOfProduct) {
		this.meanRatingOfProduct = meanRatingOfProduct;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public double getSmog() {
		return smog;
	}

	public void setSmog(double smog) {
		this.smog = smog;
	}

	public double getWordsPerSentence() {
		return wordsPerSentence;
	}

	public void setWordsPerSentence(double wordsPerSentence) {
		this.wordsPerSentence = wordsPerSentence;
	}

	public double getPercentComplexWords() {
		return percentComplexWords;
	}

	public void setPercentComplexWords(double percentComplexWords) {
		this.percentComplexWords = percentComplexWords;
	}

	public double getSyllablesPerWords() {
		return syllablesPerWords;
	}

	public void setSyllablesPerWords(double syllablesPerWords) {
		this.syllablesPerWords = syllablesPerWords;
	}

	public double getFleschReadingScore() {
		return fleschReadingScore;
	}

	public void setFleschReadingScore(double fleschReadingScore) {
		this.fleschReadingScore = fleschReadingScore;
	}

	public double getFogIndex() {
		return fogIndex;
	}

	public void setFogIndex(double fogIndex) {
		this.fogIndex = fogIndex;
	}

	public double getFleschKincaid() {
		return fleschKincaid;
	}

	public void setFleschKincaid(double fleschKincaid) {
		this.fleschKincaid = fleschKincaid;
	}

	public double getNumComplexWords() {
		return numComplexWords;
	}

	public void setNumComplexWords(double numComplexWords) {
		this.numComplexWords = numComplexWords;
	}

	public double getNumberOfSentiment() {
		return numberOfSentiment;
	}

	public void setNumberOfSentiment(double numberOfSentiment) {
		this.numberOfSentiment = numberOfSentiment;
	}

	public double getPopularityOfProduct() {
		return popularityOfProduct;
	}

	public void setPopularityOfProduct(double popularityOfProduct) {
		this.popularityOfProduct = popularityOfProduct;
	}

	public double getStdDevRating() {
		return stdDevRating;
	}

	public void setStdDevRating(double stdDevRating) {
		this.stdDevRating = stdDevRating;
	}

	public String toString() {
		return  rating + "," + numberOfSentences 
				+ "," + numberOfWords 
				+ "," + width + "," + depth
				+ "," + smog + "," + wordsPerSentence
				+ "," + percentComplexWords
				+ "," + syllablesPerWords
				+ "," + fleschReadingScore
				+ "," + meanRatingOfProduct
				+ "," + stdDevRating + "," + fleschKincaid
				+ "," + numComplexWords
				+ "," + numberOfSentiment
				+ "," + popularityOfProduct
				+ "," + fogIndex + ","  + reviewHelpfulness;
	}
	
	
}
