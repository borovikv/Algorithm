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
            root = node;
            return;
        }
        while (true) {
            if (node.key < current.key) {
                if (current.left == null) {
                    current.left = node;
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = node;
                    break;
                }
                current = current.right;
            }

        }
    }

    @SuppressWarnings("ConstantConditions")
    void delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeft = true;
        while (current.key != key && current != null) {
            parent = current;
            if (key < current.key) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isLeft = false;
            }
        }
        if (current == null) return;
        if(current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            Node min = min(current.right);
            if (min != current.right) {
                Node p = parent(min);
                p.left = min.right;
                min.right = current.right;
            }
            min.left = current.left;
            if (current == root) {
                root = min;
            } else if (isLeft) {
                parent.left = min;
            } else {
                parent.right = min;
            }
        }

    }

    private Node min(Node root) {
        Node min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    private Node parent(Node node) {
        Node parent = null;
        Node current = root;
        while (current != node) {
            parent = current;
            current = node.key < current.key ? current.left : current.right;
        }
        return parent;
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
        System.out.println(tree.parent(n));
    }
}

class Node {
    int key;
    Node left;
    Node right;

    public Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
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
}
