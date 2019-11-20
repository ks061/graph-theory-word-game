//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class HeapElt {
    protected Comparable record;
    protected int handle = 0;

    public HeapElt() {
    }

    public void setRecord(Comparable inRec) {
        this.record = inRec;
    }

    public Comparable getRecord() {
        return this.record;
    }

    public void setHandle(int inHandle) {
        this.handle = inHandle;
    }

    public int getHandle() {
        return this.handle;
    }
}
