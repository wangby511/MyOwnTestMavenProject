package future;

import java.time.Instant;
import java.util.concurrent.*;

/**
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306581155184674
 * Runnable接口有个问题，它的方法没有返回值。如果任务需要一个返回结果，那么只能保存到变量，还要提供额外的方法读取，非常不便。
 * 所以，Java标准库还提供了一个Callable接口，和Runnable接口比，它多了一个返回值
 */

/**
 * 一个Future<V>接口表示一个未来可能会返回的结果，它定义的方法有
    get()：获取结果（可能会等待）
    get(long timeout, TimeUnit unit)：获取结果，但只等待指定的时间；
    cancel(boolean mayInterruptIfRunning)：取消当前任务；
    isDone()：判断任务是否已完成。
 */

class Task implements Callable<String> {
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        return longTimeCalculation();
    }

    private String longTimeCalculation() {
        return Instant.now().toString();
    }

    public static void main(String []args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 定义任务:
        Callable<String> task = new Task();

        // 提交任务并获得Future:
        Future<String> future = executor.submit(task);

        try {
            // 从Future获取异步执行返回的结果:
            String result = future.get(5, TimeUnit.SECONDS); // 可能阻塞

            // String result = future.get();

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}