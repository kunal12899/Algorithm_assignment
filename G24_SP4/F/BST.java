/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kunal krishna
 */
import java.util.*;

public class BST<T extends Comparable<? super T>> {
    class Entry<T> {
        T element;
        Entry<T> left, right, parent;

        Entry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
            element = x;
	    left = l;
	    right = r;
	    parent = p;
        }
    }
    
    Entry<T> root;
    int size;

    BST() {
	root = null;
	size = 0;
    }

    // Find x in subtree rooted at node t.  Returns node where search ends.
    Entry<T> find(Entry<T> t, T x) {
	Entry<T> pre = t;
	while(t != null) {
	    pre = t;
	    int cmp = x.compareTo(t.element);
	    if(cmp == 0) {
		return t;
	    } else if(cmp < 0) {
		t = t.left;
	    } else {
		t = t.right;
	    }
	}
	return pre;
    }

    // Is x contained in tree?
    public boolean contains(T x) {
	Entry<T> node = find(root, x);
	return node == null ? false : x.equals(node.element);
    }

    // Add x to tree.  If tree contains a node with same key, replace element by x.
    // Returns true if x is a new element added to tree.
    public boolean add(T x) {
	if(size == 0) {
	    root = new Entry<>(x, null, null, null);
	} else {
	    Entry<T> node = find(root, x);
	    int cmp = x.compareTo(node.element);
	    if(cmp == 0) {
		node.element = x;
		return false;
	    }
	    Entry<T> newNode = new Entry<>(x, null, null, node);
	    if(cmp < 0) {
		node.left = newNode;
	    } else {
		node.right = newNode;
	    }
	}
	size++;
	return true;
    }

    // Remove x from tree.  Return x if found, otherwise return null
    public T remove(T x) {
	T rv = null;
	if(size > 0) {
	    Entry<T> node = find(root, x);
	    if(x.equals(node.element)) {
		rv = node.element;
		remove(node);
		size--;
	    }
	}
	return rv;
    }

    // Called when node has at most one child.  Returns that child.
    Entry<T> oneChild(Entry<T> node) {
	return node.left == null? node.right : node.left;
    }

    // Remove a node from tree
    void remove(Entry<T> node) {
	if(node.left != null && node.right != null) {
	    removeTwo(node);
	} else {
	    removeOne(node);
	}
    }

    // remove node that has at most one child
    void removeOne(Entry<T> node) {
	if(node == root) {
	    root = oneChild(root);
	} else {
	    Entry<T> p = node.parent;
	    if(p.left == node) {
		p.left = oneChild(node);
	    } else {
		p.right = oneChild(node);
	    }
	}
    }

    // remove node that has two children
    void removeTwo(Entry<T> node) {
	Entry<T> minRight = node.right;
	while(minRight.left != null) {
	    minRight = minRight.left;
	}
	node.element = minRight.element;
	removeOne(minRight);
    }
   // static AVLEntry<Integer> avl; 
    public static void main(String[] args) {
	BST<Integer> t = new BST<>();
        boolean result;
	Scanner in = new Scanner(System.in);
	while(in.hasNext()) {
	    int x = in.nextInt();
	    if(x > 0) {
		System.out.print("Add " + x + " : ");
		t.add(x);
		t.printTree();
               
	    } else if(x < 0) {
		System.out.print("Remove " + x + " : ");
		t.remove(-x);
		t.printTree();
	    } else {
		Comparable[] arr = t.toArray();
		System.out.print("Final: ");
		for(int i=0; i<t.size; i++) {
		    System.out.print(arr[i] + " ");
		}
		System.out.println();
                
                result= t.verifyAVLTree(t.root);
                System.out.println(" Is it AVL: "+result);
		return;
	    }
            
	}
    
    }
   
    // Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
	Comparable[] arr = new Comparable[size];
	inOrder(root, arr, 0);
	return arr;
    }

    // Recursive in-order traversal of tree rooted at "node".
    // "index" is next element of array to be written.
    // Returns index of next entry of arr to be written.
    int inOrder(Entry<T> node, Comparable[] arr, int index) {
	if(node != null) {
	    index = inOrder(node.left, arr, index);
	    arr[index++] = node.element;
	    index = inOrder(node.right, arr, index);
	}
	return index;
    }

    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print(" " + node.element);
	    printTree(node.right);
	}
    }
   
    Entry<T> prev;
     boolean isBST()  {
       Entry<T> prev=null;
        return isBST(root);
    }
 
    /* will return true if tree is binary search tree 
     First travese the tree in inorder fashion and will keep track of previous node
     */
    boolean isBST(Entry<T> node){
        if (node != null){
            if (!isBST(node.left))
                return false;
            if (prev != null && node.element.compareTo(prev.element)<0 )
                return false;
            prev = node;
            return isBST(node.right);
        }
        return true;
    }
    
 
    
    
    /*  verify AVL tree By checking below Three condition:
      1) If the element of the node is null
      2) check it is BST or not
      3) check height of balance of the tree
      if above three condition satisfy then it will return true
    */
    
    // I am calling this function after every insertion of node.
    
    public boolean verifyAVLTree(Entry<T> t){
      if(t != null){
        if(isBST(t)){                   
              int rHeight,lHeight;          
              if(t.element!= null){   
                  rHeight=height(t.right);
                  lHeight=height(t.left);     
                     if(Math.abs(rHeight-lHeight)>1){
                        return false;                     
                      }
                     else{ 
                         verifyAVLTree(t.right);
                         verifyAVLTree(t.left);
                       }   
                }
              else{
            return false;} //if element is null
        }
        else{
        return false;}// if tree is not BST
      } 
      else{
     return true; 
      } //if reach up to leaf node
   return true;
    }
    
    public int height(Entry<T> t){
        if(t==null)
            return 0;
        else
            return (1+Math.max(height(t.left),height(t.right)));
    }   
}

/*
Sample input:
5 10 3 4 2 

Output:
Add 5 : [1] 3
Add 10 :[2] 3 10
Add 3 : [3] 3 5 10
Add 4 : [4] 3 4 5 10
Add 2 : [5] 2 3 4 5 10
Final: 2 3 4 5 10 
 Is it AVL: true


Sample input:
5 10 3 4 2 

Output:
Add 5 : [1] 3
Add 10 :[2] 3 10
Add 3 : [3] 3 5 10
Add 4 : [4] 3 4 5 10
Add 2 : [5] 2 3 4 5 10
Final: 2 3 4 5 10 
 Is it AVL: true


 Sample input 2: 1 2 3 4 5
Add 1 : [1] 1
Add 2 : [2] 1 2
Add 3 : [3] 1 2 3
Add 4 : [4] 1 2 3 4
Add 5 : [5] 1 2 3 4 5
Final: 1 2 3 4 5 
 Is it AVL: false

*/
