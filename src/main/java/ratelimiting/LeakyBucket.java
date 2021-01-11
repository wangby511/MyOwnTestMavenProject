package ratelimiting;

import java.util.concurrent.TimeUnit;

public class LeakyBucket {
    private int capacity;
    private int dropPerSeconds;
    private int water = 0;
    private long timestamp = System.currentTimeMillis();

    public LeakyBucket(int tokenPerUnit, TimeUnit unit) {
        capacity = dropPerSeconds = (int)(tokenPerUnit / unit.toSeconds(1));
    }

    public boolean place() {
        long now = System.currentTimeMillis();
        water -= (int)((now - timestamp) * dropPerSeconds / 1000);
        timestamp = now;
        water = Math.max(0, water);
        if (water + 1 > capacity) {
            return false;
        }
        water++;
        return true;
    }

    public static void main(String []args) throws InterruptedException {
        LeakyBucket bucket = new LeakyBucket(7, TimeUnit.SECONDS);
        System.out.println("Testing LeakyBucket...");

        Thread.sleep(1000L);
        for (int i = 0;i < 10;i++) {
            System.out.println(i + ":" + bucket.place());
        }

        Thread.sleep(2000L);
        for (int i = 0;i < 10;i++) {
            System.out.println(i + ":" + bucket.place());
        }
    }
}
