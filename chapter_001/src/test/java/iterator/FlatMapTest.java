package iterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class FlatMapTest {
    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next(), is(1));
        assertThat(flat.next(), is(2));
        assertThat(flat.next(), is(3));
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next(), is(1));
        assertThat(flat.next(), is(2));
        assertThat(flat.next(), is(3));
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.hasNext(), is(true));
        assertThat(flat.hasNext(), is(true));
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next(), is(1));
        assertThat(flat.hasNext(), is(false));
    }

    @Test
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(
                List.of().iterator()
        ).iterator();
        FlatMap<Object> flat = new FlatMap<>(data);
        assertThrows(NoSuchElementException.class, flat::next);
    }

    @Test
    public void whenEmptyIteratorInEnd() {
        List<Integer> empty = new ArrayList<>();
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                empty.iterator()
        ).iterator();

        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.hasNext(), is(true));
        assertThat(flat.next(), is(1));
        assertThat(flat.hasNext(), is(false));
        assertThrows(NoSuchElementException.class, flat::next);
    }
}