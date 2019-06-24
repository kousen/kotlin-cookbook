package javaintegration;

import java.io.IOException;

import static javaintegration.IOProblemKt.houstonWeHaveAProblem;

public class IOProblemDemo {
    public static void useTryCatchBlock() {
        try {
            houstonWeHaveAProblem();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void useThrowsClause() throws IOException {
        houstonWeHaveAProblem();
    }

    public static void doNothing() {
        // houstonWeHaveAProblem();  // won't compile
    }

    public static void main(String[] args) {
        // doNothing(); // throws exception
        // useTryCatchBlock();  // Won't compile
        try {
            useThrowsClause();  // IDE wants to remove exc declaration
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
