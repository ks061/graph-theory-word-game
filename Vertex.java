//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Iterator;
import java.util.LinkedList;

public class Vertex extends HeapElt {
    private String word;
    private int index;
    private LinkedList<Edge> adjList;
    private static final int wordsPerLineInOutput = 6;	

    public Vertex(String word, int index) {
        this.word = word.toUpperCase();
	this.index = index;
        this.adjList = new LinkedList<Edge>();
    }

    public void printNeighbors() {
        Iterator iterator = this.adjList.iterator();
	
	int counter = 0;
	Edge currentEdge;
        while(iterator.hasNext()) {
	    if (counter == wordsPerLineInOutput) {
		System.out.println();
		counter = 0;
	    }
	    currentEdge = (Edge)iterator.next();
            System.out.print(currentEdge.getOtherVertex(this).getWord() + " (" + currentEdge.getWeight() + ")  ");
            counter++;
	}

    }

    public void addEdge(Edge v) {
        this.adjList.add(v);
    }

    public String getWord() {
        return this.word;
    }

    public int getIndex() {
        return this.index;
    }
}
