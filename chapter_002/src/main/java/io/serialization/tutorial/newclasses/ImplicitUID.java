package io.serialization.tutorial.newclasses;

import java.io.Serializable;

public class ImplicitUID implements Serializable {
    int number = 2;

    void increment() {
        number++;
    }
}
