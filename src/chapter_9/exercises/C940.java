package chapter_9.exercises;

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;
import chapter_7.positional_list.Position;
import chapter_7.positional_list.PositionalList;
import chapter_9.priority_queue.DefaultComparator;
import chapter_9.priority_queue.Entry;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * C-9.40
 *
 * Explain how the k largest elements from an unordered collection of
 * size n can be found in time O(nlogk) using O(k) auxiliary space.
 *
 * This is a maximum oriented approach for UnsortedPriorityQueue
 * (TODO: not sure about the time complexity)
 */
public class C940<K, V> {

    private final Comparator<K> comp;

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

    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    public C940() {
        this(new DefaultComparator<K>());
    }

    public C940(Comparator<K> comp) {
        this.comp = comp;
    }

    /**
     * RunningTime: O(n)
     *
     * Because all the elements in the list will be visited
     */
    private Position<Entry<K, V>> findMax() {
        Position<Entry<K, V>> larger = list.first();

        for (Position<Entry<K, V>> walk : list.positions()) {

            if (compare(walk.getElement(), larger.getElement()) > 0) {
                larger = walk;   // found an even larger key
            }
        }

        return larger;
    }

    public Entry<K, V> insert(K key, V value) throws IllegalStateException {
        checkKey(key);

        Entry<K, V> newest = new PQEntry<>(key, value);
        list.addLast(newest);

        return newest;
    }

    /**
     * Running Time:    O(n)
     */
    public Entry<K, V> max() {
        if (list.isEmpty()) {
            return null;
        }
        return findMax().getElement();
    }

    /**
     * Running Time:    O(n)
     */
    public Entry<K, V> removeMax() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(findMax());
    }

    public int size() {
        return list.size();
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

    public ArrayList<Entry<K, V>> kLargestElements(int k) {
        ArrayList<Entry<K, V>> largestKElements = new ArrayList<>();

        for (int i = 0; i < k; i++){
            largestKElements.add(removeMax());
        }

        return largestKElements;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {

        C940<Integer, String> pq = new C940();

        pq.insert(1, "Segun");
        pq.insert(2, "Ben");
        pq.insert(3, "Adam");
        pq.insert(7, "Eve");
        pq.insert(4, "Ken");
        pq.insert(4, "Kate");

        int k = 4;
        // Display the largest k elements
        for (Entry<Integer, String> entry : pq.kLargestElements(k)){
            System.out.println("[" + entry.getKey() + ", " + entry.getValue() + "]");
        }
    }
}
