package chapter_10.hash_maps;

import chapter_10.Map.AbstractMap;
import chapter_9.priority_queue.Entry;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {
    protected int n = 0;
    protected int capacity;
    private int prime;
    private long scale, shift;

    public AbstractHashMap(int cap, int p) {
        this.capacity = cap;
        prime = p;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    public AbstractHashMap(int cap) {
        this(cap, 109345121);   // default prime
    }

    public AbstractHashMap() {
        this(17);   // default capacity
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    @Override
    public V put(K key, V value) {
        V answer  = bucketPut(hashValue(key), key, value);
        if (n > capacity / 2) { // keep load factor <= 0.5
            resize(2 * capacity - 1);
        }
        return answer;
    }

    private int hashValue(K key) {
        // i.e MAD compression: (|(ai + b)| mod p) mod N
        return (int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
    }

    private void resize(int newCap) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (Entry<K, V> e : entrySet()) {
            buffer.add(e);
        }
        capacity = newCap;
        createTable();
        n = 0;      // n will be recomputed while reinserting entries
        for (Entry<K, V> e : buffer) {
            put(e.getKey(), e.getValue());
        }
    }

    // protected abstract methods to be implemented by subclasses
    protected abstract void createTable();

    protected abstract V bucketGet(int h, K k);

    protected abstract V bucketPut(int h, K k, V v);

    protected abstract V bucketRemove(int h, K k);
}