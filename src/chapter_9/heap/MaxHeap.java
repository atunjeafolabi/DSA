package chapter_9.heap;

import chapter_9.priority_queue.DefaultComparator;
import chapter_9.priority_queue.Entry;

import java.util.ArrayList;
import java.util.Comparator;

public class MaxHeap<K, V> {

    protected static class PQEntry<K, V> implements Entry<K, V> {
        private K k;
        private V v;

        public PQEntry(K key, V value) {
            k = key;
            v = value;
        }
        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        public void setKey(K key) {
            this.k = key;
        }

        public void setValue(V value) {
            this.v = value;
        }
    }

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    private Comparator<K> comp;

    public MaxHeap() {
        this(new DefaultComparator<K>());
    }

    public MaxHeap(Comparator<K> comp) {
        this.comp = comp;
    }

    public MaxHeap(K[] keys, V[] values) {
        this(new DefaultComparator<K>());
        for (int j = 0; j < Math.min(keys.length, values.length); j++) {
            heap.add(new PQEntry<>(keys[j], values[j]));
        }
        heapify();
    }

    protected int parent(int j) {
        return (j - 1) / 2;
    }

    protected int left(int j) {
        return 2 * j + 1;
    }

    protected int right(int j) {
        return 2 * j + 2;
    }

    protected boolean hasLeft(int j) {
        return this.left(j) < heap.size();
    }

    protected boolean hasRight(int j) {
        return this.right(j) < heap.size();
    }

    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary,
     * to restore the heap property.
     *
     * (max-oriented heap property)
     */
    protected void upheap(int j) {
        while (j > 0) {                                           // continue until reaching root (or break statement)
            int p = parent(j);

            if (compare(heap.get(j), heap.get(p)) <= 0) {
                break;
            }

            swap(j, p);
            j = p;                                               // continue from the parent's location
        }
    }

    protected void downheap(int j) {

        while (hasLeft(j)) {                                    // continue to bottom (or break statement)
            int leftIndex = left(j);
            int largerChildIndex = leftIndex;                    // although right may be smaller

            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) < 0) {
                    largerChildIndex = rightIndex;
                }
            }

            if (compare(heap.get(largerChildIndex), heap.get(j)) <= 0) {
                break;
            }

            swap(j, largerChildIndex);
            j = largerChildIndex;
        }
    }

    public int size() {
        return heap.size();
    }

    public Entry<K, V> max() {

        if (heap.isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);

        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upheap(heap.size() - 1);

        return newest;
    }

    public Entry<K, V> removeMax() {

        if (heap.isEmpty()) {
            return null;
        }

        Entry<K, V> temp = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);

        return temp;
    }

    protected boolean checkKey(K key) throws IllegalStateException {
        try {
            return (comp.compare(key, key) == 0);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Incompatible key");
        }
    }

    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    protected void heapify() {
        int startIndex = parent(size() - 1);             // start at PARENT of last entry
        for (int j = startIndex; j >= 0; j--) {             // loop until processing the root
            downheap(j);
        }
    }

    public static void main(String[] args) {
        Integer[] keys = {1, 2, 3, 4};
        String[] values = {"A", "B", "C", "D"};

        MaxHeap<Integer, String> maxHeap = new MaxHeap<>(keys, values);

        System.out.println("Size: " + maxHeap.size());
        System.out.println(maxHeap.max().getKey());

        maxHeap.removeMax();
        System.out.println(maxHeap.max().getKey());

        maxHeap.removeMax();
        System.out.println(maxHeap.max().getKey());

        System.out.println("Size: " + maxHeap.size());

        //
        maxHeap.insert(5, "F");
        System.out.println(maxHeap.max().getKey());
    }
}
