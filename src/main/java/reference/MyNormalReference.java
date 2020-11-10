package reference;

import java.io.IOException;

public class MyNormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;

        System.gc();
        System.in.read();
    }
}
