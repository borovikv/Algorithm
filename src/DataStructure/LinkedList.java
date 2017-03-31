package DataStructure;

import java.util.Iterator;

/**
 * Created by vborovic on 3/30/17.
 */
public class LinkedList<T> implements Iterable<T> {
    private Node head;
    private Node tail;

    public void add(T value) {
        Node n = new Node(value);
        if (head == null) {
            head = n;
        } else {
            n.prev = tail;
            tail.next = n;
        }
        tail = n;
    }

    public void delete(T value) {
        Node n = search(value);
        if (n == null) return;

        if (n.prev != null) {
            n.prev.next = n.next;
        } else {
            head = n.next;
        }

        if (n.next != null) {
            n.next.prev = n.prev;
        }
    }

    public Node search(T value) {
        Node x = head;
        while (x != null && x.key != value) {
            x = x.next;
        }
        return x;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node n = head;
        while (n != null) {
            result.append(n.key).append(", ");
            n = n.next;
        }
        return "[" + result.toString() + "]";
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            Node n = head;

            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public T next() {
                T key = n.key;
                n = n.next;
                return key;
            }
        };
    }

    private class Node {
        Node prev;
        Node next;
        T key;

        public Node(T value) {
            key = value;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        System.out.println(l);

        System.out.println("*");
        for (Integer i: l) {
            System.out.println(i);
        }
        System.out.println("*");

        System.out.println(l.search(1));
        System.out.println(l.search(2));

        l.delete(2);
        System.out.println(l);
        l.delete(3);
        System.out.println(l);
        l.delete(1);
        System.out.println(l);
    }
}
