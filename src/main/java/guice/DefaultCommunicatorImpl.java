package guice;

public class DefaultCommunicatorImpl implements Communicator {
    public boolean sendMessage(String message) {
        System.out.println("Sending Message + " + message);
        return true;
    }
}