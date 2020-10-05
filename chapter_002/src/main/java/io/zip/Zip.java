package io.zip;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFile(Path sourceFile, Path targetDir) throws IOException {
        targetDir = Files.createFile(Paths.get(targetDir.toString(), sourceFile.getFileName().toString()));
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(targetDir.toFile())))) {
            zip.putNextEntry(new ZipEntry(sourceFile.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sourceFile.toFile()))) {
                zip.write(out.readAllBytes());
            }
        }
    }

    public void packDirectory(Path sourceDir, Path targetDir) throws IOException {
        packDirectory(sourceDir, targetDir, null);
    }

    public void packDirectory(Path sourceDir, Path targetDir, String toExclude) throws IOException {
        Files.walkFileTree(sourceDir, new DirectoryZipper(sourceDir, targetDir, toExclude));
    }

    class DirectoryZipper  extends SimpleFileVisitor<Path> {
        private final Path sourceDir;
        private Path targetDir;
        private final String toExclude;

        public DirectoryZipper(Path sourceDir, Path targetDir, String toExclude) {
            this.sourceDir = sourceDir;
            this.targetDir = targetDir;
            this.toExclude = toExclude;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Path targetSubFolder = Paths.get(targetDir.toString(), sourceDir.relativize(dir).toString()).normalize();
            if (!Files.exists(targetSubFolder)) {
                targetDir = Files.createDirectory(targetSubFolder);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (toExclude == null || !file.toString().endsWith(toExclude)) {
                packFile(file, targetDir);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
