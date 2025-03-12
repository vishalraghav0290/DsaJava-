import java.util.Arrays;

public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count; // Number of components
    
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        count = size;
        
        // Initialize each element as a separate set
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    // Find the root of the set that x belongs to (with path compression)
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }
    
    // Union two sets (by rank)
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) {
            return; // Already in the same set
        }
        
        // Union by rank: attach smaller rank tree under root of higher rank tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // If ranks are the same, make one as root and increment its rank
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        
        count--; // Reduce the number of components
    }
    
    // Check if two elements are in the same set
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    // Get the number of components (disjoint sets)
    public int getCount() {
        return count;
    }
    
    public static void main(String[] args) {
        int n = 10;
        UnionFind uf = new UnionFind(n);
        
        // Connect some elements
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(0, 2);
        uf.union(4, 6);
        uf.union(0, 4);
        
        System.out.println("Number of components: " + uf.getCount());
        System.out.println("Are 0 and 9 connected? " + uf.connected(0, 9));
        System.out.println("Are 1 and 5 connected? " + uf.connected(1, 5));
        
        // Display parent array
        System.out.println("Parent array: " + Arrays.toString(uf.parent));
        
        // Display actual root for each element
        System.out.print("Root of each element: ");
        for (int i = 0; i < n; i++) {
            System.out.print(uf.find(i) + " ");
        }
    }
}