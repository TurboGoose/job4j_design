package io.serialization.tutorials.baeldung.externalizable;

import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        try {
            testCountry();
            testRegion();
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void testCountry() throws IOException, ClassNotFoundException {
        Country country = new Country();
        country.setName("Russia");
        country.setCode(88888888);

        final File FILE = Files.createTempFile("tempCountry", "txt").toFile();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(country);
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            System.out.println(in.readObject());
        }
    }

    public static void testRegion() throws IOException, ClassNotFoundException {
        Region region = new Region();
        region.setName("Siberia");
        region.setClimate("Cold");
        region.setCode(66666);
        region.setPopulation(1000000);

        final File FILE = Files.createTempFile("tempRegion", "txt").toFile();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(region);
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            System.out.println(in.readObject());
        }
    }

}
