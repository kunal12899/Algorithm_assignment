
/*** @author rbk ***/
/** Program to illustrate effect of calling height/depth recursively from each node of a tree
 *  Sample runs:
 *  > java Tree  (uses default depth of 100)
 *  Answer: 4613 Time: 10 msec.
 *
 *  > java Tree 1000
 *  Answer: 24745 Time: 34 msec.
 *
 *  > java Tree 10000
 *  ## Stack overflow.  Need to ask for a larger stack for program to execute.
 *
 *  > java -Xss256m Tree 10000
 *  Answer: 13783 Time: 2382 msec.
 *
 *  > java -Xss256m Tree 100000
 *  Answer: 8811 Time: 293937 msec.
 *
 *  Challenge: rewrite program so that it runs quickly (0.3s for 100K, 4s for 1M)
 * 
 * 
 * /*** @author rbk **
 * Modified by : 
 *               Kunal Krishna
 *               
 * 
 * Modification done : Instead of finding depth and height recursively we are storing height and depth of each node 
 *                      as a class field and while evaluating some function height and depth are accessed in constant time.
 * 
 * Depth :100
 * Answer: 4613 Time: 1 msec.
 * 
 * Depth : 1000
 * Answer: 24745 Time: 4 msec.
 * 
 * Depth : 10000
 * Answer: 13783 Time: 8 msec.
 * 
 * Depth : 100000
 * Answer: 8811 Time: 22 msec.
 * 
 * 
 */


import java.lang.Math;
import java.util.Queue;
import java.util.LinkedList;

public class Tree {
    TreeNode root;
     static long ht;
        static long dpth;
    static final long p = 999959;
    Tree() { root = new TreeNode(0); }

    /** binary tree node */
    public class TreeNode {
	long data;
	TreeNode left, right, parent;
        long depth;
        long height;
       
	TreeNode(long x) { data = x;  left = null;  right = null;  parent = null;depth=0;height=0; }

	/** create a new node that is attached to par node as left child if goLeft is true;
	 *  otherwise, the new node is attached to par as right child
	 */
	TreeNode(long x, TreeNode par, boolean goLeft) {
	    data = x;  left = null;  right = null;  parent = par;height=0;depth=0;
	    if (goLeft) { par.left = this; } 
	    else { par.right = this; }
	}
    } // end of TreeNode class


    // If there is a command line argument, it is used as the depth of the tree generated
    public static void main(String[] args) {
	long depth = 10000;
       
	if(args.length > 0) depth = Long.parseLong(args[0]);
         ht=depth;
	Tree x = new Tree();

	// Create a tree composed of 2 long paths 
	TreeNode last = x.root;
	for(long i=1; i<=depth; i++) { last = x.new TreeNode(i, last, true); }

	last = x.root;
	for(long i=1; i<=depth; i++) { last = x.new TreeNode(depth+i, last, false); }
	    
	Timer timer = new Timer();
	// The tree is visited in level order, using a Queue.  Depth and height of each node is computed recursively
	long minSum = Long.MAX_VALUE;
	Queue<TreeNode> Q = new LinkedList<>();
	Q.add(x.root);
        
        x.root.height++;
        x.root.depth++;
        
	while(!Q.isEmpty()) {
	    TreeNode cur = Q.remove();
            if(ht<0 && dpth>depth)
                break;
	    if(cur != null) {
                
                cur.height=(ht--);
                cur.depth=dpth++;
                
		minSum = Math.min(minSum, someFunction(cur));
                
                 
		Q.add(cur.left);  Q.add(cur.right);
	    }
	}
	System.out.println("Answer: " + minSum + " " + timer.end());
    }
    
   

    static long someFunction(TreeNode cur) {
        
        return rotater(rotater(cur.depth*cur.depth)%p + rotater(cur.height*cur.height)%p);
        
        
    }

   static long rotater(long h) {
       
	h ^= (h >>> 20) ^ (h >>> 12);
	h = (h ^ (h >>> 7) ^ (h >>> 4));
        
	return h;
    }

    
}