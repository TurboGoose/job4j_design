package io.tasks;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

public class ResultWriter {
    public static void writeToFile(String outputFile, List<Path> foundFiles) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(outputFile)) {
            out.println("---- Searching results ----");
            foundFiles.forEach(out::println);
        }
    }

    public static void writeToConsole(List<Path> foundFiles) {
        System.out.println("---- Searching results ----");
        foundFiles.forEach(System.out::println);
    }
}
