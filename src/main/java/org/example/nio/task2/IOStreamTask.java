package org.example.nio.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOStreamTask {
    public static void main(String[] args) {
        long startMills = System.currentTimeMillis();
        File src = new File("src/main/resources/nio/file_100mb.txt");
        File dst = new File("src/main/resources/nio/dst.txt");
        copyFileByIO(src, dst);
        long endMills = System.currentTimeMillis();
        System.out.println("Время выполнения в миллисекундах: " + (endMills - startMills));
        dst.delete();
    }

    public static void copyFileByIO(File src, File dst) {
        try (InputStream inputStream = new FileInputStream(src);
             OutputStream outputStream = new FileOutputStream(dst)) {

            byte[] buffer = new byte[1024];
            int length;
            // Читаем данные в байтовый массив, а затем выводим в OutStream
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
