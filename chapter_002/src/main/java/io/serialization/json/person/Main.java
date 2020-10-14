package io.serialization.json.person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class Main {
    private final static Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");


    public static void main(String[] args) {
        testGson();
        testJson();
    }

    public static void testGson() {
        final Gson gson = new GsonBuilder().create();
        String personJson = gson.toJson(person);
        System.out.println(personJson);

        Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }

    public static void testJson() {
        JSONObject jsonObject = new JSONObject(person);
        String personJson = jsonObject.toString();
        System.out.println(personJson);
    }
}
