public class BinarySearchTree {
    private static class Node {
        int key;
        Node left, right;
        
        public Node(int key) {
            this.key = key;
            left = right = null;
        }
    }
    
    private Node root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    public void insert(int key) {
        root = insertRecursive(root, key);
    }
    
    private Node insertRecursive(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        
        if (key < root.key) {
            root.left = insertRecursive(root.left, key);
        } else if (key > root.key) {
            root.right = insertRecursive(root.right, key);
        }
        
        return root;
    }
    
    public boolean search(int key) {
        return searchRecursive(root, key);
    }
    
    private boolean searchRecursive(Node root, int key) {
        if (root == null) {
            return false;
        }
        
        if (root.key == key) {
            return true;
        }
        
        if (key < root.key) {
            return searchRecursive(root.left, key);
        }
        
        return searchRecursive(root.right, key);
    }
    
    public void inorderTraversal() {
        System.out.print("Inorder traversal: ");
        inorderRecursive(root);
        System.out.println();
    }
    
    private void inorderRecursive(Node root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.key + " ");
            inorderRecursive(root.right);
        }
    }
    
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] keys = {50, 30, 70, 20, 40, 60, 80};
        
        for (int key : keys) {
            bst.insert(key);
        }
        
        bst.inorderTraversal();
        
        int searchKey = 40;
        System.out.println("Found key " + searchKey + ": " + bst.search(searchKey));
        searchKey = 100;
        System.out.println("Found key " + searchKey + ": " + bst.search(searchKey));
    }
}