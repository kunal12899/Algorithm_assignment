/*** @author rbk ***/

import java.io.*;
import java.util.*;

public class DemoHashing {
    static class Edge {
	int u, v;
	Edge(int u, int v, int w) { this.u = u;   this.v = v; }
	public String toString() { return "(" + u + "," + v + ")"; }
    }

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	Set<Edge> s = new HashSet<>();
	int n = in.nextInt();
	int m = in.nextInt();
	for (int i = 0; i < m; i++) {
	    int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
	    s.add(new Edge(u, v, w));
	}

	System.out.println("Edges in hash table:");
	for(Edge e: s) {
	    System.out.print(e);
	}
	System.out.println();

	while(in.hasNext()) {
	    int a = in.nextInt();
	    int b = in.nextInt();
	    Edge e = new Edge(a, b, 0);
	    if(s.contains(e)) {
		System.out.println("Yes!  Edge " + e + " exists");
	    } else {
		System.out.println("No!  Edge " + e + " missing");
	    }
	}
    }
}
/*
Sample output:
Edges in hash table:
(2,3)(4,6)(3,6)(1,6)(1,4)(5,6)(1,3)(3,4)(1,2)(4,5)
No!  Edge (1,2) missing
No!  Edge (1,3) missing
No!  Edge (1,4) missing
No!  Edge (1,5) missing
No!  Edge (1,6) missing
No!  Edge (2,3) missing
No!  Edge (2,4) missing
No!  Edge (2,5) missing
No!  Edge (2,6) missing
No!  Edge (3,4) missing
No!  Edge (3,5) missing
No!  Edge (3,6) missing
No!  Edge (4,5) missing
No!  Edge (4,6) missing
No!  Edge (5,6) missing
*/
