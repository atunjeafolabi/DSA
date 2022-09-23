package chapter_7.exercises;


import chapter_6.stack.Stack;
import chapter_7.list.ArrayList;
import chapter_7.list.List;

/*
 * R-7.2
 *
 * Implementation of the stack ADT using an array list for storage.
 */
public class R72<E> implements Stack<E> {
    List<E> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.add(0, e);
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        while (i < size()) {
            sb.append(list.get(i));

            if (i != size() - 1)
                sb.append(", ");
            i++;
        }

        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        Stack<String> stack = new R72<>();

        stack.push("red");
        stack.push("green");
        stack.push("blue");

        System.out.println(stack.toString());

    }
}
