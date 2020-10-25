package io.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class App {
    private final static Logger LOG = LoggerFactory.getLogger(App.class);
    private final ArgumentParser parser;

    public App(String[] args) {
        parser = new ArgumentParser(args);
    }

    public void run() {
        try {

            if (parser.isValid()) {
                startSearching();
            } else {
                printUsage();
            }

        } catch (IOException exc) {
                LOG.error("Searching or writing exception", exc);
        } catch (Exception exc) {
                LOG.error("Unexpected exception", exc);
        }
    }

    private void startSearching() throws IOException {
        FileSearcher searcher = new FileSearcher(Path.of(parser.getSearchDirectory()));
        List<Path> foundFiles = searcher.search(SearchPredicateFactory.getPredicate(
                parser.getSearchOption(),
                parser.getSearchExpression()
        ));
        if (foundFiles.isEmpty()) {
            System.out.println("No files were found");
        } else {
            ResultWriter.writeToFile(parser.getOutputFile(), foundFiles);
            ResultWriter.writeToConsole(foundFiles);
        }
    }

    private void printUsage() {
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
