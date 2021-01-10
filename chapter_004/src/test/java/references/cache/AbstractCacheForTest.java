package references.cache;

import java.lang.ref.SoftReference;
import java.util.Map;

public class AbstractCacheForTest<K, V> extends AbstractCache<K, V>{

    public AbstractCacheForTest() {}

    public AbstractCacheForTest(Map<K, SoftReference<V>> map) {
        cache = map;
    }

    public Map<K, SoftReference<V>> getMap() {
        return cache;
    }
}
