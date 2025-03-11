import java.util.*;

public class Graph {
    // Adjacency List representation
    private Map<Integer, List<Integer>> adjacencyList;
    
    // Constructor
    public Graph() {
        adjacencyList = new HashMap<>();
    }
    
    // Add a vertex to the graph
    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }
    
    // Add an edge between source and destination
    public void addEdge(int source, int destination) {
        // Add source vertex if it doesn't exist
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        
        // Add destination vertex if it doesn't exist
        adjacencyList.putIfAbsent(destination, new ArrayList<>());
        
        // Add edge from source to destination
        adjacencyList.get(source).add(destination);
        
        // For undirected graph, add edge from destination to source as well
        adjacencyList.get(destination).add(source);
    }
    
    // Remove an edge between source and destination
    public void removeEdge(int source, int destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(Integer.valueOf(destination));
        }
        
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(Integer.valueOf(source));
        }
    }
    
    // Remove a vertex and all its connections
    public void removeVertex(int vertex) {
        // Remove the vertex from all adjacency lists
        adjacencyList.values().forEach(e -> e.remove(Integer.valueOf(vertex)));
        
        // Remove the vertex's own adjacency list
        adjacencyList.remove(vertex);
    }
    
    // Get all neighbors of a vertex
    public List<Integer> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }
    
    // Display the graph
    public void displayGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Integer neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph();
        
        // Add vertices
        for (int i = 0; i < 5; i++) {
            graph.addVertex(i);
        }
        
        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        
        System.out.println("Graph representation:");
        graph.displayGraph();
        
        System.out.println("\nNeighbors of vertex 1:");
        for (Integer neighbor : graph.getNeighbors(1)) {
            System.out.print(neighbor + " ");
        }
        
        System.out.println("\n\nRemoving edge between 1 and 4...");
        graph.removeEdge(1, 4);
        
        System.out.println("Graph after removing edge:");
        graph.displayGraph();
        
        System.out.println("\nRemoving vertex 2...");
        graph.removeVertex(2);
        
        System.out.println("Graph after removing vertex:");
        graph.displayGraph();
    }
}