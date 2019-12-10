/**
 * This file contains the Graph class.
 *
 * @author Kartikeya Sharma
 * @author Nick Passantino
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
     * Creates an edge with the inputed weight connecting v1 to v2 and adds it to v1.
     * Creates an edge with the inputed weight connecting v2 to v1 and adds it to v2.
     *
     * @param v1 a vertex being connected to v2 via a weighted edge
     * @param v2 a vertex being connected to v1 via a weighted edge
     * @param weight weight of edge
     */  
    public void addEdge(Vertex v1, Vertex v2, int weight) {
        Edge edge1 = new Edge(v2, weight);
        Edge edge2 = new Edge(v1, weight);
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
        if (v != null) {
            v.printNeighbors();
        }
    }

    
    /**
     * Checks whether word1 and word2 exist in the graph and returns true
     * only if both exist in the graph. A helpful message is printed if one
     * or both words are not found.
     * 
     * @param word1 word that will be checked for existence in this graph
     * @param word2 another word that will be checked for existence in this graph
     * @return true if both words exist in this graph
     */
    public boolean checkValidity(String word1, String word2) {
        Vertex v1 = this.getVertex(word1);
        Vertex v2 = this.getVertex(word2);
        
        if (v1 == null && v2 == null) {
        	System.out.println("\nSorry, neither " + word1 + " nor " + word2 + " is not in the list.");
        } else if (v1 == null && v2 != null) {
        	System.out.println("\nSorry, " + word1 + " is not in the list.");
        } else if (v1 != null && v2 == null) {
        	System.out.println("\nSorry, " + word2 + " is not in the list.");
        }
        else {
            return true;
        } 
        
        return false;
    }


    /**
     * Prints the total of the edge weights of the shortest path to get from 
     * word1 to word2 and prints the associated path with relevant edge weights.
     * 
     * @param v vertex that is being traced back from (associated with word2)
     * @param word1 word being traveled from
     * @param word2 word being traveled to
     */
    public void printVertPath(Vertex v, String word1, String word2){
    	ArrayList<Vertex> vertexPath = new ArrayList<>();
        int totalCost = 0;
        
        Vertex currentVertex = v;
        while (currentVertex.getPredecessor() != null) {
            Edge e = currentVertex.findEdge(currentVertex.getPredecessor());
            currentVertex.setPathWeight(e.getWeight());
            vertexPath.add(currentVertex);
            totalCost += e.getWeight();
            currentVertex = currentVertex.getPredecessor();
        }

        // handling first element (no cost)
        currentVertex.setPathWeight(0);
        vertexPath.add(currentVertex);

        Collections.reverse(vertexPath);
        
        System.out.println("\nThe best score for " + word1 + " to " + word2 + " is " + totalCost + " points.");

        for (int i = 0; i < vertexPath.size(); i++) {
        	if (i%6 == 0) System.out.print("\n	");
            System.out.print(vertexPath.get(i).getWord() + " (" + vertexPath.get(i).getPathWeight() + ")   ");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Gets the Vertex object containing the inputed word using a
     * binary search algorithm.
     *
     * @param word word corresponding to a vertex
     *
     * @return Vertex object containing the inputed word
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

        return null;
    }

    @SuppressWarnings("unchecked")
    /**
     * Dijkstra's algorithm that throws vertices into the priority queue,
     * implemented as a minimum heap, as they are reached during the process
     * of building the minimum spanning tree.
     * 
     * @param startingVertex vertex that is started from
     * @param endingVertex vertex that is to be reached via dijkstra's algorithm
     * 
     * @return vertex ending vertex in dijkstra's algorithm; null if ending vertex not found
     * in dijkstra's algorithm
     */
	public Vertex dijkstrasAlgo(Vertex startingVertex, Vertex endingVertex){
        Heap priorityQueue = new Heap();

        // initialization of each vertex in graph
        for(int i = 0; i < this.vertices.length; i++){
            this.vertices[i].setRecord(Integer.MAX_VALUE); // start by initializing all the 
            											   // vertices as being associated
            											   // with an edge weight (priority) 
            											   // of infinity
            this.vertices[i].setPredecessor(null); // because no predecessor known yet
            this.vertices[i].setHandle(0); // to be inserted at root
        }

        startingVertex.setRecord(0); // total edge weights leading to vertex is 0
        startingVertex.setPathWeight(0); // edge weight connecting this vertex to predecessor (none) is 0

        priorityQueue.insert(startingVertex); // insert starting vertex into the priority queue

        while (priorityQueue.getHeapSize() > 0) { // while the priority queue has vertices
            Vertex u = (Vertex) priorityQueue.removeMin(); // examine the highest priority item
            if (u.getWord().equals(endingVertex.getWord())) {
                return u; // ending vertex has been reached and will be returned
            }

            // iterate over all adjacent vertexes via the edges of the current vertex
            Iterator<Edge> iterator = u.getAdjList().iterator();
            Edge currentEdge;
            while (iterator.hasNext()) {
                currentEdge = (Edge)iterator.next();
                Vertex v = currentEdge.getAdjacentVertex();

                // see if the vertex belongs in the minimum spanning tree
                int comp = (int) u.getRecord() + currentEdge.getWeight();
                if (v.getRecord().compareTo(comp) > 0 ) { // if the sum of the edges of the best currently known
                										  // path to v from the start is greater than the best
                										  // path going from the start to u and then this
                										  // additional edge that is being explored
                    v.setRecord((int) u.getRecord() + currentEdge.getWeight()); // replace the best currently
                    															// known path length
                    v.setPredecessor(u); // link u to be v's predecessor in this shorter path

                    if (v.getHandle() == 0) {
                        priorityQueue.insert(v); // insert if not already in priority queue
                    } else {
                        priorityQueue.heapifyUp(v.getHandle()); // heapifyup if already in priority queue
                        										// because priority has now presumably been lowered
                    }
                }
            }
        }
        
        return null;
    }
}
