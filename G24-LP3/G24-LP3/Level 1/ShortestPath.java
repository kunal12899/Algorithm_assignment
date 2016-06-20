/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author kunal krishna
 */
public class ShortestPath<T> {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
static boolean notDAG =false;
static Vertex source = null;
public static boolean Bellman_Ford(Graph g, Vertex src){
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
		if(u.count>= g.numNodes){
			
			
			
			//checkForNegCycle(g);
			return false;              //Negative Cycle     
		}
		
		
		
} //end of while


	
	
	if(g.numNodes<=100){
	    for(int i=1;i<=g.numNodes;i++){
	    	
	    	if(g.verts.get(i).distance==Integer.MAX_VALUE){
	    		System.out.println(g.verts.get(i)+" "+"INF"+" "+0);
	    		continue;
	    	}
	    	sum = sum+ g.verts.get(i).distance;
	    	if(g.verts.get(i)==src)
	    	System.out.println(g.verts.get(i)+" "+g.verts.get(i).distance+" "+"-");
	    	
	    	else{
	    		System.out.println(g.verts.get(i)+" "+g.verts.get(i).distance+" "+g.verts.get(i).parent);
	    		
	    	}
	    }
	    System.out.println("B-F "+ sum);
	    
	   
	    
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
			
			
			
			
			source=y;
			y.seen=false;
			//findNegCycle(y,source,out);
			//localwt=0;
			
		}
		
		
	}
	//printing negative cycle:
	System.out.println("Edges forming negative cycle");
	
	for(Edge k : out){
		System.out.println(k);
	}
	
	
}
   
    
    //Function to run BFS to find the shortest path
  public static void bfs(Graph g, Vertex src){
		Queue<Vertex> Q = new LinkedList<>();

		for (Vertex u : g) {
		    u.seen = false;
		    u.parent = null;
		    u.distance = Integer.MAX_VALUE;
		}
		
		
		// Run BFS on every component
		

			src.distance = 0;
			Q.add(src);
			src.seen = true;
			int sum = 0;

			
			while (!Q.isEmpty()) {
				
			    Vertex u = Q.remove();
			    
			    for (Edge e : u.Adj) {
				Vertex v = e.otherEnd(u);
				if (!v.seen) {
				    v.seen = true;
				    v.parent = u;
				   
				    v.distance = u.distance + e.Weight;
				    
				    sum = sum +  v.distance;
				   
				    Q.add(v);
	    
				} 
				    
				}
			    //for each edge of u.Adj
			    
			}// End of while loop
			
			System.out.println("dist = "+sum);
			
			  System.out.println("BFS "+ sum);
			  
			  if(g.numNodes<=100){
			    for(int i=1;i<=g.numNodes;i++){
			    	
			    	if(g.verts.get(i)==src)
			    	System.out.println(g.verts.get(i)+" "+g.verts.get(i).distance+" "+"-");
			    	
			    	else{
			    		System.out.println(g.verts.get(i)+" "+g.verts.get(i).distance+" "+g.verts.get(i).parent);
			    	}
			    }
			    
			  }
			  else
			  {
				  System.out.println("The Sum="+sum);
			  }
		   }
    
    //TO find the shortest path using Dijkstra's Algorithm
    public static void Dijkstras(Graph g, Vertex src){
    int wpath=0;
     Vertex[] pq2 = new Vertex[g.verts.size()+1];

        initialize(g, src);

        //creating a binary heap of vertex
        IndexedHeap pq1 = new IndexedHeap((Index[]) pq2, new Comparator<Vertex>() {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            return (o1.distance - o2.distance);
        }
    });   

        //inserting all the nodes into indexed priority queue
        int i=1;
        for(Vertex v:g){
            pq1.insert(v, i);
            i++;
        }
        
        Vertex u,v=null;
           while(pq1.size!=0){
             u=(Vertex)pq1.deleteMin();
              
             if(u.distance!=Integer.MAX_VALUE)
             wpath+=u.distance;
             
                u.seen=true;
             //relaxing edges out of u             
              for(Edge e:u.Adj){
                v=e.otherEnd(u);
                if((v.distance>u.distance + e.Weight) && !v.seen){
                v.distance=u.distance+e.Weight;
                v.parent=u;
                pq1.percolateUp(v.getIndex());  
               }
             }
          }
           
           //disaplying the result
           System.out.println("Dij "+wpath);
           printResult(g);
    }   
    
    //TO find the shortest path using DAG Algorithm
    public static void DAG(Graph g, Vertex src){
        int dagWeight=0;
        List<Vertex> topoList= new ArrayList<Vertex>();
        topoList= toplogicalOrder(g);
        
        initialize(g, src);
        Vertex v;
        for(Vertex u: topoList){
            if(u.distance!=Integer.MAX_VALUE)
            dagWeight+=u.distance;
            u.seen=true;
            for(Edge e: u.Adj){
                v=e.otherEnd(u);
                relaxEdge(u, v, e);
            } 
        }
        
        //disaplying the result
           System.out.println("DAG "+dagWeight);
           printResult(g);
    }
    
    public static List<Vertex> toplogicalOrder(Graph g) { 
      /* Algorithm 1. Remove vertices with no incoming edges, one at a
      	 time, along with their incident edges, and add them to a list.
       */

        Stack<Vertex> st=DFS_Stack(g);
        return st;
    }
    
    public static Stack<Vertex> DFS_Stack(Graph G){ 
            int count=0;
            for(Vertex u: G){
            u.seen = false;
            u.parent = null;
            
            //checking inDegrees for each vertex
            if(u.indeg>0)
                count++;
            }
            
            //if all the vertices have inDegrees more than 0, then graph is not DAG
            if(count==G.verts.size()-1)
                return null;
            
        Stack<Vertex> st = new Stack<>();
       for(Vertex u: G){
        if (!u.seen)
        DFSVisit(u, st);
            }
       return st;
    }
    
    public static void DFSVisit (Vertex u, Stack<Vertex> st){

        //if the current vertex is not visited then cotinue DFS
            u.seen = true;
            for(Edge e: u.Adj){
            Vertex v = e.otherEnd(u);
            if (!v.seen){
            v.parent = u;
            DFSVisit (v);
            }
            else notDAG=true;
            }
            st.push(u);
    }
    
    public static void DFSVisit (Vertex u){

        //if the current vertex is not visited then cotinue DFS
            u.seen = true;
            for(Edge e: u.Adj){
            Vertex v = e.otherEnd(u);
            if (!v.seen){
            v.parent = u;
            DFSVisit (v);
            }
            else notDAG=true;
            }
    }
    
    public static void initialize(Graph g, Vertex src){
        for(Vertex v: g){
            v.seen=false;
            v.distance=Integer.MAX_VALUE;
            v.parent=null;
        }
        src.distance=0;
    }
    
    public static void relaxEdge(Vertex u, Vertex v, Edge e){
        if((v.distance>u.distance+e.Weight) && !v.seen){
            v.distance=u.distance+e.Weight;
            v.parent=u;
        }
    }
    
    public static void printResult(Graph g){
        String dist=null, parent=null;
           for(Vertex v1:g){
               if(v1.distance==2147483647)
                   dist="INF";
               else
                   dist=Integer.toString(v1.distance);
               if(v1.parent==null)
                   parent="-";
               else 
                   parent=Integer.toString(v1.parent.name);
               System.out.println(v1+" "+dist+" "+parent);
           }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        try{
            //reading input file name from command line
            Scanner sc = new Scanner(new File(args[0]));
            //true as graph is directed , reading graph:from input file
            Graph g = Graph.readGraph(sc, true);                    
            
           // start timer;
            long startTime = System.currentTimeMillis();
            g.check_weight(g);  //check uniform weight
            boolean isDag=false;
            isDag=g.DAC_check(g);      //check graph is DAC or not            
        
            if(g.uniform_G){
                bfs(g,g.verts.get(1));          
            }
            else if(isDag){  
             
              DAG(g,g.verts.get(1));
            }
            else if(!g.negative_G && !g.uniform_G){
               Dijkstras(g,g.verts.get(1));
            }          
            //if all the above condition fails then run below algorithm to find the shortest path
            else{
                boolean ans;
                 ans=Bellman_Ford(g,g.verts.get(1));   
                 if(ans)
                     System.out.println("Not a negative cycle");
                 else
                     System.out.println("Unable to solve problem. Graph has a negative cycle");
            }
//printing weight of MST
           // System.out.println("Weight of mst : "+MSTWt); 
            
//end timer
            long end = System.currentTimeMillis();
            long diff = end-startTime;
            System.out.println("time taken : "+diff+" ms");
            
            //Dijkstras(g, g.verts.get(1));
            //DAG(g, g.verts.get(1));
        }catch(Exception e){
            e.printStackTrace();
        }     
    }
    
}