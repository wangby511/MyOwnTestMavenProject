import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://www.baeldung.com/junit-5-test-order

@TestMethodOrder(OrderAnnotation.class)
public class OrderAnnotationUnitTest {
    private static StringBuilder output = new StringBuilder("");

    @Test
    @Order(1)
    public void secondTest() {
        output.append("1");
        System.out.println(output.toString());
        assertEquals(output.toString(), "1");
    }

    @Test
    @Order(2)
    public void firstTest() {
        output.append("2");
        System.out.println(output.toString());
        assertEquals(output.toString(), "12");
    }


    @Test
    @Order(3)
    public void thirdTest() {
        output.append("3");
        System.out.println(output.toString());
        assertEquals(output.toString(), "123");
    }


    @AfterAll
    public static void assertOutput() {
        output.append("4");
        System.out.println(output.toString());
        assertEquals(output.toString(), "1234");
    }
}