package io.simple;

public class Promotion {
    public static void main(String[] args) {
        int myInt = 3;
        long myLong = myInt;
        System.out.println(myInt + ", " + myLong);

        Integer myInteger = 3;
        // Long myWrappedLong = myInteger; // Does not compile
        Long myWrappedLong = myInteger.longValue();
        myWrappedLong = Long.valueOf(myInteger);
        System.out.println(myInteger + ", " + myWrappedLong);
    }
}
