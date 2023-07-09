package chapter_9.exercises;

import chapter_9.priority_queue.AbstractPriorityQueue;
import chapter_9.priority_queue.Entry;

import java.util.Arrays;

/**
 * C-9.28
 *
 * <p>
 * Reimplement the SortedPriorityQueue using a Java array.
 * Make sure to maintain removeMin’s O(1) performance.
 */
public class C928<K, V> extends AbstractPriorityQueue<K, V> {

    private Entry<K, V>[] list;
    final int CAPACITY;
    private int size;

    public C928(int capacity) {
        super();
        CAPACITY = capacity;
        list = new PQEntry[CAPACITY];
    }

    @Override
    public int size() {
        return this.size;
    }

    private boolean isFull() {
        return size() == CAPACITY;
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        if (isFull()) {
            throw new IllegalStateException("Array is full");
        }

        Entry<K, V> newest = new PQEntry<>(key, value);

        if (isEmpty()) {
            size++;
            return list[0] = newest;
        }

        for (int i = 0; i < size(); i++) {

            if (compare(newest, list[i]) > 0) {

                // shift later entries forward
                for (int j = size(); j > i; j--) {
                    list[j] = list[j - 1];
                }

                // then add the new entry
                list[i] = newest;
                break;
            }

            if (list[i + 1] == null) {
                list[i + 1] = newest;
                break;
            }
        }

        size++;
        return newest;
    }

    @Override
    public Entry<K, V> min() {
        return list[size() - 1];
    }

    @Override
    public Entry<K, V> removeMin() {
        Entry<K, V> min = list[size() - 1];
        list[size() - 1] = null;

        size--;
        return min;
    }

    public String display() {
        StringBuilder str = new StringBuilder();

        for (Entry<K, V> entry : list) {
            str.append("[");
            str.append(entry.getKey());
            str.append(", ");
            str.append(entry.getValue());
            str.append("]");
            str.append("\n");
        }

        return str.toString();
    }

    public static void main(String[] args) {
        C928<Integer, String> c928 = new C928<>(7);
        c928.insert(10, "RED");
        c928.insert(5, "ORANGE");
        c928.insert(20, "YELLOW");
        c928.insert(15, "GREEN");
        c928.insert(15, "BLUE");
        c928.insert(30, "INDIGIO");
        c928.insert(25, "VIOLET");

        System.out.println(c928.display());
        System.out.println("Size: " + c928.size());

        System.out.println("Min: " + c928.min().getKey());

        c928.removeMin();
        System.out.println("Min (after removeMin): " + c928.min().getKey());

        c928.removeMin();
        System.out.println("Min (after second removeMin): " + c928.min().getKey());
    }
}
