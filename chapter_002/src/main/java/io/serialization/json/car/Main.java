package io.serialization.json.car;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        String JSONCar = gson.toJson(new Car());
        System.out.println(JSONCar);
        System.out.println(gson.fromJson(JSONCar, Car.class));
    }
}
