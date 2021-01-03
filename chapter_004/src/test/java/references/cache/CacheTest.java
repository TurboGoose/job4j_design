package references.cache;

import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CacheTest {
    @Test
    public void getFromEmptyCacheThenNull() {
        Cache<String, String> cache = new Cache<>();
        assertThat(cache.get("non-existent-key"), is(nullValue()));
    }

    @Test
    public void getExistingKeyThenReturnValue() {
        Map<String, SoftReference<String>> content = Map.of("key", new SoftReference<>("value"));
        Cache<String, String> cache = new Cache<>(content);
        assertThat(cache.get("key"), is("value"));
    }

    @Test
    public void loadCacheThenCheckEntryInMap() {
        Cache<String, String> cache = new Cache<>();
        cache.load("key", "value");
        Map<String, SoftReference<String>> actual = cache.getMap();
        assertThat(actual.containsKey("key"), is(true));
        assertThat(actual.get("key").get(), is("value"));
    }
}
