package solid.isp;

public class Menu {
    private final Tree<MenuItem> tree;

    public Menu(Tree<MenuItem> tree) {
        this.tree = tree;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Node<MenuItem> child : tree.getRootNode().getChildren()) {
            new Tree<>(child).traverseDepth(
                    node -> result.append(levelSeparator(node.getTreeLevel() - 1))
                            .append(node.getData().getTitle()).append(System.lineSeparator()));
        }
        return result.toString();
    }

    private String levelSeparator(int level) {
        return level <= 0 ? "" : ("----".repeat(level) + " ");
    }
}
