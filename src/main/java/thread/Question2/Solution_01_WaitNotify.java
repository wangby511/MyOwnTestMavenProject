package thread.Question2;

public class Solution_01_WaitNotify {

    public static void main(String[] args) {

        final Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print((char) (64 + i));
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

        t1.start();
        t2.start();
    }
}
//    private char x1 = 'A';
//    private int count = 1;
//
//    final Object lock = new Object();
//
//
//    public void printChar() {
//        synchronized (lock) {
//            try {
//                System.out.print(x1++);
//                lock.notify();
//                lock.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            lock.notify();
//        }
//    }
//
//    public void printNumber() {
//        synchronized (lock) {
//            try {
//                System.out.print(count++);
//                lock.notify();
//                lock.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            lock.notify();
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution_01_WaitNotify c = new Solution_01_WaitNotify();
//        new Thread(() -> {
//            for (int j = 0; j < 26; j++) c.printChar();
//        }, "Print char thread").start();
//
//        new Thread(() -> {
//            for (int j = 0; j < 26; j++) c.printNumber();
//        }, "Print number thread").start();
//    }
//}
