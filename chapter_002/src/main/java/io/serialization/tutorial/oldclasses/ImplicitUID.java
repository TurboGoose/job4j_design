package io.serialization.tutorial.oldclasses;

import java.io.Serializable;

public class ImplicitUID implements Serializable {
    int number = 2;

    void increment() {
        number++;
    }
}