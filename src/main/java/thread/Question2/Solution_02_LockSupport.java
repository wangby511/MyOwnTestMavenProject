package thread.Question2;

import java.util.concurrent.locks.LockSupport;

public class Solution_02_LockSupport {

    static Thread t1 = null,t2 = null;
    public static void main(String[] args) {


         t1 = new Thread(() -> {

            for (int i = 1; i <= 26; i++) {
                System.out.print((char) (64 + i));
                LockSupport.unpark(t2);
                LockSupport.park();
            }
             LockSupport.unpark(t2);
         },"t1");

        t2 = new Thread(() -> {

            for (int i = 1; i <= 26; i++) {
                LockSupport.park(); // t2挂起
                System.out.print(i);
                LockSupport.unpark(t1); // 叫醒t1
            }
            LockSupport.unpark(t1);
        },"t2");

        t1.start();
        t2.start();
//        LockSupportDemo();
    }

    public static void LockSupportDemo() {
        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
    }
}
