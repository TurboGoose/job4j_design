package io.zip;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFile(Path sourceFile, Path targetFile) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targetFile.toFile()))) {
            zos.putNextEntry(new ZipEntry(sourceFile.toString()));
            Files.copy(sourceFile, zos);
            zos.closeEntry();
        }
    }

    public void packDirectory(Path sourceDir, Path targetFile) throws IOException {
        packDirectory(sourceDir, targetFile, null);
    }

    public void packDirectory(Path sourceDir, Path targetFile, String toExclude) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targetFile.toFile()))) {
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (toExclude == null || !file.getFileName().toString().endsWith(toExclude)) {
                        zos.putNextEntry(new ZipEntry(sourceDir.relativize(file).toString()));
                        Files.copy(file, zos);
                        zos.closeEntry();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    zos.putNextEntry(new ZipEntry(sourceDir.relativize(dir).toString() + File.separator));
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }
}
