package chapter_9.priority_queue;

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;
import chapter_7.positional_list.Position;
import chapter_7.positional_list.PositionalList;

import java.util.Comparator;

public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    public SortedPriorityQueue(){
        super();
    }

    public SortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Running Time:    O(n)
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalStateException {

        checkKey(key);

        Entry<K, V> newest = new PQEntry<>(key, value);

        Position<Entry<K, V>> walk = list.last();
        while (walk != null && walk.getElement()  != null && compare(newest, walk.getElement()) < 0) {
            walk = list.before(walk);
        }

        if (walk == null) {
            list.addFirst(newest);
        } else {
            list.addAfter(walk, newest);
        }

        return newest;
    }

    @Override
    public Entry<K, V> min() {

        if (list.isEmpty()) {
            return null;
        }

        return list.first().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {

        if (list.isEmpty()) {
            return null;
        }

        return list.remove(list.first());
    }

    @Override
    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        SortedPriorityQueue<Integer, String> pq = new SortedPriorityQueue();

        pq.insert(1, "Segun");
        pq.insert(2, "Ben");
        pq.insert(3, "Adam");
        pq.insert(7, "Eve");
        pq.insert(4, "Ken");
        pq.insert(4, "Kate");

        /**
         * Print all the entries in the priority queue
         * according to their priorities (i.e keys)
         */
        Entry pqEntry;
        while (!pq.isEmpty()) {
            pqEntry = pq.removeMin();
            System.out.println("Key: " + pqEntry.getKey() + " Value: " + pqEntry.getValue());
        }
    }
}