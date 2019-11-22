//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Iterator;
import java.util.HashSet;

public class Edge {
    private Vertex source;
    private Vertex adjacentVertex;
    private int weight;

    public Edge(Vertex source, Vertex adjacentVertex, int weight) {
        this.source = source;
	this.adjacentVertex = adjacentVertex;
	this.weight = weight;
    }

    public Vertex getAdjacentVertex() {
	return this.adjacentVertex;
    }

    public int getWeight() {
	return this.weight;
    }
}
