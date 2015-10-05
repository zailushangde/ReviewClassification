/**
 * An example class to demonstrate NLP parsing
 * 
 * Michael O'Mahony
 * 20/02/2013
 */

package examples;

import util.nlp.Parser;

public class NLPExample {
	public static void main(String[] args)
	{
		String str = "It provides amazing picture quality and is perfect for amateur photographers. " +
				"The battery life is truly outstanding, I am not even going to buy a spare battery. " +
				"It's weight is heavier than the D60, but that's not a problem. " +
				"The stereo sound and autofocus are limited, however. " +
				"The viewfinder is also really poor.";
		str = "He reckons the current account deficit will narrow to only 1.8 billion in September";
		str = "It's weight is heavier than the D60, but that's not a problem.";
		
		System.out.println(str + "\n");
		
		Parser parser = new Parser(); // create an instance of the Parser class
		String[] sentences = parser.getSentences(str); // get the sentences
		System.out.println("Token\t\tChunk Tag\tPOS Tag");
		for(String sentence: sentences) // iterate over each sentence
		{
			String[] tokens = parser.getSentenceTokens(sentence); // get the sentence tokens (words)
			String pos[] = parser.getPOSTags(tokens); // get the POS tag for each sentence token
			String chunks[] = parser.getChunkTags(tokens, pos); // get the chunk tags for the sentence
			
			for(int i = 0; i < tokens.length; i++) // print the sentence tokens and corresponding chunk and POS tags
				System.out.println(tokens[i] + "\t\t" + chunks[i] + "\t\t" + pos[i]);
			System.out.println("\n+++++\n");			
		}
	}

}
