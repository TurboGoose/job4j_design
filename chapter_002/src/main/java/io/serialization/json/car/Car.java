package io.serialization.json.car;

import java.util.Arrays;

public class Car {
    Engine engine = new Engine(16);
    int horsepower = 200;
    boolean leftHand = true;
    String brand = "Toyota";
    String[] manufacturers = new String[] {"Toyota inc.", "Ford inc."};

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", horsepower=" + horsepower +
                ", leftHand=" + leftHand +
                ", brand='" + brand + '\'' +
                ", manufacturers=" + Arrays.toString(manufacturers) +
                '}';
    }
}
