public class All_Paths_from_Source_Lead_to_Destination {
  // There are 2 cases it should return false.
  //  case 1: it encounters a node that has no outgoing edges, but it is not destination.
  //  case 2: it has cycle.
  class Solution_bfs {
    // Runtime: O(n + e); Space: O(n + e)
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
      Set<Integer>[] graph = new Set[n]; // node => set of its next nodes

      for (int i = 0; i < n; i++) { // array of set
        graph[i] = new HashSet<Integer>();
      }

      int[] inDegrees = new int[n];
      for (int[] edge : edges) {
        graph[edge[0]].add(edge[1]);
        inDegrees[edge[1]]++;
      }

      Queue<Integer> q = new LinkedList<Integer>();
      q.add(source);

      while (!q.isEmpty()) {
        int cur = q.poll();
        if (graph[cur].size() == 0 && cur != destination) return false;

        for (int nei : graph[cur]) {
          if (inDegrees[nei] < 0) { // i.e. a cycle
            return false;
          }

          inDegrees[nei]--;
          q.add(nei);
        }
      }
      return true;
    }
  }

  class Solution_dfs {
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
      Map<Integer, Set<Integer>> graph = new HashMap<>();
      for (int[] edge : edges) {
        Set<Integer> neighbours = graph.getOrDefault(edge[0], new HashSet<>());
        neighbours.add(edge[1]);
        graph.put(edge[0], neighbours);
      }

      if (graph.get(destination) != null) return false;
      boolean[] isVisited = new boolean[n];
      isVisited[source] = true;
      return dfs(graph, isVisited, source, destination);
    }

    private boolean dfs(Map<Integer, Set<Integer>> graph, boolean[] isVisited, int source, int destination) {
      if (source == destination)  return true;
      
      Set<Integer> neighbours = graph.getOrDefault(source, new HashSet<>());
      if (neighbours.size() == 0) return false;

      for (int neib : neighbours) {
        if (isVisited[neib]) return false; // cycle spotted

        isVisited[neib] = true;
        if (!dfs(graph, isVisited, neib, destination)) return false;
        isVisited[neib] = false; // backtracking
      }
      return true;
    }
  }
}