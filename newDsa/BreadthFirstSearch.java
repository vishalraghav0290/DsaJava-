import java.util.*;

public class BreadthFirstSearch {
    private Map<String, List<String>> graph;
    
    public BreadthFirstSearch() {
        graph = new HashMap<>();
    }
    
    public void addEdge(String source, String destination) {
        graph.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        graph.computeIfAbsent(destination, k -> new ArrayList<>()); // Ensure the destination is in the graph
    }
    
    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        visited.add(start);
        queue.add(start);
        
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            System.out.print(vertex + " ");
            
            for (String neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        BreadthFirstSearch bfsExample = new BreadthFirstSearch();
        
        // Add edges to the graph
        bfsExample.addEdge("A", "B");
        bfsExample.addEdge("A", "C");
        bfsExample.addEdge("B", "D");
        bfsExample.addEdge("B", "E");
        bfsExample.addEdge("C", "F");
        bfsExample.addEdge("E", "F");
        
        System.out.println("BFS traversal starting from vertex A:");
        bfsExample.bfs("A");
    }
}