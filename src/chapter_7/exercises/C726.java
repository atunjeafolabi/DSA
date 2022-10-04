package chapter_7.exercises;

import chapter_7.list.List;

/**
 * C-7.26
 *
 * Complete the previous exercise (7.25), except using a dynamic array
 * to provide unbounded capacity.
 */
public class C726<E> implements List<E> {

    int N;
    E[] data;
    private int zeroIndex = 0;
    int size = 0;

    public C726(int capacity) {
        data = (E[]) new Object[capacity];
        N = data.length;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
//        checkIndex(i);
        if (isEmpty()) {
            return null;
        }
        int modifiedIndex = (zeroIndex + i) % N;
        return data[modifiedIndex];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        int oModifiedIndex =  zeroIndex + i;
        E ans = data[oModifiedIndex % N];
        data[oModifiedIndex % N] = e;

        return ans;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
//        checkIndex(i);
        int oModifiedIndex = zeroIndex + i;
        if (isFull()) {
            resize(2 * N);                                                 // double the capacity
            oModifiedIndex = zeroIndex + i;
            // resize(2 * N, oModifiedIndex % N);
        }
        if (i == 0) {                                                               // element added at the beginning of the list
            zeroIndex = (zeroIndex - 1 + N) % N;
            data[zeroIndex] = e;
        } else if (oModifiedIndex % N == ((zeroIndex + size()) % N)) {              // element added at the end of the list
            data[(zeroIndex + size()) % N] = e;
        } else {                                                                    // element added in between existing elements in the list
            for (int k = zeroIndex + size() - 1; k >= (zeroIndex + i); k--) {
                data[(k+1+N) % N] = data[(k+ N) % N];
            }
            data[oModifiedIndex % N] = e;
        }
        size++;
    }

    /*
     * Resizes internal array to have given capacity >= size.
     */
    // protected void resize(int capacity, int indexToSkip) {
    protected void resize(int capacity) {
        E[ ] temp = (E[ ]) new Object[capacity];                        // safe cast; compiler may give warning
        for (int k=0; k < size; k++) {
//            if (k >= indexToSkip) {
//                temp[k+1] = data[k];
//                continue;
//            }
            temp[k] = data[zeroIndex++ % N];
        }
        data = temp;                                                    // start using the new array
        zeroIndex = 0;                                                  // reset zero index
        N = data.length;
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i);
        int oModifiedIndex = zeroIndex + i;
        int oLastIndex = zeroIndex + size() - 1;
        E elementToBeRemoved = data[(zeroIndex + i) % N];
        data[oModifiedIndex % N] = null;

        if (i == 0) {                                                   // element removed at the beginning of the list
            data[zeroIndex] = null;
            zeroIndex = (zeroIndex + 1) % N;
        } else if (oModifiedIndex == (oLastIndex % N)) {                // element removed at the end of the list
            data[oModifiedIndex] = null;
        } else {                                                        // element removed in between already existing elements in the list
            for (int k = oModifiedIndex; k < oLastIndex; k++) {
                data[k % N] = data[(k+1) % N];
                data[(k+1) % N] = null;
            }
        }
        size--;
        return elementToBeRemoved;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public int getCapacity() {
        return data.length;
    }

    private boolean isFull() {
        return size() == N;
    }

    private void checkIndex(int i) {
        if (i < 0 || i > N - 1) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public static void main(String[] args) {
        C726<String> arrayList = new C726<>(10);
        arrayList.add(0, "Sam");
        arrayList.add(1, "Ken");
        arrayList.add(2, "Ben");
        arrayList.add(3, "Den");
        arrayList.add(4, "Kate");
        System.out.println("N: " + arrayList.N);                // array capacity
        arrayList.add(5, "Lola");
        System.out.println("N: " + arrayList.N);
        arrayList.add(2, "xBen");
        arrayList.add(2, "yBen");
        arrayList.add(2, "zBen");
        arrayList.add(0, "Abeni");
        arrayList.add(9, "Ajani");
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.get(2));
        System.out.println(arrayList.get(3));
        System.out.println(arrayList.get(4));
        System.out.println(arrayList.get(5));
        System.out.println(arrayList.get(6));
        System.out.println(arrayList.get(7));
        System.out.println(arrayList.get(8));
        System.out.println(arrayList.get(9));
        System.out.println(arrayList.get(10));
        System.out.println("Size: " + arrayList.size());
        System.out.println("N: " + arrayList.N);

        System.out.println("Removed Element: " + arrayList.remove(2));
        System.out.println("Size after removing one element: " + arrayList.size());
        System.out.println("Removed another Element Again: " + arrayList.remove(2));
        System.out.println("Size after removing another element: " + arrayList.size());
        // arrayList.add(8, "Labake");
        // arrayList.add(9, "Asake");
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.get(2));
        System.out.println(arrayList.get(3));
        System.out.println(arrayList.get(4));
        System.out.println(arrayList.get(5));
        System.out.println(arrayList.get(6));
        System.out.println(arrayList.get(7));
        System.out.println(arrayList.get(8));
        System.out.println(arrayList.get(9));
        System.out.println(arrayList.get(10));
    }
}

