package thread.Question03;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author : mashibing
 *
 * ReentrantLock的condition精确指定哪些线程被唤醒 基于不同的等待队列
 * 可以让线程进入指定的等待队列，也可以指定唤醒某个等待队列里面的线程。
 * https://github.com/luvnpce/luvnpce/tree/7c418679d4914dd81fab1de8956651e0f1ad13fa/juc/src/main/java/Question_03
 */
public class MyContainer2<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while(lists.size() == MAX) {
                producer.await();
            }

            lists.add(t);
            ++count;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while(lists.size() == 0) {
                consumer.await();
                System.out.println("--");
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();

        for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                for(int j = 0; j < 5; j++) System.out.println(c.get());
            }, "consumer" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 2; i++) {
            new Thread(() -> {
                for(int j = 0; j < 25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "producer" + i).start();
        }
    }
}
