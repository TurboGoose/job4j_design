package io.zip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ZipTest {
    Path srcTemp;
    Path resTemp;

    @BeforeEach
    public void setupTestFixture(@TempDir Path tempDir) throws IOException {
        srcTemp = Files.createTempDirectory(tempDir, "src");
        resTemp = Files.createTempDirectory(tempDir, "res");
    }

    @Test
    public void zipSingleFile() throws IOException {
        Path sourceFile = Files.writeString(Paths.get(srcTemp.toString(), "source.txt"), "Hello world!!!");
        Path targetFile = Paths.get(resTemp.toString(), "target.txt");
        new Zip().packFile(sourceFile, targetFile);
        assertThat(Files.exists(targetFile), is(true));
        assertThat(targetFile.toFile().length(), is(not(0)));
    }

    @Test
    public void zipDirectoryWithoutExcluding() throws IOException {
        Path sourceRoot = Files.createDirectory(Paths.get(srcTemp.toString(), "sourceRoot"));
        Files.writeString(Paths.get(sourceRoot.toString(), "fileInRoot.txt"), "my location : src/sourceRoot/fileInRoot.txt");
        Path innerFolder = Files.createDirectory(Paths.get(sourceRoot.toString(), "innerFolder"));
        Files.writeString(Paths.get(innerFolder.toString(), "fileInInnerFolder.txt"), "my location : src/sourceRoot/innerFolder/fileInInnerFolder.txt");
        Path targetFile = Paths.get(resTemp.toString(), "target.zip");
        new Zip().packDirectory(sourceRoot, targetFile);
        assertThat(Files.exists(targetFile), is(true));
        assertThat(targetFile.toFile().length(), is(not(0)));
    }

    @Test
    public void zipDirectoryWithExcluding() throws IOException {
        Path sourceRoot = Files.createDirectory(Paths.get(srcTemp.toString(), "sourceRoot"));
        Files.writeString(Paths.get(sourceRoot.toString(), "fileInRoot.excl"), "my location : src/sourceRoot/fileInRootToExclude.excl");
        Path innerFolder = Files.createDirectory(Paths.get(sourceRoot.toString(), "innerFolder"));
        Files.writeString(Paths.get(innerFolder.toString(), "fileInInnerFolder.txt"), "my location : src/sourceRoot/innerFolder/fileInInnerFolder.txt");
        Path targetFile = Paths.get(resTemp.toString(), "target.zip");
        new Zip().packDirectory(sourceRoot, targetFile, "excl");
        assertThat(Files.exists(targetFile), is(true));
        assertThat(targetFile.toFile().length(), is(not(0)));
    }

    @Test
    public void zipEmptyFolders() throws IOException {
        Path sourceRoot = Files.createDirectory(Paths.get(srcTemp.toString(), "sourceRoot"));
        Files.createDirectory(Paths.get(sourceRoot.toString(), "innerFolder"));
        Path targetFile = Paths.get(resTemp.toString(), "target.zip");
        new Zip().packDirectory(sourceRoot, targetFile);
        assertThat(Files.exists(targetFile), is(true));
        assertThat(targetFile.toFile().length(), is(not(0)));
    }
}
