
import java.util.Stack;

/**
 *
 * @author 
 *         Kunal Krishna
 *         
 */
public class DFS {
    
    boolean notDAG=false;
    public Stack<Edge> DFS(Vertex u){ 
        
        
        
        Stack<Edge> st = new Stack<>();
       
        if (!u.seen){
            
            DFSVisit(u, st);
        }
            
            
       return st;
    }
    
    public void DFSVisit (Vertex u, Stack<Edge> st){
        
        if(!u.seen){
            u.seen = true;
            for(Edge e: u.Adj){
                
                if(e.TransformedWt!=0){
                   
                    continue;
                }
                
                Vertex v = e.otherEnd(u);
                
                st.push(e);
                
                if (!v.seen){
                    
                    v.parent = u;
                    
                    DFSVisit (v, st);
                }
                else 
                    notDAG=true;
                
                
            }
            
        }
    
    }
}
