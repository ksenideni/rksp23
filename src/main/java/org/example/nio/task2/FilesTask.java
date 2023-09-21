package org.example.nio.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesTask {
    public static void main(String[] args) {
        long startMills = System.currentTimeMillis();
        Path sourceDirectory = Paths.get("src/main/resources/nio/file_100mb.txt");
        Path targetDirectory = Paths.get("src/main/resources/nio/dst.txt");
        copyFileByFiles(sourceDirectory, targetDirectory);
        long endMills = System.currentTimeMillis();
        System.out.println("Время выполнения в миллисекундах: " + (endMills - startMills));
    }

    public static void copyFileByFiles(Path src, Path dst) {
        try {
            Files.copy(src, dst);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
