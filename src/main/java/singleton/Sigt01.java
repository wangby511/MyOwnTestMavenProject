package singleton;

public class Sigt01 {
    private static final Sigt01 INSTANCE = new Sigt01();

    private Sigt01() {};

    public static Sigt01 getInstance() {return INSTANCE; }

    public static void main(String[] args) {
        Sigt01 m1 = Sigt01.getInstance();
        Sigt01 m2 = Sigt01.getInstance();

        System.out.println(m1 == m2);
    }
}
