package list;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleArrayTest {
    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>();
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
    }

    @Test
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
    }

    @Test
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>();
        Iterator<String> it = array.iterator();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        assertThrows(ConcurrentModificationException.class ,it::next);
    }
}