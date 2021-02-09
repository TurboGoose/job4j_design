package io.serialization.xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

public class XmlDemo {
    private static final  Person person = new Person(
            false, 30, new Contact("11-111"), "Worker", "Married");

    public static void main(String[] args) {
        try {
            serializePersonToXml();
            deserializePersonFromXml();
        } catch (JAXBException exc) {
            exc.printStackTrace();
        }
    }

    private static void serializePersonToXml() throws JAXBException {
        System.out.println("--- Serializing ---");
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter stringWriter = new StringWriter();
             FileWriter fileWriter = new FileWriter("person.xml")) {
            marshaller.marshal(person, fileWriter);
            marshaller.marshal(person, stringWriter);
            String result = stringWriter.getBuffer().toString();
            System.out.println(result);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private static void deserializePersonFromXml() throws JAXBException {
        System.out.println("\n--- Deserializing ---");
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File("person.xml");
        Person person = (Person) unmarshaller.unmarshal(file);
        System.out.println(person);
    }
}
