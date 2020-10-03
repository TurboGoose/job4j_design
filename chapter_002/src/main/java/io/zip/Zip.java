package io.zip;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip extends SimpleFileVisitor<Path> {
    private Path source;
    private Path target;
    private final String toExclude;

    public Zip(Path source, Path target, String toExclude) {
        this.source = source;
        this.target = target;
        this.toExclude = toExclude;
    }

    public Zip(Path source, Path target) {
        this(source, target, null);
    }

    public void packFiles() throws IOException {
        Files.walkFileTree(source, this);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path targetSubFolder = Paths.get(target.toString(), source.relativize(dir).toString()).normalize();

        System.out.println("Copying folder : " + targetSubFolder);

        if (!Files.exists(targetSubFolder)) {
            target = Files.createDirectory(targetSubFolder);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (toExclude == null || !file.endsWith(toExclude)) {
            packSingleFile(file, Files.createFile(Paths.get(target.toString(), file.getFileName().toString())));
        }
        return FileVisitResult.CONTINUE;
    }

    public void packSingleFile(Path source, Path target) throws IOException {
        System.out.println("Copying file : " + source + "\n          To : " + target);
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        }
    }
}
