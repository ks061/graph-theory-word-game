//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Wordgame!");
        System.out.print("Filename: ");
        Scanner scanIn = new Scanner(System.in);
        String filename = scanIn.nextLine();

        File file = new File(filename);
	if (!file.exists()) {
		System.out.println("Error: File " + filename + " could not be found.");
		System.exit(1);
	}

	ArrayList<String> words = readWords(filename);
        Graph graph = new Graph(words);

        String doReplay = "yes";
        while(doReplay.equalsIgnoreCase("yes") || doReplay.equalsIgnoreCase("y")){
            System.out.print("Enter a five letter word: ");
            String word = scanIn.nextLine();
            System.out.println("The neighbors of " + word + " are: ");
            graph.displayNeighbors(word);
            System.out.print("\nEnter another word? (yes / y or n / no): ");
            doReplay = scanIn.nextLine();
        }


    }
}
