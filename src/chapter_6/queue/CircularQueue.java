package chapter_6.queue;

import chapter_6.queue.Queue;

/**
 * Code Fragment 6.12:
 * ------------------
 * A Java interface, CircularQueue, that extends the Queue ADT with a new rotate() method.
 *
 * @param <E>
 */
public interface CircularQueue<E> extends Queue<E> {
    /**
     * Rotates the front element of the queue to the back of the queue.
     * This does nothing if the queue is empty.
     */
    void rotate();
}
