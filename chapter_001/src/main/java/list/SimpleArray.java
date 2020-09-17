package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        container = new Object[10];
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (isFull()) {
            container = grow();
        }
        container[size++] = model;
        modCount++;
    }

    public boolean contains(T model) {
        for (int i = 0; i < size; i++) {
            if (container[i].equals(model)) {
                return true;
            }
        }
        return false;
    }

    private Object[] grow() {
        final double GROW_COEFFICIENT = 1.5;
        Object[] newContainer = new Object[(int)(container.length * GROW_COEFFICIENT)];
        System.arraycopy(container, 0, newContainer, 0, Math.min(container.length, newContainer.length));
        return newContainer;
    }

    private boolean isFull() {
        return size == container.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[point++];
            }
        };
    }
}