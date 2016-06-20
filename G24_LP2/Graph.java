/**
 * Class to represent a graph
 * 
 *
 */

import java.io.*;
import java.util.*;

class Graph implements Iterable<Vertex> {
    public List<Vertex> verts; // array of vertices
    public int numNodes; // number of verices in the graph
     ArrayList<Edge> edgeList ;
    /**
     * Constructor for Graph
     * 
     * @param size
     *            : int - number of vertices
     */
    Graph(int size) {
        
	numNodes = size;
	verts = new ArrayList<>(size + 1);
	verts.add(0, null);
	// create an array of Vertex objects
	for (int i = 1; i <= size; i++){
            verts.add(i, new Vertex(i));
            
        }
	   
            
    }
    
    public void init(){
        for(Vertex m : verts){
               if(m!=null){
                   m.seen=false;
               }
           }
    }
    
    ArrayList getBaseList(){
        return (ArrayList) verts;
    }
    void printNodes(){
        for(Vertex in : verts){
            
            System.out.println(""+in);
        }
    }
    void printAdjList(){
        
        VertexIterator vIt = (VertexIterator) iterator();
        System.out.println("Adj List");
        while(vIt.hasNext()){
            Vertex x = vIt.next();
            System.out.print(x+"-->\t");
            for(int i=0;i<x.Adj.size();i++){
                System.out.print(x.Adj.get(i));
                System.out.print(" "+x.Adj.get(i).Weight+"\t");
            }
            System.out.println("");
        }
        
    }
    void calcInDeg(){
        VertexIterator vIt = (VertexIterator) iterator();
       
        while(vIt.hasNext()){
            Vertex x = vIt.next();
            x.indeg=0;
            for(int i=0;i<x.Adj.size();i++){
                x.indeg++;
            }
            System.out.println("");
        }
    }
    
    void printInDeg(){
        VertexIterator vIt = (VertexIterator) iterator();
        while(vIt.hasNext()){
            Vertex x = vIt.next();
            System.out.print(x+"-->"+x.indeg);
            System.out.println("");
        }
        System.out.println("");
        
    }
    ArrayList<Edge> edgeList(){
        VertexIterator vIt = (VertexIterator) iterator();
       edgeList= new ArrayList();
        while(vIt.hasNext()){
            Vertex x = vIt.next();
           
            for(int i=0;i<x.Adj.size();i++){
                edgeList.add(x.Adj.get(i));
            }
            
        }
        return edgeList;
    }
    void printRevAdjList(){
        
        VertexIterator vIt = (VertexIterator) iterator();
        System.out.println("RevAdj List");
        while(vIt.hasNext()){
            Vertex x = vIt.next();
            System.out.print(x+"-->\t");
            for(int i=0;i<x.revAdj.size();i++){
                System.out.print(x.revAdj.get(i));
                System.out.print(" "+x.revAdj.get(i).Weight+"\t");
            }
            System.out.println("");
        }
        
    }
    /**
     * Method to add an edge to the graph
     * 
     * @param a
     *            : int - one end of edge
     * @param b
     *            : int - other end of edge
     * @param weight
     *            : int - the weight of the edge
     */
    void addEdge(int a, int b, int weight) {
	Vertex u = verts.get(a);
	Vertex v = verts.get(b);
	Edge e = new Edge(u, v, weight);
	u.Adj.add(e);
	v.Adj.add(e);
        
    }
    
    /**
     * Method to add an arc (directed edge) to the graph
     * 
     * @param a
     *            : int - the head of the arc
     * @param b
     *            : int - the tail of the arc
     * @param weight
     *            : int - the weight of the arc
     */
    void addDirectedEdge(int a, int b, int weight) {
        
        //System.out.println("reached here with "+a+" : "+b+" : "+weight);
	Vertex head = verts.get(a);
	Vertex tail = verts.get(b);
	Edge e = new Edge(head, tail, weight);
        
	head.Adj.add(e);
	tail.revAdj.add(e);
    }
    
    void addWantEdge(int a, int b, int weight,Edge enew){
        Vertex head = verts.get(a);
	Vertex tail = verts.get(b);
	Edge e = new Edge(head, tail, weight,enew);
        
	head.Adj.add(e);
	tail.revAdj.add(e);
        
        
    }

//    void graphTrans(int a,int b,int weight){
//        
//        Vertex head = verts.get(a);
//	Vertex tail = verts.get(b);
//	Edge e = new Edge(tail, head, weight);
//        
//	head.Adj.add(e);
//	tail.revAdj.add(e);
//    }
    /**
     * Method to create an instance of VertexIterator
     */
    public Iterator<Vertex> iterator() {
	return new VertexIterator();
    }

    /**
     * A Custom Iterator Class for iterating through the vertices in a graph
     * 
     *
     * @param <Vertex>
     */
    private class VertexIterator implements Iterator<Vertex> {
	private Iterator<Vertex> it;
	/**
	 * Constructor for VertexIterator
	 * 
	 * @param v
	 *            : Array of vertices
	 * @param n
	 *            : int - Size of the graph
	 */
	private VertexIterator() {
	    it = verts.iterator();
	    it.next();  // Index 0 is not used.  Skip it.
	}

	/**
	 * Method to check if there is any vertex left in the iteration
	 * Overrides the default hasNext() method of Iterator Class
	 */
	public boolean hasNext() {
	    return it.hasNext();
	}

	/**
	 * Method to return the next Vertex object in the iteration
	 * Overrides the default next() method of Iterator Class
	 */
	public Vertex next() {
	    return it.next();
	}

	/**
	 * Throws an error if a vertex is attempted to be removed
	 */
	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }
    
   
    
    public static Graph readGraphFromFile(String filename) throws IOException{
       
        System.out.println("fhdljepofjefpo");
        BufferedReader br = null;
        String line ="";
        String splitby = "\t";
        String split=" ";
        Graph g = null;
        
        try{
            
            br= new BufferedReader(new FileReader(filename));
            line=br.readLine();
            
            String[] count = line.split(split);
             g= new Graph(Integer.parseInt(count[0].trim()));
            while((line = br.readLine())!=null){
                
                String[] nodes= line.split(splitby);
                
               
              int u = Integer.parseInt(nodes[0].trim());
                
              int v = Integer.parseInt(nodes[1].trim());
              int w = Integer.parseInt(nodes[2].trim());
            
	    
		g.addEdge(u,v,w);
               // g.graphTrans(u, v, w);
	    
	}
	
	
                
            
//            for(int i=0;i<nodes.length;i++){
//                
//            }
            
        }catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        
        return g;
        
    }
    Edge getUnseenEdge(Vertex v){
        
       
        return v.Adj.iterator().next();
        
    }
    
    

    public static Graph readGraph(Scanner in, boolean directed) {
	// read the graph related parameters
        //Scanner scnwe= new Scanner(new File("prim1.txt"));
	       //System.out.println("Enter no of vertices");
        int n = in.nextInt(); // number of vertices in the graph
            //System.out.println("Enter no of edges");
	int m = in.nextInt(); // number of edges in the graph

	// create a graph instance
	Graph g = new Graph(n);
	for (int i = 0; i < m; i++) {
            //System.out.println("Enter node 1,node 2,wt");
	    int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
            
	    if(directed) {
		g.addDirectedEdge(u, v, w);
               // g.graphTrans(u, v, w);
	    } else {
		g.addEdge(u, v, w);
	    }
	}
	in.close();
	return g;
    }
}