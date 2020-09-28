package io;

import org.junit.jupiter.api.Test;

class AnalizyTest {
    @Test
    public void readThenWrite() {
        String SOURCE_PATH = "../files/tests/AnalizyTest/source.txt";
        String TARGET_PATH = "../files/tests/AnalizyTest/target.txt";
        Analizy analizy = new Analizy();
        analizy.unavailable(SOURCE_PATH, TARGET_PATH);
    }
}
