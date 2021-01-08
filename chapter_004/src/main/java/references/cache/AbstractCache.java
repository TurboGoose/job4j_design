package references.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class AbstractCache<K, V> implements Cache<K, V> {
    protected SoftReference<Map<K, V>> cache = new SoftReference<>(new HashMap<>());

    @Override
    public V load(K key, V value) {
        Map<K, V> map = cache.get();
        V prevValue = null;
        if (map != null) {
            prevValue = map.get(key);
            map.put(key, value);
        }
        return prevValue;
    }

    @Override
    public V get(K key) {
        V result = null;
        Map<K, V> map = cache.get();
        if (map != null) {
            result = map.get(key);
        }
        return result;
    }
}
