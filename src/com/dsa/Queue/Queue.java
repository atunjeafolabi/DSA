package com.dsa.Queue;

/**
 * Code Fragment 6.9:
 * ------------------
 * A Queue interface defining the queue ADT, with a standard FIFO protocol for insertions and removals.
 *
 * @param <E>
 */
public interface Queue<E> {

    /**
     * Adds element e to the back of queue.
     * @param element
     */
    void enqueue(E element);

    /**
     * Removes and returns the first element from the queue (or null if the queue is empty).
     * @return E
     */
    E dequeue();

    /**
     * Returns the first element of the queue, without removing it (or null if the queue is empty).
     * @return The first element of the queue
     */
    E first();

    /**
     * Returns the number of elements in the queue.
     * @return
     */
    int size();

    /**
     * Returns a boolean indicating whether the queue is empty.
     * @return {@code true} or {@code false}
     */
    boolean isEmpty();
}
