package io.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileSearcher {
    private final Path searchDirectory;

    public FileSearcher(Path searchDirectory) {
        this.searchDirectory = searchDirectory;
    }

    public List<Path> searchByMask(String mask) throws IOException {
        return Files.find(searchDirectory, 100,
                (p, bfa) -> p.toString().endsWith(mask.startsWith("*") ? mask.substring(1) : mask))
                .collect(Collectors.toList());
    }

    public List<Path> searchByRegex(String regex) throws IOException {
        return Files.find(searchDirectory, 100, (p, bfa) -> p.toString().matches(regex))
                .collect(Collectors.toList());
    }

    public List<Path> searchByFullName(String name) throws IOException {
        return Files.find(searchDirectory, 100, (p, bfa) -> p.toString().equals(name))
                .collect(Collectors.toList());
    }
}
