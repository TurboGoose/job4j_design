package list;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (it.nextIndex() == index) {
                it.add(value);
                break;
            }
            it.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (it.nextIndex() == index) {
                it.next();
                it.add(value);
                break;
            }
            it.next();
        }
    }

    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.remove();
            }
        }
        return list;
    }

    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (filter.test(it.next())) {
                it.set(value);
            }
        }
        return list;
    }

    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        ListIterator<T> it = list.listIterator();
        while (it.hasNext()) {
            if (elements.contains(it.next())) {
                it.remove();
            }
        }
        return list;
    }
}
