package thread.Question2;

import java.util.concurrent.CountDownLatch;

public class Solution_03_WaitNotifyWIthLatchCountDown {

    private static CountDownLatch latch = new CountDownLatch(1); // 门闩 0表示打开 保证t1比t2先执行打印

    public static void main(String[] args) {

        final Object lock = new Object();

        Thread t1 = new Thread(() -> {

            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print((char) (64 + i));
                    latch.countDown();
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });

        Thread t2 = new Thread(() -> {

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });

        t2.start();
        t1.start();
    }
}