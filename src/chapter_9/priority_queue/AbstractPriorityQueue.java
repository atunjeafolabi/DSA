package chapter_9.priority_queue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {

    //---------------- nested PQEntry class ----------------
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

    /**
     * Instance variable:
     * For the comparator defining the ordering
     * of keys in the priority queue.
     */
    private Comparator<K> comp;

    /**
     * Creates an empty priority queue using the
     * given comparator to order keys.
     */
    protected AbstractPriorityQueue(Comparator<K> c) {
        comp = c;
    }

    /**
     * Creates an empty priority queue based
     * on the natural ordering of its keys
     * using a default comparator definition.
     */
    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    /** Method for comparing two entries according to key */
    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    /**
     * Determines whether a key is valid.
     *
     * ( when a key is compared with itself, we get 0.
     *  i.e there should be no difference )
     */
    protected boolean checkKey(K key) throws IllegalStateException {
        try {
            return (comp.compare(key, key) == 0);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Incompatible key");
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
