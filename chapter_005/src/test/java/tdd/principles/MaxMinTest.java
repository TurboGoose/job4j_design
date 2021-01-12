package tdd.principles;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MaxMinTest {
    public MaxMin maxMin = new MaxMin();

    @Test
    public void whenEmptyThenReturnNull() {
        assertThat(maxMin.<Integer>max(Collections.emptyList(), Comparator.naturalOrder()), is(nullValue()));
        assertThat(maxMin.<Integer>min(Collections.emptyList(), Comparator.naturalOrder()), is(nullValue()));
    }

    @Test
    public void whenGettingMaxFromIntegerListThenReturnCorrectNumber() {
        List<Integer> testList = List.of(2, 5, 3, 9);
        assertThat(maxMin.<Integer>max(testList, Comparator.naturalOrder()), is(9));
    }

    @Test
    public void whenGettingMinFromIntegerListThenReturnCorrectNumber() {
        List<Integer> testList = List.of(2, 5, 3, 9);
        assertThat(maxMin.<Integer>min(testList, Comparator.naturalOrder()), is(2));
    }
}
