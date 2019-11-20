//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Wordgame!");
        System.out.print("Filename: ");
        Scanner scanIn = new Scanner(System.in);
        String filename = scanIn.nextLine();
        int numLines = -1;
	int numWords = 0;
	
	FileReader fr = new FileReader(filename);
        try {
            BufferedReader br1 = new BufferedReader(fr);

		
            for(numLines = 0; br1.readLine() != null; ++numLines) {
	    	numWords += br1.readLine().split(" ").length;            	
	    }

            br1.close();
        } catch (FileNotFoundException var) {
            System.out.println("Error: File " + filename + " could not be found.");
            System.exit(1);
        }

        BufferedReader br1 = new BufferedReader(new FileReader(filename));
        Graph graph = new Graph(numWords);
	System.out.println("numWords: " + numWords);
        graph.fillVertices(br1);
        graph.generateEdges();

        String replay = "YES";
        while(replay == "YES" || replay == "Y"){
            replay = scanIn.nextLine().toUpperCase();
            System.out.println("Enter a five letter word: ");
            String word = scanIn.nextLine();
            System.out.println("The neighbors of " + word + "are: ");
            graph.displayNeighbors(word);
            System.out.println("\nEnter another word? (yes / y or n / no");
        }


    }
}
