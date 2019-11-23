/**
 * This file contains the WordGame class.
 *
 * @author Kartikeya Sharma
 * @author Nick Passantino
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
	int wordIndex = 0;
	
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
     * The game takes in a filename from its first command line argument.
     * It checks if the file exists; if it does not, an error message is printed,
     * and the program is exited. The program then imports dictionary words from a file.
     * It prompts the user to enter a five letter word. After, the neighboring words in the
     * graphs and corresponding edge weights to connect over to the vertices containing those
     * neighboring words is printed. The user is then asked if they would like to enter
     * in another word, at which point y and yes are accepted as yes and n and no are accepted
     * as no. If any other input is entered, the user is reprompted whether they would like
     * to enter in another word.
     *
     * @param args command line arguments
     * @exception IOException if issues with reading in dictionary words from file with inputted filename
     */
    public static void main(String[] args) throws IOException {
	System.out.println("Welcome to WordGame!");
	String filename = args[0];

        File file = new File(filename);
	if (!file.exists()) {
		System.out.println("Error: File " + filename + " could not be found.");
		System.exit(1);
	}

	ArrayList<String> words = readWords(filename);
        Graph graph = new Graph(words);

	Scanner scanIn = new Scanner(System.in);
        String doReplay = "yes";
	String word;
        while (doReplay.equalsIgnoreCase("yes") || doReplay.equalsIgnoreCase("y")) {
            System.out.println("Enter a five letter word: ");
            word = scanIn.nextLine().toUpperCase();
            System.out.println("The neighbors of " + word + " are: ");
            graph.displayNeighbors(word);
	    do  {
            	System.out.println("\nEnter another word? (yes / y or n / no): ");
            	doReplay = scanIn.nextLine();
            } while (!doReplay.equalsIgnoreCase("yes") && !doReplay.equalsIgnoreCase("y") && !doReplay.equalsIgnoreCase("no") && !doReplay.equalsIgnoreCase("n"));
	}

    }
}
