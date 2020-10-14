package io.serialization.tutorials.baeldung.uid;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

public class DeserializationUtility {
    public static void main(String[] args) {
        try {
            String serialized = "rO0ABXNyAC9pby5zZXJpYWxpemF0aW9uLnR1dG9yaWFsLmJhZWxkdW5nLkFwcGxlUHJvZHVjdDiyWXRfgUdMAgADTAANaGVhZHBob25lUG9ydHQAEkxqYXZhL2xhbmcvU3RyaW5nO0wADmxpZ2h0bmluZ1BvcnRzcQB+AAFMAA90aHVuZGVyYm9sdFBvcnRxAH4AAXhwdAATaGVhZHBob25lIHBvcnQgMjAyMHQAE2xpZ2h0bmluZyBwb3J0IDIwMjB0ABV0aHVuZGVyYm9sdCBwb3J0IDIwMjA=";
            System.out.println("Deserialized :\n" + deserializeObjectFromString(serialized));
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public static Object deserializeObjectFromString(String obj) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(
                        Base64.getDecoder().decode(obj)))) {
            return ois.readObject();
        }
    }
}
