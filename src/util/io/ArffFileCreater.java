package util.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import util.ReviewInstance;
import util.reader.DatasetReader;

public class ArffFileCreater {

	public static void createArffFile(DatasetReader reader, String category)
	{
		String att = "@attribute ";
		
		String filename = "dataset" + File.separator + category.replace(" ", "_") + ".arff";
		File file = new File("D:\\" + filename);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdir();
		if (file.exists())
			System.out.println("The file has existed");
		else
		{
			try {
				file.createNewFile();
				System.out.println("file done");
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//write the data into the file
		try {
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fileWriter);
			
			// the title
			pw.println("@relation " + category.replace(" ", "_") + ".arff");
			pw.println();
			pw.println(att + "rating numeric");
			pw.println(att + "numberOfSentences numeric");
			pw.println(att + "numberOfWords numeric");
			pw.println(att + "width numeric");
			pw.println(att + "depth numeric");
			pw.println(att + "smog numeric");
			pw.println(att + "wordsPerSentence numeric");
			pw.println(att + "percentComplexWords numeric");
			pw.println(att + "syllablesPerWords numeric");
			pw.println(att + "fleschReadingScore numeric");
			pw.println(att + "meanRatingOfProduct numeric");
			pw.println(att + "stdDevRating numeric");
			pw.println(att + "fleschKincaid numeric");
			pw.println(att + "numComplexWords numeric");
			pw.println(att + "numberOfSentiment numeric");
			pw.println(att + "popularityOfProduct numeric");
			pw.println(att + "fogIndex numeric");
			pw.println(att + "reviewHelpfulness {helpful,unhelpful}");
			pw.println();
			pw.println("@data");
			
			for(int i=0;i<reader.getReviews().size();++i){
				ReviewInstance ri = reader.getReInstanceList().get(i);
				pw.println(ri.toString());
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
