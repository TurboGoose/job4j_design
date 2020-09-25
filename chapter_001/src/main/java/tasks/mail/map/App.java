package tasks.mail.map;

import java.util.*;

/**
 * Input format:
 * ----------------------------
 * M
 * user1 - mail1, mail2, ...
 * ...
 * userM - mail1, mail2, ...
 * ----------------------------
 * where M is a number of users
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Set<String>> users = new HashMap<>();
        int numOfEntries = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numOfEntries; i++) {
            String[] inputLine = sc.nextLine().split(" - ");
            if (inputLine.length == 2) {
                users.put(inputLine[0], new HashSet<>(Arrays.asList(inputLine[1].split(", "))));
            }
        }


    }
}

