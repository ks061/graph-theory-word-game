//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {
    final int STRLEN = 5;
    private Vertex[] vertices;

    public Graph(ArrayList<String> words) {
        this.vertices = new Vertex[words.size()];
    	this.createVertices(words);
	this.generateEdges();
    }

    private void createVertices(ArrayList<String> words) {
	for(int i = 0; i < words.size(); i++) {
	    this.vertices[i] = new Vertex(words.get(i), i);
	}
    }

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

    public void addEdge(Vertex v1, Vertex v2, int weight) {
        Edge edge1 = new Edge(v1, v2, weight);
	Edge edge2 = new Edge(v2, v1, weight);
        this.vertices[v1.getIndex()].addEdge(edge1);
        this.vertices[v2.getIndex()].addEdge(edge2);
    }

    public void displayNeighbors(String word) {
        Vertex v = this.getVertex(word);
        if (v != null) v.printNeighbors();
    }

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
