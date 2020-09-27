package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        final String PATH = "./files/read/even.txt";
        try (FileInputStream in = new FileInputStream(PATH)) {
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
