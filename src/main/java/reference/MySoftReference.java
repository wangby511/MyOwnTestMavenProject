package reference;

import java.io.IOException;
import java.lang.ref.SoftReference;

public class MySoftReference {
    public static void main(String[] args) throws IOException {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);

        // m -> SoftReference ... byte[]
        System.out.println(m.get());
        System.gc();

        byte[] b = new byte[1024 * 1024 * 15];

        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        // If -Xmx20M in Settings
        System.out.println(m.get());

        // 软引用 主要用于缓存
    }
}
