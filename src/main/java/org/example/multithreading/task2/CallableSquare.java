package org.example.multithreading.task2;

import java.util.concurrent.Callable;

import static org.example.multithreading.task2.Main.squareNumber;

public class CallableSquare implements Callable<Long> {
    int a;

    CallableSquare(int a) {
        this.a = a;
    }

    public Long call() {
        System.out.println("Current thread: " + Thread.currentThread().getId());
        System.out.println();
        return squareNumber(a);
    }
}
