package chapter_10.hash_maps;

import chapter_9.priority_queue.Entry;

import java.util.ArrayList;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap() {
        super();
    }

    public ProbeHashMap(int cap) {
        super(cap);
    }

    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    protected void createTable() {
        table = (MapEntry<K, V>[]) new MapEntry[capacity];
    }

    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DEFUNCT);
    }

    private int findSlot(int h, K k) {
        int avail = -1;                 // no slot available (thus far)
        int j = h;                      // index while scanning table
        do {
            if (isAvailable(j)) {       // may be either empty or defunct
                if (avail == -1) {      // this is the ﬁrst available slot!
                    avail = j;
                }
                if (table[j] == null) { // if empty, search fails immediately
                    break;
                }
            } else if (table[j].getKey().equals(k)) {
                return j;               // successful match
            }
            j = (j + 1) % capacity;     // keep looking (cyclically)
        } while (j != h);               // stop if we return to the start
        return -(avail + 1);            // search has failed
    }

    /**
     * Returns value associated with key k in bucket
     * with hash value h, or else null.
     * */
    @Override
    protected V bucketGet(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0)
            return null;
        return (V) table[j].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h, k);
        if (j >= 0)
            return table[j].setValue(v);
        table[-(j + 1)] = new MapEntry<>(k, v); // convert to proper index
        n++;
        return null;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) {
            return null;    // nothing to remove
        }
        V answer = (V) table[j].getValue();
        table[j] = DEFUNCT;
        return answer;
    }

    /**
     * Returns an iterable collection of all
     * key-value entries of the map.
     * */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (!isAvailable(h)) {
                buffer.add(table[h]);
            }
        }
        return null;
    }


}
