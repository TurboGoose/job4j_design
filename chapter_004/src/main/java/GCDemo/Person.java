package GCDemo;

public class Person {
    private int id;
    private String name;
    public static int personsAlive = 0;

    public Person(int id, String name) {
        personsAlive++;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed Person {id=%d, name=%s}%n", id, name);
        personsAlive--;
    }
}
