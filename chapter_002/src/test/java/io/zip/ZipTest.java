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
    public void setupTestFixture(@TempDir Path tempDir) throws IOException {
        srcTemp = Files.createTempDirectory(tempDir, "src");
        resTemp = Files.createTempDirectory(tempDir, "res");
        //setupLocalTestFixture("p:/test");
    }

    public void setupLocalTestFixture(String path) throws IOException {
        Path testFolder = Paths.get(path);
        if (Files.exists(testFolder)) {
            deleteDir(testFolder);
        }
        Files.createDirectory(testFolder);
        srcTemp = Files.createDirectory(Paths.get(testFolder.toString(), "src"));
        resTemp = Files.createDirectory(Paths.get(testFolder.toString(), "res"));
    }

    public void deleteDir(Path dir) throws IOException {
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
        Path sourceFile = Files.writeString(Paths.get(srcTemp.toString(), "source.txt"), "Hello world!!!");
        Path targetFile = Paths.get(resTemp.toString(), "source.txt");

        new Zip().packFile(sourceFile, resTemp);

        assertThat(Files.exists(targetFile), is(true));
        assertThat(targetFile.toFile().length(), is(not(0)));
    }

    @Test
    public void zipDirectoryWithoutExcluding() throws IOException {
        Path sourceRoot = Files.createDirectory(Paths.get(srcTemp.toString(), "sourceRoot"));
        Path fileInSourceRoot = Files.writeString(Paths.get(sourceRoot.toString(), "fileInRoot.txt"), "my location : src/sourceRoot/fileInRoot.txt");
        Path innerFolder = Files.createDirectory(Paths.get(sourceRoot.toString(), "innerFolder"));
        Path fileInInnerSourceFolder = Files.writeString(Paths.get(innerFolder.toString(), "fileInInnerFolder.txt"), "my location : src/sourceRoot/innerFolder/fileInInnerFolder.txt");

        Path targetRoot = Files.createDirectory(Paths.get(resTemp.toString(), "targetRoot"));
        Path fileInTargetRoot = Paths.get(targetRoot.toString(), "fileInRoot.txt");
        Path innerTargetFolder = Paths.get(targetRoot.toString(), "innerFolder");
        Path fileInInnerTargetFolder = Paths.get(innerTargetFolder.toString(), "fileInInnerFolder.txt");

        new Zip().packDirectory(sourceRoot, targetRoot);

        assertThat(Files.exists(targetRoot), is(true));
        assertThat(Files.exists(fileInTargetRoot), is(true));
        assertThat(Files.exists(innerTargetFolder), is(true));
        assertThat(Files.exists(fileInInnerTargetFolder), is(true));
        assertThat(fileInTargetRoot.toFile().length(), is(not(0)));
        assertThat(fileInInnerTargetFolder.toFile().length(), is(not(0)));
    }

    @Test
    public void zipDirectoryWithExcluding() throws IOException {
        Path sourceRoot = Files.createDirectory(Paths.get(srcTemp.toString(), "sourceRoot"));
        Path fileInSourceRootToExclude = Files.writeString(Paths.get(sourceRoot.toString(), "fileInRoot.excl"), "my location : src/sourceRoot/fileInRootToExclude.excl");
        Path innerFolder = Files.createDirectory(Paths.get(sourceRoot.toString(), "innerFolder"));
        Path fileInInnerFolder = Files.writeString(Paths.get(innerFolder.toString(), "fileInInnerFolder.txt"), "my location : src/sourceRoot/innerFolder/fileInInnerFolder.txt");

        Path targetRoot = Files.createDirectory(Paths.get(resTemp.toString(), "targetRoot"));
        Path fileInTargetRootExcluded = Paths.get(targetRoot.toString(), "fileInRoot.excl");
        Path innerTargetFolder = Paths.get(targetRoot.toString(), "innerFolder");
        Path fileInInnerTargetFolder = Paths.get(innerTargetFolder.toString(), "fileInInnerFolder.txt");

        new Zip().packDirectory(sourceRoot, targetRoot, "excl");

        assertThat(Files.exists(targetRoot), is(true));
        assertThat(Files.exists(fileInTargetRootExcluded), is(false));
        assertThat(Files.exists(innerTargetFolder), is(true));
        assertThat(Files.exists(fileInInnerTargetFolder), is(true));
        assertThat(fileInInnerTargetFolder.toFile().length(), is(not(0)));
    }
}
