package chapter_9.priority_queue;

public interface PriorityQueue<K, V> {

    int size();

    boolean isEmpty();

    Entry<K, V> insert(K key, V value) throws IllegalStateException;

    Entry<K, V> min();

    Entry<K, V> removeMin();
}