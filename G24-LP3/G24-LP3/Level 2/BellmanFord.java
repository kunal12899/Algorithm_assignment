import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BellmanFord {

	static int localwt=0;
	static Vertex source=null;
	
public static void main(String args[]) throws FileNotFoundException
	
	{
	
	Scanner sc = new Scanner(new File(args[0]));
	Graph g = Graph.readGraph(sc, true);
//	System.out.println("hdshgd="+g.numNodes);
//	
//	for(Vertex u : g){
//	System.out.println("hdshgd="+u);
//	
//	}
	
	boolean ans = BellmanFord.bellman(g,g.verts.get(1));
	if(ans)
	System.out.println("Not a Negative cycle");
	else
		
		System.out.println("There is a Negative cycle");
	
	}  //End of main





//Take 3


public static boolean bellman(Graph g, Vertex src){
	Queue<Vertex> Q = new LinkedList<>();
	
	for(Vertex u : g){
		
		u.distance = Integer.MAX_VALUE;
		u.parent = null;
		u.count = 0;
		u.seen = false;
		
		
	}
	
	src.distance = 0;
	src.seen = true;
	Q.add(src);
	
	int sum = 0;
	boolean flag=true;
	while(!Q.isEmpty()){
		
		
		Vertex u  = Q.remove();
		
		
		
		u.seen = false;
//		if(flag)
		u.count = u.count + 1;

		if(u.count>= g.numNodes){
			
			
			
			checkForNegCycle(g);
			return false;              //Negative Cycle     
		}
		
		for(Edge e: u.Adj){
			Vertex v = e.otherEnd(u);
			if(v.distance>u.distance + e.Weight){
				
				
				v.distance = u.distance + e.Weight;
				
				
				v.parent = u;
				
				
				if(!v.seen){
					Q.add(v);
					
					v.seen = true;
				}	
			}
			
			
		}
		
		
		
} //end of while

//g.printRevAdjList();
src.seen=true;
	checkForNegCycle(g);
	
	if(g.numNodes<=100){
	    for(int i=1;i<=g.numNodes;i++){
	    	if(g.verts.get(i).distance==Integer.MAX_VALUE){
	    		System.out.println(g.verts.get(i)+" "+"INF"+" "+g.verts.get(i).parent);
	    		continue;
	    	}
	    	sum = sum+ g.verts.get(i).distance;
	    	if(g.verts.get(i)==src)
	    	System.out.println(g.verts.get(i)+" "+g.verts.get(i).distance+" "+"-");
	    	
	    	else{
	    		System.out.println(g.verts.get(i)+" "+g.verts.get(i).distance+" "+g.verts.get(i).parent);
	    		
	    	}
	    }
	    System.out.println("BELL "+ sum);
	    
	   
	    
	  }
	  else
	  {
		  for(int i=1;i<=g.numNodes;i++){
		    	sum = sum+ g.verts.get(i).distance;
		  }
		  
		  System.out.println("The Sum="+sum);
	  }
//	for(Vertex u : g){
//		System.out.println("abccc="+u);
//		
//		}
	

	return true;
	}


public static void checkForNegCycle(Graph g){
	
	ArrayList<Edge> out = new ArrayList<>();
	for(Vertex u : g.verts){
		
		if(u==null)
			continue;
		u.seen=false;
	}
	for (Edge last : g.edgeList()){
		
		Vertex x = last.From;
		Vertex y = last.To;
		
		if(y.distance>x.distance + last.Weight){
			
			
			//System.out.println("nodes passing non positive cycle : "+y);
			
			source=y;
			y.seen=false;
			findNegCycle(y,source,out);
			localwt=0;
			
		}
		
		
	}
	//printing negative cycle:
//	System.out.println("Edges forming negative cycle");
//	
//	for(Edge k : out){
//		System.out.println(k);
//	}
	
	
}

public static void findNegCycle(Vertex y,Vertex start,ArrayList<Edge> out ){
	
	
	//System.out.println("came here : "+y+ " start: "+start);
	
	for(Edge in : y.revAdj){
		
		
		y.seen=true;
		
		Vertex other = in.otherEnd(y);
		System.out.println("other node : "+other);
		if(other==start)
		{
		
		localwt+=in.Weight;
		System.out.println("final localwt : "+localwt);
		out.add(in);
		System.out.println("Edges in neg cycle :");
		for(Edge l : out){
			System.out.println(l);
		}
		return;
		
		}
		
		if(other.seen){
			
			continue;
		}
			
		 
		
		else {
			localwt+=in.Weight;
			//System.out.println("current localwt : "+localwt);
			out.add(in);
			//System.out.println("adding 11111111......."+in);
			findNegCycle(other, start,out);
		}
			
	}
	
	if(localwt>0){
		
		Iterator itr = out.iterator();
		while(itr.hasNext()){
			//System.out.println("removing 2222222222222.........."+itr.next());
			
			itr.next();    // This was added later because above line was commented
			itr.remove();
		}
		
			
	}
	
	
	
}
}


		


