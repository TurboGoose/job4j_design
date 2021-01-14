package tdd.templates;

import java.util.Map;

public interface Generator {
    String produce(String template, Map<String, String> args);
}
