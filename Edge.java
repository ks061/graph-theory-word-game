/**
 * This file contains the Edge class.
 *
 * @author Kartikeya Sharma
 * @author Nick Passantino
 */


/**
 * Represents an edge in an undirected graph.
 */
public class Edge {
    // Vertex adjacent to the vertex holding this edge
    private Vertex adjacentVertex;
    private int weight;

    /**
     * Constructor
     *
     * @param adjacentVertex vertex adjacent to the vertex holding this edge
     * @param weight weight of the edge
     */
    public Edge(Vertex adjacentVertex, int weight) {
        this.adjacentVertex = adjacentVertex;
        this.weight = weight;
    }

    /**
     * Gets the vertex adjacent to the vertex holding this edge
     *
     * @return vertex adjacent to the vertex holding this edge
     */
    public Vertex getAdjacentVertex() {
    	return this.adjacentVertex;
    }

    /**
     * Gets the weight of this edge
     *
     * @return weight of this edge
     */
    public int getWeight() {
    	return this.weight;
    }
}
