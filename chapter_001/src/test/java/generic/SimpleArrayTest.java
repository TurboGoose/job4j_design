package generic;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleArrayTest {
    @Test
    public void normalCase() {
        SimpleArray<String> arr = new SimpleArray<>(3);
        arr.add("0");
        arr.add("1");
        arr.add("2");
        assertThat(arr.get(0), is("0"));
        assertThat(arr.get(1), is("1"));
        assertThat(arr.get(2), is("2"));
        arr.remove(0);
        assertThat(arr.get(0), is("1"));
        arr.remove(0);
        assertThat(arr.get(0), is("2"));
        arr.remove(0);
    }

    @Test
    public void zeroSize() {
        SimpleArray<String> arr = new SimpleArray<>(0);
        assertThrows(IndexOutOfBoundsException.class, () -> arr.add("0"));
    }

    @Test
    public void negativeSize() {
        assertThrows(NegativeArraySizeException.class, () -> new SimpleArray<>(-1));
    }

    @Test
    public void arrayOverflow() {
        SimpleArray<String> arr = new SimpleArray<>(1);
        arr.add("0");
        assertThrows(IndexOutOfBoundsException.class, () -> arr.add("1"));
    }

    @Test
    public void removeMoreElementsThanSize() {
        SimpleArray<String> arr = new SimpleArray<>(1);
        arr.add("1");
        arr.remove(0);
        assertThrows(IndexOutOfBoundsException.class, () -> arr.remove(0));
    }

    @Test
    public void removeElementsOutOfBounds() {
        SimpleArray<String> arr = new SimpleArray<>(2);
        assertThrows(IndexOutOfBoundsException.class, () -> arr.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> arr.remove(0));
        arr.add("1");
        assertThrows(IndexOutOfBoundsException.class, () -> arr.remove(1));
        arr.remove(0);
    }

    @Test
    public void setElementOutOfBounds() {
        SimpleArray<String> arr = new SimpleArray<>(2);
        assertThrows(IndexOutOfBoundsException.class, () -> arr.set("1", 1));
        assertThrows(IndexOutOfBoundsException.class, () -> arr.set("0", 0));
        arr.add("0");
        arr.set("00", 0);
        assertThrows(IndexOutOfBoundsException.class, () -> arr.set("11", 1));
    }

    @Test
    public void getElementOutOfBounds() {
        SimpleArray<String> arr = new SimpleArray<>(2);
        assertThrows(IndexOutOfBoundsException.class, () -> arr.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> arr.get(0));
        arr.add("0");
        assertThrows(IndexOutOfBoundsException.class, () -> arr.get(1));
        assertThat(arr.get(0), is("0"));
    }

    @Test
    public void iteratorNormalCase() {
        SimpleArray<String> arr = new SimpleArray<>(3);
        arr.add("0");
        arr.add("1");
        arr.add("2");
        Iterator<String> it = arr.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("0"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("2"));
    }

    @Test
    public void iteratorOfEmptyArrayHasNext() {
        SimpleArray<String> arr = new SimpleArray<>(0);
        Iterator<String> it = arr.iterator();
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    public void iteratorNextAfterPassAllElements() {
        SimpleArray<String> arr = new SimpleArray<>(1);
        arr.add("0");
        Iterator<String> it = arr.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("0"));
        assertThat(it.hasNext(), is(false));
        assertThrows(NoSuchElementException.class, it::next);
    }
}