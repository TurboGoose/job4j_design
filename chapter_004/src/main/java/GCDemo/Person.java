package GCDemo;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

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

    public long size() {
        return sizeOf(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}' + "[size: " + size() + "]";
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Removed " + this);
        personsAlive--;
    }
}
