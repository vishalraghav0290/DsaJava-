public class LinkedList {
    private static class Node {
        int data;
        Node next;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    
    public LinkedList() {
        this.head = null;
    }
    
    public void append(int data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            return;
        }
        
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    
    public void prepend(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
    
    public void delete(int data) {
        if (head == null) {
            return;
        }
        
        if (head.data == data) {
            head = head.next;
            return;
        }
        
        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }
        
        if (current.next != null) {
            current.next = current.next.next;
        }
    }
    
    public void display() {
        Node current = head;
        System.out.print("Linked list: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        
        list.append(1);
        list.append(2);
        list.prepend(0);
        list.append(3);
        
        list.display();
        
        list.delete(2);
        System.out.print("After deletion: ");
        list.display();
    }
}