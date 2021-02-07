package solid.isp.menu;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MenuTest {
    @Test
    public void whenRenderMenu() {
        Menu menu = new Menu(createTree());
        String expected = String.format(
                "node1%n" +
                "---- node2%n" +
                "-------- node3%n" +
                "-------- node4%n" +
                "---- node5%n" +
                "-------- node6%n" +
                "node7%n");
        String actual = menu.toString();
        assertThat(actual, is(expected));
    }

    private Tree<MenuItem> createTree() {
        Node<MenuItem> root = new Node<>(new MenuItem("root"));
        Node<MenuItem> node1 = new Node<>(new MenuItem("node1"));
        node1.setParent(root);
        Node<MenuItem> node2 = new Node<>(new MenuItem("node2"));
        node2.setParent(node1);
        Node<MenuItem> node3 = new Node<>(new MenuItem("node3"));
        node3.setParent(node2);
        Node<MenuItem> node4 = new Node<>(new MenuItem("node4"));
        node4.setParent(node2);
        Node<MenuItem> node5 = new Node<>(new MenuItem("node5"));
        node5.setParent(node1);
        Node<MenuItem> node6 = new Node<>(new MenuItem("node6"));
        node6.setParent(node5);
        Node<MenuItem> node7 = new Node<>(new MenuItem("node7"));
        node7.setParent(root);
        return new Tree<>(root);
    }
}
