package tasks.mail.user;

import java.util.*;

public class UserMerger {
    public static List<User> merge(List<User> users) {
        List<User> mergedUsers = new ArrayList<>();
        boolean[] alreadyMerged = new boolean[users.size()];
        for (int i = 0; i < users.size(); i++) {
            if (!alreadyMerged[i]) {
                User userToMerge = new User(users.get(i));
                for (int j = i + 1; j < users.size(); j++) {
                    if (!intersectionOf(userToMerge.mailBoxes, users.get(j).mailBoxes).isEmpty()) {
                        userToMerge.mailBoxes.addAll(users.get(j).mailBoxes);
                        alreadyMerged[j] = true;
                    }
                }
                mergedUsers.add(userToMerge);
            }
        }
        return mergedUsers;
    }

    static <T> Set<T> intersectionOf(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> set1Copy = new HashSet<>(set1);
        set1Copy.retainAll(set2);
        return set1Copy;
    }
}
