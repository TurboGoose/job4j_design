package io.iostreams.zip;

import java.util.*;

public class ArgZip {
    private final boolean valid;
    private final String[] args;
    private final Map<String, String> passedArgs = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
        valid = parse();
    }

    boolean parse() {
        if (args.length % 2 != 0) {
            return false;
        }

        final Set<String> ALL_PARAMS = Set.of("-d", "-e", "-o");
        final Set<String> REQUIRED_PARAMS = Set.of("-d", "-o");

        Set<String> passedParams = new HashSet<>();
        for (int i = 0; i < args.length; i += 2) {
            if (!ALL_PARAMS.contains(args[i]) || ALL_PARAMS.contains(args[i + 1])) {
                return false;
            }
            passedParams.add(args[i]);
            passedArgs.put(args[i], args[i + 1]);
        }

        return passedParams.containsAll(REQUIRED_PARAMS);
    }

    public boolean valid() {
        return valid;
    }

    public String directory() {
        if (!valid) {
            throw new UnsupportedOperationException();
        }
        return passedArgs.get("-d");
    }

    public String exclude() {
        if (!valid) {
            throw new UnsupportedOperationException();
        }
        return passedArgs.get("-e");
    }

    public String output() {
        if (!valid) {
            throw new UnsupportedOperationException();
        }
        return passedArgs.get("-o");
    }
}
