I have used the BST.java which is available on elearning. 

I have added below method: 

isBST()
public boolean verifyAVLTree(Entry<T> t)
public int height(Entry<T> t)

VerifyAVLTree function used the main method once we build our tree.

Below steps is going to perform:

First it build the tree.
After building the tree it will check three condition
first: Is it BST or not
second: Does it contain null value in the element field
third: check the height balance of the each node.


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