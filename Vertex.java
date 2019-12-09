/**
 * This file holds the Vertex class.
 *
 * @author Kartikeya Sharma
 * @author Nick Passantino
 */

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Vertex represents a vertex in an undirected graph.
 */
public class Vertex extends HeapElt {
    private String word;
    private int index;
    private LinkedList<Edge> adjList;
    private static final int wordsPerLineInOutput = 6;
    private Vertex predecessor;

    private int pathWeight;
    /**
     * Constructor
     *
     * @param word word held by vertex
     * @param index index that this vertex lies within the
     *              graph that holds all of the
     *              vertices of the graph
     */
    public Vertex(String word, int index) {
        this.word = word.toUpperCase();
	    this.index = index;
        this.adjList = new LinkedList<Edge>();
        this.record = Integer.MAX_VALUE;
    }

    /**
     * Prints all of the neighbors adjacent to this vertex.
     */
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
            System.out.print(currentEdge.getAdjacentVertex().getWord() + " (" + currentEdge.getWeight() + ")  ");
            counter++;
	    }
    }

    /**
     * Adds an edge that connects this vertex to a particular vertex
     * adjacent to this vertex.
     *
     * @param v edge connecting this vertex to an adjacent vertex
     */
    public void addEdge(Edge v) {
        this.adjList.add(v);
    }

    /**
     * Gets the word held by this vertex
     *
     * @return word held by this vertex
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Gets the index of this vertex in the list of vertices
     * held by the graph containing this vertex
     *
     * @return index of this vertex within the list of vertices
     * held by the graph containing this vertex
     */
    public int getIndex() {
        return this.index;
    }

    public void setPredecessor(Vertex p){
        this.predecessor = p;
    }

    public LinkedList<Edge> getAdjList(){
        return this.adjList;
    }

    public int getPathWeight(){
        return this.pathWeight;
    }

    public void setPathWeight(int w) {
        this.pathWeight = w;
    }

    public Vertex getPredecessor(){
        return this.predecessor;
    }

}
