package references.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

public class TxtCache {
    private final Cache<String, String> cache = new AbstractCache<>();

    public String getContent(String filename) throws IOException {
        String result = cache.get(filename);
        if (result == null) {
            result = loadFile(filename);
        }
        return result;
    }

    public String loadFile(String filename) throws IOException {
        String content = loadContent(filename);
        cache.put(filename, content);
        return content;
    }

    private String loadContent(String filename) throws IOException {
        String fileContent;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filename))))) {
            fileContent = in.lines().collect(Collectors.joining());
        }
        return fileContent;
    }
}
