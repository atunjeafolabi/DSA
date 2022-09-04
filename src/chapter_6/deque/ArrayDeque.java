package chapter_6.deque;

/**
 * Array-based implementation of a dequeue with a circular array that wraps around the end.
 * Modular arithmetic was used for wrapping around at the end of the array
 */
public class ArrayDeque<E> implements Deque<E> {

    private static final int CAPACITY = 1000;
    private int front = 0;
    private int storedSize;
    private E[] data;

    public ArrayDeque() {
        this(CAPACITY);
    }

    public ArrayDeque(final int capacity) {
        data = (E[]) new Object[capacity];
    }

    public void addFirst(E element) {
        if (storedSize == data.length) {
            throw new IllegalStateException("Queue is full!");
        }

        front = (front - 1 + data.length) % data.length;

        data[front] =  element;
        storedSize++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E element = first();
        data[front] = null;
        front = (front + 1) % data.length;
        storedSize--;
        return element;
    }

    @Override
    public void addLast(E element) {
        if (storedSize == data.length) {
            throw new IllegalStateException("Queue is full!");
        }

        int availBack = (front + storedSize) % data.length;
        data[availBack] = element;
        storedSize++;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        E e = last();
        int back = front + storedSize - 1;
        data[back] =  null;
        storedSize--;

        return e;
    }

    @Override
    public int size() {
        return storedSize;
    }

    @Override
    public boolean isEmpty() {
        return storedSize == 0;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }

        return data[front];
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }

        int back = front + storedSize - 1;
        return data[back];
    }
}
