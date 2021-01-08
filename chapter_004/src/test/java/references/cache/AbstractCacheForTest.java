package references.cache;

import java.lang.ref.SoftReference;
import java.util.Map;

public class AbstractCacheForTest<K, V> extends AbstractCache<K, V>{

    public AbstractCacheForTest() {}

    public AbstractCacheForTest(Map<K, V> map) {
        cache = new SoftReference<>(map);
    }

    public Map<K, V> getMap() {
        return cache.get();
    }
}
