package guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class CommunicationTest {


    /**
     * 输出如下
     * Message logging enabled
     * Sending Message + hello world
     * https://blog.csdn.net/qq_21383435/article/details/105430867
     */
    @org.junit.Test
    public  void sendMessage() {
        Injector injector = Guice.createInjector(new BasicModule());

        Communication comms = injector.getInstance(Communication.class);

        comms.sendMessage("hello world");
    }
}
