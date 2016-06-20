/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author  kunal krishna
 */
// Ver 1.0:  Wec, Feb 3.  Initial description.
import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
    T[] pq;
    int size;
    Comparator<T> c;
    
    /** Build a priority queue with a given array q */
    BinaryHeap(T[] q, Comparator<T> comp) {
	pq = q;
	c = comp;
        size=0;
	//buildHeap();
    }

    /** Create an empty priority queue of given maximum size */
    BinaryHeap(int n, Comparator<T> comp) { 
        //T[] pq;
        //size=n;
    }


    public void insert(T x) {
	add(x);
    }

    public T deleteMin() {
	return remove();
    }

    public T min() { 
	return peek();
    }

    public void add(T x) { 
        size++;
        pq[size]=x;
        percolateUp(size);
    }

    public T remove() { 
	T min=pq[1];
        pq[1]=pq[size--];
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
            i=i/2;
        }
        pq[i]=pq[0];
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) {
        int schild;
        T x=pq[i];
        while(2*i<=size){
            if(2*i==size) {// one child 
                if(c.compare(x,pq[size])>0){
                    pq[i]=pq[size];
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
                        i=schild;
                        }
                else break;
                }
        }
        
        pq[i]=x;
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
        System.out.println(" pq's lenght here is"+pq.length);
        //inserting values into the heap order
        for(int i=1;i<pq.length;i++){

            if(pq[i]!=null)
            insert(pq[i]);
        }
    }

    /* sort array A[1..n].  A[0] is not used. 
       Sorted order depends on comparator used to build heap.
       min heap ==> descending order
       max heap ==> ascending order
     */
//    public <T> void heapSort(T[] a/*, Comparator<T> comp*/) { 
//        
//        T x;
//        //sorting the array in descending order
//        for(int i=a.length-1;i>0;i--){
//            x=(T)deleteMin();
//            a[i]=x;    
//            //percolateDown(1);
//        }
//        
//        System.out.println("Heap array after sorting:");
//        for(int i=0;i<a.length;i++)
//            System.out.print(a[i]+" ");        
//
//    }

    public Object[] resize() {
        Object[] newPq= new Object[pq.length+(pq.length/2)];
        System.out.println("newPq.length: "+newPq.length);
        for(int i=1;i<pq.length;i++){
            newPq[i]=pq[i];
            pq[i]=null;
        }
            
        return newPq;
    }
}

