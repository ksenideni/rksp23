package org.example.nio.task2;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ApacheCommonsIOTask {
    public static void main(String[] args) {
        long startMills = System.currentTimeMillis();
        File src = new File("src/main/resources/nio/file_100mb.txt");
        File dst = new File("src/main/resources/nio/dst.txt");
        copyFileByApache(src, dst);
        long endMills = System.currentTimeMillis();
        System.out.println("Время выполнения в миллисекундах: " + (endMills - startMills));
        dst.delete();
    }

    public static void copyFileByApache(File src, File dst) {
        try {
            FileUtils.copyFile(src, dst);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
