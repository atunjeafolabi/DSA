package chapter_9.priority_queue;

import chapter_7.positional_list.IterableLinkedPositionalList.LinkedPositionalList;
import chapter_7.positional_list.Position;
import chapter_7.positional_list.PositionalList;

import java.util.Comparator;

public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    public UnsortedPriorityQueue() {
        super();
    }

    public UnsortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * RunningTime: O(n)
     *
     * Because all the elements in the list will be visited
     */
    private Position<Entry<K, V>> findMin() {
        Position<Entry<K, V>> small = list.first();

        for (Position<Entry<K, V>> walk : list.positions()) {

            if (compare(walk.getElement(), small.getElement()) < 0) {
                small = walk;   // found an even smaller key
            }
        }

        return small;
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalStateException {
        checkKey(key);

        Entry<K, V> newest = new PQEntry<>(key, value);
        list.addLast(newest);

        return newest;
    }

    /**
     * Running Time:    O(n)
     */
    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) {
            return null;
        }
        return findMin().getElement();
    }

    /**
     * Running Time:    O(n)
     */
    @Override
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(findMin());
    }

    @Override
    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        UnsortedPriorityQueue<Integer, String> pq = new UnsortedPriorityQueue();

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
