package javaintegration;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IOProblemTest {
    @Test
    void functionThrowsIOException() {
        assertThrows(IOException.class,
                IOProblemKt::houstonWeHaveAProblem);
    }
}
