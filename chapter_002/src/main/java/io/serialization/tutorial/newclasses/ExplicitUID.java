package io.serialization.tutorial.newclasses;

import java.io.Serializable;

public class ExplicitUID implements Serializable {
    private static final long serialVersionUID = 1234L;
    int number = 2;

    void increment() {
        number++;
    }
}
