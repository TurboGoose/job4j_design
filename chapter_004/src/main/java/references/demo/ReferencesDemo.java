package references.demo;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferencesDemo {
    public static void main(String[] args) {
        testSoftRef();
        testWeakRef();
    }

    static void testSoftRef() {
        System.out.println("\n=== Soft Reference behaviour ===");
        TestObj obj = new TestObj();
        SoftReference<TestObj> softObj = new SoftReference<>(obj);
        System.out.println(">> Before nulling");
        System.out.println("Object : " + obj);
        System.out.println("Ref    : " + softObj.get());

        System.out.println(">> After nulling");
        obj = null;
        System.out.println("Object : " + obj);
        System.out.println("Ref    : " + softObj.get());

        System.out.println(">> GC has occurred");
        System.gc();
        System.out.println("Object : " + obj);
        System.out.println("Ref    : " + softObj.get());
    }

    static void testWeakRef() {
        System.out.println("\n=== Weak Reference behaviour ===");
        TestObj obj = new TestObj();
        WeakReference<TestObj> weakObj = new WeakReference<>(obj);
        System.out.println(">> Before nulling");
        System.out.println("Object : " + obj);
        System.out.println("Ref    : " + weakObj.get());

        System.out.println(">> After nulling");
        obj = null;
        System.out.println("Object : " + obj);
        System.out.println("Ref    : " + weakObj.get());

        System.out.println(">> GC has occurred");
        System.gc();
        System.out.println("Object : " + obj);
        System.out.println("Ref    : " + weakObj.get());
    }
}

class TestObj {
    private static int objCount = 0;
    private final int objNum;

    public TestObj() {
        objNum = ++objCount;
        System.out.println("Created: " + this);
    }

    @Override
    public String toString() {
        return "TestObj {" + objNum + '}';
    }

    @Override
    protected void finalize() throws Throwable {
        objCount--;
        System.out.println("Finalized: " + this);
    }
}
