package io.tasks.shell;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.stream.Collectors;

public class Shell {
    private String currentDir = "/";

    public void cd(String path) {
        if (UnixPath.isAbsolute(path)) {
            currentDir = UnixPath.normalize(path);
        }
        else {
            currentDir = UnixPath.normalize(UnixPath.merge(currentDir, path));
        }
    }

    public String pwd() {
        return currentDir;
    }
}

class UnixPath {
    public static boolean isAbsolute(String path) {
        return path.startsWith("/");
    }

    public static String normalize(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (String dir : path.split("/")) {
            if (".".equals(dir) || dir.isEmpty()) {
                continue;
            }
            else if ("..".equals(dir)) {
                stack.pop();
            }
            else {
                stack.push(dir);
            }
        }
        return "/" + stack.stream().sorted(Collections.reverseOrder()).collect(Collectors.joining("/"));
    }


    public static String merge(String prefix, String suffix) {
        return prefix + "/" + suffix;
    }
}
