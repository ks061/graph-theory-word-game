/**
 * This file contains the WordGame class.
 *
 * @author Kartikeya Sharma
 * @author Nick Passantino
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * WordGame represents the main class for running the word game.
 */
public class WordGame {
    /**
     * Inputs dictionary words from a file
     *
     * @param filename filename of the file containing the dictionary of words to be imported
     *
     * @return list of imported dictionary words
     */
    private static ArrayList<String> readWords(String filename) throws IOException {
    	String line;	
		String wordsInLine[];
		ArrayList<String> words = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		while ((line = br.readLine()) != null) {
	    	wordsInLine = line.split(" ");
	    	for(int i = 0; i < wordsInLine.length; i++) {
	    		if (wordsInLine[i].equals("")) continue;
	    		words.add(wordsInLine[i]);
	    	}
		}
		br.close();

		return words;
    }

    /**
     * Runs the WordGame
     * 
     * The game takes in a filename from its first command line argument.
     * It checks if the file exists; if it does not, an error message is printed,
     * and the program is exited. The program then imports dictionary words from a file.
     * It prompts the user to enter two five letter words. After, the shortest path
     * from one word to another is printed along with the total of the edge weights along
     * that path. The user is then asked if they would like to enter
     * in another word, at which point y and yes are accepted as yes and n and no are accepted
     * as no. If any other input is entered, the user is re-prompted whether they would like
     * to enter in another word.
     *
     * @param args command line arguments
     * @exception IOException if issues with reading in dictionary words from file with inputted filename
     */
    public static void main(String[] args) throws IOException {
    	System.out.println("\nWelcome to WordGame!\n");
    	
    	if (args.length != 1) {
    		System.out.println("\nUsage: java WordGame <dictionary filename>");
    		System.exit(1);
    	}
    	
    	String filename = args[0];
    	File file = new File(filename);
    	if (!file.exists()) {
    		System.out.println("\nError: File " + filename + " could not be found.");
    		System.exit(1);
    	}

    	ArrayList<String> words = readWords(filename);
    	Graph graph = new Graph(words);

    	Scanner scanIn = new Scanner(System.in);
    	String doReplay = "yes";
    	String word1, word2;

    	while (doReplay.equalsIgnoreCase("yes") || doReplay.equalsIgnoreCase("y")) {
    		System.out.print("Enter the first five-letter word: ");
    		word1 = scanIn.nextLine().toUpperCase();
    		System.out.println();
    		System.out.print("Enter the second five-letter word: ");
    		word2 = scanIn.nextLine().toUpperCase();

    		if (graph.checkValidity(word1, word2)){
    			Vertex startingVertex = graph.getVertex(word1);
    			Vertex endingVertex = graph.getVertex(word2);

    			graph.printVertPath(graph.dijkstrasAlgo(startingVertex, endingVertex), word1, word2);
    		}
    		
    		System.out.print("Do you want to try another word? Enter yes or no: ");
    		doReplay = scanIn.nextLine();
    		while (!doReplay.equalsIgnoreCase("yes") && !doReplay.equalsIgnoreCase("y") && !doReplay.equalsIgnoreCase("no") && !doReplay.equalsIgnoreCase("n")) {
    			System.out.print("Please enter yes or no: ");
    			doReplay = scanIn.nextLine();
    		}
    	}
    	
    	scanIn.close();
    }
}
