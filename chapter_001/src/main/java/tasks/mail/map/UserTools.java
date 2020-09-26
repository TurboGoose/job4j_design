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
                    User newMergedUser = originalUser.mergeEmails(user);
                    if (originalUser.emails.size() != newMergedUser.emails.size()) {
                        for (String e : newMergedUser.emails) {
                            emailToUser.put(e, newMergedUser);
                        }
                    }
                } else {
                    emailToUser.put(email, user);
                }
            }
        }
        return emailToUser;
    }
}
