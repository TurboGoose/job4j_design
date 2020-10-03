package io.zip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ZipTest {
    Path srcTemp;
    Path resTemp;

    @BeforeEach
    public void setupFiles(@TempDir Path tempDir) throws IOException {
        //srcTemp = Files.createTempDirectory(tempDir, "src");
        //resTemp = Files.createTempDirectory(tempDir, "res");

        Path testDir = Paths.get("p:/test");
        deleteDir(testDir);
        Files.createDirectory(testDir);
        srcTemp = Files.createDirectory(Paths.get("p:/test/src"));
        resTemp = Files.createDirectory(Paths.get("p:/test/res"));
    }

    void deleteDir(Path dir) throws IOException {
        Files.walkFileTree(dir, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void zipSingleFile() throws IOException {
        Path source = Files.writeString(Paths.get(srcTemp.toString(), "source.txt"), "Hello world!!!");
        Path target = Files.createFile(Paths.get(resTemp.toString(), "result.txt"));
        new Zip(source, target).packFiles();

        assertThat(target.toFile().length(), is(not(0)));
    }

    @Test
    public void zipDirectory() throws IOException {
        Path sourceRoot = Files.createDirectory(Paths.get(srcTemp.toString(), "sourceRoot"));
        Path fileInRoot = Files.writeString(Paths.get(sourceRoot.toString(), "fileInRoot.txt"), "location : srcTemp/sourceRoot/fileInRoot.txt");
        Path innerFolder = Files.createDirectory(Paths.get(sourceRoot.toString(), "innerFolder"));
        Path fileInInnerFolder = Files.writeString(Paths.get(innerFolder.toString(), "fileInInnerFolder.txt"), "location : serTemp/sourceRoot/innerFolder/fileInInnerFolder.txt");

        Path targetRoot = Files.createDirectory(Paths.get(resTemp.toString(), "targetRoot"));
        new Zip(sourceRoot, targetRoot).packFiles();
        Path fileInTargetRoot = Paths.get(targetRoot.toString(), "fileInRoot.txt");
        Path innerTargetFolder = Paths.get(targetRoot.toString(), "innerFolder");
        Path fileInInnerTargetFolder = Paths.get(innerTargetFolder.toString(), "fileInInnerFolder.txt");

        assertThat(Files.exists(fileInTargetRoot), is(true));
        assertThat(Files.exists(innerTargetFolder), is(true));
        assertThat(Files.exists(fileInInnerTargetFolder), is(true));
        assertThat(fileInTargetRoot.toFile().length(), is(not(0)));
        assertThat(fileInInnerTargetFolder.toFile().length(), is(not(0)));
    }
}
