package chapter_10.sorted_map;

import chapter_9.priority_queue.Entry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class SortedTableMap<K, V> extends AbstractSortedMap<K, V> {
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    public SortedTableMap() {
        super();
    }

    public SortedTableMap(Comparator<K> comp) {
        super(comp);
    }

    private int findIndex(K key, int low, int high) {
        if (high < low) return high + 1;                    // no entry qualifies
        int mid = (low + high) / 2;
        int comp = compare(key, table.get(mid));
        if (comp == 0)
            return mid; // found exact match
        else if (comp < 0)
            return findIndex(key, low, mid -1);     // answer is left of mid (or possibly mid)
        else
            return findIndex(key, mid + 1, high);   // answer is right of mid
    }

    private int findIndex(K key) {
        return findIndex(key, 0, table.size() - 1);
    }

    public int size() {
        return table.size();
    }

    /**
     * Overall Time complexity: O(log n)
     *
     * Because a binary search was used
     * to do the searching. Other steps
     * are constant time O(1)
     */
    public V get(K key) {
        int j = findIndex(key);
        if (j == size() || compare(key, table.get(j)) != 0) return null; // no match
        return table.get(j).getValue();
    }

    /**
     * Overall Time complexity: O(log n + n)
     * Which simplifies to O(n)
     *
     * O(logn) if map has entry with given key
     */
    public V put(K key, V value) {
        int j = findIndex(key);
        if (j < size() && compare(key, table.get(j)) == 0)  // match exists
            return table.get(j).setValue(value);
        table.add(j, new MapEntry<K, V>(key, value));       // otherwise new
        return null;
    }

    /**
     * Time complexity: O(log n + n)
     * Which simplifies to O(n)
     */
    public V remove(K key) {
        int j = findIndex(key);
        if (j == size() || compare(key, table.get(j)) != 0) return null;    // no match
        return table.remove(j).getValue();
    }

    /**
     * Overall Time complexity: O(1)
     */
    private Entry<K, V> safeEntry(int j) {
        if (j < 0 || j >= table.size()) return null;
        return table.get(j);
    }


    /**
     * Overall Time complexity: O(1)
     */
    public Entry<K, V> firstEntry() {
        return safeEntry(0);
    }

    /**
     * Overall Time complexity: O(1)
     */
    public Entry<K, V> lastEntry() {
        return safeEntry(table.size() - 1);
    }


    /**
     * Overall Time complexity: O(log n)
     *
     * Because a binary search was used to
     * do the searching and other steps
     * are constant time O(1)
     */
    public Entry<K, V> ceilingEntry(K key) {
        return safeEntry(findIndex(key));
    }

    /**
     * Overall Time complexity: O(log n)
     *
     * Because a binary search was used
     * to do the searching. Other steps
     * are constant time O(1)
     */
    public Entry<K, V> floorEntry(K key) {
        int j = findIndex(key);
        if (j == size() || !key.equals(table.get(j).getKey()))
            j--;            // look one earlier (unless we had found a perfect match)
        return safeEntry(j);
    }

    /**
     * Overall Time complexity: O(log n)
     *
     * Because a binary search was used to
     * do the searching and other steps
     * are constant time O(1)
     */
    public Entry<K, V> lowerEntry(K key) {
        return safeEntry(findIndex(key) - 1); // go strictly before the ceiling entry

    }

    /**
     * Overall Time complexity: O(log n)
     *
     * Because a binary search was used to
     * do the searching and other steps
     * are constant time O(1)
     */
    public Entry<K, V> higherEntry(K key) {
        int j = findIndex(key);
        if (j < size() && key.equals(table.get(j).getKey()))
            j++; // go past exact match
        return safeEntry(j);
    }

    private Iterable<Entry<K, V>> snapshot(int startIndex, K stop) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        int j = startIndex;
        while (j < table.size() && (stop == null || compare(stop, table.get(j)) > 0))
            buffer.add(table.get(j++));
        return buffer;
    }

    /**
     * Time complexity: O(n)
     */
    public Iterable<Entry<K, V>> entrySet() {
        return snapshot(0, null);
    }

    /**
     * Time complexity: O(s + log n)
     * where s is the number of items reported
     * in the range fromKey to toKey
     */
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {
        return snapshot(findIndex(fromKey), toKey);
    }

    public boolean containsKey(K key) {
        Iterator<Entry<K, V>> i = entrySet().iterator();
        if (key == null) {
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                if (e.getKey() == null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<K, V> e = i.next();
                if (key.equals(e.getKey()))
                    return true;
            }
        }
        return false;
    }
}