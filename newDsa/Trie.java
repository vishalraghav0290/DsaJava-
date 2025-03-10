import java.util.HashMap;
import java.util.Map;

public class Trie {
    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        
        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
    
    private final TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    // Insert a word into the trie
    public void insert(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        
        current.isEndOfWord = true;
    }
    
    // Search if the word exists in the trie
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }
    
    // Check if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }
    
    // Find the node that matches the word/prefix
    private TrieNode findNode(String str) {
        TrieNode current = root;
        
        for (char c : str.toCharArray()) {
            TrieNode node = current.children.get(c);
            if (node == null) {
                return null;
            }
            current = node;
        }
        
        return current;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        
        // Insert words
        trie.insert("apple");
        trie.insert("application");
        trie.insert("app");
        trie.insert("banana");
        
        // Search for words
        System.out.println("Search for 'apple': " + trie.search("apple"));
        System.out.println("Search for 'app': " + trie.search("app"));
        System.out.println("Search for 'appl': " + trie.search("appl"));
        System.out.println("Search for 'ban': " + trie.search("ban"));
        
        // Check for prefixes
        System.out.println("Starts with 'app': " + trie.startsWith("app"));
        System.out.println("Starts with 'ban': " + trie.startsWith("ban"));
        System.out.println("Starts with 'cat': " + trie.startsWith("cat"));
    }
}