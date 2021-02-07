package solid.isp;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private final List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private T data;
    private int treeLevel = 0;

    public Node(T data) {
        this.data = data;
    }

    public void addChild(Node<T> node) {
        children.add(node);
    }

    public void addChildren(List<Node<T>> nodes) {
        children.addAll(nodes);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setParent(Node<T> node) {
        node.addChild(this);
        treeLevel = node.getTreeLevel() + 1;
    }

    public int getTreeLevel() {
        return treeLevel;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public void removeParent() {
        parent = null;
    }
}
