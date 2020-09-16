package list;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class ForwardLinkedTest {
    ForwardLinked<Integer> linked = new ForwardLinked<>();

    @Test
    public void whenDeleteFirst() {
        linked.addLast(1);
        linked.deleteFirst();
        assertThrows(NoSuchElementException.class, () -> linked.iterator().next());
    }

    @Test
    public void whenDeleteFirstEmptyLinked() {
        assertThrows(NoSuchElementException.class, linked::deleteFirst);
    }

    @Test
    public void whenDeleteLastEmptyLinked() {
        assertThrows(NoSuchElementException.class, linked::deleteLast);
    }

    @Test
    public void whenMultiDeleteFirst() {
        linked.addLast(1);
        linked.addLast(2);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddFirstThenAddLastThenDeleteFirstTwice() {
        linked.addFirst(1);
        linked.addLast(2);
        assertThat(linked.deleteFirst(), is(1));
        assertThat(linked.deleteFirst(), is(2));
    }

    @Test
    public void whenAddThenIterator() {
        linked.addLast(1);
        linked.addLast(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Nested
    class Revert {
        @Test
        public void whenRevertEmpty() {
            linked.revert();
            Iterator<Integer> it = linked.iterator();
            assertThat(it.hasNext(), is(false));
        }

        @Test
        public void whenAddOneElementThenRevert() {
            linked.addLast(1);
            linked.revert();
            Iterator<Integer> it = linked.iterator();
            assertThat(it.next(), is(1));
        }

        @Test
        public void whenAddTwoElementsThenRevert() {
            linked.addLast(1);
            linked.addLast(2);
            linked.revert();
            Iterator<Integer> it = linked.iterator();
            assertThat(it.next(), is(2));
            assertThat(it.next(), is(1));
        }

        @Test
        public void whenAddThreeElementsThenRevert() {
            linked.addLast(1);
            linked.addLast(2);
            linked.addLast(3);
            linked.revert();
            Iterator<Integer> it = linked.iterator();
            assertThat(it.next(), is(3));
            assertThat(it.next(), is(2));
            assertThat(it.next(), is(1));
        }

        @Test
        public void whenAddFourElementsThenRevert() {
            linked.addLast(1);
            linked.addLast(2);
            linked.addLast(3);
            linked.addLast(4);
            linked.revert();
            Iterator<Integer> it = linked.iterator();
            assertThat(it.next(), is(4));
            assertThat(it.next(), is(3));
            assertThat(it.next(), is(2));
            assertThat(it.next(), is(1));
        }
    }
}
