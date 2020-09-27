package tasks.mail.map;

import java.util.*;

public class User {
    public String name;
    public Set<String> emails;

    public User(String name) {
        this(name, new HashSet<>());
    }

    public User(String name, String email) {
        this(name, Collections.singleton(email));
    }

    public User(User user) {
        this(user.name, user.emails);
    }

    public User(String name, Collection<String> emails) {
        this.name = name;
        this.emails = new HashSet<>(emails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(emails, user.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emails);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", emails=" + emails +
                '}';
    }

    public Set<String> mergeEmails(User other) {
        Set<String> diff = new HashSet<>(other.emails);
        diff.removeAll(emails);
        emails.addAll(other.emails);
        return diff;
    }
}
