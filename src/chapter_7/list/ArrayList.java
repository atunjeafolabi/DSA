package chapter_7.list;

/**
 * Code Fragment 7.3, 7.4 and 7.5:
 * ------------------------------
 * An implementation of a simple ArrayList class with unbounded capacity.
 *
 * The main idea behind this implementation is that, whenever the storage array
 * becomes full, we double the capacity to accommodate more additions.
 *
 * @param <E>
 */
public class ArrayList<E> implements List<E> {

    private static final int CAPACITY = 16;                         // initial capacity of storage array
    private int size = 0;                                           // number of elements in array
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
        if (size == data.length) {                                  // Not enough capacity,
            /*
             * so double the current capacity, while leaving a
             * space for the new element to be added at index i
             */
            resize(2 * data.length, i);
        }else {
            for (int k=size-1; k >= i; k--) {                       // start by shifting rightmost
                data[k + 1] = data[k];
            }
        }
        data[i] = e;                                                // ready to place the new element
        size++;
    }

    /*
     * Resizes internal array to have given capacity >= size.
     */
    protected void resize(int capacity, int indexToSkip) {
        E[ ] temp = (E[ ]) new Object[capacity];                // safe cast; compiler may give warning
         for (int k=0; k < size; k++) {
             if (k >= indexToSkip) {
                 temp[k+1] = data[k];
                 continue;
             }
             temp[k] = data[k];
         }
         data = temp;                                           // start using the new array
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i);
        E removed = data[i];

        for (int k = i; k < size - 1; k++) {
            data[k] = data[k+1];
        }

        data[size - 1] = null;                                  // help Garbage Collector
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

    /**
     * R-7.7
     *
     * Consider an implementation of the array list ADT using a dynamic array, but instead of copying the elements into
     * an array of double the size (that is, from N to 2N) when its capacity is reached, we copy the elements into an
     * array with ⌈N/4⌉ additional cells, going from capacity N to N + ⌈N/4⌉. Show that performing a sequence of n
     * push operations (that is, insertions at the end) still runs in O(n) time in this case.
     */
    public void resizeIncreaseByQuarter() {
        int newCapacity = size() + (int) Math.ceil(size() / 4);
        E[] temp = (E[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    /**
     * R-7.5
     *
     * The java.util.ArrayList includes a method, trimToSize(), that replaces the underlying array with one whose
     * capacity precisely equals the number of elements currently in the list. Implement such a method for our
     * dynamic version of the ArrayList class from Section 7.2.
     */
    public void trimToSize() {
        E[] temp = (E[]) new Object[size()];
        for (int i = 0; i < size(); i++) {
            temp[i] = data[i];
        }
        data = temp;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size(); i++) {
            sb.append(get(i));

            if (i != size() - 1)
                sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(8);
        list1.add(0,3);
        list1.add(1,5);
        list1.add(2,2);
        list1.add(3,8);
        list1.add(4,6);
        list1.add(5,7);
        list1.add(6,1);
        list1.add(7,9);

        /*
         * add one more entry at index 3 to make the list resize
         */
        list1.add(3, 15);

        System.out.println(list1.toString());


    }
}
