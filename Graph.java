/**
 * This file contains the Graph class.
 *
 * @author Kartikeya Sharma
 * @author Nick Passantino
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Graph represents an undirected graph
 */
public class Graph {
    final int STRLEN = 5;
    private Vertex[] vertices;

    /**
     * Constructor that initializes the vertices and edges
     * such that vertices contain five-letter words and edges
     * connect vertices that have words that are different by
     * either one or two characters
     *
     * @param words list of words to be inserted into the graph
     */
    public Graph(ArrayList<String> words) {
        this.vertices = new Vertex[words.size()];
    	this.createVertices(words);
	this.generateEdges();
    }

    /**
     * Initializes each vertex to hold a word and its respective
     * index in the array of vertices held by this graph
     * 
     * @param words list of words
     */
    private void createVertices(ArrayList<String> words) {
	for(int i = 0; i < words.size(); i++) {
	    this.vertices[i] = new Vertex(words.get(i), i);
	}
    }

    /**
     * Generates edges between vertices if said vertices have words
     * that differ by one or two characters, in which case the
     * edge weight is 1 or 5, respectively. Edges are added to both
     * vertices.
     */
    public void generateEdges() {
        for(int i = 0; i < this.vertices.length; i++) {
            for(int j = i+1; j < this.vertices.length; j++) {
                String word1 = this.vertices[i].getWord();
                String word2 = this.vertices[j].getWord();
                
		int numCharactersDifferent = 0;
                for(int charIndex = 0; charIndex < STRLEN; charIndex++) {
                    if (word1.charAt(charIndex) != word2.charAt(charIndex))
                        numCharactersDifferent++;
                }

                if (numCharactersDifferent == 1)
                    this.addEdge(this.vertices[i], this.vertices[j], 1);
                else if (numCharactersDifferent == 2)
                    this.addEdge(this.vertices[i], this.vertices[j], 5);
            }
        }
    }

    /**
     * Creates an edge with the inputted weight connecting v1 to v2 and adds it to v1.
     * Creates an edge with the inputted weight connecting v2 to v1 and adds it to v2.
     *
     * @param v1 a vertex being connected to v2 via a weighted edge
     * @param v2 a vertex being connected to v1 via a weighted edge
     * @param weight weight of edge
     */  
    public void addEdge(Vertex v1, Vertex v2, int weight) {
        Edge edge1 = new Edge(v1, v2, weight);
	Edge edge2 = new Edge(v2, v1, weight);
        this.vertices[v1.getIndex()].addEdge(edge1);
        this.vertices[v2.getIndex()].addEdge(edge2);
    }

    /**
     * Displays the words contained in vertices adjacent to the vertex containing the inputted word and related edge weight.
     *
     * @param word displays the neighbors of this vertex
     */
    public void displayNeighbors(String word) {
        Vertex v = this.getVertex(word);
        if (v != null) v.printNeighbors();
    }

    /**
     * Gets the Vertex object containing the inputted word using a
     * binary search algorithm.
     *
     * @param word word corresponding to a vertex
     *
     * @return Vertex object containing the inputted word
     */
    public Vertex getVertex(String word) {
	word = word.toUpperCase();
        int lowIndex = 0;
        int highIndex = this.vertices.length - 1;
	int midIndex = (lowIndex + highIndex) / 2;
	int verticesSearched = 0;

        while(lowIndex <= highIndex && verticesSearched < this.vertices.length) {
	    verticesSearched++;
            if (this.vertices[midIndex].getWord().compareTo(word) < 0)
                lowIndex = midIndex + 1;
            else if (this.vertices[midIndex].getWord().compareTo(word) > 0)
                highIndex = midIndex - 1;
            else if (this.vertices[midIndex].getWord().compareTo(word) == 0)
                return this.vertices[midIndex];

            midIndex = (lowIndex + highIndex) / 2;
        }

        System.out.println("ERROR: Failed to find word.");
        return null;
    }
}
