package javaintegration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class OverloadsKtTest {

    @Test
    void checkOverloads() {
        assertAll("overloads called from Java",
            () -> System.out.println(OverloadsKt.addProduct("Name", 5.0, "Desc")),
            () -> System.out.println(OverloadsKt.addProduct("Name", 5.0)),
            () -> System.out.println(OverloadsKt.addProduct("Name"))
        );
    }

    @Test
    void supplyAllArguments() {
        System.out.println(OverloadsKt.addProduct("Name", 5.0, "Desc"));
    }

    @Test
    void checkOverloadedProductCtor() {
        assertAll("overloads called from Java",
                () -> System.out.println(new Product("Name", 5.0, "Desc")),
                () -> System.out.println(new Product("Name", 5.0)),
                () -> System.out.println(new Product("Name"))
        );
    }
}
