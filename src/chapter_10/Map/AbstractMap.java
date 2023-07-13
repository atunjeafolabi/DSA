package chapter_10.Map;

import chapter_9.priority_queue.Entry;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {

    protected static class MapEntry<K, V> implements Entry {
        private K k;
        private V v;

        public MapEntry(K key, V value) {
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

        protected void setKey(K key) {
            k = key;
        }

        public V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }
    }

    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().getKey();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class KeyIterable implements Iterable<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    @Override
    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterable implements Iterable<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    public boolean isEmpty() { return size() == 0; }
}
