import java.util.*;

public class DepthFirstSearch {
    private Map<String, List<String>> graph;
    
    public DepthFirstSearch() {
        graph = new HashMap<>();
    }
    
    public void addEdge(String source, String destination) {
        graph.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        graph.computeIfAbsent(destination, k -> new ArrayList<>()); // Ensure the destination is in the graph
    }
    
    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        dfsUtil(start, visited);
    }
    
    private void dfsUtil(String vertex, Set<String> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");
        
        // Recur for all the vertices adjacent to this vertex
        for (String neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, visited);
            }
        }
    }
    
    public static void main(String[] args) {
        DepthFirstSearch dfsExample = new DepthFirstSearch();
        
        // Add edges to the graph
        dfsExample.addEdge("A", "B");
        dfsExample.addEdge("A", "C");
        dfsExample.addEdge("B", "D");
        dfsExample.addEdge("B", "E");
        dfsExample.addEdge("C", "F");
        dfsExample.addEdge("E", "F");
        
        System.out.println("DFS traversal starting from vertex A:");
        dfsExample.dfs("A");
    }
}