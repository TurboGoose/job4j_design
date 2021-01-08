package references.cache;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AbstractCacheTest {
    @Test
    public void getFromEmptyCacheThenNull() {
        AbstractCache<String, String> cache = new AbstractCache<>();
        assertThat(cache.get("non-existent-key"), is(nullValue()));
    }

    @Test
    public void getExistingKeyThenReturnValue() {
        Map<String, String> content = Map.of("key", "value");
        AbstractCacheForTest<String, String> cache = new AbstractCacheForTest<>(content);
        assertThat(cache.get("key"), is("value"));
    }

    @Test
    public void loadCacheThenCheckEntryInMap() {
        AbstractCacheForTest<String, String> cache = new AbstractCacheForTest<>();
        cache.load("key", "value");
        Map<String, String> actual = cache.getMap();
        assertThat(actual.containsKey("key"), is(true));
        assertThat(actual.get("key"), is("value"));
    }
}
