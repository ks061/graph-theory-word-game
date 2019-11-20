/**
 * Wordgame Project
 *
 * @author Kartikeya Sharma
 * @author Nick Passantino
 */

import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// welcome to the game
		System.out.println("Welcome to the Wordgame!");
		
		// input filename and relevant error handling
		System.out.print("Filename: ");
		Scanner scanIn = new Scanner(System.in);
		String filename = scanIn.nextLine();
		File file = new File(filename);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("Error: File " + filename + " could not be found.");
			System.exit(1);
		}
	}
}
