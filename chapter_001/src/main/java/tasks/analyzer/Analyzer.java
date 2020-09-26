package tasks.analyzer;

import java.util.*;

public class Analyzer {

    public Info diff(List<User> previous, List<User> current) {
        Info diff = new Info();
        Map<Integer, User> currentMap = createIdToUserMap(current);
        for (User u : previous) {
            if (currentMap.containsKey(u.id)) {
                if (!Objects.equals(currentMap.get(u.id), u)) {
                    diff.changed++;
                }
                currentMap.remove(u.id);
            } else {
                diff.deleted++;
            }
        }
        diff.added += currentMap.size();
        return diff;
    }

    Map<Integer, User> createIdToUserMap(List<User> users) {
        Map<Integer, User> idToUser = new HashMap<>();
        for (User u : users) {
            idToUser.putIfAbsent(u.id, u);
        }
        return idToUser;
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
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info() {}

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
