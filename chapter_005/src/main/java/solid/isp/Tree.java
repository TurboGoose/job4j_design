package solid.isp;

import java.util.*;
import java.util.function.Consumer;

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


}
