package guice;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class Communication {
    @Inject
    @Named("communicator")
    private Communicator communicator;

    @Inject
    @Named("anotherCommunicator")
    private Communicator anotherCommunicator;


    public Communication(Boolean keepRecords) {
        if (keepRecords) {
            System.out.println("Message logging enabled");
        }
    }

    public boolean sendMessage(String message) {
        communicator.sendMessage(message);
        anotherCommunicator.sendMessage(message);
        return true;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BasicModule());

        Communication comms = injector.getInstance(Communication.class);

        comms.sendMessage("hello world");
    }
}
