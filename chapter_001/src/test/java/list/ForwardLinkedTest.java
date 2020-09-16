package list;

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
}
