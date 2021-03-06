package io.iostreams;

import io.iostreams.Analizy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AnalizyTest {
    @TempDir
    File tempDir;

    @Test
    public void readThenWrite() throws IOException {
        File source = new File(tempDir, "source.txt");
        File target = new File(tempDir, "target.txt");
        String inputData = String.join(
                System.lineSeparator(),
                "200 10:56:01",
                "500 10:57:01",
                "400 10:58:01",
                "200 10:59:01",
                "500 11:01:02",
                "200 11:02:02"
        );

        String expected =
                "10:57:01 - 10:59:01" +
                "11:01:02 - 11:02:02";
        try (FileWriter sourceFile = new FileWriter(source)) {
            sourceFile.write(inputData);
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder actual = new StringBuilder();
        try (BufferedReader targetFile = new BufferedReader(new FileReader(target))) {
            targetFile.lines().forEach(actual::append);
        }
        assertThat(actual.toString(), is(expected));
    }
}
