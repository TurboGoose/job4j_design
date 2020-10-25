package io.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArgumentParser {
    public enum SearchOption {FULL_NAME, REGEX, MASK}

    private SearchOption searchOption;
    private String searchDirectory;
    private String outputFile;
    private String searchExpression;
    private boolean valid;

    public ArgumentParser(String[] args) {
        valid = parse(args);
    }

    private boolean parse(String[] args) {
        Iterator<String> it = Arrays.stream(args).iterator();
        while (it.hasNext()) {
            String arg = it.next();
            if (List.of("-d", "-n", "-o").contains(arg)) {
                if (!it.hasNext()) {
                    return false;
                }
                switch (arg) {
                    case "-d" -> {
                        if (searchDirectory != null) {
                            return false;
                        }
                        searchDirectory = it.next();
                    }
                    case "-n" -> {
                        if (searchExpression != null) {
                            return false;
                        }
                        searchExpression = it.next();
                    }
                    case "-o" -> {
                        if (outputFile != null) {
                            return false;
                        }
                        outputFile = it.next();
                    }
                }
            }
            else if (List.of("-m", "-r", "-f").contains(arg)) {
                if (searchOption != null) {
                    return false;
                }
                searchOption = switch (arg) {
                    case "-m" -> SearchOption.MASK;
                    case "-r" -> SearchOption.REGEX;
                    case "-f" -> SearchOption.FULL_NAME;
                    default -> null;
                };
            }
            else {
                return false;
            }
        }
        return searchDirectory != null && outputFile != null &&
                searchExpression != null && searchOption != null;
    }

    public boolean isValid() {
        return valid;
    }

    public SearchOption getSearchOption() {
        if (!valid) {
            throw new UnsupportedOperationException();
        }
        return searchOption;
    }

    public String getSearchDirectory() {
        if (!valid) {
            throw new UnsupportedOperationException();
        }
        return searchDirectory;
    }

    public String getOutputFile() {
        if (!valid) {
            throw new UnsupportedOperationException();
        }
        return outputFile;
    }

    public String getSearchExpression() {
        if (!valid) {
            throw new UnsupportedOperationException();
        }
        return searchExpression;
    }
}
