package io.serialization.tutorials.baeldung.serializable;

import java.io.*;
import java.nio.file.Files;

class Main {
    public static void main(String[] args) {
        try {
            Employee employee = new Employee("Ilya", "user1", 45);

            final File FILE = Files.createTempFile("temp", "txt").toFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
                oos.writeObject(employee);
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
                System.out.println(ois.readObject());
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}