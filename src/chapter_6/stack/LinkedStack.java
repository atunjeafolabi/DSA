package chapter_6.stack;

import chapter_3.linked_list.singly_linked_list.SinglyLinkedList;

/**
 * Code Fragment 6.4:
 * ------------------
 * Implementation of a Stack using a SinglyLinkedList as storage.
 *
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {

    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E top() {
        return list.first();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        String str = "";
        while (size() != 0) {
            str = str + " " + pop() + ", ";
        }

        return str;
    }

}