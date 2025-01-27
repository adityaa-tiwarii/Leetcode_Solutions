class Solution {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Floyd-Warshall algorith to determine transitive closure of prerequisites
        boolean[][] transitiveClosure = new boolean[numCourses][numCourses];
        List<Integer>[] graph = new List[numCourses];
        int[] inDegree = new int[numCourses]; // For topological sorting

        // Initialize adjacency list
        Arrays.setAll(graph, i -> new ArrayList<>());
      
        // Build graph and in-degree array from prerequisites
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
            ++inDegree[prerequisite[1]]; // Increment in-degree of successor
        }
      
        // Queue used for topological sorting
        Deque<Integer> queue = new ArrayDeque<>();
      
        // Adding all nodes with in-degree 0 to queue
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
      
        // Perform topological sort (Kahn's algorithm)
        while (!queue.isEmpty()) {
            int course = queue.poll();
          
            // Explore all neighbors of the current course
            for (int neighbor : graph[course]) {
              
                transitiveClosure[course][neighbor] = true;
              
                // Update transitive closure for all nodes that lead to current
                for (int preCourse = 0; preCourse < numCourses; ++preCourse) {
                    transitiveClosure[preCourse][neighbor] |= transitiveClosure[preCourse][course];
                }
              
                // Decrement in-degree of neighbor and if 0, add to queue
                if (--inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
      
        // Prepare the answer list to fulfill queries
        List<Boolean> answers = new ArrayList<>();
      
        // Check in the transitive closure if prerequisites are met
        for (int[] query : queries) {
            answers.add(transitiveClosure[query[0]][query[1]]);
        }
      
        // Return the list of results for each query
        return answers;
    }
}
