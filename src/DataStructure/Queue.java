package DataStructure;

import java.util.Arrays;

/**
 * Created by vborovic on 3/28/17.
 */
public class Queue<T> {
    final T[] a;
    int tail = 0;
    int head = 0;
    int size = 0;

    public Queue(int size) {
        //noinspection unchecked
        a = (T[]) new Object[size];
    }

    public void enqueue(T element) {
        if (full()) {
            throw new RuntimeException("full: " + element);
        }
        a[tail] = element;
        if (tail == a.length - 1) {
            tail = 0;
        } else {
            tail++;
        }
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            throw new RuntimeException("empty" + Arrays.toString(a));
        }
        T result = a[head];
        a[head] = null;
        if (head == a.length - 1) {
            head = 0;
        } else {
            head++;
        }
        size--;
        return result;
    }

    private boolean full() {
        return a.length == size;
    }

    @Override
    public String toString() {
        return Arrays.toString(a);
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>(3);
        q.enqueue(1);
        q.enqueue(2);
        System.out.println(q);
        System.out.println(q.dequeue());
//        System.out.println(q.dequeue());
        System.out.println(q.head + " " + q.tail + " " + 3);

        q.enqueue(3);
        q.enqueue(4);
        System.out.println(q);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

    }
}
