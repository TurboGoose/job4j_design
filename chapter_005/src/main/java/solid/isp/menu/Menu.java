package solid.isp.menu;

import java.util.Objects;

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

    public Action getAction(String itemName) {
        Action result = null;
        for (Node<MenuItem> child : tree.getRootNode().getChildren()) {
            MenuItem menuItem = new Tree<>(child).search(item -> Objects.equals(itemName, item.getTitle()));
            if (menuItem != null) {
                result = menuItem.getAction();
                break;
            }
        }
        return result;
    }
}
