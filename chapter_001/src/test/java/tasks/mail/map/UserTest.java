package tasks.mail.map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class UserTest {
    User user1;
    User user2;

    @Nested
    class MergeEmailsTests {
        @Test
        public void mergeEmptyUsers() {
             user1 = new User("user1");
             user2 = new User("user2");
             assertThat(user1.mergeEmails(user2), is(new HashSet<>()));
             assertThat(user2.mergeEmails(user1), is(new HashSet<>()));
        }

        @Test
        public void mergeEmptyAndNotEmptyUsers() {
            user1 = new User("user1", "mail1");
            user2 = new User("user2");
            assertThat(user1.mergeEmails(user2), is(Collections.emptySet()));
            assertThat(user2.mergeEmails(user1), is(Collections.singleton("mail1")));
        }

        @Test
        public void mergeUsersWithoutCommonEmails() {
            user1 = new User("user1", Arrays.asList("mail1", "mail2"));
            user2 = new User("user2", Arrays.asList("mail3", "mail4"));
            assertThat(user1.mergeEmails(user2), is(new HashSet<>(Arrays.asList("mail3", "mail4"))));
            assertThat(user2.mergeEmails(user1), is(new HashSet<>(Arrays.asList("mail1", "mail2"))));
        }

        @Test
        public void mergeUsersWithCommonEmails() {
            user1 = new User("user1", Arrays.asList("mail1", "mail2"));
            user2 = new User("user2", Arrays.asList("mail2", "mail3"));
            assertThat(user1.mergeEmails(user2), is(Collections.singleton("mail3")));
            assertThat(user2.mergeEmails(user1), is(Collections.singleton("mail1")));
        }
    }
}