/**
 * An implementation of a minimum heap with handles
 */

public class Heap {

    private HeapElt[] array;
    int heapsize;
    int arraysize;

    /*
      The constructor has been set up with an initial array of size 4
      so that your doubleHeap() method will be tested.  Don't change
      this!
    */
    public Heap() {
	array = new HeapElt[4];
	heapsize = 0;
	arraysize = 4;
    }



    /*
      Exchanges that values at positions pos1 and pos2 in the heap array.
      Handles must be exchanged correctly as well.

      Running time = O(1)
    */
    private void exchange(int pos1, int pos2) {
        // store heap element at pos 2
        HeapElt heapElt2 = array[pos2];
        
        // swap elements
        array[pos2] = array[pos1];
        array[pos1] = heapElt2;

        // update handles
        array[pos1].setHandle(pos1);
        array[pos2].setHandle(pos2);
    }



    /*
      Doubles the size of the array.  A new array is created, the elements in
      the heap are copied to the new array, and the array data member is set
      to the new array.  Data member arraysize is set to the size of the
      new array.

      Running time = O(n)
    */
    private void doubleHeap() {
        // new array created with double the size
        HeapElt[] newArray = new HeapElt[arraysize * 2];

        // elements in heap copied to new array
        for (int i = 0; i < arraysize; i++) {
            newArray[i] = array[i];
        }

        // array points to the newly created array
        array = newArray; 
        // arraysize is set to the size of the new array
        arraysize *= 2;
    }



    /*
      Fixes the heap if the value at position pos may be smaller than its
      parent.  Using exchange() to swap elements will simplify your
      implementation.  Heap elements contain records that are Comparable;
      you will need to figure out what to test and how to test it.

      Running time = O(lg(n))
    */
    public void heapifyUp(int pos) {
        // heapify up only if not looking at minimum element 
        // and if current node is less than the parent node
        if ((pos > 1) && (array[pos].getRecord().compareTo(array[pos/2].getRecord()) < 0)) {
            exchange(pos/2, pos);
            heapifyUp(pos/2);
        }
    }

    /*
      Fixes the heap if the value at position pos may be bigger than one of
      its children.  Using exchange() to swap elements will simplify your
      implementation.  Heap elements contain records that are Comparable;
      you will need to figure out what to test and how to test it.

      Running time = O(lg(n))
    */
    public void heapifyDown(int pos) {
        int smallest_pos = pos;
        // set smallest_pos to the position of the left child if it is the smallest node examined thus far
        if (2*pos <= heapsize) {
            HeapElt leftChildHeapElt = array[2*pos];
            if (leftChildHeapElt.getRecord().compareTo(array[smallest_pos].getRecord()) < 0) {
                smallest_pos = leftChildHeapElt.getHandle();
            }
        }
        // set smallest_pos to the position of the right child if it is the smallest node examined thus far
	if (2*pos + 1 <= heapsize) {
            HeapElt rightChildHeapElt = array[2*pos+1];
            if (rightChildHeapElt.getRecord().compareTo(array[smallest_pos].getRecord()) < 0) {
                smallest_pos = rightChildHeapElt.getHandle();
            }
        }
        // if the position of the smallest node (smallest_pos) is not the position of the node passed in,
        // swap the node passed in with the node found to be the smallest
        if (smallest_pos != pos) {
            exchange(smallest_pos, pos);
            heapifyDown(smallest_pos);
        }
    }



    /*
      Insert inElt into the heap.  Before doing so, make sure that there is
      an open spot in the array for doing so.  If you need more space, call
      doubleHeap() before doing the insertion.

      Running time = O(n) when doubling the size of the array; otherwise, O(lg(n)) 
      (NOTE that there are a couple of different cases here!)
    */
    public void insert(HeapElt inElt) {
        // only double the heap if there are insufficient spaces in the array
        // to add another element (note that elements are stored starting at index 1)
	if (heapsize + 1 >= arraysize) {
            doubleHeap();
        }

        // insert element into heap
        heapsize++;
        array[heapsize] = inElt;
        inElt.setHandle(heapsize);
        
        // sort inserted element relative to tree
        heapifyUp(heapsize);    
    }


    /*
      Remove the minimum element from the heap and return it.  Restore
      the heap to heap order.  Assumes heap is not empty, and will
      cause an exception if the heap is empty.

      Running time = O(lg(n))
    */
    public HeapElt removeMin() {
	// WARNING: Will fail with empty heap!
        HeapElt minElt = array[1];
       
        // swap root of tree with last element in heap
        exchange(1, heapsize);
        heapsize--;
        // sort formerly last element in the heap relative to tree
        heapifyDown(1);
        
        return minElt;
    }



    /*
      Return the number of elements in the heap..

      Running time = O(1)
    */
    public int getHeapsize() {

        return this.heapsize;
    
    }

 

   /*
      Print out the heap for debugging purposes.  It is recommended to 
      print both the key from the record and the handle.

      Running time = O(n)
    */
    public void printHeap() {

        System.out.println("Started printing heap...");
	for (int i = 1; i < this.heapsize; i++) {
            // prints handle and key of each element in the array of the minimum heap
            System.out.println("Handle: " + array[i].getHandle() + " Key: " + array[i].getRecord());
        }
        System.out.println("Finished printing heap...");

     }

}
