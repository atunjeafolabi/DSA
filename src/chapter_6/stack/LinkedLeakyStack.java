package chapter_6.stack;

import chapter_3.linked_list.singly_linked_list.SinglyLinkedList;

/*
 * P-6.39
 *
 * Repeat the previous problem using a singly linked list for storage,
 * and a maximum capacity speciﬁed as a parameter to the constructor.
 */
public class LinkedLeakyStack<E> implements Stack<E> {

    private SinglyLinkedList<E> list;
    int capacity;

    public LinkedLeakyStack (int capacity) {
        list = new SinglyLinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public void push(E e) {
        if (isFull()) {
            list.removeLast();
        }
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

    public boolean isFull() {
        return size() == capacity;
    }
}
