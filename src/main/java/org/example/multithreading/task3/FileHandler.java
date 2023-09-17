package org.example.multithreading.task3;

public class FileHandler {

    public FileHandler(int type) {
        this.type = type;
    }

    public int type;

    public void process(CustomFile file) {
        System.out.println("CustomFile processing start:" + file.toString() + ": " + file.getSize() + " " + file.getType());
        if (file.getType() != type) {
            throw new IllegalArgumentException();
        }
        try {
            Thread.sleep(file.getSize() * 7L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("CustomFile processing end:" + file.toString() + ": " + file.getSize() + " " + file.getType());
    }
}
