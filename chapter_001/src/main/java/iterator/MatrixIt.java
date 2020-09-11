package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt  implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return !(row == data.length - 1 && column == Math.max(data[row].length - 1, 0));
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[row].length) {
            nextRow();
            passEmptyRows();
        }
        return data[row][column++];
    }

    private void nextRow() {
        row++;
        column = 0;
    }

    private void passEmptyRows() {
        while (data[row].length == 0) {
            nextRow();
        }
    }
}
