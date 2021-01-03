package references.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cache <K, V> {
    private Map<K, SoftReference<V>> cache = new HashMap<>();

    public Cache() {}

    // Constructor for test
    Cache(Map<K, SoftReference<V>> map) {
        cache = map;
    }

    // Method for test
    Map<K, SoftReference<V>> getMap() {
        return cache;
    }

    public void load(K key, V value) {
        SoftReference<V> actualValue = cache.get(key);
        if (actualValue != null) {
            actualValue.clear();
        }
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V result = null;
        SoftReference<V> value = cache.get(key);
        if (value != null) {
            result = value.get();
        }
        return result;
    }
}
