package list;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleLinkedListTest {
    private SimpleLinkedList<String> list = new SimpleLinkedList<>();

    @Test
    public void whenEmpty() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    public void addElementsThenGetThemByIndices() {
        list.add("zero");
        list.add("one");
        list.add("two");
        list.add("four");
        assertThat(list.get(0), is("zero"));
        assertThat(list.get(1), is("one"));
        assertThat(list.get(2), is("two"));
        assertThat(list.get(3), is("four"));
    }

    @Test
    public void getByIndexOutOfBounds() {
        list.add("zero");
        assertThat(list.get(0), is("zero"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    public void getByNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }


    @Nested
    class IteratorTests {

        @Test
        public void iteratorMultipleHasNextThenNextThenHasNext() {
            list.add("zero");
            Iterator<String> it = list.iterator();
            assertThat(it.hasNext(), is(true));
            assertThat(it.hasNext(), is(true));
            assertThat(it.next(), is("zero"));
            assertThat(it.hasNext(), is(false));
            assertThat(it.hasNext(), is(false));
        }

        @Test
        public void iteratorCallNextWhenElementsAreOver() {
            list.add("zero");
            Iterator<String> it = list.iterator();
            assertThat(it.next(), is("zero"));
            assertThrows(NoSuchElementException.class, it::next);
        }

        @Test
        public void iteratorWhenEmpty() {
            Iterator<String> it = list.iterator();
            assertThat(it.hasNext(), is(false));
            assertThrows(NoSuchElementException.class, it::next);
        }

        @Test
        public void iteratorConcurrentModification() {
            list.add("zero");
            Iterator<String> it = list.iterator();
            assertThat(it.hasNext(), is(true));
            assertThat(it.next(), is("zero"));
            list.add("one");
            assertThrows(ConcurrentModificationException.class, it::hasNext);
            assertThrows(ConcurrentModificationException.class, it::next);
        }
    }
}
