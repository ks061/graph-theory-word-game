//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.PrintStream;

public class Heap {
    private HeapElt[] array = new HeapElt[4];
    int heapsize = 0;
    int arraysize = 4;

    public Heap() {
    }

    private void exchange(int pos1, int pos2) {
        HeapElt heapElt2 = this.array[pos2];
        this.array[pos2] = this.array[pos1];
        this.array[pos1] = heapElt2;
        this.array[pos1].setHandle(pos1);
        this.array[pos2].setHandle(pos2);
    }

    private void doubleHeap() {
        HeapElt[] newArray = new HeapElt[this.arraysize * 2];

        for(int i = 0; i < this.arraysize; ++i) {
            newArray[i] = this.array[i];
        }

        this.array = newArray;
        this.arraysize *= 2;
    }

    public void heapifyUp(int pos) {
        if (pos > 1 && this.array[pos].getRecord().compareTo(this.array[pos / 2].getRecord()) < 0) {
            this.exchange(pos / 2, pos);
            this.heapifyUp(pos / 2);
        }

    }

    public void heapifyDown(int pos) {
        int smallest_pos = pos;
        HeapElt rightChildHeapElt;
        if (2 * pos <= this.heapsize) {
            rightChildHeapElt = this.array[2 * pos];
            if (rightChildHeapElt.getRecord().compareTo(this.array[pos].getRecord()) < 0) {
                smallest_pos = rightChildHeapElt.getHandle();
            }
        }

        if (2 * pos + 1 <= this.heapsize) {
            rightChildHeapElt = this.array[2 * pos + 1];
            if (rightChildHeapElt.getRecord().compareTo(this.array[smallest_pos].getRecord()) < 0) {
                smallest_pos = rightChildHeapElt.getHandle();
            }
        }

        if (smallest_pos != pos) {
            this.exchange(smallest_pos, pos);
            this.heapifyDown(smallest_pos);
        }

    }

    public void insert(HeapElt inElt) {
        if (this.heapsize + 1 >= this.arraysize) {
            this.doubleHeap();
        }

        ++this.heapsize;
        this.array[this.heapsize] = inElt;
        inElt.setHandle(this.heapsize);
        this.heapifyUp(this.heapsize);
    }

    public HeapElt removeMin() {
        HeapElt minElt = this.array[1];
        this.exchange(1, this.heapsize);
        --this.heapsize;
        this.heapifyDown(1);
        return minElt;
    }

    public int getHeapsize() {
        return this.heapsize;
    }

    public void printHeap() {
        System.out.println("Started printing heap...");

        for(int i = 1; i < this.heapsize; ++i) {
            PrintStream var10000 = System.out;
            int var10001 = this.array[i].getHandle();
            var10000.println("Handle: " + var10001 + " Key: " + this.array[i].getRecord());
        }

        System.out.println("Finished printing heap...");
    }
}
