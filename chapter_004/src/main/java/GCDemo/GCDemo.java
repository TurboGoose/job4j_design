package GCDemo;

public class GCDemo {
    private static final long MB = 1000000;
    private static final Runtime ENV = Runtime.getRuntime();

    public static void info() {
        System.out.printf("%n=== Environment state ===%nFree: %d%nTotal: %d%nMax: %d%nPersons alive: %d%n%n",
                ENV.freeMemory() / MB,
                ENV.totalMemory() / MB,
                ENV.maxMemory() / MB,
                Person.personsAlive
        );
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10000; i++) {
            new Person(i, "P" + i);
        }
        System.gc();
        info();
    }
}
