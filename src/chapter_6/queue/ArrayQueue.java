package chapter_6.queue;

/**
 * Code Fragment 6.10:
 * -------------------
 * Array-based implementation of a queue with a circular array that wraps around the end.
 * Modular arithmetic was used for wrapping around at the end of the array
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {

    private static final int CAPACITY = 1000;
    private int front;                                              // index of the front element in the queue
    private int storedSize;                                         // current number of elements in the queue
    private E[] data;                                               // array used for storage

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(final int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public void enqueue(E element) {
        if (storedSize == data.length) {
            throw new IllegalStateException("Queue is full!");
        }

        int avail = (front + storedSize) % data.length;              // modular arithmetic
        data[avail] = element;
        storedSize++;
    }

    /** Removes and returns the first element of the queue (null if empty). */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        E element = first();
        data[front] = null;
        front = (front + 1) % data.length;                           // modular arithmetic
        storedSize--;
        return element;
    }

    @Override
    public int size() {
        return storedSize;
    }

    @Override
    public boolean isEmpty() {
        return storedSize == 0;
    }

    /** Returns, but does not remove, the first element of the queue (null if empty). */
    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }

        return data[front];
    }
}
