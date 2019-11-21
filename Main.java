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

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Wordgame!");
        System.out.print("Filename: ");
        Scanner scanIn = new Scanner(System.in);
        String filename = scanIn.nextLine();
        int numLines = -1;
	    int numWords = 0;
	
	    FileReader fr = new FileReader(filename);
	    Graph graph = new Graph();

        try {
            BufferedReader br1 = new BufferedReader(fr);
            String line;
            while((line = br1.readLine()) != null) {
                if(!line.trim().isEmpty()) {
                    numWords += line.split(" ").length;
                }
            }
            br1.close();


            FileReader fr1 = new FileReader(filename);
            BufferedReader br2 = new BufferedReader(fr1);
            graph.setAttributes(numWords);

            int index = 0;
            while((line = br2.readLine())!= null){
                String words[] = line.split(" ");
                for(int i = 0; i < words.length; i++){
                    if(!line.trim().isEmpty()) {
                        graph.fillVertex(words[i], index);
                        index++;
                    }
                }
            }
            br2.close();

        } catch (FileNotFoundException var) {
            System.out.println("Error: File " + filename + " could not be found.");
            System.exit(1);
        }

        graph.generateEdges();
        String replay = "YES";
        while(replay.equals("YES") || replay.equals("Y")){
            System.out.println("Enter a five letter word: ");
            String word = scanIn.nextLine();
            System.out.println("The neighbors of " + word.toUpperCase() + " are: ");
            graph.displayNeighbors(word);
            System.out.println("\nEnter another word? (yes / y or n / no)");
            replay = scanIn.nextLine().toUpperCase();
        }


    }
}
