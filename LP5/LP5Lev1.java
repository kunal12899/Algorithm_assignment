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
public class LP5Lev1 {
    
    static int mSize=0;
    
    
    
    public static void main(String[] args) throws FileNotFoundException, Exception{
        
        IsBipartite isb = new IsBipartite();
        //Scanner sc = new Scanner(new File("bip4.txt"));
	Scanner in;
	if(args.length >0) {
            in = new Scanner(new File(args[0]));
        } else {
	   //in = new Scanner(System.in);
           in = new Scanner(new File(""));
	}
	Graph g = Graph.readGraph(in, true); 
        
        //step 1 : check for bipartite
        /* Use a maximal spanning forest of G to classify nodes as outer and inner:
 Mark a node as outer, its neighbors as inner, and their neighbors
 as outer, etc. If there is any edge of G that connects two outer
 nodes or two inner nodes, then raise exception "Not bipartite"*/
        
        isb.driver(g);
        
        //step 2 : initialize
        /*Initialize: msize <−− 0; for each u in V do u.mate <−− null*/
        
        initialize(g);
        
        //step3 : 
        /* Find a maximal matching of G using a greedy algorithm:
 for each edge e=(u,v) in E do
 if u.mate = null and v.mate = null then
 u.mate <−− v; v.mate <−− u; msize++*/
        //start timer
        long startTime = System.currentTimeMillis();
        
        for(Edge e : g.edgeList()){
            Vertex u=e.From;
            Vertex v=e.otherEnd(u);
            if(u.mate==null && v.mate==null){
                u.mate=v;
                v.mate=u;
                mSize++;
            }
        }

        //step4:
        findAugPath(g);
        
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time in ms: "+totalTime);
        //step5:
//        for(Edge e : g.edgeList()){
//                Vertex v=e.From;
//                Vertex w=e.To;
//                if(v.mate==w && w.mate==v)
//                    System.out.println("E: "+e);
//            }
        System.out.println("max matching : "+mSize);
        
        
    }
    
    
    public static void processAugPath(Vertex u){
       if(!u.outer){
            Vertex p = u.parent;
            Vertex x = p.parent;
            u.mate=p;
            p.mate=u;
            Vertex y=null;
            Vertex nmx=null;
            //c
            while(x!=null){
                nmx=x.parent;
                 y=nmx.parent;
                x.mate=nmx;
                nmx.mate=x;
                x=y;
            
            }
            mSize++;
       } 
        
            
    }
    
    
    public static void findAugPath(Graph g){
       
        while(true){
             boolean flag=false;
            Queue<Vertex> q = new LinkedList();
            //System.out.println("created queue");
        for(Vertex u : g){
            
            u.seen=false;
            u.parent=null;
            //System.out.println("u.mate : "+u.mate + " u.outer : "+u.outer);
            if(u.mate==null && u.outer){
               //System.out.println("came here with u : "+u);
                u.seen=true;
                q.add(u);
                //System.out.println("addig u : "+u);
            }
        
        }
        
        
        
        while(!q.isEmpty()){
            
            Vertex u = q.remove();
            //System.out.println("vertex u removed : "+u);
            Vertex x=null;
            for(Edge ab : u.Adj){
                
                Vertex a=ab.From;
                Vertex b=ab.otherEnd(a);
                //System.out.println("came here with b : "+b);
                if(!b.seen){
                    //System.out.println("came @b.seen chck with b : "+b);
                    b.parent=a;
                    b.seen=true;
                    if(b.mate==null){   // augmenting path has been found
                        //System.out.println("aug path found : "+b);
                        flag=true;
                        processAugPath(b);
                       break;
                    }else{
                        flag=false;
                        x = b.mate;
                        x.seen=true;
                        x.parent=b;
                        q.add(x);
                    }
                   
                    
                }
                
            }
            
            
            if(flag){
               
                break; 
            }
              
            
        }
        if(q.isEmpty() && !flag){   
            //watch this step
            //System.out.println("came here to brk while-forever loop");
            break;
        }
            
        }
        
    }
    
    
    public static void initialize(Graph g){
     mSize=0;
     for(Vertex u : g.verts){
         if(u==null)
             continue;
         u.mate=null;
     }
     
     
        
    }
    
}
