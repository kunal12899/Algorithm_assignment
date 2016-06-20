/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author ankush
 */
public class Topological_Ordering {
            boolean notDAG=false;
            
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, true);
        
        Topological_Ordering top= new Topological_Ordering();
        List<Vertex> list= top.toplogicalOrder1(g);
        Stack<Vertex> st = top.toplogicalOrder2(g);
        
        try{
        if(!list.isEmpty()){
            System.out.println("Printing List contents below: ");
            System.out.println("-----------------------------------");
        for(int i=0;i<list.size();i++)
            System.out.print(list.get(i)+" ");
        }
                    System.out.println();
        }
        catch(Exception e){
            System.err.println("The given graph is not a DAG, Hence, List is empty!"); 
                }

                try{
        if(!st.empty()){
            System.out.println();
            System.out.println("Printing stack contents below: ");
            System.out.println("-----------------------------------");
        Iterator<Vertex> iter = st.iterator();

            while (iter.hasNext()){
                System.out.print(iter.next()+" ");
            }
                        System.out.println();
        }
        }
        catch(Exception e){
            System.err.println("The given graph is not a DAG, Hence, Stack is empty!");                
                }
                
    }
    
    List<Vertex> toplogicalOrder1(Graph g) { 
      /* Algorithm 1. Remove vertices with no incoming edges, one at a
      	 time, along with their incident edges, and add them to a list.
       */
        List<Vertex> list=DFS(g);
        return list;
    }
    
    Stack<Vertex> toplogicalOrder2(Graph g) {
      /* Algorithm 2. Run DFS on g and push nodes to a stack in the
      	 order in which they finish.  Write code without using global variables.
       */
        Stack<Vertex> st=DFS_Stack(g);
        return st;
   }
    
    public List<Vertex> DFS(Graph G){ 
            int count=0;
            for(Vertex u: G){
            u.seen = false;
            u.parent = null;
            
            //checking inDegrees for each vertex
            if(u.inDeg>0)
                count++;
            }
            
            //if all the vertices have inDegrees more than 0, then graph is not DAG
            if(count==G.verts.size()-1)
                return null;
            
        List<Vertex> list = new ArrayList<>();
       for(Vertex u: G){
        if (!u.seen)
        DFSVisit(u, list);
            }
       return list;
    }
    
    public void DFSVisit (Vertex u, List<Vertex> list){

        //if the current vertex is not visited then cotinue DFS
        if(!u.seen){
            u.seen = true;
            for(Edge e: u.Adj){
            Vertex v = e.otherEnd(u);
            if (!v.seen){
            v.parent = u;
            DFSVisit (v, list);
            }
            else notDAG=true;
            }
            list.add(u);
        }
    }
    
    public Stack<Vertex> DFS_Stack(Graph G){ 
            int count=0;
            for(Vertex u: G){
            u.seen = false;
            u.parent = null;
            
            //checking inDegrees for each vertex
            if(u.inDeg>0)
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
    
    public void DFSVisit (Vertex u, Stack<Vertex> st){

        //if the current vertex is not visited then cotinue DFS
        if(!u.seen){
            u.seen = true;
            for(Edge e: u.Adj){
            Vertex v = e.otherEnd(u);
            if (!v.seen){
            v.parent = u;
            DFSVisit (v, st);
            }
            else notDAG=true;
            }
            st.push(u);
        }
    }
}