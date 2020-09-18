package set;
import list.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private final SimpleArray<T> array = new SimpleArray<>();

    public void add(T value) {
        if (!contains(value)) {
            array.add(value);
        }
    }

    private boolean contains(T value) {
        for (T element : array) {
            if (Objects.equals(element, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
