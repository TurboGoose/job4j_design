package io.serialization.tutorials.habr;

import java.io.*;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Parent implements Serializable {
    public byte parentField = 1;

    @Override
    public String toString() {
        return "Parent{" +
                "parentField=" + parentField +
                '}';
    }
}

class Composition implements Serializable  {
    public int compositionField = 10;

    @Override
    public String toString() {
        return "Composition{" +
                "compositionField=" + compositionField +
                '}';
    }
}

public class TestSerial extends Parent {
    public byte version = 100;
    public Composition composition = new Composition();
    public byte[] array = new byte[3];

    @Override
    public String toString() {
        return "TestSerial{" +
                "parentField=" + parentField +
                ", version=" + version +
                ", composition=" + composition +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}

class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
            
    public static void main(String[] args) {
        try {
            final String FILE = "chapter_002/src/main/java/io/serialization/tutorials/habr/obj.txt";
            writeObjectToFile(new TestSerial(), FILE);
            System.out.println(readFromFile(FILE));
        }
        catch (Exception exc) {
            LOG.error("Object IO error", exc);
        }
    }

    static void writeObjectToFile(Object obj, String filename) throws IOException {
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(filename))) {
            ous.writeObject(obj);
            ous.flush();
        }
    }

    static TestSerial readFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (TestSerial) ois.readObject();
        }
    }
}
