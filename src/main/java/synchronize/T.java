package synchronize;

public class T {
    private int count = 10;

    /**
     * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
     * t.o = new Object();
     * 但是如果o变成另外一个对象，则锁定的对象发生改变
     * 应该避免将锁定对象的引用变成另外的对象
     */
    private final Object o = new Object();

    private static int staticCount = 10;

    public void m() {
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count = " + count);
        }
    }

    public synchronized void m2() {
        count--;
        System.out.println(Thread.currentThread().getName() + "count = " + count);
    }

    public synchronized static void m3() { // synchronized(T.class)
        staticCount--;
        System.out.println(Thread.currentThread().getName() + "staticCount = " + staticCount);
    }
}
