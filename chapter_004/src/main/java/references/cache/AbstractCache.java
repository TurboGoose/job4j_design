package references.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class AbstractCache<K, V> implements Cache<K, V> {
    protected Map<K, SoftReference<V>> cache = new HashMap<>();

    @Override
    public V put(K key, V value) {
        SoftReference<V> prevValue = cache.put(key, new SoftReference<>(value));
        V result = null;
        if (prevValue != null) {
             result = prevValue.get();
             prevValue.clear();
        }
        return result;
    }

    @Override
    public V get(K key) {
        V result = null;
        SoftReference<V> value = cache.get(key);
        if (value != null) {
            result = value.get();
        }
        return result;
    }
}
