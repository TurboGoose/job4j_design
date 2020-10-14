package io.serialization.json.car;

public class Engine {
    private int pistons;

    public Engine(int pistons) {
        this.pistons = pistons;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "pistons=" + pistons +
                '}';
    }
}
