package tree;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean success = false;
        Optional<Node<E>> parentNode = findBy(parent);
        if (parentNode.isPresent() && findBy(child).isEmpty()) {
            parentNode.get().children.add(new Node<>(child));
            success = true;
        }
        return success;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                result = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return result;
    }

    public boolean isBinary() {
        boolean isBinary = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> currentNode = data.poll();
            if (currentNode.children.size() > 2) {
                isBinary = false;
                break;
            }
            data.addAll(currentNode.children);
        }
        return isBinary;
    }
}
