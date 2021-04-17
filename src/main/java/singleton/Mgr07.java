package singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时，不会加载内部类，这样可以实现懒加载
 * https://github.com/beyondfly2005/study-design-patterns/blob/0c7c9a14a46ec11b02db04c8497d4e5601ad2b25/Singleton/src/main/java/com/beyondsoft/singleton/Mgr07.java
 */

public class Mgr07 {
    private Mgr07() {}

    private static class Mgr07Holder {
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance() {
        return Mgr07Holder.INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr07.getInstance().hashCode());
            }).start();
        }
    }
}