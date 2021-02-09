package solid.isp.menu;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tree<T> {
    private final Node<T> tree;

    public Tree(Node<T> tree) {
        this.tree = tree;
    }

    Node<T> getRootNode() {
        return tree;
    }

    public void traverseDepth(Consumer<Node<T>> action) {
        Deque<Node<T>> stack = new LinkedList<>();
        stack.push(tree);
        while (!stack.isEmpty()) {
            Node<T> element = stack.pop();
            action.accept(element);
            List<Node<T>> children = element.getChildren();
            ListIterator<Node<T>> iter = children.listIterator(children.size());
            while (iter.hasPrevious()) {
                stack.push(iter.previous());
            }
        }
    }

    public T search(Predicate<T> predicate) {
        T result = null;
        Deque<Node<T>> stack = new LinkedList<>();
        stack.push(tree);
        while (!stack.isEmpty()) {
            Node<T> element = stack.pop();
            T data = element.getData();
            if (predicate.test(data)) {
                result = data;
                break;
            }
            List<Node<T>> children = element.getChildren();
            ListIterator<Node<T>> iter = children.listIterator(children.size());
            while (iter.hasPrevious()) {
                stack.push(iter.previous());
            }
        }
        return result;
    }
}
