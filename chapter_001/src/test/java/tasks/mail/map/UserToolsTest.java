package tasks.mail.map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class UserToolsTest {
    @Nested
    class CreateEmailToUserMapTests {
        List<User> users;

        @Test
        public void addTwoUsersWithUniqueEmails() {
            User user1 = new User("user1", "mail1");
            User user2 = new User("user2", "mail2");
            users = Arrays.asList(user1, user2);

            Map<String, User> expected = new HashMap<>();
            expected.put("mail1", user1);
            expected.put("mail2", user2);

            assertThat(UserTools.createEmailToUserMap(users), is(expected));
        }

        @Test
        public void addTwoUsersWithDifferentEmailsThenAddThirdWithTwoCoincidentalEmails() {
            User user1 = new User("user1", "mail1");
            User user2 = new User("user2", "mail2");
            User user3 = new User("user3", Arrays.asList("mail1", "mail2"));
            users = Arrays.asList(user1, user2, user3);

            Map<String, User> expected1 = new HashMap<>();
            User updatedUser1 = new User("user1", Arrays.asList("mail1", "mail2"));
            expected1.put("mail1", updatedUser1);
            expected1.put("mail2", updatedUser1);

            Map<String, User> expected2 = new HashMap<>();
            User updatedUser2 = new User("user2", Arrays.asList("mail1", "mail2"));
            expected2.put("mail1", updatedUser2);
            expected2.put("mail2", updatedUser2);

            Map<String, User> expected3 = new HashMap<>();
            expected3.put("mail1", user3);
            expected3.put("mail2", user3);

            assertThat(UserTools.createEmailToUserMap(users), isOneOf(expected1, expected2, expected3));
        }

        @Test
        public void addThreeUsersWithTransitiveCoincidentalEmails() {
            User user1 = new User("user1", Arrays.asList("mail1", "mail2"));
            User user2 = new User("user2", Arrays.asList("mail2", "mail3"));
            User user3 = new User("user3", Arrays.asList("mail3", "mail4"));
            users = Arrays.asList(user1, user2, user3);

            Map<String, User> expected1 = new HashMap<>();
            User updatedUser1 = new User("user1", Arrays.asList("mail1", "mail2", "mail3", "mail4"));
            expected1.put("mail1", updatedUser1);
            expected1.put("mail2", updatedUser1);
            expected1.put("mail3", updatedUser1);
            expected1.put("mail4", updatedUser1);

            Map<String, User> expected2 = new HashMap<>();
            User updatedUser2 = new User("user2", Arrays.asList("mail1", "mail2", "mail3", "mail4"));
            expected2.put("mail1", updatedUser2);
            expected2.put("mail2", updatedUser2);
            expected2.put("mail3", updatedUser2);
            expected2.put("mail4", updatedUser2);

            Map<String, User> expected3 = new HashMap<>();
            User updatedUser3 = new User("user3", Arrays.asList("mail1", "mail2", "mail3", "mail4"));
            expected3.put("mail1", updatedUser3);
            expected3.put("mail2", updatedUser3);
            expected3.put("mail3", updatedUser3);
            expected3.put("mail4", updatedUser3);

            assertThat(UserTools.createEmailToUserMap(users), isOneOf(expected1, expected2, expected3));
        }
    }

    @Test
    public void mergeUsersWhenTwoOfThemHaveCoincidentalEmailsAndOneHaveNot() {
        List<User> users = Arrays.asList(
                new User("user1", Arrays.asList("mail1", "mail2")),
                new User("user2", Arrays.asList("mail2", "mail3")),
                new User("user3", "mail4")
        );
        Set<User> expected1 = new HashSet<>(Arrays.asList(
                new User("user1", Arrays.asList("mail1", "mail2", "mail3")),
                new User("user3", "mail4")
        ));
        Set<User> expected2 = new HashSet<>(Arrays.asList(
                new User("user2", Arrays.asList("mail1", "mail2", "mail3")),
                new User("user3", "mail4")
        ));
        assertThat(UserTools.mergeUsers(users), isOneOf(expected1, expected2));
    }
}