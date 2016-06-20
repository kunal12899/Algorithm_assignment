
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author kunal krishna
 */
 class StronglyConnectedComponent {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        
        Graph gp = Graph.readGraph(sc, true);
        
        System.out.println("No of strongly connected components: "+stronglyConnectedComponents(gp));
        
    }
    
    //main method calls below method which returns no of strongly connected components
    
    
    protected static int stronglyConnectedComponents(Graph g) {
        
       //run dfs on graph g
        Stack<Vertex> s = dfs(g);
        //run dfs on g^T with stack order got from performing dfs on g
        int ssc = dfsOnGraphTrans(s);
        
        return ssc;
    }
    
    public static Stack dfs(Graph g){
        
        //make all nodes univisited and parents for all as null.
        //here u=node
        //seen is boolean flag to mark nodes as either visited or unvisited 
        //u.parent will set parent for node u
        
        
        for(Vertex u : g){
            u.seen=false;
            u.parent=null;
        }
        int cno=0;
        Stack<Vertex> st = new Stack<>();
        
        for(Vertex u : g){
            
            if(!u.seen){
                //increment component count cno and then call dfsvisit method
                dfsVisit(u,st,++cno);
                
                
            }
            
            
        }
                
        return st;
    }
    
    public static void dfsVisit(Vertex u,Stack s,int cno){
        u.seen=true;
        u.cc = cno;
        for(Edge e : u.Adj){
            Vertex v=e.otherEnd(u);
            
            if(!v.seen){
                
                v.parent=u;
                
                dfsVisit(v,s,cno);
                
            }
        }
        s.push(u);
        
        
    }
    
    static int dfsOnGraphTrans(Stack<Vertex> s){
        
        Stack<Vertex> st = new Stack<>();
        int cn0=0;
        
        for(Vertex v : s){
            v.seen=false;
            v.parent=null;
        }
        
        
        while(!s.empty()){
            Vertex x = s.peek();
           
              if(!x.seen){
                 
                dfsVisitonTras(x, st,++cn0);
              }
              s.pop();
        }
        return cn0;
    }
    static void dfsVisitonTras(Vertex u,Stack s,int cn0){
        
       
        u.seen=true;
        u.cct = cn0;
        for(Edge e : u.revAdj){
           
            Vertex v=e.otherEnd(u);
             
            if(!v.seen){
                
                v.parent=u;
                
                dfsVisitonTras(v,s,cn0);
                
            }
        }
        s.push(u);
        
        
    }
    
    
    
}

