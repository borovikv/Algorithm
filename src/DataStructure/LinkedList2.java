package DataStructure;

import java.util.Iterator;

/**
 * Created by vborovic on 3/30/17.
 */
public class LinkedList2<T> implements Iterable<T> {
    private Node nil;

    LinkedList2 () {
        nil = new Node(null);
        nil.prev = nil;
        nil.next = nil;
    }

    public void add(T value) {
        Node x = new Node(value);
        x.prev = nil.prev;
        x.next = nil;
        nil.prev.next = x;
        nil.prev = x;
    }

    public void delete(T value) {
        Node n = search(value);
        if (n == null) return;
        
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    public Node search(T value) {
        Node x = nil.next;
        nil.key = value;
        while (x.key != value) {
            x = x.next;
        }
        nil.key = null;
        if (x == nil) {
            return null;
        }
        return x;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node n = nil.next;
        while (n != nil) {
            result.append(n.key).append(", ");
            n = n.next;
        }
        result.append("null");
        return "[" + result.toString() + "]";
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            Node n = nil.next;

            @Override
            public boolean hasNext() {
                return n != nil;
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
            if (key != null) {
                return key.toString();
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        LinkedList2<Integer> l = new LinkedList2<>();
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
