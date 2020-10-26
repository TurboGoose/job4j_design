package io.tasks.filesearcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileSearcher {
    private final Path searchDirectory;

    public FileSearcher(Path searchDirectory) {
        this.searchDirectory = searchDirectory;
    }

    public List<Path> search(Predicate<Path> predicate) throws IOException {
        return Files.find(searchDirectory, 100, (p, bfa) -> predicate.test(p))
                .collect(Collectors.toList());
    }
}
