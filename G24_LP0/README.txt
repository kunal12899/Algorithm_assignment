1. Please enter the absolute path of the input file as command line arguments, when executing the program to find Euler Tour.


2. You can read input from Scanner input.

1. Calculate indegree of all nodes
2. If all indegrees are even, search fro Euler tour by following below steps:
	1. mark any node as source and add its first edge connecting to its first neighbour
	2. From that neighbour, go on traversing the graph 
			a.check if unvisited neighbours are more than one don't go back to source 
			b.if unvisited neighbour is only source then go to source and now mark source as unvisted neighbour of previous source and repeat the process
			 
else

Given graph is does not have an Euler tour.
