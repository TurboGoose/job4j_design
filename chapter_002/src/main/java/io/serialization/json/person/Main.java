package io.serialization.json.person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");

        final Gson gson = new GsonBuilder().create();
        String personJson = gson.toJson(person);
        System.out.println(personJson);

        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
