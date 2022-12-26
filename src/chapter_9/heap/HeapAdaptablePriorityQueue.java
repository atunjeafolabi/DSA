package chapter_9.heap;

import chapter_9.priority_queue.Entry;

import java.util.Comparator;


public class HeapAdaptablePriorityQueue<K, V> extends HeapPriorityQueue<K, V> implements AdaptablePriorityQueue<K, V> {

    //---------------- nested AdaptablePQEntry class ----------------
    /**
     * Extension of the PQEntry to include location information.
     */
    protected static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {

        private int index;      // entry’s current index within the heap

        public AdaptablePQEntry(K key, V value, int index) {
            super(key, value);
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public HeapAdaptablePriorityQueue() {
        super();
    }

    public HeapAdaptablePriorityQueue(Comparator<K> comparator) {
        super(comparator);
    }

    /**
     * Validates an entry to ensure it is location-aware.
     */
    protected AdaptablePQEntry<K, V> validate(Entry<K, V> entry) throws IllegalArgumentException {
        if (!(entry instanceof AdaptablePQEntry)) {
            throw new IllegalArgumentException("Invalid entry");
        }

        AdaptablePQEntry<K, V> locator = (AdaptablePQEntry<K, V>) entry;

        int index = locator.getIndex();
        if (index >= heap.size() || heap.get(index) != locator) {
            throw new IllegalArgumentException("Invalid entry");
        }

        return locator;
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    @Override
    protected void swap(int i, int j) {

        super.swap(i,j); // perform the swap

        ((AdaptablePQEntry<K,V>) heap.get(i)).setIndex(i);      // reset entry's index

        ((AdaptablePQEntry<K,V>) heap.get(j)).setIndex(j);      // reset entry's index
    }

    /**
     * Restores the heap property by moving the entry at index j upward/downward.
     */
    protected void bubble(int j) {
        if (j > 0 && compare(heap.get(j), heap.get(parent(j))) < 0) {
            upheap(j);
        }
        else {
            downheap(j);    // although it might not need to move
        }
    }

    /**
     * Inserts a key-value pair and returns the entry created.
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {

        checkKey(key);

        Entry<K, V> newest = new AdaptablePQEntry<>(key, value, heap.size());
        heap.add(newest);
        upheap(heap.size() - 1);    // upheap newly added entry

        return newest;
    }

    /**
     * Removes the given entry from the priority queue.
     */
    @Override
    public void remove(Entry<K, V> entry) throws IllegalArgumentException {
        AdaptablePQEntry<K, V> locator = validate(entry);
        int j = locator.getIndex();

        if (j == heap.size() - 1) {                    // entry is at last position
            heap.remove(heap.size() - 1);
        } else {
            swap(j, heap.size() - 1);               // swap entry to last position
            heap.remove(heap.size() - 1);       // then remove it
            bubble(j);                                 // and fix entry displaced by the swap
        }
    }

    /**
     * Replaces the key of an entry.
     */
    @Override
    public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
        AdaptablePQEntry<K, V> locator = validate(entry);
        checkKey(key);
        locator.setKey(key);
        bubble(locator.getIndex());         // with new key, may need to move entry
    }

    /**
     * Replaces the value of an entry.
     */
    @Override
    public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
        AdaptablePQEntry<K, V> locator = validate(entry);
        locator.setValue(value);
    }
}