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
        if (!checkIndexInBounds(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    public void set(T value, int index) {
        if (!checkIndexInBounds(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        data[index] = value;
    }

    public void remove(int index) {
        if (!checkIndexInBounds(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = index + 1; i < data.length; i++) {
            data[i - 1] = data[i];
        }
        currentIndex--;
    }

    private boolean checkIndexInBounds(int index) {
        try{
            return Objects.checkIndex(index, currentIndex) == index;
        } catch (IndexOutOfBoundsException ignored) {}
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                return checkIndexInBounds(point);
            }

            @Override
            public T next() {
                try {
                    return get(point++);
                } catch (IndexOutOfBoundsException exc) {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
