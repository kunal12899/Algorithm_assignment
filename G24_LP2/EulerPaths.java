import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
*
* @authors : G24
* 		  
*         Kunal Krishna
*        
*/

public class EulerPaths {

	public static void main(String args[]) throws FileNotFoundException{
		
	//	String a = args[0];
		
		Scanner sc = new Scanner(new File(args[0]));
		Graph g = Graph.readGraph(sc, false); 
		ArrayList<Edge> tour = new ArrayList<Edge>();
		//System.out.println("");
		
	
		
		/*
		Choose any starting vertex v, and follow a trail of edges from that vertex until returning to v. 
		It is not possible to get stuck at any vertex other than v, because the even degree of all vertices ensures that, 
		when the trail enters another vertex w there must be an unused edge leaving w. 
		The tour formed in this way is a closed tour, but may not cover all the vertices and edges of the initial graph.
		*/
		
		  g.calcInDeg();
		//  g.printAdjList();
		  
		  ArrayList<Vertex> verts = g.getBaseList();
		  ArrayList<Edge> edgeLists = g.edgeList();
		  
		 long startTime = System.currentTimeMillis();
		  
		 boolean flag  = false;
		 
		 for(Vertex in: verts){
			 if(in==null)
				 continue;
			 
			 else{
				 if(in.indeg%2 == 0)
					 flag = true;
				 else
					 flag  = false;
			 }
			 
		 }
		 
		 
		 //check if indegree is true
		 if(flag == true){
			
			 Vertex source = verts.get(1);
			 Vertex u = source.Adj.get(0).To ;
			 
			// System.out.println("source u="+source+" "+u);
			 
			 tour.add(source.Adj.get(0));
			 
			// System.out.println("tour="+tour);
			 
			 
			// System.out.println("tour="+source.Adj.get(0).To);
			 if(source == source.Adj.get(0).To){
				 u=source.Adj.get(0).From;
	                source=source.Adj.get(0).To;
				 
			 }
			 else
			 {
				 	u = source.Adj.get(0).To;
	                source = source.Adj.get(0).From;
			 }
			 
			// System.out.println("source u="+source+" "+u);
			 
			 
			 
			 	source.Adj.remove(0);
		        u.Adj.remove(0);
		        source.indeg--;
		        u.indeg--;
		      //  g.printAdjList();
		        
		        
		        
		        
		        
		        while(u.indeg!=0){
		        	
		        	Edge e=null;
		        	
		        	
		        	for(Edge in: u.Adj){
		        		
		        		
		        		if((in.From == source || in.To ==source) && u.Adj.size()>1){
		        			continue;
		        		}
		        		
		        		 else if(u.Adj.size()==1 && (in.From == source || in.To ==source)){
		        			 e=in;
		                     tour.add(e);
		                     source=source.Adj.get(0).To;
		        		 }
		        		
		        		 else{
		        			 e =in;
		                     tour.add(e);
		                     break;
		        		 }
		        	}
		        	
		        	//System.out.println("tour1111="+tour);
		        	  if(e==null){
		        		  //System.out.println("e null");
		        		  break;
		        	  }
		                  
		              
		        	 // System.out.println("eee="+e);
		        	  Vertex v = null;
		              if(u==e.To){
		                  v=e.From;
		                  u=e.To;
		              }
		              else{
		                  v = e.To;
		                  u = e.From;
		              }
		              
		              u.Adj.remove(e);
		              
		              
		              v.Adj.remove(e);
		              
		              u.indeg--;
		              v.indeg--;
		              u=v;
		        	
		        }
		        
		        
		   	 System.out.println("Euler tour is ");
		        for(Edge in : tour){
		            System.out.println(in+"");
		        }
		      //  System.out.println("Tour size="+tour.size());
		        
		            System.out.println("");
		        System.out.println("Completed Main");
		        long endTime = System.currentTimeMillis();
		        long diff = endTime-startTime;
		        System.out.println("time taken "+diff+" ms");
		        
		        }
		        else{
		            System.out.println("not a tour");
		        }
   
		 }

	}

