/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShortProject2;

/**
 *
 * @author kunal krishna
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Comparable;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MST<T> {
    static final int infinity = Integer.MAX_VALUE;
    
    static int PrimMST1(Graph g)
    {
        int wmst = 0;
        Vertex src = g.verts.get(1);
        
        // Code for Prim's algorithm
        //Pre processing the graph
        for(Vertex u: g){
            u.seen=false;
            u.parent=null;
        }
        src.seen=true;
        wmst=0;
        int counter=1;
        //adding all the edges of the src to the PQ
        PriorityQueue<Edge> pqPrim1 = new PriorityQueue<Edge>(300, new Comparator<Edge>() {
        public int compare(Edge edge1, Edge edge2) {
            if(edge1.Weight > edge2.Weight) return 1;
            else if(edge1.Weight < edge2.Weight) return -1;
            else return 0;
        }
    });
        
        for(Edge e: src.Adj){
         pqPrim1.add(e); 
            
        }
            
            
        Vertex u, v, w;
        
        while(!pqPrim1.isEmpty()){
            Edge e=(Edge)pqPrim1.remove();
           // System.out.println("selecting edge"+e);
            if(e.From.seen && e.To.seen) 
                continue;
            
            
            
            u=e.From;
            v=e.To;
            u.seen=true;
            
            v.parent=u;

            wmst+=e.Weight;
            System.out.println(wmst);
            v.seen=true;
        
            
            for(Edge f: v.Adj){


                if(f.From.seen && !(f.To.seen)){
                     pqPrim1.add(f);           
                }
                   
                
                w=f.otherEnd(v);
                if(! w.seen){
                    pqPrim1.add(f); 
                   
                }
                
            
            }
        }
        
        return wmst;
    }

    static int PrimMST2(Graph g)
    {
        int wmst = 0;
        Vertex src = g.verts.get(1);
        
        // Code for Prim's algorithm
        //Pre processing the graph
        for(Vertex u: g){
            u.seen=false;
            u.parent=null;
            u.distance=infinity;
        }
        src.distance=0;
        wmst=0;
        
        //adding all the vertices of the src to the PQ
        PriorityQueue<Vertex> pqPrim2 = new PriorityQueue<Vertex>(10, new Comparator<Vertex>() {
        public int compare(Vertex vertex1, Vertex vertex2) {
            if(vertex1.distance > vertex2.distance) return 1;
            else if(vertex1.distance < vertex2.distance) return -1;
            else return 0;
        }
    });
        
        for(Vertex u: g)
            pqPrim2.add(u);
        
        Vertex u, v, w;
        
        while(!pqPrim2.isEmpty()){
            u=(Vertex)pqPrim2.remove();
            u.seen = true;
            wmst+=u.distance;
            
            for(Edge e: u.Adj){
                v = e.otherEnd(u);
                    if(!v.seen && e.Weight<v.distance)
                    v.distance=e.Weight;
                    v.parent=u;
                    
                    //BinaryHeap binPrim2 = new BinaryHeap(pqPrim2);
            }
        }
        
        return wmst;
    }
    
    public static void main(String[] args) throws IOException 
    {
        Scanner in = new Scanner(System.in);
        //Graph g = Graph.readGraph(in, false);
        Graph g = Graph.readGraph(new Scanner(new File("prim2.txt")), false);
        int wt = PrimMST1(g);
        System.out.println("Final wt="+wt);
        
        //starting timer before addition
//        long startTime1 = System.currentTimeMillis();
//        System.out.println("The prim's lenght or weight here is:"+PrimMST1(g));
//        long endTime1   = System.currentTimeMillis();
//        //end time for subtraction
//        long totalTime1 = endTime1 - startTime1;
//        System.out.println("Total time in ms for Prim1 is: "+totalTime1);
//
//        System.out.println();
//        long startTime2 = System.currentTimeMillis();
//        System.out.println("The prim's lenght or weight here is:"+PrimMST2(g));
//        long endTime2   = System.currentTimeMillis();
//        //end time for subtraction
//        long totalTime2 = endTime2 - startTime2;
//        System.out.println("Total time in ms for Prim2 is: "+totalTime2);
//        
//        Integer[] a= new Integer[]{0,4,2,3,1,5,21,23,7,17,33,29};
//        System.out.println();
//        long startTime3 = System.currentTimeMillis();
//        BinaryHeap bin = new BinaryHeap(a);
//        long endTime3   = System.currentTimeMillis();
//        //end time for subtraction
//        long totalTime3 = endTime3 - startTime3;
//        System.out.println("Total time in ms for Prim2 is: "+totalTime3);
//        
        
        
    }
}
