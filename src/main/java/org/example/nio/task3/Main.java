package org.example.nio.task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    public static void main(String[] args) throws IOException {
        Short checkSum = 0;
        File file = new File("src/main/resources/nio/file.txt");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            FileChannel channel = inputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            while (channel.position() < channel.size()) {
                channel.read(byteBuffer);
//                System.out.println(byteBuffer.position());
//                System.out.println(new String(byteBuffer.array()));
                for (int i = 0; i < byteBuffer.position(); i += 2) {
                    Short current = byteBuffer.getShort(i);
//                    System.out.println(current);
                    checkSum = (short) (checkSum ^ current);
                }
                byteBuffer.clear();
            }
            System.out.println(checkSum);
        }
    }
}
