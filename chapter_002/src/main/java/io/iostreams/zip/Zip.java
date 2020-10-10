package io.iostreams.zip;

import java.io.*;
import java.nio.file.*;
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

    public void packDirectory(Path sourceDir, Path targetFile)  throws IOException {
        packDirectory(sourceDir, targetFile, null);
    }

    public void packDirectory(Path sourceDir, Path targetFile, String toExclude) throws IOException {
        FileSearcher searcher = new FileSearcher(toExclude == null ? null : e -> e.toString().endsWith(toExclude));
        Files.walkFileTree(sourceDir, searcher);
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targetFile.toFile()))) {
            for (Path src : searcher.getPaths()) {
                zos.putNextEntry(new ZipEntry(sourceDir.relativize(src) + (Files.isDirectory(src) ? "/" : "")));
                if (!Files.isDirectory(src)) {
                    Files.copy(src, zos);
                }
                zos.closeEntry();
            }
        }
    }
}
