package io.serialization.tutorials.baeldung.serializable;

import java.io.*;

class Person implements Serializable {
    public String name;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Address {
    public int houseNumber;

    @Override
    public String toString() {
        return "Address{" +
                "houseNumber=" + houseNumber +
                '}';
    }
}

public class Employee implements Serializable {
    public String login;
    public Person person;
    transient public Address address;

    public Employee(String name, String login, int houseNumber) {
        person = new Person();
        person.name = name;
        this.login = login;
        address = new Address();
        address.houseNumber = houseNumber;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        Address address = new Address();
        address.houseNumber = (int) ois.readObject();
        this.address = address;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(address.houseNumber);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "login='" + login + '\'' +
                ", person=" + person +
                ", address=" + address +
                '}';
    }
}
