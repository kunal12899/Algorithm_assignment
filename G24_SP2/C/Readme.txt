G24: SP2:C

1. Program's main method calls 
Graph.readGraph method and returned graph is used in further code.
2. "stronglyConnectedComponents" method is called which in turns work as follows:
	1. Calls dfs on graph G
	2. Computed G^T i.e. just used revAdj 		list here which works as transpose of 		graph.
	3. On stack order call dfs on G^T
Graph is read from console, so it takes user input.
