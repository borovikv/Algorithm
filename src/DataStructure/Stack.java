package DataStructure;

import java.util.Arrays;

/**
 * Created by vborovic on 3/28/17.
 */
public class Stack<T> {
    private T[] storage;
    private int top = -1;

    public Stack(int size) {
        //noinspection unchecked
        storage = (T[]) new Object[size];
    }

    public boolean empty() {
        return top == -1;
    }

    public void push(T element) {
        if (top == storage.length - 1) {
            throw new RuntimeException();
        }
        storage[++top] = element;
    }

    public T pop() {
        if (empty()) {
            throw new RuntimeException();
        }
        return storage[top--];
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>(6);
        s.push(1);
        s.push(2);
        System.out.println(s);
        System.out.println(s.pop());
        System.out.println(s.pop());
//        System.out.println(s.pop());
    }
}