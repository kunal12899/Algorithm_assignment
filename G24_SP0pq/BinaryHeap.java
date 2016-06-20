// Ver 1.0:  Wec, Feb 3.  Initial description.
package ImplementationBalaji.spPQ;

import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
    T[] pq;
    int size;
    Comparator<T> c;
    //implementing compare operation of Comparable
//    Comparator<Edge> c = new Comparator<Edge>() {
//
//        @Override
//        public int compare(Edge o1, Edge o2) {
//            if((o1.Weight - o2.Weight)>0)
//                return 1;
//            else if((o1.Weight - o2.Weight)<0)
//                return -1;
//            else return 0;
//        }
//    };
//    
//    Comparator<Vertex> c1 = new Comparator<Vertex>() {
//            public int compare(Vertex o1, Vertex o2) {
//            if((o1.distance - o2.distance)>0)
//                return 1;
//            else if((o1.distance - o2.distance)<0)
//                return -1;
//            else return 0;
//        }
//    };
    
    /** Build a priority queue with a given array q */
    BinaryHeap(T[] q, Comparator<T> comp) {
	pq = q;
	c = comp;
	buildHeap();
    }

    /** Create an empty priority queue of given maximum size */
    BinaryHeap(int n, Comparator<T> comp) { 
        T[] pq;
        size=n;
    }
    
    BinaryHeap(T[] p) { 
        pq=p;
        size=1;
        buildHeap();
    }

    public void insert(T x) {
        System.out.println("x: here is: "+x);
	add(x);
    }

    public T deleteMin() {
	return remove();
    }

    public T min() { 
	return peek();
    }

    public void add(T x) { 
//        if(size==pq.length-1)
//        {
//            System.out.println("Resize case: here!!!");
//            pq=(T[])resize();
//            
//        }
        
        //System.out.println("Came to add node to the heap: "+x+" size "+size);
        //System.out.println("Size here in add: "+size);
        pq[size]=x;
        percolateUp(size);
    }

    public T remove() { 
	T min=pq[1];
        pq[1]=pq[size--];
        percolateDown(1); //why percolate down 1?
        return min;
    }

    public T peek() { 
	return pq[1];
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { 
        
        //System.out.println("Came here in percolate up with i"+i+" pq[0]: "+pq[0]+"pq[i/2]: "+pq[i/2]);
        pq[0]=pq[i];
        //System.out.println("pq[i/2]: "+pq[i/2]+" pq[0]: "+pq[0]+" and this: "+c.compare(pq[i/2],pq[0]));
        while(c.compare(pq[i/2],pq[0])>0 && i!=0){
            pq[i]=pq[i/2];
            i=i/2;
            //System.out.println("Inside while");
        }
        pq[i]=pq[0];
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) {
        System.out.println("Came here in percolate down");
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
        System.out.println("Before leaving");
        pq[i]=x;
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
        System.out.println(" pq's lenght here is"+pq.length);
        //inserting values into the heap order
//        for(int i=1;i<pq.length;i++){
        for(int i=pq.length/2;i>=1;i--){

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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object[] newPq= new Object[pq.length+(pq.length/2)];
        for(int i=1;i<newPq.length;i++){
            newPq[i]=pq[i];
            pq[i]=null;
        }
            
        return newPq;
    }
}
