package alg;

import alg.analysis.Analysis;
import alg.analysis.GenerateReviewInstance;
import util.reader.DatasetReader;

public class Execute {
	
	public static final boolean ISDEBUG = true;
	
	public static void main(String[] args)
	{
		// choose one of the categorys to analysis or creating the arff file
		String category = "Printer";
//		String category = "Digital Camera";
		
		// to create a now DatasetReader object, and it stores the details about all reviews
		DatasetReader reader = new DatasetReader(category);
		
		// analysis median and IQR
		Analysis analysis = new Analysis(reader);
		analysis.generateResults();
		
		// generate the review instances
		GenerateReviewInstance gri = new GenerateReviewInstance(reader, category);
		
		// to generate the arff file, and when ISDEBUG is true, the console will output the ReviewIntance
		gri.generate(ISDEBUG);
	}
}
