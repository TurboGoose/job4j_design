package tasks.mail.map;

import java.util.*;

public class UserTools {

    public static Set<User> mergeUsers(Collection<User> users) {
        return new HashSet<>(createEmailToUserMap(users).values());
    }

    static Map<String, User> createEmailToUserMap(Collection<User> users) {
        Map<String, User> emailToUser = new LinkedHashMap<>();
        for (User user : users) {
            for (String email : user.emails) {
                if (emailToUser.containsKey(email)) {
                    User originalUser = emailToUser.get(email);
                    for (String e : originalUser.mergeEmails(user)) {
                        emailToUser.put(e, originalUser);
                    }
                } else {
                    emailToUser.put(email, user);
                }
            }
        }
        return emailToUser;
    }
}
