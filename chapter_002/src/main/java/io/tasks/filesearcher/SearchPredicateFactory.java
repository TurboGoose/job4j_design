package io.tasks.filesearcher;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchPredicateFactory {
    public static Predicate<Path> getPredicate(ArgumentParser.SearchOption option, String exp) {
        return switch (option) {
            case MASK -> p -> p.toString().endsWith(exp.startsWith("*") ? exp.substring(1) : exp);
            case FULL_NAME -> p -> p.toString().equals(exp);
            case REGEX -> {
                Pattern pattern = Pattern.compile(exp);
                yield p -> pattern.matcher(p.toString()).matches();
            }
        };
    }
}
