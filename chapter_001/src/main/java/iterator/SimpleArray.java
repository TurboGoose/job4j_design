package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] data;
    private int currentIndex = 0;

    public SimpleArray(int size) {
        data = new Object[size];
    }

    public void add(T value) {
        data[currentIndex++] = value;
    }

    public T get(int index) {
        Objects.checkIndex(index, currentIndex);
        return (T) data[index];
    }

    public void set(T value, int index) {
        Objects.checkIndex(index, currentIndex);
        data[index] = value;
    }

    public void remove(int index) {
        Objects.checkIndex(index, currentIndex);
        System.arraycopy(data, index + 1, data, index, currentIndex - index - 1);
        currentIndex--;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                try {
                    return Objects.checkIndex(point, currentIndex) == point;
                } catch (IndexOutOfBoundsException ignore) {}
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
    }
}
