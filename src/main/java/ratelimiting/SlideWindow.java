package ratelimiting;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 滑动时间窗口限流工具
 * 本限流工具只适用于单机版，如果想要做全局限流，可以按本程序的思想，用redis的List结构去实现
 *
 * @author dijia478
 * @date 2020-10-13 10:53
 */

public class SlideWindow {
    private volatile static Map<String, List<Long>> MAP = new ConcurrentHashMap<>();

    private SlideWindow() {}

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0;i < 10;i++){
            // 任意1秒内，只允许1次通过
            System.out.println(LocalTime.now().toString() + " " + SlideWindow.isGo("ListId", 1, 1000L));

            // 睡眠0-10秒
//            Thread.sleep(1000 * new Random().nextInt(5));

            Thread.sleep((long)(1000 * 0.4));
        }
    }

    /**
     * 滑动时间窗口限流算法
     * 在指定时间窗口，指定限制次数内，是否允许通过
     *
     * @param listId     队列id
     * @param count      限制次数
     * @param timeWindow 时间窗口大小
     * @return 是否允许通过
     */
    public static synchronized boolean isGo(String listId, int count, long timeWindow) {
        // 获取当前时间
        long nowTime = System.currentTimeMillis();
        // 根据队列id，取出对应的限流队列，若没有则创建
        List<Long> list = MAP.computeIfAbsent(listId, k -> new LinkedList<>());
        // 如果队列还没满，则允许通过，并添加当前时间戳到队列开始位置
        if (list.size() < count) {
            list.add(0, nowTime);
            return true;
        }

        // 队列已满（达到限制次数），则获取队列中最早添加的时间戳
        Long farTime = list.get(count - 1);
        // 用当前时间戳 减去 最早添加的时间戳
        if (nowTime - farTime <= timeWindow) {
            // 若结果小于等于timeWindow，则说明在timeWindow内，通过的次数大于count
            // 不允许通过
            return false;
        }

        // 若结果大于timeWindow，则说明在timeWindow内，通过的次数小于等于count
        // 允许通过，并删除最早添加的时间戳，将当前时间添加到队列开始位置
        list.remove(count - 1);
        list.add(0, nowTime);
        return true;
    }
}
