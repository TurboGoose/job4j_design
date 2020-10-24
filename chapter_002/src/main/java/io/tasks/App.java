package io.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

public class App {
    private final static Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser(args);
        if (parser.isValid()) {
            Path searchDir = Path.of(parser.getSearchDirectory());
            FileSearcher searcher = new FileSearcher(searchDir);
            try {
                List<Path> foundFiles =
                        switch (parser.getSearchOption()) {
                    case MASK -> searcher.searchByMask(parser.getFileName());
                    case REGEX -> searcher.searchByRegex(parser.getFileName());
                    case FULL_NAME -> searcher.searchByFullName(parser.getFileName());
                };

                if (foundFiles.isEmpty()) {
                    System.out.println("No files were found");
                }
                else {
                    try (PrintWriter out = new PrintWriter(parser.getOutputFile())) {
                        out.println("---- Searching results ----");
                        System.out.println("---- Searching results ----");
                        for (Path file : foundFiles) {
                            out.println(file);
                            System.out.println(file);
                        }
                    }
                }
            }
            catch (IOException exc) {
                LOG.error("Searching or writing exception", exc);
            }
            catch (Exception exc) {
                LOG.error("Unexpected exception", exc);
            }
        }
        else {
            System.out.println(String.join(System.lineSeparator(),
                    "Usage:  java -jar find.jar [arguments]",
                    "Arguments:",
                    "-d - search directory",
                    "-o - result output file",
                    "-n - filename, mask or regex",
                    "-m - search by mask",
                    "-f - search by full name",
                    "-r - search by regex"
            ));
        }
    }
}
