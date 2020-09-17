package list;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleArrayTest {
    SimpleArray<String> array = new SimpleArray<>();

    @Test
    public void whenAddThenGet() {
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenGetEmpty() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
    }

    @Test
    public void whenGetOutBound() {
        array.add("first");
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
    }

    @Test
    public void whenGetEmptyFromIt() {
        Iterator<String> it = array.iterator();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    public void whenCorruptedIt() {
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        assertThrows(ConcurrentModificationException.class ,it::next);
    }

    @Test
    public void whenContainsElement() {
        array.add("first");
        assertThat(array.contains("first"), is(true));
    }

    @Test
    public void whenNotContainsElement() {
        array.add("first");
        assertThat(array.contains("element that not contains in array"), is(false));
    }

    @Test
    public void whenContainsEmpty() {
        assertThat(array.contains("element that not contains in array"), is(false));
    }
}