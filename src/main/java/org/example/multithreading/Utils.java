package org.example.multithreading;

public class Utils {
    public static void sleepInInterval(int maximum, int minimum) {
        try {
            long numOfSeconds = ((int) (Math.random() * (maximum - minimum))) + minimum;
            Thread.sleep(numOfSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
