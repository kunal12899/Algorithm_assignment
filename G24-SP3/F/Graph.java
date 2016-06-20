
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 */
public class Graph<V extends Vertex,E extends Edge>{
    
    //public List<Vertex> verts; // array of vertices
    
    public V[] verts;
    public int numNodes; // number of verices in the graph
     
    /**
     * Constructor for Graph
     * 
     * @param size
     *            : int - number of vertices
     */
    Graph(int size) {
        
	numNodes = size;
	verts = (V[]) new Vertex[size+1];
	verts[0]=null;
	// create an array of Vertex objects
	for (int i = 1; i <= size; i++){
            verts[i]=(V) new Vertex(i);
            
            
        }
	   
            
    }
    
 
    /**
     * Method to add an edge to the graph
     * 
     * @param a
     *            : int - one end of edge
     * @param b
     *            : int - other end of edge
     * @param weight
     *            : int - the weight of the edge
     */
    void addEdge(int a, int b, int weight) {
	Vertex u = verts[a];
	Vertex v = verts[b];
        System.out.println("creating edge with"+u+"\t\t"+v);
	Edge e = new Edge(u, v, weight);
	u.Adj.add(e);
	v.Adj.add(e);
        
    }
    
    /**
     * Method to add an arc (directed edge) to the graph
     * 
     * @param a
     *            : int - the head of the arc
     * @param b
     *            : int - the tail of the arc
     * @param weight
     *            : int - the weight of the arc
     */
    void addDirectedEdge(int a, int b, int weight) {
        
	Vertex head = verts[a];
	Vertex tail = verts[b];
	Edge e = new Edge(head, tail, weight);
        
	head.Adj.add(e);
	tail.revAdj.add(e);
    }

    
   void printAdjList(){
        
        
        System.out.println("Adj List");
        for(int i=1;i<verts.length;i++){
            Vertex x = verts[i];
            System.out.print(x+"-->\t");
            for(int j=0;j<x.Adj.size();j++){
                System.out.print(x.Adj.get(j));
            }
            System.out.println("");
        }
        
    }
    

    public static <V extends Vertex,E extends Edge> Graph<V,E>  readGraph(Scanner in, boolean directed) {
	// read the graph related parameters
        //Scanner scnwe= new Scanner(new File("prim1.txt"));
	       //System.out.println("Enter no of vertices");
        int n = in.nextInt(); // number of vertices in the graph
            //System.out.println("Enter no of edges");
	int m = in.nextInt(); // number of edges in the graph

	// create a graph instance
	Graph<V,E> g = new Graph(n);
	for (int i = 0; i < m; i++) {
            //System.out.println("Enter node 1,node 2,wt");
	    int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
            
	    if(directed) {
		g.addDirectedEdge(u, v, w);
               // g.graphTrans(u, v, w);
	    } else {
		g.addEdge(u, v, w);
	    }
	}
	in.close();
	return g;
    }
    
}
