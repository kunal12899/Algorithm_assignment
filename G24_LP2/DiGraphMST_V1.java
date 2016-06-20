
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author 
 *         Kunal Krishna
 *         
 */
public class DiGraphMST_V1 {
    
    static long MSTWt =0;
    static Vertex source =null;
    
    
    public static void main(String[] args) throws FileNotFoundException{
       
       
       Scanner sc = new Scanner(new File("C:\\Spring16\\Impl6301002\\LP2\\lp2-data\\lp2-ck - Copy.txt"));
       //Scanner sc = new Scanner(new File(args[0]));
       Graph g = Graph.readGraph(sc, true);
       long startTime = System.currentTimeMillis();
        recursiveMST(g);
        System.out.println("wt of mst : "+MSTWt); 
        long end = System.currentTimeMillis();
        long diff = end-startTime;
        System.out.println("time taken : "+diff);
       
    }
    
     static void recursiveMST(Graph g){
        
       Stack<Edge> st = new Stack();
       DFS dfs = new DFS();
       ArrayList<Edge> out = new ArrayList<>();
        
        //init graph
       
       g.init();
       
       /* 1. . Transform weights so that every node except r has an incoming edge of weight 0:
        for u ∈ V − {r} do
        Let du be the weight of a minimum weight edge into u.
        for (p, u) ∈ E do
        w(p, u) ← w(p, u) − du
        Reduction in weight of MST by above transformation = Pdu, where u ∈ V − r.
    */
      
        Edge du=null;
        for(Vertex u : g.verts){
            if(u==null)
                continue;
            if(u.name==1)
               continue;
            du=calcMinEdgeWt(u);
            //System.out.println("min wt edge found  : "+du);
            if(!du.visited)
                //out.add(du);         //min wt edge added to graph
            du.visited=true;
           //MSTWt+=du.Weight;
           calcZeroWtEdge(u, du);
        }
      
       /* 2. Let G0 = (V, Z) be the subgraph of G containing all edges of 0-weight, i.e., Z = {e ∈ E :
        w(e) = 0}. Run DFS/BFS in G0, from r. Note that we are using only edges of G with
        0-weight. If all nodes of V are reached from r, then return this DFS/BFS tree as MST.
        */
        
        Vertex root = g.verts.get(1);
        st=dfs.DFS(root);
             
        if(st.size()==g.verts.size()-2){
            System.out.println("MST found");
            printList(out);
        }
            
        
        else{
            System.out.println("cycles are formed : only "+st.size()+ " Vetex/vertices is/are discovered");
            printList(out);
        }
            
        
       
        
        /*3. If there is no spanning tree rooted at r in G0, then there is a 0-weight cycle. Find a 0-weight
            cycle as follows:
            (a) Find a node z that is not reachable from r in G0, in the above search.
            (b) Walk backward from z in G0, using incoming edges at each node visited. Every node
            except r has a 0-edge coming into it, and so this walk can keep going forever. Since r
            has no path to z using 0-edges, this walk will never get to r. There are only a finite
            number of nodes. So, some node x will be repeated on this walk. The path from x to
            itself on this walk is composed of 0-weight edges, and this gives a 0-weight cycle C.
            --all unseen nodes from result of DFS(root) are unreachable,hence finding cycles on each of them*/
       
        for(Vertex z : g.verts){
            if(z==null)     //ignore null nodes
                continue;
            if(z.seen)      //ignore already visited node in DFS(root)
                continue;
            source=z;       //this is node x : which will be repeated on walk back
            identifyCycle(z);

        }
        
        
        /*4. Shrink cycle C into a single node c. There may be many edges from the nodes of C to a
        node u outside the cycle. These are replaced by a single edge. For each edge (a, u) in G, with
        a ∈ C and u 6∈ C, introduce the edge (c, u) of weight w(a, u).
        Similarly, for edges of G that are going into C, do the following. For each edge (u, a) in G,
        with u 6∈ C and a ∈ C, introduce the edge (u, c) of weight w(u, a).
        If there are multiple edges (c, u), keep just one edge with minimum weight, and record the
        corresponding edge of G. Similarly, process multiple edges (u, c) by replacing each multi-edge
        by a single edge of minimum weight.
        The new graph has fewer nodes than the original graph, and the MSTs of the two graphs
        have equal weight.  */
        
        
        
       //Add new node in verts replacing formed cycle : c
            g.verts.add(g.verts.size(),new Vertex(g.verts.size()));
            g.verts.get(g.verts.size()-1).newNode=true;
            Vertex c = g.verts.get(g.verts.size()-1);
        
        //4.a.adding new node--> outside cycle edges
       
        for(Edge au : g.edgeList()){
            
            Vertex a = au.From;
            Vertex u = au.To;
            
            if(!a.active && u.active && !u.seen){
               
                for(Edge edgeFromNew : c.Adj){
                    if(edgeFromNew.To==u){
                        if(au.Weight<edgeFromNew.Weight)
                            edgeFromNew.want=au;
                    }
                    else{
                        g.addWantEdge(c.name,u.name,au.Weight, au);
                    }
                        
                }
            }
       
        } 
       
            
        //4b. adding new edge from G-->to cycle new node
        Edge actualCheapEdgeOnCycle=null;
        for(Edge ua : g.edgeList()){
            
            Vertex u=ua.From;   //ua is an edge from G-->cycle 
            Vertex a =ua.To;    
            
            if(u.active && !a.active){
                actualCheapEdgeOnCycle=ua;
                g.addDirectedEdge(u.name,c.name, ua.Weight);
            }
        }
        
        //keep cheapest edge incoming on new node c
        Edge cheapEdgeOnC = calcMinEdgeWt(c);
        calcZeroWtEdge(c, cheapEdgeOnC);
        
        /*5. Recursively find an MST of the smaller graph. This MST has exactly one edge into c, and
        this edge corresponds to some actual edge (u, a) in the graph before shrinking, where a ∈ C.
        Now, expand node c, and include the edges of the 0-weight cycle C. Since the total weight
        of the cycle is 0, adding it to the MST does not increase its weight. But node a will have
        2 incoming edges: edge (u, a) from the MST, and one edge from the cycle. Delete the edge
        coming into node a in the cycle, to get an MST of the original graph. Return this MST.*/
        
        //init graph
        g.init();
        
        st=dfs.DFS(g.verts.get(1));
        
       //adding edges from cycle node to outside: 
        
      for(Edge in : c.Adj){
          
          st.push(in.want);
      }
        
        
        //adding cycle edges into MST
        
        st.push(actualCheapEdgeOnCycle);
        
        
        
        
        
        
        for(Vertex u : g.verts){
            
            if(u==null)
                continue;
            if(!u.active){
                //System.out.println("came here with inactive node : "+u);
                for(Edge include : u.revAdj){
                    if(!include.From.active && !include.To.active){
                        include.From.seen=true;
                        include.To.seen=true;
                         st.push(include);
                         //System.out.println("pushing edge : "+include+ " on stack");
                    }
                       
                }
            }
        }
        
        
    for (Edge st2 : st) {
             
        if(st2==null)
            continue;
        if(st2.To==c)
            continue;
        if(st2.From==c)
            continue;
        if(st2.To==actualCheapEdgeOnCycle.To && !st2.From.active)
            continue;
          
        if(st2.From.mst && st2.To.mst)
            continue;
        if(st2.To.mst && !st2.From.mst)
            continue;
             
                 
        out.add(st2);
        MSTWt+=st2.Weight;
        st2.To.mst=true;
              
            
        }

        if(g.verts.size()<51){
            System.out.println("MST ");
            for(Edge st2:st)
                System.out.println(""+st2);
        }

        if(!checkAllNodesVisited(g))
            recursiveMST(g);
       
    }

    



//check if all nodes are visited 
    
    public static boolean checkAllNodesVisited(Graph g){
        boolean flag=false;
        for(Vertex u : g.verts){
            if(u==null)
                continue;
            if(u.seen)
                flag=true;
            else
                flag=false;
        }
        
        return flag;
    }


//calculate edge with min wt for given vertex
    public static Edge calcMinEdgeWt(Vertex u){
        
        //System.out.println("vertex start under test: "+u);
        int min=Integer.MAX_VALUE;
        Edge e = null;
        for (Edge x : u.revAdj) {
            //System.out.println("edge under test"+x);
            if(x.Weight<min){
                min = x.Weight;
                e= x;
            }
            
            //System.out.println("min wt edge till time for : "+u+" : "+e);
        }
        //System.out.println("min wt edge for: "+u+" : "+e);    
        return e;
    }
    
    //calculate zero wt edge
    public static void calcZeroWtEdge(Vertex u,Edge e){
        
        //System.out.println("came here in zerowt calc with : "+u+" with edge :  "+e);
        Edge x=null;
            for (Edge revAdj : u.revAdj) {
                
                x=revAdj;
                x.TransformedWt-=e.Weight;
                //System.out.println("new transfrmd wt of "+x+" : "+x.TransformedWt);
            }
    }
    
    public static void printList(ArrayList<Edge> out){
        
        //System.out.println("MST : ");
        for(Edge e : out)
            System.out.print(" "+e);
        System.out.println("");
    }
    
    public static void identifyCycle(Vertex x){
        
        

        for(Edge zero : x.revAdj){
            Vertex other =zero.otherEnd(x);
            if(zero.TransformedWt==0 && !x.seen){
                //System.out.println("ccame here in identifyCycle with : "+x);
                
                if(x.active){
                    x.active=false;         //marking nodes in cycle as inactive
                   // cycleContents.add(x);
                    //System.out.println("cycle formed with : "+source+" : is :"+x);
                    identifyCycle(other);
                    
                }
                else{
                   // x.active=true;
                    return;
                }
                    
            }
            else
                continue;
            }
        }
        
    
}
