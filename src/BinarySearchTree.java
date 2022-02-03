public class BinarySearchTree<T extends Comparable<T>> {
    int nodeCount = 0;
    Node root = null;

    public class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return nodeCount == 0;
    }

    public int size() {
        return nodeCount;
    }

    public boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(Node node, T elem) {
        if (node == null)
            return false;

        if (elem.compareTo(node.data) == 0)
            return true;

        if (elem.compareTo(node.data) < 0)
            return contains(node.left, elem);
        else
            return contains(node.right, elem);

    }

    public boolean add(T elem) {
        if (contains(elem))
            return false;
        root = add(root, elem);
        nodeCount++;
        return true;
    }

    private Node add(Node node, T elem) {
        if (node == null) {
            node = new Node(null, null, elem);
        } else {
            if (elem.compareTo(node.data) < 0) {
                node.left = add(node.left, elem);
            } else {
                node.right = add(node.right, elem);
            }
        }
        return node;
    }

    public boolean remove(T elem) {
        if (contains(root, elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T elem) {
        if (node == null)
            return null;

        int cmp = elem.compareTo(node.data);

        if (cmp < 0) {
            node.left = remove(node.left, elem);
        } else if (cmp > 0) {
            node.right = remove(node.right, elem);
        } else {
            // case when there is only a right subtree
            if (node.left == null) {
                return node.right;
            }
            // case when there is only a left subtree
            else if (node.right == null) {
                return node.left;
            }
            // case when there is both left and right subtree
            else if (node.left != null && node.right != null) {
                // get the min node on the right subtree
                Node minNode = findMin(node.right);

                // set the min node to the node you are trying to remove i.e. node
                node.data = minNode.data;
                node.right = remove(node.right, minNode.data);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        if (node.left == null)
            return node;

        return findMin(node.left);
    }

    public void preOrderTraversal(Node node) {
        if (node != null) {
            preOrderTraversal(node.left);
            visit(node);
            preOrderTraversal(node.right);
        }
    }

    public void inOrderTraversal(Node node) {
        if (node != null) {
            visit(node);
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            visit(node);
        }
    }

    private void visit(Node node) {
        System.out.println(node.data);
    }

}
