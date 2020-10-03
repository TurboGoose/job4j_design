package io.zip;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip extends SimpleFileVisitor<Path> {
    private final List<Path> sources;
    private final Path target;
    private final String toExclude;
    private Path curTarget;
    private Path curSource;

    public Zip(List<Path> sources, Path target, String toExclude) {
        this.sources = sources;
        this.target = target;
        this.toExclude = toExclude;
    }

    public Zip(List<Path> sources, Path target) {
        this(sources, target, null);
    }

    public Zip(Path source, Path target) {
        this(List.of(source), target);
    }

    public void packFiles() throws IOException {
        for (Path src : sources) {
            Files.walkFileTree(src, this);
        }
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println(target);
        System.out.println(dir);
        Files.createDirectory(Paths.get(target.toString(), curSource.relativize(dir).toString()));

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (toExclude == null || !file.endsWith(toExclude)) {
            packSingleFile(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public void packSingleFile(Path source) throws IOException {
        System.out.println(source + System.lineSeparator() + target);
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        }
    }
}
