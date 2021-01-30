import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class FooTest {

    @Test
    @Order(1)
    public void checkoutOrder() {
        System.out.println("checkoutOrder");
    }

    @Test
    @Order(2)
    public void addItemsInBasket() {
        System.out.println("addItemsInBasket");
    }

    @Test
    @Order(3)
    public void createUserAndLogin() {
        System.out.println("createUserAndLogin");
    }
}