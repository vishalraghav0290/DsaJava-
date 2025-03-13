import java.util.*;

public class TopologicalSort {
    private Map<Integer, List<Integer>> graph;
    private int vertices;
    
    public TopologicalSort(int vertices) {
        this.vertices = vertices;
        graph = new HashMap<>();
        
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    
    // Add a directed edge from source to destination
    public void addEdge(int source, int destination) {
        graph.get(source).add(destination);
    }
    
    // Topological Sort using Depth-First Search
    public List<Integer> topologicalSort() {
        LinkedList<Integer> result = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> temporaryMark = new HashSet<>();
        
        // Visit each vertex
        for (int i = 0; i < vertices; i++) {
            if (!visited.contains(i)) {
                if (!dfs(i, visited, temporaryMark, result)) {
                    // Cycle detected
                    return Collections.emptyList();
                }
            }
        }
        
        return result;
    }
    
    private boolean dfs(int vertex, Set<Integer> visited, Set<Integer> temporaryMark, LinkedList<Integer> result) {
        // Cycle detection
        if (temporaryMark.contains(vertex)) {
            return false; // Cycle detected
        }
        
        if (visited.contains(vertex)) {
            return true; // Already processed
        }
        
        temporaryMark.add(vertex); // Mark current vertex as temporarily visited
        
        // Visit all neighbors
        for (int neighbor : graph.get(vertex)) {
            if (!dfs(neighbor, visited, temporaryMark, result)) {
                return false; // Cycle detected
            }
        }
        
        temporaryMark.remove(vertex); // Remove temporary mark
        visited.add(vertex); // Mark as permanently visited
        result.addFirst(vertex); // Add to the front of the result list
        
        return true;
    }
    
    // Kahn's algorithm for Topological Sort (alternative approach)
    public List<Integer> topologicalSortKahn() {
        List<Integer> result = new ArrayList<>();
        
        // Calculate in-degree of all vertices
        int[] inDegree = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : graph.get(i)) {
                inDegree[neighbor]++;
            }
        }
        
        // Queue for storing vertices with 0 in-degree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        int visitedCount = 0;
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            visitedCount++;
            
            // Reduce in-degree of all neighbors
            for (int neighbor : graph.get(vertex)) {
                inDegree[neighbor]--;
                
                // If in-degree becomes 0, add to queue
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        // Check if there was a cycle
        if (visitedCount != vertices) {
            return Collections.emptyList(); // Cycle detected
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        TopologicalSort ts = new TopologicalSort(6);
        
        // Add edges for a Directed Acyclic Graph (DAG)
        ts.addEdge(5, 2);
        ts.addEdge(5, 0);
        ts.addEdge(4, 0);
        ts.addEdge(4, 1);
        ts.addEdge(2, 3);
        ts.addEdge(3, 1);
        
        System.out.println("Topological Sort (DFS):");
        List<Integer> sortedOrder = ts.topologicalSort();
        for (int vertex : sortedOrder) {
            System.out.print(vertex + " ");
        }
        
        System.out.println("\n\nTopological Sort (Kahn's algorithm):");
        List<Integer> sortedOrderKahn = ts.topologicalSortKahn();
        for (int vertex : sortedOrderKahn) {
            System.out.print(vertex + " ");
        }
        
        // Create a graph with a cycle to test cycle detection
        TopologicalSort cycleGraph = new TopologicalSort(3);
        cycleGraph.addEdge(0, 1);
        cycleGraph.addEdge(1, 2);
        cycleGraph.addEdge(2, 0); // Creates a cycle
        
        System.out.println("\n\nTopological Sort on a graph with cycle:");
        List<Integer> cycleResult = cycleGraph.topologicalSort();
        if (cycleResult.isEmpty()) {
            System.out.println("Cycle detected, no valid topological sort possible");
        } else {
            for (int vertex : cycleResult) {
                System.out.print(vertex + " ");
            }
        }
    }
}