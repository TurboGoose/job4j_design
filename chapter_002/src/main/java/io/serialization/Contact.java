package io.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "zipCode=" + zipCode +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode &&
                Objects.equals(phone, contact.phone);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path tempFile = Files.createTempFile(null, null);

        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        try (FileOutputStream fos = new FileOutputStream(tempFile.toFile());
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        final Contact contactFromFile;
        try (FileInputStream fis = new FileInputStream(tempFile.toFile());
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            contactFromFile = (Contact) ois.readObject();
        }

        System.out.println(contact);
        System.out.println(contactFromFile);
        System.out.println(Objects.equals(contact, contactFromFile));
    }
}
