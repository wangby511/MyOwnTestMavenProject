package guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
//        表明：当需要 Communicator 这个变量时，我们注入 DefaultCommunicatorImpl 的实例作为依赖
//        bind(Communicator.class).to(DefaultCommunicatorImpl.class);

        bind(Communication.class).toInstance(new Communication(true));
    }

    @Provides
    @Singleton
    @Named("communicator")
    public Communicator getCommunicator() {
        return new DefaultCommunicatorImpl();
    }

    @Provides
    @Singleton
    @Named("anotherCommunicator")
    public Communicator getAnotherCommunicator() {
        return new AnotherCommunicatorImpl();
    }
}
