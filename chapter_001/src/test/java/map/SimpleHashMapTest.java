package map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleHashMapTest {
    SimpleHashMap<Integer, String> map = new SimpleHashMap<>();

    @Nested
    class InsertAndGetTests {
        @Test
        public void insertEntriesThenGet() {
            assertThat(map.insert(0, "zero"), is(true));
            assertThat(map.insert(1, "one"), is(true));
            assertThat(map.insert(2, "two"), is(true));
            assertThat(map.get(0), is("zero"));
            assertThat(map.get(1), is("one"));
            assertThat(map.get(2), is("two"));
        }

        @Test
        public void insertEntryWithNullKeyThenGet() {
            assertThat(map.insert(null, "null"), is(true));
            assertThat(map.get(null), is("null"));
        }

        @Test
        public void insertEntryWithNullValueThenGet() {
            assertThat(map.insert(1, null), is(true));
            assertThat(map.get(1), equalTo(null));
        }

        @Test
        public void insertEntryWithNullKeyAndValueThenGet() {
            assertThat(map.insert(null, null), is(true));
            assertThat(map.get(null), equalTo(null));
        }

        @Test
        public void getValueByDefunctKey() {
            assertThat(map.get(0), equalTo(null));
        }
    }

    @Nested
    class TestCollisions {
        private class TestClass {
            int n1, n2;

            public TestClass(int n1, int n2) {
                this.n1 = n1;
                this.n2 = n2;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                TestClass that = (TestClass) o;
                return n1 == that.n1 &&
                        n2 == that.n2;
            }

            @Override
            public int hashCode() {
                return n1 + n2;
            }
        }

        @Test
        public void insertDifferentEntriesWithOneHashCodeThenGetAndDelete() {
            SimpleHashMap<TestClass, String> map = new SimpleHashMap<>();
            TestClass obj1 = new TestClass(128, 128);
            TestClass obj2 = new TestClass(127, 129);
            assertThat(map.insert(obj1, "128 + 128"), is(true));
            assertThat(map.insert(obj2, "127 + 129"), is(false));
            assertThat(map.get(obj1), is("128 + 128"));
            assertThat(map.get(obj2), equalTo(null));
            assertThat(map.delete(obj1), is(true));
            assertThat(map.delete(obj2), is(false));
        }

        @Test
        public void insertEntryWithExistingKey() {
            assertThat(map.insert(0, "zero"), is(true));
            assertThat(map.get(0), is("zero"));
            assertThat(map.insert(0, "ZERO"), is(true));
            assertThat(map.get(0), is("ZERO"));
        }
    }

    @Nested
    class ResizeTests {
        @Test
        public void resizeDuringInsertion() {
            int lastIndex = 0;
            for (int i = 0; i < 16; i++) {
                map.insert(i, "value" + i);
                if (map.getSize() == 32) {
                    lastIndex = i;
                    break;
                }
            }
            assertThat(map.getSize(), is(32));
            for (int i = 0; i < lastIndex; i++) {
                assertThat(map.get(i), is("value" + i));
            }
        }
    }

    @Nested
    class DeleteTests {
        @Test
        public void insertEntriesThenDelete() {
            assertThat(map.insert(0, "zero"), is(true));
            assertThat(map.delete(0), is(true));
            assertThat(map.get(0), equalTo(null));
        }

        @Test
        public void deleteDefunctKey() {
            assertThat(map.delete(0), is(false));
        }
    }

    @Nested
    class IteratorTests {
        @Test
        public void iteratorInLoop() {
            assertThat(map.insert(0, "zero"), is(true));
            assertThat(map.insert(1, "one"), is(true));
            assertThat(map.insert(2, "two"), is(true));

            List<String> elementList = Arrays.asList("zero", "one", "two");
            for (Entry<Integer, String> entry : map) {
                assertThat(elementList.contains(entry.value), is(true));
            }
        }

        @Test
        public void hasNextTwiceThenNextThenHasNextTwice() {
            assertThat(map.insert(0, "zero"), is(true));
            Iterator<Entry<Integer, String>> it = map.iterator();
            assertThat(it.hasNext(), is(true));
            assertThat(it.hasNext(), is(true));
            assertThat(it.next().value, is("zero"));
            assertThat(it.hasNext(), is(false));
            assertThat(it.hasNext(), is(false));
        }

        @Test
        public void nextEmptyIterator() {
            Iterator<Entry<Integer, String>> it = map.iterator();
            assertThat(it.hasNext(), is(false));
            assertThrows(NoSuchElementException.class, it::next);
        }
    }
}