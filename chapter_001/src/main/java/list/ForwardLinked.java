package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void addLast(T value) {
        size++;
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = tail.next;
    }

    public void addFirst(T value) {
        size++;
        Node<T> newNode = new Node<>(value, head);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        head = newNode;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T resultValue = head.value;
        Node<T> newHead = head.next;
        head.next = null;
        head = newHead;
        size--;
        return resultValue;
    }

    public T deleteLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        T resultValue = tail.value;
        if (head != tail) {
            Node<T> beforeTail = head;
            while (beforeTail.next != tail) {
                beforeTail = beforeTail.next;
            }
            beforeTail.next = null;
            tail = beforeTail;
        }
        size--;
        return resultValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
