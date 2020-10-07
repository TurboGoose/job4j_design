package io.iostreams;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }
        throw new IllegalArgumentException("Wrong key: " + key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] keyValue = arg.substring(1).split("=");
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("Wrong argument format: " + arg);
            }
            values.putIfAbsent(keyValue[0], keyValue[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
