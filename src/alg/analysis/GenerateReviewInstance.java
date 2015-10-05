/**
 * this class mainly to update the ReviewInstance
 */
package alg.analysis;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Review;
import util.ReviewInstance;
import util.com.representqueens.lingua.en.Fathom;
import util.com.representqueens.lingua.en.Readability;
import util.io.ArffFileCreater;
import util.nlp.Parser;
import util.reader.DatasetReader;
import util.reader.FileReader;

public class GenerateReviewInstance {
	private DatasetReader reader; // store the DatesetReader object
	private FileReader fileReader; // store the FileReader object
	// paths and filenames of the positive and negative words
	private String posWordFile = "sentiment lexicon" + File.separator + "positive-words.txt"; 
	private String negWordFile = "sentiment lexicon" + File.separator + "negative-words.txt";
	
	private String category; // the category to be analyzed
	private Set<String> featurewords; // store the features of a category
	private Set<String> posWords; // store the positive sentiment words
	private Set<String> negWords; // store the negative sentiment words
	/**
	 * Constructor - create a GenerateReviewInstance object
	 * @param reader - the object about the DatasetReader
	 * @param category - the category to update
	 */
	public GenerateReviewInstance(DatasetReader reader, String category) {
		super();
		this.reader = reader;
		this.category = category;
		String featureFile = "feature sets" + File.separator + category + " Features.txt";
		String sameMeaningWords = "same features" + File.separator + category + " same feature.csv";
		fileReader = new FileReader(featureFile, posWordFile, negWordFile, sameMeaningWords);
		featurewords = fileReader.getFeaturewords();
		posWords = fileReader.getPosWords();
		negWords = fileReader.getNegWords();
	}
	
	/**
	 * prepare to generate the ARFF file 
	 */
	public void generate(boolean debug)
	{
		List<Review> reviews = this.reader.getReviews();
		// get product profiles
		Map<String, Map<String, Double>> profile = this.reader.getProducts();
		String str = null;
		int index  = 0;
		Parser parser = new Parser();
		for (Review review: reviews)
		{
			Map<String, Double> details = profile.get(review.getProductId());
			
			double numOfFeatures = 0.0; // #features in the review
			double numOfSentiments = 0.0; // #sentiment in the review
			double numOfWordsInSentenceWithFeature = 0.0; // #words in sentences that contain feature
			double numOfSentenceWithFeature = 0.0; // #sentences that contain feature
			str = review.getReviewText().replaceAll("<br />", ". ");
			// parse text into sentences
			
			String[] sentences = parser.getSentences(str);
						
			for (String sentence: sentences)
			{
				// parse sentence into tokens
				String[] tokens = parser.getSentenceTokens(sentence.toLowerCase());
				// get POS tags
				String[] pos = parser.getPOSTags(tokens);
				boolean flag = false;
				for(int i = 0; i < tokens.length; i++) // print the sentence tokens and corresponding chunk and POS tags
				{
					if (((i+1) < tokens.length) && ((pos[i].startsWith("JJ") && featurewords.contains(tokens[i] + " " + tokens[i+1]))
							|| (pos[i].startsWith("NN") && featurewords.contains(tokens[i] + " " + tokens[i+1]))))
					{
						numOfFeatures ++;
						i ++;
						flag = true;
					}
						
					else if (featurewords.contains(tokens[i]))
					{
						numOfFeatures ++;
						flag = true;
					}
					if ((i + 1) < tokens.length)
						numOfSentiments = defineSentiment(tokens[i+1], numOfSentiments);
				}
				if (flag)
				{
					numOfSentenceWithFeature ++;
					numOfWordsInSentenceWithFeature += tokens.length;
				}
				
			}
			// to obtain the corresponding ReviewInstance object
			ReviewInstance ri = this.reader.getReInstanceMap().get(review.getReviewId());
			// analysis readability
			Fathom.Stats stats = Fathom.analyze(str); // create an instance of the Fathom.Stats class
			index ++;			
			ri.setPopularityOfProduct(details.get(DatasetReader.NUMBEROFREVIEWS)); //
			ri.setDepth(numOfWordsInSentenceWithFeature/numOfSentenceWithFeature); //
			ri.setWidth(numOfFeatures); //
			ri.setSyllablesPerWords(Readability.syllablesPerWords(stats)); //
			ri.setWordsPerSentence(Readability.wordsPerSentence(stats)); //
			ri.setNumComplexWords(stats.getNumComplexWords()); //
			ri.setSmog(Readability.calcSMOG(stats)); //
			ri.setFleschKincaid(Readability.calcKincaid(stats)); //
			ri.setFleschReadingScore(Readability.calcFlesch(stats)); //
			ri.setNumberOfSentences(stats.getNumSentences()); //
			ri.setNumberOfSentiment(numOfSentiments); //
			ri.setMeanRatingOfProduct(details.get(DatasetReader.MEANRATING));//
			ri.setStdDevRating(details.get(DatasetReader.STDDEV)); //
			ri.setPercentComplexWords(Readability.percentComplexWords(stats)); //
			ri.setFogIndex(Readability.calcFog(stats)); //
			ri.setNumberOfWords(stats.getNumWords()); //
			if (debug)
				System.out.println(index + " " + ri.toString());
		}
		// create the ARFF file
		ArffFileCreater.createArffFile(reader, category);
	}

	/**
	 * @param string the single word in this sentence
	 * @param numOfSentiments to record the number of the sentiment word
	 * @return if it is a sentiment the number will be plused one
	 */
	private double defineSentiment(String string, double numOfSentiments) {
		if (posWords.contains(string) || negWords.contains(string))
			return (++numOfSentiments);
		else
			return numOfSentiments;
	}
	
}
