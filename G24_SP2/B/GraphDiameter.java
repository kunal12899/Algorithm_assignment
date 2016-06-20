/**
 * 
 * @author kunal krishna
 * Example to illustrate an algorithm that find the diametre of a given
 * undirected graph.
 */

import java.io.*;
import java.util.*;
import java.lang.Exception;

public class GraphDiameter {	

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		if (args.length > 1) {
			File inputFile = new File(args[0]);
			in = new Scanner(inputFile);
		} else {
			in = new Scanner(System.in);
		}
       // read undirected graph from stream "in"
		Graph g = Graph.readGraph(in, false);
		int d= Diameter(g);
		System.out.println(d);
	}

	public static int Diameter(Graph g){
		Random randomGenerator = new Random();
		int answer=0;
		while(answer == 0)
			answer = randomGenerator.nextInt(g.numNodes);
		Vertex first=g.verts.get(answer);
		first = DFS_ON(first);
		if(first == null) {
			return -1;
		}
		setting_vertex(g);
		Vertex result= DFS_ON(first);
		if(result == null) {
			 return -1;
		} else {
			return result.distance;
		}

	}
	public static void setting_vertex(Graph g){
		for (Vertex u : g) {
			u.seen = false;
			u.parent = null;
			u.distance = Integer.MAX_VALUE;
		}
	}
	// Run DFS on given component

	public static Vertex DFS_ON(Vertex src)
	{
		Vertex destination=null;
		Queue<Vertex> Q = new LinkedList<>();
		if(!src.seen) {
			src.distance = 0;
			Q.add(src);
			src.seen = true;
			int dmax=0;
			while (!Q.isEmpty()) {
				Vertex u = Q.remove();
				for (Edge e : u.Adj) {
					Vertex v = e.otherEnd(u);
					if (!v.seen) {
						v.seen = true;
						v.parent = u;
						v.distance = u.distance + 1;
						if(v.distance>dmax){
							dmax=v.distance;
							destination=v;
							destination.distance=dmax;
						}
						Q.add(v);
					} else if(u.parent != v) {
						return null;
					}
				}
			}
		}
		return destination;
	}
}