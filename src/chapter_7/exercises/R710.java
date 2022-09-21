package chapter_7.exercises;

import chapter_6.stack.Stack;
import chapter_7.list.ArrayList;
import chapter_7.list.List;

/**
 * R-7.10
 *
 * Reimplement the ArrayStack class, from Section 6.1.2, using dynamic arrays (i.e dynamic ArrayLists)
 * to support unlimited capacity.
 */
public class R710<E> implements Stack<E> {
    private List<E> data = new ArrayList<>();

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void push(E e) throws IllegalStateException {
        data.add(size(), e);
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data.get(size() - 1);
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E answer = data.get(size()-1);
        data.remove(size()-1);
        return answer;
    }

    public static void main(String[] args) {
        R710 stack = new R710<>();

        for (int i = 10; i > 0; i--) {
            stack.push(i);
        }

        System.out.println("Top: " + stack.top());
        System.out.println("Size before popping all elements: " + stack.size());

        while (stack.size() > 0) {
            System.out.println("Pop: " + stack.pop());
        }

        System.out.println("Size after popping all elements: " + stack.size());

    }
}