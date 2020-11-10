package reference;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class MyWeakReference {
    public static void main(String[] args) throws IOException {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
        // 一次性

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
