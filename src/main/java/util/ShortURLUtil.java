package util;

import redis.clients.jedis.Jedis;

/**
 * Created by pfliu on 2019/06/23.
 */

public class ShortURLUtil {
    private static final String SHORT_URL_KEY = "SHORT_URL_KEY";
    private static final String LOCALHOST = "http://localhost:8080/";
    private static final String SHORT_LONG_PREFIX = "short_long_prefix_";
    private static final String CACHE_KEY_PREFIX = "cache_key_prefix_";
    private static final int CACHE_SECONDS = 1 * 60 * 60;
    private static final int BASE = 62;

    private final String redisConfig;
    private final Jedis jedis;

    public ShortURLUtil(String redisConfig) {
        this.redisConfig = redisConfig;
        this.jedis = new Jedis(this.redisConfig);
    }

    public String getShortURL(String longURL, int base) {
        // 查询缓存
        String cache = jedis.get(CACHE_KEY_PREFIX + longURL);
        if (cache != null) {
            return LOCALHOST + toOtherBaseString(Long.valueOf(cache), base);
        }

        // 自增
        long num = jedis.incr(SHORT_URL_KEY);
        // 在数据库中保存短-长URL的映射关系,可以保存在MySQL中
        jedis.set(SHORT_LONG_PREFIX + num, longURL);
        // 写入缓存
        jedis.setex(CACHE_KEY_PREFIX + longURL, CACHE_SECONDS, String.valueOf(num));
        return LOCALHOST + toOtherBaseString(num, base);
    }

    public String getLongURL(String url) {
        String[] split = url.split("/");
        String ID = split[split.length - 1];
        long num = 0;
        for (char x : ID.toCharArray()) {
            if (x >= '0' && x <= '9') {
                num = num * BASE + x - '0';
            } else if (x >= 'A' && x <= 'Z') {
                num = num * BASE + x - 'A' + 10;
            } else {
                num = num * BASE + x - 'a' + 10 + 26;
            }
        }
        return jedis.get(SHORT_LONG_PREFIX + num);
    }

    public String toOtherBaseString(long n, int base) {
        long num = 0;
        if (n < 0) {
            num = ((long) 2 * 0x7fffffff) + 2 + n;
        } else {
            num = n;
        }
        char[] buf = new char[32];
        int charPos = 32;
        while ((num / base) > 0) {
            buf[--charPos] = digits[(int) (num % base)];
            num /= base;
        }
        buf[--charPos] = digits[(int) (num % base)];
        return new String(buf, charPos, (32 - charPos));
    }

    final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {

        ShortURLUtil shortURLUtil = new ShortURLUtil("localhost");

//        for (int i = 0; i < 100; i++) {
//            System.out.println(shortURLUtil.getShortURL("www.google.com/" + String.format(String.valueOf(i)), BASE));
//        }
        for(int i = 1;i < 300;i++) {
            String ID = shortURLUtil.toOtherBaseString(i, BASE);
            String longUrl = shortURLUtil.getLongURL("http://localhost:8080/" + ID);

            if(longUrl == null) break;
            System.out.println(longUrl);
        }
    }

}
