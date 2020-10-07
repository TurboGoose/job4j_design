package io.iostreams.zip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        try {
            ArgZip argZip = new ArgZip(args);
            if (!argZip.valid()) {
                System.out.println(">>> Wrong input format:\n\t" + String.join(" ", args));
                return;
            }
            Zip zip = new Zip();
            zip.packDirectory(Path.of(argZip.directory()), Files.createFile(Path.of(argZip.output())), argZip.exclude());
            System.out.println("*** Complete! ***");
        }
        catch (IOException exc) {
            System.out.println(">>> Input file exception:\n\t" + exc.getMessage());
        }
        catch (Exception exc) {
            System.out.println(">>> Unexpected exception:\n\t" + exc.getMessage());
        }
    }
}
