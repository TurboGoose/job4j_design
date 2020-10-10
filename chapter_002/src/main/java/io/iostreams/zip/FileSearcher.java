package io.iostreams.zip;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


class FileSearcher extends SimpleFileVisitor<Path> {
    private final Predicate<Path> predicate;
    private final List<Path> paths = new ArrayList<>();

    public FileSearcher(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (predicate != null && predicate.test(file)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        paths.add(dir);
        return FileVisitResult.CONTINUE;
    }
}
