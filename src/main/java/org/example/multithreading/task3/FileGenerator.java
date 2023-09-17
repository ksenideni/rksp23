package org.example.multithreading.task3;

import static org.example.multithreading.Utils.sleepInInterval;

public class FileGenerator {
    private static final int minimumType = 0;
    private static final int maximumType = 3;
    private static final int minimum = 10;
    private static final int maximum = 101;

    public static CustomFile generateFile() {
        sleepInInterval(100, 1000);
        int fileType = ((int) (Math.random() * (maximumType - minimumType))) + minimumType;
        int fileSize = ((int) (Math.random() * (maximum - minimum))) + minimum;
        return new CustomFile(fileType, fileSize);
    }
}
