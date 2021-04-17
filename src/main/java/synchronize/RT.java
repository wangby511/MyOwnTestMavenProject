package synchronize;

public class RT implements Runnable{

    private int count = 100;

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String args[]) {
        RT t = new RT();
        for(int i = 0;i < 100;i++) {
            new Thread(t, "THREAD-" + i).start();
        }

    }
}
