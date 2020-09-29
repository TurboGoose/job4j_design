package io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ConfigTest {
    @TempDir
    File tempDir;

    @Test
    public void emptyPropertyFile() {
        File source = new File(tempDir, "test.txt");
        Config conf = new Config(source.getAbsolutePath());
        conf.load();
        assertThat(conf.value(""), is(nullValue()));
    }

    @Test
    public void propertyFileWithoutCommentsAndEmptyLines() throws IOException {
        String inputData =
                "property1=1\n" +
                "property2=2";
        File source = new File(tempDir, "test.txt");
        try (FileWriter sourceFile = new FileWriter(source)) {
            sourceFile.write(inputData);
        }
        Config conf = new Config(source.getAbsolutePath());
        conf.load();
        assertThat(conf.value("property1"), is("1"));
        assertThat(conf.value("property2"), is("2"));
    }

    @Test
    public void propertyFileWithEmptyLines() throws IOException {
        String inputData =
                "property1=1\n\n\n" +
                "property2=2\n";
        File source = new File(tempDir, "test.txt");
        try (FileWriter sourceFile = new FileWriter(source)) {
            sourceFile.write(inputData);
        }
        Config conf = new Config(source.getAbsolutePath());
        conf.load();
        assertThat(conf.value("property1"), is("1"));
        assertThat(conf.value("property2"), is("2"));
        assertThat(conf.value(""), is(nullValue()));
    }

    @Test
    public void propertyFileWithComments() throws IOException {
        String inputData =
                "property1=1\n" +
                "//comment\n" +
                "property2=2";
        File source = new File(tempDir, "test.txt");
        try (FileWriter sourceFile = new FileWriter(source)) {
            sourceFile.write(inputData);
        }
        Config conf = new Config(source.getAbsolutePath());
        conf.load();
        assertThat(conf.value("property1"), is("1"));
        assertThat(conf.value("property2"), is("2"));
        assertThat(conf.value("//comment"), is(nullValue()));
    }

    @Test
    public void propertyFileWithCommentsAndEmptyLines() throws IOException {
        String inputData =
                "property1=1\n\n\n" +
                "//comment\n\n" +
                "property2=2\n" +
                "//comment\n\n";
        File source = new File(tempDir, "test.txt");
        try (FileWriter sourceFile = new FileWriter(source)) {
            sourceFile.write(inputData);
        }
        Config conf = new Config(source.getAbsolutePath());
        conf.load();
        assertThat(conf.value("property1"), is("1"));
        assertThat(conf.value("property2"), is("2"));
        assertThat(conf.value(""), is(nullValue()));
        assertThat(conf.value("//comment"), is(nullValue()));
    }
}
