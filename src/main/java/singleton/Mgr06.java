package singleton;

import java.util.concurrent.ConcurrentHashMap;

public class Mgr06 {
    private static volatile Mgr06 INSTANCE; // JIT

    private Mgr06() {}

    ConcurrentHashMap chm = null;

    public static Mgr06 getInstance() {

        if (INSTANCE == null) { // Double Check Lock (DCL)
            synchronized (Mgr06.class) {

                if (INSTANCE == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                INSTANCE = new Mgr06();
            }
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr06.getInstance().hashCode());
            }).start();
        }
    }
}
