package DataStructure;

/**
 * Created by vborovic on 4/12/17.
 */
@SuppressWarnings({"SuspiciousNameCombination"})
public class RBTree {
    /*
    1 - Каждый узел является либо красным либо черным
    2 - Корень дерева является черным узлом
    3 - Каждый лист дерева является черным узлом
    4 - Если узел красный, то оба его дочерних узла черные
    5 - Для каждого узла все простые пити от него до листьев, являющихся потомками данного узла содержат одно и то же количество черных узлов
     */

    RBNode nil = new RBNode(-1, null, null, RBNode.Color.BLACK, null);
    RBNode root = nil;
    private final RBNode.Color RED = RBNode.Color.RED;
    private final RBNode.Color BLACK = RBNode.Color.BLACK;

    void insert(int key) {
        RBNode z = new RBNode(key, nil, nil, RBNode.Color.RED, null);
        RBNode y = nil;
        RBNode x = root;
        while (x != nil) {
            y = x;
            x = z.key < x.key ? x.left : x.right;
        }
        if (y == nil) {
            setRoot(z);
        } else if (z.key < y.key) {
            y.setLeft(z);
        } else {
            y.setRight(z);
        }
        insertFixup(z);
    }

    private void insertFixup(RBNode z) {
        while (z.parent.color == RED) {
            if (z.parent == z.parent.parent.left) {
                RBNode y = z.parent.parent.right;
                if (y.color == RED) {
                    z.parent.color = BLACK;      // case 1
                    y.color = BLACK;             // case 1
                    z.parent.parent.color = RED; // case 1
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {   // case 2
                        z = z.parent;            // case 2
                        leftRotate(z);           // case 2
                    }
                    z.parent.color = BLACK;       // case 3
                    z.parent.parent.color = RED;  // case 3
                    rightRotate(z.parent.parent); // case 3
                }
            } else {
                RBNode y = z.parent.parent.left;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }

    void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.setRight(y.left);

        if (x.parent == nil) {
            setRoot(y);
        } else if (x.isLeftChild()) {
            x.parent.setLeft(y);
        } else {
            x.parent.setRight(y);
        }

        y.setLeft(x);
    }

    void rightRotate(RBNode y) {
        RBNode x = y.left;
        y.setLeft(x.right);

        if (y.parent == nil) {
            setRoot(x);
        } else if (y.isLeftChild()) {
            y.parent.setLeft(x);
        } else {
            y.parent.setRight(x);
        }

        x.setRight(y);
    }

    void setRoot(RBNode node) {
        root = node;
        root.parent = nil;
    }

    public static void main(String[] args) {
        RBTree t = new RBTree();
        t.insert(1);
        t.insert(2);
        t.insert(3);
        t.insert(4);
        t.insert(5);
        t.insert(0);
        System.out.println(t.root);
    }
}

class RBNode {
    int key;
    Color color;
    RBNode parent;
    RBNode left;
    RBNode right;

    public RBNode(int key, RBNode left, RBNode right, Color color, RBNode parent) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.color = color;
        this.parent = parent;
    }

    @Override
    public String toString() {
        String result;
        if (left != null && right != null) {
            result = key + "(" + left + ", " + right + ")";
        } else if (left == null && right == null) {
            result = "" + key;
        } else if (left == null) {
            result = key + "(" + right + ")";
        } else {
            result = key + "(" + left + ")";
        }
        return color + result;
    }

    public void setLeft(RBNode node) {
        left = node;
        if (node != null) {
            node.parent = this;
        }
    }

    public void setRight(RBNode node) {
        right = node;
        if (node != null) {
            node.parent = this;
        }
    }

    public boolean isLeftChild() {
        return parent != null && parent.left == this;
    }

    enum Color {RED, BLACK}
}

