package DataStructure;

/**
 * Created by vborovic on 4/11/17.
 */
public class BinaryTree {
    Node root;

    @SuppressWarnings("ConstantConditions")
    Node find(int key) {
        Node current = root;
        while (current.key != key && current != null) {
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    void insert(int key) {
        Node newNode = new Node(key, null, null);
        insertNode(newNode);

    }

    private void insertNode(Node node) {
        Node current = root;
        if (current == null) {
            setRoot(node);
            return;
        }
        while (true) {
            if (node.key < current.key) {
                if (current.left == null) {
                    current.setLeft(node);
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.setRight(node);
                    break;
                }
                current = current.right;
            }
        }
    }

    private void setRoot(Node node) {
        root = node;
        root.parent = null;
    }

    void delete(int key) {
        Node nodeToDelete = find(key);
        if (nodeToDelete == null) return;

        Node replacement;
        if(nodeToDelete.left == null && nodeToDelete.right == null) {
            replacement = null;
        } else if (nodeToDelete.right == null) {
            replacement = nodeToDelete.left;
        } else if (nodeToDelete.left == null) {
            replacement = nodeToDelete.right;
        } else {
            replacement = min(nodeToDelete.right);
            if (replacement != nodeToDelete.right) {
                replacement.parent.setLeft(replacement.right);
                replacement.setRight(nodeToDelete.right);
            }

            replacement.setLeft(nodeToDelete.left);
        }
        removeNode(nodeToDelete, replacement);
    }

    private void removeNode(Node nodeToDelete, Node replacement) {
        if (nodeToDelete == root) {
            setRoot(replacement);
        } else if (nodeToDelete.isLeft()) {
            nodeToDelete.parent.setLeft(replacement);
        } else {
            nodeToDelete.parent.setRight(replacement);
        }
    }

    private Node min(Node root) {
        Node min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    void print(Node root) {
        if (root == null) return;
        print(root.left);
        System.out.println(root.key);
        print(root.right);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(74);
        tree.insert(26);
        tree.insert(14);

        tree.print(tree.root);
        System.out.println(tree.root);

        Node n = tree.find(14);
        System.out.println(n.key);

        tree.delete(25);
        System.out.println(tree.root);
    }
}

class Node {
    int key;
    Node left;
    Node right;
    Node parent;

    public Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
        parent = null;
    }

    @Override
    public String toString() {
        if (left != null && right != null) {
            return key + "(" + left + ", " + right + ")";
        } else if (left == null && right == null){
            return "" + key;
        } else if (left == null) {
            return key + "(" + right + ")";
        } else {
            return key + "(" + left + ")";
        }
    }

    public void setLeft(Node node) {
        this.left = node;
        if (node != null) node.parent = this;
    }

    public void setRight(Node node) {
        this.right = node;
        if (node != null) node.parent = this;
    }

    public boolean isLeft() {
        return parent.left == this;
    }
}
