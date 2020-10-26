package io.tasks.shell;

import java.io.File;
import java.nio.file.Path;

public class Shell {
    private Path currentDir = Path.of("/");

    public void cd(String path) {
        Path newPath = Path.of(path).normalize();
        if (newPath.isAbsolute()) {
            currentDir = newPath;
        }
        else {
            currentDir = Path.of(currentDir.toString(), File.separator, path).normalize();
        }
    }

    public String pwd() {
        return currentDir.toString();
    }
}
