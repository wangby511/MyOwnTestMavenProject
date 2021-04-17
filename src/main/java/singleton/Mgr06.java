package singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时，不会加载内部类，这样可以实现懒加载
 * https://github.com/beyondfly2005/study-design-patterns/blob/0c7c9a14a46ec11b02db04c8497d4e5601ad2b25/Singleton/src/main/java/com/beyondsoft/singleton/Mgr06.java
 */

public class Mgr06 {
    public static volatile Mgr06 INSTANCE; // JIT volatile: 保证初始化完成之后才赋值给变量

    private Mgr06() {}


    public static Mgr06 getInstance() {

        if (INSTANCE == null) { // Double Check Lock (DCL) 双重检查
            synchronized (Mgr06.class) {

                if (INSTANCE == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }

        return INSTANCE;
    }

    public static void m (){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr06.getInstance());
            }).start();
        }
    }
}
