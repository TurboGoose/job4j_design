package set;
import list.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private final SimpleArray<T> array = new SimpleArray<>();

    public void add(T value) {
        if (!array.contains(value)) {
            array.add(value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
