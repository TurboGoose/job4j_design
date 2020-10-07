package io.iostreams;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public void checkEvenAndOddNumbers(String source) {
        try (FileInputStream in = new FileInputStream(source)) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            Arrays.stream(text.toString().split(System.lineSeparator()))
                    .map(Integer::parseInt)
                    .forEach(i -> System.out.println(i + " -> " + (i % 2 == 0 ? "even" : "odd")));
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
