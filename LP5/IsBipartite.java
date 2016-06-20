/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author kunal krishna
 */
public class IsBipartite {
    
//    public static void main(String[] args) throws FileNotFoundException, Exception{
//        
//        Scanner sc = new Scanner(new File("C:\\Spring16\\Impl6301002\\LP5\\lp5-data-lev-13 (1)\\lp5-data\\bip1.txt"));
//	Graph g = Graph.readGraph(sc, true); 
//        
//        driver(g);
//        
//        printResult(g);
//        
//    }
    
     void driver(Graph g) throws Exception {
	boolean isBipartite = true;
	initialize(g);
	// Run BFS on every component
	for (Vertex src : g) {
	    if (!src.seen) {
		src.distance = 0;
		isBipartite &= checkBipartite(g, src);
	    }
	}

	// prints the detailed output if the graph is bipartite
	if (!isBipartite) {
	    System.out.println("Graph is not bipartite");
	} else {
	    System.out.println("Graph is bipartite");
	    //printResult(g);
            for (Vertex u : g) {
	    if (u.distance % 2 == 0) {
                u.outer=true;
		
	    } else {
		u.outer=false;
	    }
	}
	}
    }

    /**
     * Method to initialize a graph
     *  1) Sets the parent of every vertex as null
     *  2) Sets the seen attribute of every vertex as false 
     *  3) Sets the distance of every vertex as infinite
     * 
     * @param g
     *            : Graph - The reference to the graph
     */
     void initialize(Graph g) {
	for (Vertex u : g) {
	    u.seen = false;
	    u.parent = null;
	    u.distance = Integer.MAX_VALUE;
	}
    }

    /**
     * Method to print the graph
     * 
     * @param g
     *            : Graph - The reference to the graph
     */
     void printResult(Graph g) {
	for (Vertex u : g) {
	    if (u.distance % 2 == 0) {
                u.outer=true;
		System.out.println(u + " Outer");
	    } else {
		System.out.println(u + " Inner");
	    }
	}
    }

    /**
     * Method to perform BFS on the graph to check if the graph is bipartite or not
     * 
     * @param g
     *            : Graph - The reference to the graph
     * @param src
     *            : Vertex - The reference to the source vertex of the component
     * @return true if the graph is bipartite
     */
     boolean checkBipartite(Graph g, Vertex src) throws Exception {
	boolean isBipartite = true;
	Queue<Vertex> Q = new LinkedList<>();

	// add the source vertex to the queue
	Q.add(src);
	// mark the source as visited
	src.seen = true;

	// Perform BFS
	while (!Q.isEmpty()) {
	    // remove a vertex from the head of the queue
	    Vertex u = Q.remove();
	    // iterate through the u's adjacency list
	    for (Edge e : u.Adj) {
		Vertex v = e.otherEnd(u);
		/*
		 * if the vertex v is not visited then mark v as visited and
		 * update v's distance and parent and then add v to the queue
		 */
		if (!v.seen) {
		    v.seen = true;
		    v.parent = u;
		    v.distance = u.distance + 1;
		    Q.add(v);
		} else {
		    /*
		     * if the ends of edge (u,v), vertices u and v, are at the 
		     * same distance from the source, the graph is not bipartite
		     */
		    if (u.distance == v.distance)
			isBipartite = false;
		}
	    }
	}
        return isBipartite;
    }
} 

