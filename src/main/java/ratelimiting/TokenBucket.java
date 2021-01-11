package ratelimiting;

import java.util.concurrent.TimeUnit;

public class TokenBucket {
    private int capacity;
    private int tokenPerSeconds;
    private int tokens = 0;
    private long timestamp = System.currentTimeMillis();

    public TokenBucket(int tokenPerUnit, TimeUnit unit) {
        capacity = tokenPerSeconds = (int)(tokenPerUnit / unit.toSeconds(1));
    }

    public boolean take() {
        long now = System.currentTimeMillis();
        tokens += (int)((now - timestamp) * tokenPerSeconds / 1000);
        tokens = Math.min(capacity, tokens);
        timestamp = now;
        if (tokens < 1) {
            return false;
        }
        tokens--;
        return true;
    }

    public static void main(String []args) throws InterruptedException {
        test2();
    }

    private static void test1() throws InterruptedException {
        TokenBucket bucket = new TokenBucket(7, TimeUnit.SECONDS);
        System.out.println("Testing TokenBucket...");

        Thread.sleep(1000L);
        for (int i = 0;i < 10;i++) {
            System.out.println(i + ":" + bucket.take());
        }

        Thread.sleep(2000L);
        for (int i = 0;i < 10;i++) {
            System.out.println(i + ":" + bucket.take());
        }
    }

    private static void test2() throws InterruptedException {
        TokenBucket bucket = new TokenBucket(4, TimeUnit.SECONDS);
        System.out.println("Testing TokenBucket...");

        Thread.sleep(1000L);
        for (int i = 0;i < 8;i++) {
            System.out.println(i + ":" + bucket.take());
        }

        Thread.sleep(1000L);
        for (int i = 0;i < 8;i++) {
            System.out.println(i + ":" + bucket.take());
        }
    }
}
