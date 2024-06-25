1. NEW SECTION - Algorithms

1) Question: Describe Dijkstra Algorithm.

Answer:
- The Dijkstra algorithm is a graph search algorithm that is used to find the shortest path between two nodes in a graph.
- The algorithm works by maintaining a priority queue of unvisited nodes, where the priority is determined by the current
  shortest known distance to the node from the starting point.
- The algorithm starts by initializing the distance of the starting node to 0 and the distance of all other nodes to infinity.
- The Dijkstra algorithm then repeatedly selects the node with the smallest distance from the priority queue,
  and for each of its unvisited neighbors, it calculates the tentative distance by adding the distance of the current node
  to the weight of the edge between the current node and the neighbor.
- If this tentative distance is less than the previously known distance of the neighbor, the distance is updated,
  and the neighbor is added to the priority queue.
- The algorithm continues until all nodes have been visited or the destination node has been reached.
- Once the destination node has been visited, the algorithm can trace back the shortest path by following the path of
  the lowest-cost predecessor of each node.
- The Dijkstra algorithm is an example of a single-source shortest path algorithm, as it can only be used to find
  the shortest path from a single source node to all other nodes.
- If you want to find the shortest path between two nodes, it can be used by running the algorithm from both nodes and
  compare the shortest path from each node to the other node.
- The Dijkstra algorithm can be implemented using a heap or priority queue data structure, which allows for efficient
  extraction of the node with the smallest distance.
- The time complexity of the Dijkstra algorithm is `O(V log V + E)` where `V` is the number of vertices,
  and `E` is the number of edges in the graph.

2) 