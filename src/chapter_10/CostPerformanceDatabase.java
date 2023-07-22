package chapter_10;

import chapter_10.sorted_map.SortedMap;
import chapter_10.sorted_map.SortedTableMap;
import chapter_9.priority_queue.Entry;

/**
 * Maintains a database of maximal (cost, performance) pairs.
 */
public class CostPerformanceDatabase {

    /**
     * The map variable holds a set of maximum (c, p) pair
     * entries added by the add() method.
     */
    SortedMap<Integer, Integer> map = new SortedTableMap<>();

    // Constructs an initially empty database.
    public CostPerformanceDatabase() {}

    /**
     * Returns the (cost, performance) entry with largest cost not exceeding c.
     * (or null if no entry exist with cost c or less).
     */
    public Entry<Integer, Integer> best(int cost) {
        return map.floorEntry(cost);
    }

    /**
     * Add a new entry with given cost c and performance p.
     * (ignores and does not add (c, p) pairs that are dominated)
     *
     * Overall Time complexity: O(n)
     *
     * i.e map.put() method is O(n).
     * And the while loop in the worst case is O(n).
     * (i.e the newly added entry may dominate all the other entries)
     *
     * So, the overall time for this add() method is O(n) + O(n) = O(n)
     */
    public void add(int c, int p) {
        Entry<Integer, Integer> other = map.floorEntry(c);  // other is at least as cheap
        if (other != null && other.getValue() >= p)         // If its performance is as good
            return;                                         // (c,p) is dominated, so ignore
        map.put(c, p);                                      // else, add (c,p) to database

        // and now remove any entries that are dominated by the new one
        other = map.higherEntry(c);                         // other is more expensive than c
        while (other != null && other.getValue() <= p) {    // if not better performance
            map.remove(other.getKey());                     // remove the other entry
            other = map.higherEntry(c);
        }
    }
}