package org.example.nio.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileChannelTask {
    public static void main(String[] args) {
        long startMills = System.currentTimeMillis();
        File src = new File("src/main/resources/nio/file_100mb.txt");
        File dst = new File("src/main/resources/nio/dst.txt");
        // копия nio
        copyFileByChannel(src, dst);
        long endMills = System.currentTimeMillis();
        System.out.println("Время выполнения в миллисекундах: " + (endMills - startMills));
        dst.delete();
    }

    public static void copyFileByChannel(File src, File dst) {
        try (FileChannel srcFileChannel = new FileInputStream(src).getChannel();
             FileChannel dstFileChannel = new FileOutputStream(dst).getChannel()) {
            long count = srcFileChannel.size();
            while (count > 0) {
                long transferred = srcFileChannel.transferTo(srcFileChannel.position(),
                        count, dstFileChannel);
                srcFileChannel.position(srcFileChannel.position() + transferred);
                count -= transferred;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
