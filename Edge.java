//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Iterator;
import java.util.HashSet;

public class Edge {
    private HashSet<Vertex> vertexEndpoints;
    private int weight;

    public Edge(Vertex v1, Vertex v2, int w) {
        vertexEndpoints = new HashSet<Vertex>();
	vertexEndpoints.add(v1);
	vertexEndpoints.add(v2);
        this.weight = w;
    }

    public HashSet<Vertex> getVertexEndpoints() {
        return this.getVertexEndpoints();
    }

    public Vertex getOtherVertex(Vertex v) {
	Iterator iterator = this.vertexEndpoints.iterator();
	
	Vertex currentVertex;
	while(iterator.hasNext()) {
	    currentVertex = (Vertex)iterator.next();
	    if (currentVertex != v) {
		return currentVertex;
	    }
	}
	
	return null;
    }

    public int getWeight() {
	return this.weight;
    }
}
