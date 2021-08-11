package com.dsa.Queue.LinkedQueue;

import com.dsa.Queue.Queue;
import com.dsa.linked_list.singly_linked_list.SinglyLinkedList;

/**
 * Code Fragment 6.11:
 * ------------------
 * Implementation of a FIFO Queue using a SinglyLinkedList.
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {

    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    @Override
    public void enqueue(E element) {
        list.addLast(element);
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }

    @Override
    public E first() {
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
}