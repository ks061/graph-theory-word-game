//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Edge {
    private Vertex source;
    private Vertex destination;
    private int weight;

    public Edge(Vertex s, Vertex d, int w) {
        this.source = s;
        this.destination = d;
        this.weight = w;
    }

    public Vertex getDestination() {
        return this.destination;
    }

    public int getWeight(){
        return this.weight;
    }
}
