package io.tasks.shell;

import java.util.*;

public class Shell {
    private UnixPath currentPath = new UnixPath("/");

    public void cd(String path) {
        UnixPath newPath = new UnixPath(path);
        if (newPath.isAbsolute()) {
            currentPath = newPath.normalize();
        } else {
            currentPath.resolve(newPath).normalize();
        }
    }

    public String pwd() {
        return currentPath.toString();
    }
}

class UnixPath {
    private final List<String> dirs;
    private final boolean absolute;

    public UnixPath(String path) {
        absolute = path.startsWith("/");
        dirs = new LinkedList<>(Arrays.asList(path.split("/")));
    }

    public boolean isAbsolute() {
        return absolute;
    }

    public UnixPath normalize() {
        Deque<String> stack = new ArrayDeque<>();
        for (String dir : dirs) {
            if (".".equals(dir) || dir.isEmpty()) {
                continue;
            } else if ("..".equals(dir)) {
                stack.pop();
            } else {
                stack.push(dir);
            }
        }
        dirs.clear();
        stack.stream().sorted(Collections.reverseOrder()).forEach(dirs::add);
        return this;
    }

    public UnixPath resolve(UnixPath unixPath) {
        dirs.addAll(unixPath.dirs);
        return this;
    }

    @Override
    public String toString() {
        return (absolute ? "/" : "") + String.join("/", dirs);
    }
}
