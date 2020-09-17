package set;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SimpleSetTest {
    SimpleSet<Integer> set = new SimpleSet<>();

    @Nested
    class AddTests {
        @Test
        public void testAdd() {
            set.add(1);
            set.add(2);
            set.add(3);
            List<Integer> list = setAsSortedList();
            assertThat(list, is(Arrays.asList(1, 2, 3)));
        }

        @Test
        public void testAddDuplicates() {
            set.add(1);
            set.add(1);
            set.add(2);
            List<Integer> list = setAsSortedList();
            assertThat(list, is(Arrays.asList(1, 2)));
        }
    }

    List<Integer> setAsSortedList() {
        List<Integer> list = new ArrayList<>();
        for (Integer i : set) {
            list.add(i);
        }
        list.sort(Comparator.naturalOrder());
        return list;
    }
}
