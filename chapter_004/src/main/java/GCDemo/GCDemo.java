package GCDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// VM arguments: -Xmx100m -Xms100m -XX:+UseSerialGC -XX:SurvivorRatio=3
public class GCDemo {
    private static final Runtime env = Runtime.getRuntime();
    private static final List<Object> objectStorage = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1) Create 5 mb of garbage");
            System.out.println("2) Create 2 mb of live objects");
            System.out.println("3) Collect garbage");
            System.out.println("4) Print info");
            System.out.println("5) Exit");

            int option = sc.nextInt();
            if (option == 1) {
                allocateMemory(5);
                System.out.println("5 mb of garbage has been allocated");
            } else if (option == 2) {
                objectStorage.add(allocateMemory(2));
                System.out.println("2 mb of live objects has been allocated");
            } else if (option == 3) {
                System.gc();
            } else if (option == 4) {
                info();
            } else if (option == 5) {
                break;
            } else {
                System.out.println("Wrong input");
            }
        }
    }

    public static byte[] allocateMemory(int mb) {
        return new byte[1048576 * mb - 16];
    }

    public static void info() {
        long free = env.freeMemory();
        long total = env.totalMemory();
        long max = env.maxMemory();
        System.out.printf("[free: %d bytes (%.2f mb)] [total: %d bytes (%.2f mb)] [max: %d bytes (%.2f mb)]%n",
                free, mb(free), total, mb(total), max, mb(max)
        );
    }

    public static double mb(long bytes) {
        return bytes / 1048576.0;
    }
}
