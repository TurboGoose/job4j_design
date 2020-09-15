package list;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class ForwardLinkedTest {

    @Test
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        assertThrows(NoSuchElementException.class, () -> linked.iterator().next());
    }

    @Test
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThrows(NoSuchElementException.class, linked::deleteFirst);
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }
}
