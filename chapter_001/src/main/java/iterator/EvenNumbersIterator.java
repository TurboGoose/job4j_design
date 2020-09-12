package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        passOddNumbers();
    }

    private void passOddNumbers() {
        while (isPointInRange() && !isEven(data[point])) {
            point++;
        }
    }

    private boolean isPointInRange() {
        return point < data.length;
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int nextElement = data[point++];
        passOddNumbers();
        return nextElement;
    }

    @Override
    public boolean hasNext() {
        return isPointInRange() &&  isEven(data[point]);
    }
}
