//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.IOException;

public class Graph {
    final int STRLEN = 5;
    int numVertices;
    Vertex[] adjListArr;

    public void setAttributes(int numVertex) {
        this.numVertices = numVertex;
        this.adjListArr = new Vertex[numVertex];
    }


    public void fillVertex(String word, int index){
        this.adjListArr[index] = new Vertex(word, index);
    }

    public void generateEdges() {
        for(int i = 0; i < this.adjListArr.length; ++i) {
            for(int j = i; j < this.adjListArr.length; ++j) {
                String word1 = this.adjListArr[i].getData();
                String word2 = this.adjListArr[j].getData();

                int numOff = 0;
                for(int ci = 0; ci < 5; ++ci) {
                    if (word1.charAt(ci) != word2.charAt(ci)) {
                        ++numOff;
                    }
                }

                if (numOff == 1) {
                    //System.out.println("Edge(1) between " + this.adjListArr[i].getData() + ", and " + this.adjListArr[j].getData());
                    this.addEdge(this.adjListArr[i], this.adjListArr[j], 1);
                } else if (numOff == 2) {
                    //System.out.println("Edge(2) between " + this.adjListArr[i].getData() + ", and " + this.adjListArr[j].getData());
                    this.addEdge(this.adjListArr[i], this.adjListArr[j], 5);
                }
            }
        }

    }

    public void addEdge(Vertex v1, Vertex v2, int weight) {
        Edge edge = new Edge(v1, v2, weight);
        this.adjListArr[v1.getIndex()].addEdge(edge);
        this.adjListArr[v2.getIndex()].addEdge(edge);
    }

    public void displayNeighbors(String word) {
        Vertex v = this.getVertex(word);
        v.printNeighbors();
    }

    public Vertex getVertex(String word) {
        word = word.toUpperCase();
        int low = 0;
        int high = this.adjListArr.length - 1;
        int count = 0;
        while(low <= high && count < 10000) {
            ++count;
            int mid = (low + high) / 2;
            //System.out.println("low = " + low + ", mid = " + mid + ", high = " + high + ", midword = " + this.adjListArr[mid].getData());
            if (this.adjListArr[mid].getData().compareTo(word) < 0) {
                low = mid + 1;
            } else if (this.adjListArr[mid].getData().compareTo(word) > 0) {
                high = mid - 1;
            } else if (this.adjListArr[mid].getData().compareTo(word) == 0) {
                return this.adjListArr[mid];
            }
        }

        System.out.println("ERROR: Failed to find Vertex in adjacency list, returning -1");
        return null;
    }
}
