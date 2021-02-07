package solid.isp.menu;

public class MenuItem {
    private String title;
    private Action action;

    public MenuItem(String title, Action action) {
        this.title = title;
        this.action = action;
    }

    public MenuItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
