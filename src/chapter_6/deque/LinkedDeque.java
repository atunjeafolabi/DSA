package chapter_6.deque;

import chapter_3.linked_list.doubly_linked_list.DoublyLinkedList;

/*
 * All the methods of the Deque interface has already been implemented by the DoublyLinkedList class.
 */
public class LinkedDeque<E> implements Deque<E> {
    DoublyLinkedList<E> linkedList = new DoublyLinkedList<>();

    public LinkedDeque(){}

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public E first() {
        return linkedList.first();
    }

    @Override
    public E last() {
        return linkedList.last();
    }

    @Override
    public void addFirst(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public void addLast(E e) {
        linkedList.addLast(e);
    }

    @Override
    public E removeFirst() {
        return linkedList.removeFirst();
    }

    @Override
    public E removeLast() {
        return linkedList.removeLast();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}