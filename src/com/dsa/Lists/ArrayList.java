package com.dsa.Lists;

/**
 * Code Fragment 7.3:
 * -----------------
 * An implementation of a simple ArrayList class with bounded capacity.
 *
 * @param <E>
 */
public class ArrayList<E> implements List<E> {

    private static final int CAPACITY = 16;
    private int size = 0;
    private E[] data;

    public ArrayList(final int capacity) {
        data = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(CAPACITY);
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i);
        return data[i];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i);
        E removed = data[i];
        data[i] = e;
        return removed;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException {
        checkIndex(i);
        if (size == data.length)                    // not enough capacity
            throw new IllegalStateException("Array is full");
        for (int k=size-1; k >= i; k--)             // start by shifting rightmost
            data[k+1] = data[k];
        data[i] = e;                                // ready to place the new element

        size++;
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i);
        E removed = data[i];

        for (int k = i; k < size - 1; k++) {
            data[k] = data[k+1];
        }

        data[size - 1] = null;                      // help Garbage Collector
        size--;
        return removed;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    protected void checkIndex(int i) {
        if (i < 0 || i > data.length) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }


//    // ----- Method added on resolution of the exercise R-7.5
//    public void trimToSize() {
//        E[] temp = (E[]) new Object[this.size()];
//
//        for (int i = 0; i < this.size(); i++) {
//            temp[i] = this.data[i];
//        }
//
//        // Alternative to 'for'
////        System.arraycopy(this.data, 0, temp, 0, this.size());
//
//        this.data = temp;
//        temp = null;            // GC helper
//    }
}
