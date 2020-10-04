package guice;

public class AnotherCommunicatorImpl implements Communicator {
    public boolean sendMessage(String message) {
        System.out.println("Another Sending Message + " + message);
        return true;
    }
}

