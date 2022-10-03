package chapter_7.exercises;

import chapter_7.list.List;

/**
 * C-7.25
 *
 * Give an array-based list implementation, with ﬁxed capacity, treating the array circularly so that it achieves O(1)
 * time for insertions and removals at index 0, as well as insertions and removals at the end of the array list.
 * Your implementation should also provide for a constant-time get method.
 */
public class C725<E> implements List<E> {

    int size = 0;
    private int zeroIndex = 0;
    private final static int CAPACITY = 10;
    int N = CAPACITY;

    E[] data = (E[]) new Object[CAPACITY];

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i);
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
        checkIndex(i);
        if (isFull()) {
            throw new IllegalStateException("List is full");
        }
        int oModifiedIndex = zeroIndex + i;
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

    private boolean isFull() {
        return size() == CAPACITY;
    }

    private void checkIndex(int i) {
        if (i < 0 || i > CAPACITY - 1) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public static void main(String[] args) {
        C725<String> arrayList = new C725<>();
        arrayList.add(0, "Sam");
        arrayList.add(1, "Ken");
        arrayList.add(2, "Ben");
        arrayList.add(3, "Den");
        arrayList.add(4, "Kate");
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
        System.out.println("Size: " + arrayList.size());

        System.out.println("Removed Element: " + arrayList.remove(2));
        System.out.println("Size after removing one element: " + arrayList.size());
        System.out.println("Removed Element Again: " + arrayList.remove(2));
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
    }
}
