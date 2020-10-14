package io.serialization.tutorial;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            serializeExplicitUIDClassThenTryToDeserializeIt();
            serializeImplicitUIDClassThenTryToDeserializeIt();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static void serializeExplicitUIDClassThenTryToDeserializeIt() throws IOException, ClassNotFoundException {
        String FILE = "chapter_002/src/main/java/io/serialization/tutorial/explicitUID.txt";
        io.serialization.tutorial.oldclasses.ExplicitUID oldExplicit = new io.serialization.tutorial.oldclasses.ExplicitUID();
        serialize(oldExplicit, FILE);
        io.serialization.tutorial.newclasses.ExplicitUID newExplicit = (io.serialization.tutorial.newclasses.ExplicitUID) deserialize(FILE);
    }

    static void serializeImplicitUIDClassThenTryToDeserializeIt() throws IOException, ClassNotFoundException {
        String FILE = "chapter_002/src/main/java/io/serialization/tutorialimplicitUID.txt";
        io.serialization.tutorial.oldclasses.ImplicitUID oldImplicit = new io.serialization.tutorial.oldclasses.ImplicitUID();
        serialize(oldImplicit, FILE);
        io.serialization.tutorial.newclasses.ImplicitUID newImplicit = (io.serialization.tutorial.newclasses.ImplicitUID) deserialize(FILE);
    }

    static void serialize(Object obj, String filename) throws IOException {
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(filename))) {
            ous.writeObject(obj);
        }
    }

    static Object deserialize(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        }
    }
}
