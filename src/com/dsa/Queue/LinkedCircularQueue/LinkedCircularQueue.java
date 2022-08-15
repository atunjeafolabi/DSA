package com.dsa.Queue.LinkedCircularQueue;

import com.dsa.Queue.CircularQueue.CircularQueue;
import chapter_3.linked_list.circularly_linked_list.CircularlyLinkedList;

public class LinkedCircularQueue<E> implements CircularQueue<E> {

    private CircularlyLinkedList<E> list = new CircularlyLinkedList<>();

    @Override
    public void rotate() {
        list.rotate();
    }

    @Override
    public void enqueue(E element) {
        list.addFirst(element);
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
