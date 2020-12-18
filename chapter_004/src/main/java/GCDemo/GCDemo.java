package GCDemo;

public class GCDemo {
    private static final long MB = 1000000;
    private static final Runtime ENV = Runtime.getRuntime();

    public static void info() {
        System.out.printf("%n=== Environment state ===%nFree: %d; Total: %d; Max: %d; Persons alive: %d%n%n",
                ENV.freeMemory() / MB,
                ENV.totalMemory() / MB,
                ENV.maxMemory() / MB,
                Person.personsAlive
        );
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(new Person(i, "P" + i + 1));
        }
        info();
        System.out.println("Before calling GC");
        System.gc();
        info();
    }
}
