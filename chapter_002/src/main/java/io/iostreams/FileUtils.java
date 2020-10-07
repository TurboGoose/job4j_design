package io.iostreams;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class FileUtils {
    private final Path root;

    public FileUtils(Path root) {
        this.root = root;
    }

    public List<List<Path>> searchDuplicates() throws IOException {
        DuplicateSearcher searcher = new DuplicateSearcher();
        Files.walkFileTree(root, searcher);
        return searcher.getDuplicates();
    }

    private static class DuplicateSearcher extends SimpleFileVisitor<Path> {
        private final Map<FileNameAndSize, List<Path>> duplicates = new HashMap<>();

        public List<List<Path>> getDuplicates() {
            return duplicates.values().stream().filter(lst -> lst.size() > 1).collect(Collectors.toList());
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            FileNameAndSize dup = new FileNameAndSize(file.getFileName().toString(), Files.size(file));
            if (duplicates.containsKey(dup)) {
                duplicates.get(dup).add(file);
            } else {
                duplicates.put(dup, new ArrayList<>(List.of(file)));
            }
            return FileVisitResult.CONTINUE;
        }
    }

    private static class FileNameAndSize {
        final String name;
        final long size;

        public FileNameAndSize(String name, long size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FileNameAndSize fileNameAndSize = (FileNameAndSize) o;
            return size == fileNameAndSize.size &&
                    Objects.equals(name, fileNameAndSize.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, size);
        }
    }
}
