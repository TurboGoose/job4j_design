package mail.user;

import java.util.*;

/**
 * Input format:
 * ----------------------------
 * M
 * user1 - mail1, mail2, ...
 * ...
 * userM - mail1, mail2, ...
 * ----------------------------
 * where M is a number of entries
 */

class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfEntries = sc.nextInt();
        sc.nextLine();
        List<User> users = new ArrayList<>(numOfEntries);
        for (int i = 0; i < numOfEntries; i++) {
            User user = parseUser(sc.nextLine());
            if (user != null) {
                users.add(user);
            }
        }
        users = UserMerger.merge(users);
        System.out.println("--------- Result ---------");
        for (User u : users) {
            System.out.println(u.name + " - " + String.join(", ", u.mailBoxes));
        }
    }

    public static User parseUser(String str) {
        String[] data = str.split(" - ");
        if (data.length == 2) {
            return new User(data[0], Arrays.asList(data[1].split(", ")));
        }
        return null;
    }
}
