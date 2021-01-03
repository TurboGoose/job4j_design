package references.cache;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class TxtCacheTest {
    @Test
    public void loadFileThenReturnContent() throws IOException {
        TxtCache cache = new TxtCache();
        String content = cache.loadFile("txt_cache.txt");
        assertThat(content, is("file contents"));
    }

    @Test
    public void getContentFromEmptyCacheThenReturnContent() throws IOException {
        TxtCache cache = new TxtCache();
        assertThat(cache.getContent("txt_cache.txt"), is("file contents"));
    }
}
