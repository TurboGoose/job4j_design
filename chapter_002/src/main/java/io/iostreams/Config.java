package io.iostreams;

import java.io.*;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().forEach(line -> {
                if (line.isEmpty() || line.startsWith("//")) {
                    return;
                }
                String[] keyValue = line.split("=");
                if (keyValue.length != 2) {
                    return;
                }
                values.put(keyValue[0], keyValue[1]);
            });
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
