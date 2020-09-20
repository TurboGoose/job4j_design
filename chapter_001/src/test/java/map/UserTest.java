package map;

import org.junit.jupiter.api.Test;
import java.util.*;

class UserTest {
    @Test
    public void createMapWithEqualUserObjects() {
        User user1 = new User("Ilya", 0, new GregorianCalendar(2000, Calendar.JANUARY, 1));
        User user2 = new User("Ilya", 0, new GregorianCalendar(2000, Calendar.JANUARY, 1));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, 1);
        map.put(user2, 2);
        for (User user : map.keySet()) {
            System.out.println(user + ": " + map.get(user));
        }
    }
}
