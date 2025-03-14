import java.util.*;

public class AStarSearch {
    private static class Node implements Comparable<Node> {
        int x, y;
        int g; // Cost from start to current node
        int h; // Heuristic cost from current node to goal
        int f; // Total cost (g + h)
        Node parent;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.g = 0;
            this.h = 0;
            this.f = 0;
            this.parent = null;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return x == node.x && y == node.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    // Directions: up, right, down, left
    private static final int[][] DIRECTIONS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    
    // Calculate heuristic (Manhattan distance)
    private static int heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    
    // A* Search Algorithm
    public static List<Node> findPath(int[][] grid, int startX, int startY, int goalX, int goalY) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Validate start and goal positions
        if (startX < 0 || startX >= cols || startY < 0 || startY >= rows || 
            goalX < 0 || goalX >= cols || goalY < 0 || goalY >= rows ||
            grid[startY][startX] == 1 || grid[goalY][goalX] == 1) {
            return Collections.emptyList(); // Invalid positions
        }
        
        Node startNode = new Node(startX, startY);
        Node goalNode = new Node(goalX, goalY);
        
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Node> closedList = new HashSet<>();
        
        openList.add(startNode);
        
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            
            // Check if we've reached the goal
            if (current.x == goalNode.x && current.y == goalNode.y) {
                return reconstructPath(current);
            }
            
            closedList.add(current);
            
            // Check all neighbors
            for (int[] direction : DIRECTIONS) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];
                
                // Skip if out of bounds or obstacle
                if (newX < 0 || newX >= cols || newY < 0 || newY >= rows || grid[newY][newX] == 1) {
                    continue;
                }
                
                Node neighbor = new Node(newX, newY);
                
                // Skip if already evaluated
                if (closedList.contains(neighbor)) {
                    continue;
                }
                
                // Calculate new g score
                int tentativeG = current.g + 1; // Assuming cost of 1 for each step
                
                // Check if this path is better
                boolean isInOpenList = openList.contains(neighbor);
                if (!isInOpenList || tentativeG < neighbor.g) {
                    // Update node
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = heuristic(neighbor, goalNode);
                    neighbor.f = neighbor.g + neighbor.h;
                    
                    if (!isInOpenList) {
                        openList.add(neighbor);
                    } else {
                        // Update position in the priority queue
                        openList.remove(neighbor);
                        openList.add(neighbor);
                    }
                }
            }
        }
        
        return Collections.emptyList(); // No path found
    }
    
    // Reconstruct path from goal to start
    private static List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
    
    // Utility method to print the grid with path
    public static void printGridWithPath(int[][] grid, List<Node> path) {
        int rows = grid.length;
        int cols = grid[0].length;
        char[][] display = new char[rows][cols];
        
        // Initialize the display grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    display[i][j] = '#'; // Wall
                } else {
                    display[i][j] = '.'; // Open space
                }
            }
        }
        
        // Mark the path
        for (int i = 0; i < path.size(); i++) {
            Node node = path.get(i);
            if (i == 0) {
                display[node.y][node.x] = 'S'; // Start
            } else if (i == path.size() - 1) {
                display[node.y][node.x] = 'G'; // Goal
            } else {
                display[node.y][node.x] = '*'; // Path
            }
        }
        
        // Print the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(display[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // Create a grid (0 = open, 1 = obstacle)
        int[][] grid = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        // Define start and goal positions
        int startX = 0, startY = 0;
        int goalX = 7, goalY = 5;
        
        List<Node> path = findPath(grid, startX, startY, goalX, goalY);
        
        if (path.isEmpty()) {
            System.out.println("No path found!");
        } else {
            System.out.println("Path found with " + (path.size() - 1) + " steps:");
            printGridWithPath(grid, path);
            
            System.out.print("Path coordinates: ");
            for (Node node : path) {
                System.out.print("(" + node.x + "," + node.y + ") ");
            }
        }
    }
}