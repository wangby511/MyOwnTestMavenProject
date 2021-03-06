package atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T02_AtomicVsSyncVsLongAdder {
    static long count = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        testAtomic();
        testAdder();
    }

    public static void testAtomic() throws InterruptedException {

        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++) {
                    count1.incrementAndGet();
                }
            });
        }

        long start = System.currentTimeMillis();

        for (Thread t : threads) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        System.out.println("Atomic: " + count1.get() + " time " + (end - start));
    }

    public static void testAdder() throws InterruptedException {
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++) {
                    count3.increment();
                }
            });
        }

        long start = System.currentTimeMillis();

        for (Thread t : threads) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        System.out.println("LongAdder: " + count3.longValue() + " time " + (end - start));
    }
}
