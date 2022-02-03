import java.util.HashSet;

public class RemoveDuplicates {
    HashSet<Integer> set = new HashSet<>();
    static Node head;

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    // using exta memory - O(N) memory, O(1) time
    public void removeDuplicates(Node node) {
        Node root = node;
        while (root != null) {
            set.add(root.val);
            Node next = root.next;

            while (next != null && set.contains(next.val)) {
                next = next.next;
            }
            root.next = next;
            root = next;
        }
    }

    // without using extra memory - O(1) memory, O(N^2) time
    public void removeDuplicatesWithoutExtraMemory(Node node) {
        Node root = node;

        while (root != null) {
            removeFutureDuplicates(root);
            root = root.next;
        }
    }

    private void removeFutureDuplicates(Node node) {
        int val = node.val;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
    }

    public void printList(Node node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        RemoveDuplicates list = new RemoveDuplicates();
        list.head = new Node(3);
        list.head.next = new Node(5);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(2);
        list.head.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next.next = new Node(10);

        System.out.println("Before: ");
        list.printList(list.head);

        // list.removeDuplicates(list.head);
        list.removeDuplicatesWithoutExtraMemory(list.head);

        System.out.println("\nAfter: ");
        list.printList(list.head);
    }
}
