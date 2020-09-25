package tasks.analyzer;

import java.util.*;

public class Analyzer {

    public Info diff(List<User> previous, List<User> current) {
        Info diff = new Info();
        diff.added = differenceOf(current, previous).size();
        diff.deleted = differenceOf(previous, current).size();
        for (User user : intersectionOf(previous, current)) {
            if (!Objects.equals(
                    previous.get(previous.indexOf(user)).name,
                    current.get(current.indexOf(user)).name)) {
                diff.changed++;
            }
        }
        return diff;
    }

    <T> Set<T> intersectionOf(Collection<T> set1, Collection<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    <T> Set<T> differenceOf(Collection<T> minuend, Collection<T> subtrahend) {
        Set<T> result = new HashSet<>(minuend);
        result.removeAll(subtrahend);
        return result;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info() {
            this(0, 0, 0);
        }

        @Override
        public String toString() {
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return added == info.added &&
                    changed == info.changed &&
                    deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
