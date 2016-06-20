/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author kunal krishna
 */
public class Lp5Lev3 {
    
    
    public static void main(String[] args) throws FileNotFoundException, Exception{
        
        IsBipartite isb = new IsBipartite();
        //Scanner sc = new Scanner(new File("C:\\Spring16\\Impl6301002\\LP5\\lp5-data-lev-13 (1)\\lp5-data\\bip2.txt"));
        Scanner in;
	if(args.length >0) {
            in = new Scanner(new File(args[0]));
        } else {
	   //in = new Scanner(System.in);
           in = new Scanner(new File(""));
	}
	Graph g = Graph.readGraph(in, true); 
        //step1
        isb.driver(g);
        
        int maxW=0;
        for(Vertex u : g){
            
            if(u.outer){
                maxW=maxWt(u);
                u.lu=maxW;
            }else{
                u.lu=0;
            }
        }
        
        
        //step2
        Graph z = new Graph(g.numNodes);
        for(Edge e : g.edgeList()){
            Vertex u = e.From;
            Vertex v=e.otherEnd(u);
            if(u.lu+v.lu==e.Weight){
                z.addEdge(u.name,v.name,e.Weight);
            }
        }
        LP5Lev1 l1 = new LP5Lev1();
        
        
    }
    
    
    public static int maxWt(Vertex u){
         int maxwt=0;
        for(Edge e : u.Adj){
            if(maxwt<e.Weight){
                maxwt=e.Weight;
            }
        }
        return maxwt;
    }
    
}
