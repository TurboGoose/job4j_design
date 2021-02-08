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
            Collections.reverse(children);
            children.forEach(stack::push);
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
            Collections.reverse(children);
            children.forEach(stack::push);
        }
        return result;
    }
}
