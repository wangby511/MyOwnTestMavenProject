package reference;

public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("已被回收.");
    }
}
