package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<T> implements Iterable<T> {
    private class Node {
        public Node prev;
        public T data;
        public Node next;

        public Node(Node prev, T data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;
    private int modCount = 0;

    public void add(T value) {
        Node previousTail = tail;
        Node newTail = new Node(previousTail, value, null);
        tail = newTail;
        if (previousTail == null) {
            head = newTail;
        } else {
            previousTail.next = newTail;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node node;
        if (startIteratingFromTheHead(index)) {
            node = getNodeStartingFromTheHead(index);
        }
        else {
            node = getNodeStartingFromTheTail(index);
        }
        return node.data;
    }

    private boolean startIteratingFromTheHead(int index) {
        return size - index > index;
    }

    private Node getNodeStartingFromTheHead(int index) {
        Node nextNode = head;
        for (int i = 0; i < index; i++) {
            nextNode = nextNode.next;
        }
        return nextNode;
    }

    private Node getNodeStartingFromTheTail(int index) {
        Node prevNode = tail;
        for (int i = 0; i < size - index; i++) {
            prevNode = prevNode.prev;
        }
        return prevNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentNodeNumber = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNodeNumber < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(currentNodeNumber++);
            }
        };
    }
}
