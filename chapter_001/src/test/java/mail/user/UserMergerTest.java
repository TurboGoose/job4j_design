package mail.user;

import mail.user.User;
import mail.user.UserMerger;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class UserMergerTest {

    @Nested
    class IntersectionTests {
        @Test
        public void destructiveTest() {
            Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
            Set<Integer> set2 = new HashSet<>(Arrays.asList(1, 2));
            Set<Integer> set1Copy = new HashSet<>(set1);
            Set<Integer> set2Copy = new HashSet<>(set2);
            UserMerger.intersectionOf(set1, set2);
            assertThat(set1, is(set1Copy));
            assertThat(set2, is(set2Copy));
        }

        @Test
        public void setsAreEqual() {
            Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
            assertThat(UserMerger.intersectionOf(set, set), is(set));
        }

        @Test
        public void setsAreEmpty() {
            Set<Integer> set = new HashSet<>();
            assertThat(UserMerger.intersectionOf(set, set), is(set));
        }

        @Test
        public void oneSetIsEmptyAndSecondIsNot() {
            Set<Integer> emptySet = new HashSet<>();
            Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
            assertThat(UserMerger.intersectionOf(emptySet, set), is(Collections.emptySet()));
            assertThat(UserMerger.intersectionOf(set, emptySet), is(Collections.emptySet()));
        }

        @Test
        public void intersectionIsEmpty() {
            Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
            Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6));
            assertThat(UserMerger.intersectionOf(set1, set2), is(Collections.emptySet()));
            assertThat(UserMerger.intersectionOf(set2, set1), is(Collections.emptySet()));
        }

        @Test
        public void intersectionIsNotEmpty() {
            Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
            Set<Integer> set2 = new HashSet<>(Arrays.asList(2, 3, 4));
            assertThat(UserMerger.intersectionOf(set1, set2), is(new HashSet<>(Arrays.asList(2, 3))));
            assertThat(UserMerger.intersectionOf(set2, set1), is(new HashSet<>(Arrays.asList(2, 3))));
        }

    }

    @Nested
    class UserMergerTests {
        List<User> users = new ArrayList<>();

        @Test
        public void emptyListsOfUsers() {
            assertThat(UserMerger.merge(users), is(users));
        }

        @Test
        public void allUserAreUnique() {
            users.add(new User("user1", "mail1"));
            users.add(new User("user2", "mail2"));
            users.add(new User("user3", "mail3"));
            assertThat(UserMerger.merge(users), is(users));
        }

        @Test
        public void someUsersMerged() {
            users.add(new User("user1", Arrays.asList("mail1", "mail2")));
            users.add(new User("user2", Arrays.asList("mail2", "mail3")));
            users.add(new User("user3", "mail4"));
            assertThat(UserMerger.merge(users), is(Arrays.asList(
                    new User("user1", Arrays.asList("mail1", "mail2", "mail3")),
                    new User("user3", "mail4")
            )));
        }

        @Test
        public void allUsersMergedInOne() {
            users.add(new User("user1", Arrays.asList("mail1", "mail2")));
            users.add(new User("user2", Arrays.asList("mail2", "mail3")));
            users.add(new User("user3", Arrays.asList("mail3", "mail4")));
            assertThat(UserMerger.merge(users), is(Collections.singletonList(
                    new User("user1", Arrays.asList("mail1", "mail2", "mail3", "mail4"))
            )));
        }
    }
}