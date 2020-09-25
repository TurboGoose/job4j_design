package tasks.mail.user;

import java.util.*;

public class User {
    public Set<String> mailBoxes;
    public String name;

    public User(String name) {
        this(name, new HashSet<>());
    }

    public User(String name, String mail) {
        this(name, Collections.singleton(mail));
    }

    public User(User user) {
        this(user.name, user.mailBoxes);
    }

    public User(String name, Collection<String> mails) {
        this.name = name;
        mailBoxes = new HashSet<>(mails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(mailBoxes, user.mailBoxes) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailBoxes, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "mailBoxes=" + mailBoxes +
                ", name='" + name + '\'' +
                '}';
    }
}
