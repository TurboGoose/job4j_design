package io.serialization.tutorials.baeldung.uid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class SerializationUtility {
    public static void main(String[] args) {
        try {
            AppleProduct macBook = new AppleProduct();
            macBook.headphonePort = "headphone port 2020";
            macBook.thunderboltPort = "thunderbolt port 2020";
            macBook.lightningPorts = "lightning port 2020";

            String serializedObj = serializeObjectToString(macBook);
            System.out.println("Serialized :\n" + serializedObj);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static String serializeObjectToString(Serializable s) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(s);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }
    }
}
