package chapter_9.heap;

import chapter_7.positional_list.PositionalList;
import chapter_9.priority_queue.AbstractPriorityQueue;
import chapter_9.priority_queue.Entry;
import chapter_9.priority_queue.PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * TIME COMPLEXITY
 * Implementation of a priority queue using a heap is more efficient
 * than the implementation using either an Unsorted or Sorted list.
 * <p>
 * For an unsorted list, removeMin is O(n) but insert is O(1)
 * For a sorted list, removeMin is O(1) but insert is O(n).
 * <p>
 * But for a heap, both insert and removeMin is O(log n) which is better.
 */
public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    public HeapPriorityQueue() {
        super();
    }

    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Instead of using the insert method to insert entries one at a time,
     * the bottom-up heapify method can be used to build a heap given
     * that all the entries are known beforehand.
     * <p>
     * Although the insert method runs in O(log n) which is good,
     * but for n insert calls, it runs in O(nlogn)
     * <p>
     * The heapify method runs in O(n) which is better
     * in this scenario.
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for (int j = 0; j < Math.min(keys.length, values.length); j++) {
            heap.add(new PQEntry<>(keys[j], values[j]));
        }
        heapify();
    }

    // protected utilities
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

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary,
     * to restore the heap property.
     */
    protected void upheap(int j) {
        while (j > 0) {                                           // continue until reaching root (or break statement)
            int p = parent(j);

            if (compare(heap.get(j), heap.get(p)) >= 0) {
                break;
            }

            swap(j, p);
            j = p;                                               // continue from the parent's location
        }
    }

    /**
     * C-9.29
     * <p>
     * Alternative implementation of upheap
     * (recursive)
     */
    protected void upheap2(int j) {
        int p = parent(j);

        if (compare(heap.get(j), heap.get(p)) < 0) {
            swap(j, p);
            upheap2(p);
        }
    }

    /**
     * C-9.30
     * <p>
     * An alternative implementation of the HeapPriorityQueue’s
     * downheap method that uses recursion (and no loop).
     */
    protected void downheap2(int j) {
        if (hasLeft(j)) {
            int leftChildIndex = left(j);
            int smallChildIndex = leftChildIndex;

            if (hasRight(j)) {
                int rightChildIndex = right(j);

                if (compare(heap.get(leftChildIndex), heap.get(rightChildIndex)) >= 0) {
                    smallChildIndex = rightChildIndex;
                }
            }

            if (compare(heap.get(j), heap.get(smallChildIndex)) >= 0) {
                swap(j, smallChildIndex);
            }
            downheap2(smallChildIndex);
        }
    }

    /**
     * Moves the entry at index j lower, if necessary,
     * to restore the heap property.
     */
    protected void downheap(int j) {

        while (hasLeft(j)) {                                    // continue to bottom (or break statement)
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;                    // although right may be smaller

            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
                    smallChildIndex = rightIndex;
                }
            }

            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {
                break;
            }

            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public Entry<K, V> min() {

        if (heap.isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);

        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upheap2(heap.size() - 1);

        return newest;
    }

    @Override
    public Entry<K, V> removeMin() {

        if (heap.isEmpty()) {
            return null;
        }

        Entry<K, V> temp = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap2(0);

        return temp;
    }

    /**
     * Performs a bottom-up construction of
     * the heap in linear time, i.e O(n).
     */
    protected void heapify() {
        int startIndex = parent(size() - 1);             // start at PARENT of last entry
        for (int j = startIndex; j >= 0; j--) {             // loop until processing the root
            downheap(j);
        }
    }

    /*
     * An implementation of a pqSort method that sorts elements of
     * a positional list using an initially empty priority queue
     * to produce the ordering.
     *
     * This method will also work for other
     * implementations of priority queue
     * i.e UnsortedPriorityQueue and SortedPriorityQueue
     */
    public static <E> void pqSort(PositionalList<E> S, PriorityQueue<E, ?> P) {
        int n = S.size();
        for (int j = 0; j < n; j++) {
            E element = S.remove(S.first());
            P.insert(element, null);
        }
        for (int j = 0; j < n; j++) {
            E element = P.removeMin().getKey();
            S.addLast(element);
        }
    }

    /**
     * C-9.34
     */
    public List<Entry<K, V>> equalOrLessThan(Entry<K, V> entry) {
        List<Entry<K, V>> list = new ArrayList<>();

        preOrder(0, entry, list);

        return list;
    }

    private void preOrder(int key, Entry<K, V> entry, List<Entry<K, V>> list) {
        if (compare(heap.get(key), entry) <= 0) {
            // perform visit action
            list.add(heap.get(key));

            if (hasLeft(key)) {
                preOrder(left(key), entry, list);
            }

            if (hasRight(key)) {
                preOrder(right(key), entry, list);
            }
        }
    }

    /**
     * C937
     */
    public static <N, M> HeapPriorityQueue<N, M> combine(HeapPriorityQueue<N, M> t1, HeapPriorityQueue<N, M> t2) {
        HeapPriorityQueue<N, M> T = new HeapPriorityQueue<>();
        while(t1.size() != 0) {
            Entry<N, M> temp = t1.removeMin();
            T.insert(temp.getKey(), temp.getValue());
        }

        while(t2.size() != 0) {
            Entry<N, M> temp = t2.removeMin();
            T.insert(temp.getKey(), temp.getValue());
        }

        return T;
    }
}
