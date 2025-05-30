class Solution {
    private int nodeCount; // Number of nodes in the graph
    private List<Integer>[] graph; // Adjacency list representation of the graph

    // Calculates the closest meeting node from two starting nodes in a directed graph
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        nodeCount = edges.length;
        graph = new List[nodeCount];
      
        // Initialize graph adjacency lists
        Arrays.setAll(graph, x -> new ArrayList<>());
      
        // Build the graph from edge list representation
        for (int i = 0; i < nodeCount; ++i) {
            if (edges[i] != -1) {
                graph[i].add(edges[i]);
            }
        }
      
        // Use Dijkstra's algorithm to find shortest paths from both starting nodes
        int[] distancesFromNode1 = dijkstra(node1);
        int[] distancesFromNode2 = dijkstra(node2);
      
        // Initialize the minimum distance and answer node index
        int minDistance = Integer.MAX_VALUE;
        int closestNode = -1;
      
        // Iterate through nodes to find the closest meeting node
        for (int i = 0; i < nodeCount; ++i) {
            int maxOfBothDistances = Math.max(distancesFromNode1[i], distancesFromNode2[i]);
            if (maxOfBothDistances < minDistance) {
                minDistance = maxOfBothDistances;
                closestNode = i;
            }
        }
        return closestNode;
    }

    // Dijkstra's algorithm to find shortest path distances from a starting node 'startNode'
    private int[] dijkstra(int startNode) {
        int[] distances = new int[nodeCount];
      
        // Initialize distances to a large number
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0; // Distance to start node is zero
      
        // Priority queue to select the closest unvisited node in each step
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        priorityQueue.offer(new int[]{0, startNode});
      
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentNode = current[1];
          
            // Explore neighbors and update distances
            for (int neighbor : graph[currentNode]) {
                if (distances[neighbor] > distances[currentNode] + 1) {
                    distances[neighbor] = distances[currentNode] + 1;
                    priorityQueue.offer(new int[]{distances[neighbor], neighbor});
                }
            }
        }
      
        return distances; // Return array of distances from start node to all other nodes
    }
}