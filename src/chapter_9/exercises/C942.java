package chapter_9.exercises;

import chapter_9.priority_queue.DefaultComparator;

/**
 * C-9.42
 *
 * Implement the binarySearch algorithm (see Section 5.1.3) using a
 * Comparator for an array with elements of generic type E.
 */
public class C942<E> {

    private E[] data;
    private DefaultComparator<E> comp;

    public C942(E[] arr, DefaultComparator<E> comparator) {
        data = arr;
        comp = comparator;
    }

    public boolean search(E target) {
        return binarySearch(data, target, 0, data.length - 1, comp);
    }

    private boolean binarySearch(E[] data, E target, int low, int high, DefaultComparator comparator) {
        if (low > high) {
            return false;
        }

        int mid = (low + high) / 2;
        if (comparator.compare(target, data[mid]) == 0)
            return true;
        else if (comparator.compare(target, data[mid]) < 0)
            return binarySearch(data, target, low, mid - 1, comparator);
        else
            return binarySearch(data, target, mid + 1, high, comparator);

    }

    public static void main(String[] args) {

        String[] arr = {"A", "B", "C", "D"};
        C942<String> c942 = new C942(arr, new DefaultComparator<String>());

        System.out.println(c942.search("B"));
        System.out.println(c942.search("E"));
    }

}
