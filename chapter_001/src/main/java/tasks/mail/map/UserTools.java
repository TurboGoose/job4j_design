package tasks.mail.map;

import java.util.*;

public class UserTools {
    public static Map<String, Set<String>> mergeUsers(Map<String, Set<String>> users) {
        return mailsByUser(userByMail(users));
    }

    static <U, M> Map<M, U> userByMail(Map<U, Set<M>> map) {
        Map<M, U> result = new HashMap<>();
        for (U user : map.keySet()) {
            for (M mail : map.get(user)) {
                result.putIfAbsent(mail, user);
            }
        }
        return result;
    }

    static <U, M> Map<U, Set<M>> mailsByUser(Map<M, U> map) {
        Map<U, Set<M>> result = new HashMap<>();
        for (M mail : map.keySet()) {
            result.putIfAbsent(map.get(mail), new HashSet<>());
            result.get(map.get(mail)).add(mail);
        }
        return result;
    }

}
