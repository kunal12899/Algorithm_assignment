
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @authors : G24
 *        
 *         Kunal Krishna
 *          
 */
public class EulerPathCircuit {
    
    public static void main(String[] args) throws FileNotFoundException{
        
        String filename = args[0];
        Scanner sc = new Scanner(new File(filename));
        Graph g = Graph.readGraph(sc, false);
        ArrayList<Edge> tour = new ArrayList<>();
        System.out.println("input read");
        g.calcInDeg();
       
        ArrayList<Vertex> verts = g.getBaseList();
        ArrayList<Edge> edgeList = g.edgeList();
        long startTime = System.currentTimeMillis();
     /* Setting the boolean flag inorder to check the degree of each vertex
          if degree is even then flag is set to true. 
        
        */
        boolean flag = false;
        for(Vertex in : verts){
            if(in == null)
                continue;
            else{
            //System.out.println(""+in);
            if(in.indeg%2==0)
                flag=true;
            else
                flag=false;
            }
        }
        if(flag==true){
        Vertex source = verts.get(1);
        
        Vertex u = source.Adj.get(0).To ;
        tour.add(source.Adj.get(0));
        if(source==source.Adj.get(0).To){
                u=source.Adj.get(0).From;
                source=source.Adj.get(0).To;
            }
            else{
                u = source.Adj.get(0).To;
                source = source.Adj.get(0).From;
            }
        
        
        source.Adj.remove(0);
        u.Adj.remove(0);
        source.indeg--;
        u.indeg--;
        //g.printAdjList();
        
        
        System.out.println("processing started");
        
        
        while(u.indeg!=0){
           
            
           
            Edge e=null;
           
            for(Edge in : u.Adj){
                
                
                if((in.From == source || in.To ==source )&& u.Adj.size()>1){
                    
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
           
            if(e==null)
                break;
            
            
           
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

/*Input
6 10      
1 2 1
1 3 1
1 4 1
1 6 1
2 3 1
3 6 1
3 4 1
4 5 1
4 6 1
5 6 1*/

/*output
(1,2)
(2,3)
(3,6)
(4,6)
(3,4)
(1,3)
(1,4)
(4,5)
(5,6)
(1,6)
*/
