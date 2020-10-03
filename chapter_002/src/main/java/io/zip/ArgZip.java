package io.zip;

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
        final List<String> PARAMS = List.of("-d", "-e", "-o");

        if (args.length != 6) {
            return false;
        }
        for (int i = 0; i < args.length; i += 2) {
            if (!PARAMS.contains(args[i]) || PARAMS.contains(args[i + 1])) {
                return false;
            }
            passedArgs.put(args[i], args[i + 1]);
        }
        return true;
    }

    public boolean valid() {
        return valid;
    }

    public String directory() {
        return passedArgs.get("-d");
    }

    public String exclude() {
        return passedArgs.get("-e");
    }

    public String output() {
        return passedArgs.get("-o");
    }
}
