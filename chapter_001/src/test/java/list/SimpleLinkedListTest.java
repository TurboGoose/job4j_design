package list;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleLinkedListTest {
    SimpleArray<String> array = new SimpleArray<>();

    @Test
    public void whenEmpty() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
    }

    @Test
    public void addElementsThenGetThemByIndices() {
        array.add("zero");
        array.add("one");
        array.add("two");
        assertThat(array.get(0), is("zero"));
        assertThat(array.get(1), is("one"));
        assertThat(array.get(2), is("two"));
    }

    @Test
    public void getByIndexOutOfBounds() {
        array.add("zero");
        assertThat(array.get(0), is("zero"));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
    }

    @Test
    public void getByNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
    }

    @Test
    public void iteratorMultipleHasNextThenNextThenHasNext() {
        array.add("zero");
        Iterator<String> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("zero"));
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void iteratorCallNextWhenElementsAreOver() {
        array.add("zero");
        Iterator<String> it = array.iterator();
        assertThat(it.next(), is("zero"));
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    public void iteratorWhenEmpty() {
        Iterator<String> it = array.iterator();
        assertThat(it.hasNext(), is(false));
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    public void iteratorConcurrentModification() {
        array.add("zero");
        Iterator<String> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("zero"));
        array.add("one");
        assertThrows(ConcurrentModificationException.class, it::hasNext);
        assertThrows(ConcurrentModificationException.class, it::next);
    }
}
