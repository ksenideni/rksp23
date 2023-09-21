package org.example.nio.task1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        //nio high-level api
        Path path = Paths.get("src/main/resources/nio/file.txt");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        }

        //nio FileChannel for latin alphabet
//        File file = new File("src/main/resources/nio/file.txt");
//        try (FileInputStream inputStream = new FileInputStream(file)) {
//            FileChannel fileChannel = inputStream.getChannel();
//
//            ByteBuffer buf = ByteBuffer.allocate(64);
//            int bytesRead = fileChannel.read(buf);
//            while (bytesRead != -1) {
//                buf.flip();
//                while (buf.position() < buf.limit()) {
//                    System.out.print((char) buf.get());
//                }
//                buf.clear();
//                bytesRead = fileChannel.read(buf);
//            }
//        }

        //используя то, что в буффере массив:
        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/nio/file.txt", "r");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(64);
        int bytesRead = fileChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            System.out.print(new String(buf.array(), 0, buf.limit()));
            buf.clear();
            bytesRead = fileChannel.read(buf);
        }


        //весь файл копится в массив и только в конце выводится, чтобы не было дробления символов
//        RandomAccessFile randomAccessFile = new RandomAccessFile("src/main/resources/nio/file.txt", "r");
//        FileChannel fileChannel = randomAccessFile.getChannel();
//        byte[] byteArray = new byte[(int) randomAccessFile.length()];
//        int i = 0;
//        ByteBuffer buf = ByteBuffer.allocate(64);
//        int bytesRead = fileChannel.read(buf);
//        while (bytesRead != -1) {
//            buf.flip();
//            while (buf.position() < buf.limit()) {
//                byteArray[i] = buf.get();
//                i++;
//            }
//            buf.clear();
//            bytesRead = fileChannel.read(buf);
//        }
//        System.out.println(new String(byteArray));
    }
}
