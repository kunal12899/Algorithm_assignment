

// Ver 1.0:  Wed, Feb 3.  Initial description.
// Ver 1.1:  Thu, Feb 11.  Simplified Index interface

import java.util.Comparator;
import java.util.NoSuchElementException;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
    /** Build a priority queue with a given array q */
    private int[] keys;
    IndexedHeap(T[] q, Comparator<T> comp) {
	super(q, comp);
        
    }

    /** Create an empty priority queue of given maximum size */
    IndexedHeap(int n, Comparator<T> comp) {
	super(n, comp);
        keys = new int[n];
    }

    /** restore heap order property after the priority of x has decreased */    
    //addded here
    public void insert(T x, int key) {
	add(x, key);
    }

    public T deleteMin() {
	return remove();
    }

    public T min() { 
	return peek();
    }

    public void add(T x, int key) { 
        size++;
        pq[size]=x;
        
        //index updated
        x.putIndex(size);
        percolateUp(size);
    }

    public T remove() { 
	T min=pq[1];
        pq[1]=pq[size--];
        
        //made this key null
        min.putIndex(0);
        percolateDown(1); 
        return min;
    }

    public T peek() { 
        return pq[1];
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { 
        pq[0]=pq[i];
        while(c.compare(pq[i/2],pq[0])>0 && i!=0){
            pq[i]=pq[i/2];
            changeKey(pq[i], i);
            i=i/2;
        }
       pq[i]=pq[0];
        changeKey(pq[i], i);
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) {
        int schild;
        T x=pq[i];
        while(2*i<=size){
            if(2*i==size) {// one child 
                if(c.compare(x,pq[size])>0){
                    pq[i]=pq[size];
                    changeKey(pq[i], i);
                    i=size;
                }
                else break;
            }
            else { //two children
                if(c.compare(pq[2*i],pq[2*i+1])<=0)
                        schild=2*i;
                else
                        schild=2*i+1;
                
                if(c.compare(x,pq[schild])>0){
                    pq[i]=pq[schild];
                    changeKey(pq[i], i);
                    i=schild;
                        }
                else break;
                }
        }
        pq[i]=x;
       pq[i].putIndex(i);
    }


    public Object[] resize() {
        Object[] newPq= new Object[pq.length+(pq.length/2)];
        for(int i=1;i<pq.length;i++){
            newPq[i]=pq[i];
            pq[i]=null;
        }
        return newPq;
    }
    
    public int keyOf(T i) {
        return i.getIndex();
    }
    
    public void changeKey(T i, int key) {
        i.putIndex(key);
    }
    
    public int minKey() {
        if (size == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1].getIndex();
    }
}
