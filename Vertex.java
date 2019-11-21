//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Iterator;
import java.util.LinkedList;

public class Vertex extends HeapElt {
    private String data;
    private int index;
    private LinkedList<Edge> adjList;

    public Vertex(String d, int index) {
        this.index = index;
        this.data = d.toUpperCase();
        this.adjList = new LinkedList();
    }

    public void printNeighbors() {
        Iterator var1 = this.adjList.iterator();
        int counter = 0;
        while(var1.hasNext()) {
            counter++;
            Edge curr = (Edge)var1.next();
            System.out.print(curr.getDestination().getData() + "(" + curr.getWeight() + ") ");
            if(counter == 5){
                System.out.println();
                counter = 0;
            }
        }

    }

    public void addEdge(Edge v) {
        this.adjList.add(v);
    }

    public String getData() {
        return this.data;
    }

    public int getIndex() {
        return this.index;
    }
}
