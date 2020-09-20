package map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

class Entry<K, V> {
    public K key;
    public V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class SimpleHashMap <K, V> implements Iterable<Entry<K, V>> {
    private final int INITIAL_CAPACITY = 16;
    private Entry<K, V>[] buckets;
    private int bucketLoad = 0;

    public SimpleHashMap() {
        buckets = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
    }

    public V get(K key) {
        Entry<K, V> entry = buckets[rangedHash(key)];
        if (entry != null && (entry.key == null || entry.key.hashCode() == key.hashCode() && entry.key.equals(key))) {
            return entry.value;
        }
        return null;
    }

    private int rangedHash(Object key) {
        return hash(key) & (buckets.length - 1);
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 8);
    }

    public boolean delete(K key) {
        boolean success = false;
        int indexToDelete = rangedHash(key);
        if (buckets[indexToDelete] != null) {
            buckets[indexToDelete] = null;
            success = true;
        }
        return success;
    }

    public boolean insert(K key, V value) {
        if (loadFactor() >= 0.75) {
            resize();
        }
        boolean success = false;
        int indexToInsert = rangedHash(key);
        if (buckets[indexToInsert] == null ||
                buckets[indexToInsert].key.hashCode() == key.hashCode() &&
                    buckets[indexToInsert].key.equals(key)) {
            buckets[indexToInsert] = new Entry<>(key, value);
            bucketLoad++;
            success = true;
        }
        return success;
    }

    private double loadFactor() {
        return (double) bucketLoad / buckets.length;
    }

    private void resize() {
        Entry<K, V>[] newBuckets = (Entry<K, V>[]) new Entry[buckets.length * 2];
        for (Entry<K, V> entry : buckets) {
            if (entry != null) {
                newBuckets[hash(entry.key) & (newBuckets.length - 1)] = entry;
            }
        }
        buckets = newBuckets;
    }

    int getSize() {
        return buckets.length;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                passNulls();
                return point < buckets.length;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return buckets[point++];
            }

            private void passNulls() {
                while (point < buckets.length && buckets[point] == null) {
                    point++;
                }
            }
        };
    }
}
