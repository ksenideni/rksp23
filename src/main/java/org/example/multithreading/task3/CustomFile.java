package org.example.multithreading.task3;

public class CustomFile {


    private int type;
    private int size;

    public CustomFile(int type, int size) {
        this.type = type;
        this.size = size;
        if (size < 10 || size > 100) {
            throw new IllegalArgumentException();
        }
    }

    public int getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
}
