import java.util.HashMap;
import java.util.Map;

public class FibonacciDP {
    // Recursive approach (inefficient)
    public static long fibRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }
    
    // Top-down approach with memoization
    public static long fibMemoization(int n, Map<Integer, Long> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        if (n <= 1) {
            return n;
        }
        
        long result = fibMemoization(n - 1, memo) + fibMemoization(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    // Bottom-up approach with tabulation
    public static long fibTabulation(int n) {
        if (n <= 1) {
            return n;
        }
        
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // Space-optimized bottom-up approach
    public static long fibOptimized(int n) {
        if (n <= 1) {
            return n;
        }
        
        long prev = 0;
        long current = 1;
        
        for (int i = 2; i <= n; i++) {
            long next = prev + current;
            prev = current;
            current = next;
        }
        
        return current;
    }
    
    // Matrix exponentiation approach (O(log n) time complexity)
    public static long fibMatrix(int n) {
        if (n <= 1) {
            return n;
        }
        
        long[][] base = {{1, 1}, {1, 0}};
        long[][] result = matrixPower(base, n - 1);
        
        return result[0][0];
    }
    
    private static long[][] matrixPower(long[][] base, int power) {
        long[][] result = {{1, 0}, {0, 1}}; // Identity matrix
        
        while (power > 0) {
            if ((power & 1) == 1) {
                result = multiplyMatrix(result, base);
            }
            
            base = multiplyMatrix(base, base);
            power >>= 1;
        }
        
        return result;
    }
    
    private static long[][] multiplyMatrix(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int n = 40;
        Map<Integer, Long> memo = new HashMap<>();
        
        // Measure time for different approaches
        
        // Recursive approach
        long startTime = System.currentTimeMillis();
        long resultRecursive = fibRecursive(30); // Using smaller n because it's inefficient
        long endTime = System.currentTimeMillis();
        System.out.println("Recursive (n=30): " + resultRecursive);
        System.out.println("Time: " + (endTime - startTime) + " ms");
        
        // Memoization approach
        startTime = System.currentTimeMillis();
        long resultMemo = fibMemoization(n, memo);
        endTime = System.currentTimeMillis();
        System.out.println("\nMemoization (n=" + n + "): " + resultMemo);
        System.out.println("Time: " + (endTime - startTime) + " ms");
        
        // Tabulation approach
        startTime = System.currentTimeMillis();
        long resultTabulation = fibTabulation(n);
        endTime = System.currentTimeMillis();
        System.out.println("\nTabulation (n=" + n + "): " + resultTabulation);
        System.out.println("Time: " + (endTime - startTime) + " ms");
        
        // Optimized approach
        startTime = System.currentTimeMillis();
        long resultOptimized = fibOptimized(n);
        endTime = System.currentTimeMillis();
        System.out.println("\nOptimized (n=" + n + "): " + resultOptimized);
        System.out.println("Time: " + (endTime - startTime) + " ms");
        
        // Matrix exponentiation approach
        startTime = System.currentTimeMillis();
        long resultMatrix = fibMatrix(n);
        endTime = System.currentTimeMillis();
        System.out.println("\nMatrix Exponentiation (n=" + n + "): " + resultMatrix);
        System.out.println("Time: " + (endTime - startTime) + " ms");
        
        // Demonstrate memoization advantage by calculating sequence
        System.out.println("\nFibonacci Sequence (first 10 numbers):");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibOptimized(i) + " ");
        }
    }
}