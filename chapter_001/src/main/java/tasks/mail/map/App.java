package tasks.mail.map;

import tasks.mail.user.UserMerger;

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
        int numOfUsers = sc.nextInt();
        sc.nextLine();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < numOfUsers; i++) {
            String[] inputLine = sc.nextLine().split(" - ");
            if (inputLine.length == 2) {
                users.add(new User(inputLine[0], Arrays.asList(inputLine[1].split(", "))));
            }
        }

        System.out.println("--------- Result ---------");
        for (User u : UserTools.mergeUsers(users)) {
            System.out.println(u.name + " - " + String.join(", ", u.emails));
        }
    }
}

