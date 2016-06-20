import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BellmanFordL2 {
	
	public static void main(String args[]) throws FileNotFoundException{
		
		
		Scanner sc = new Scanner(new File(args[0]));
		Graph g = Graph.readGraph(sc, true);
		ArrayList<Edge> out = new ArrayList<>();
		
		
		BellmanFord bf = new BellmanFord();
		
		bf.bellman(g, g.verts.get(1));
		
		
		
//		int count = 0;
//		for(Vertex x:g.verts){
//			
//			if(x==null)
//				continue;
//			
//			if(x.parent!=null){
//				count++;
//			}
//		}
//		
//		System.out.println(count);
		
		Graph D = new Graph(g.verts.size()-1);
		for(Edge e: g.edgeList()){
			Vertex u = e.From;
			Vertex v = e.To;
			
			if(u.distance+e.Weight==v.distance){
				out.add(e);
				D.addDirectedEdge(u.name, v.name,e.Weight);
				//System.out.println(e);
			}
			
			
		}
		
		//adding u.distance in D
		int j=1;
		for(Vertex i : D.verts){
			
			if(i==null)
				continue;
			
			i.distance=g.verts.get(j).distance;
			j++;
			
		}
		
		
		//D.printRevAdjList();
		for(Vertex y: D.verts){
			
			if(y==null)
				continue;
			
			find(y,D,y);
			
			
		}
		int totalpaths=0;
		for(Vertex y: D.verts){
			
			if(y==null)
				continue;
			
			 totalpaths+=y.csp;
			
		}
		System.out.println(totalpaths);
		for(Vertex y : D.verts){
			if(y==null)
				continue;
			if(y.distance==Integer.MAX_VALUE){
				System.out.println(y+" "+"INF"+" "+y.csp);
			continue;
			}
			
			System.out.println(y+" "+y.distance+" "+y.csp);
			
			
		}
		
		
	}

	public static void find(Vertex y,Graph D,Vertex init){
		
			
		
		if(y==D.verts.get(1)){
				init.csp++;
				return ;
			}
		
			for(Edge r : y.revAdj){
				
				Vertex other = r.otherEnd(y);
				
				
				find(other,D,init);
			
			}
			
		}
		
		
	}
	


