package list;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ListUtilsTest {

    @Nested
    class AddBeforeTests {

        @Test
        public void whenAddBefore() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
            ListUtils.addBefore(input, 0, 0);
            ListUtils.addBefore(input, 2, 2);
            assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
        }

        @Test
        public void whenAddBeforeWithInvalidIndex() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
            assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addBefore(input, -1, 2));
            assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addBefore(input, 3, 2));
        }
    }

    @Nested
    class addAfterTests {

        @Test
        public void whenAddAfter() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
            ListUtils.addAfter(input, 0, 2);
            ListUtils.addAfter(input, 2, 4);
            assertThat(input, is(Arrays.asList(1, 2, 3, 4)));
        }

        @Test
        public void whenAddAfterOutOfRange() {
            List<Integer> input = new ArrayList<>();
            assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addAfter(input, -1, -1));
            assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addAfter(input, 0, 0));
            assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addAfter(input, 1, 1));
        }
    }

    @Nested
    class RemoveIfTests {

        @Test
        public void removeOddNumbers() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
            assertThat(ListUtils.removeIf(input, el -> el % 2 == 0), is(Arrays.asList(1, 3, 5)));
        }

        @Test
        public void whenNoElementsWereRemoved() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
            assertThat(ListUtils.removeIf(input, el -> el > 10), is(input));
        }

        @Test
        public void whenAllElementsWereRemoved() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
            assertThat(ListUtils.removeIf(input, el -> el < 10), is(Collections.emptyList()));
        }
    }

    @Nested
    class ReplaceIfTests {

        @Test
        public void replaceAllOddElementsToZeros() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
            assertThat(ListUtils.replaceIf(input, el -> el % 2 != 0, 0), is(Arrays.asList(0, 2, 0, 4, 0, 6)));
        }

        @Test
        public void whenNoElementsWereReplaced() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
            assertThat(ListUtils.replaceIf(input, el -> el > 10, 0), is(input));
        }

        @Test
        public void whenAllElementsWereReplacedToZeros() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
            assertThat(ListUtils.replaceIf(input, el -> el < 10, 0), is(Arrays.asList(0, 0)));
        }
    }

    @Nested
    class RemoveAllTests {

        @Test
        public void removeAllElementsFrom2To4() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
            assertThat(ListUtils.removeAll(input, Arrays.asList(2, 3, 4)), is(Arrays.asList(1, 5, 6)));
        }

        @Test
        public void removeDuplicatedElements() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 3));
            assertThat(ListUtils.removeAll(input, Collections.singletonList(2)), is(Arrays.asList(1, 3)));
        }

        @Test
        public void whenNoElementWereRemoved() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
            assertThat(ListUtils.removeAll(input, Arrays.asList(4, 5, 6)), is(input));
        }

        @Test
        public void whenAllElementWereRemoved() {
            List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
            assertThat(ListUtils.removeAll(input, Arrays.asList(1, 2, 3, 4)), is(Collections.emptyList()));
        }
    }
}

