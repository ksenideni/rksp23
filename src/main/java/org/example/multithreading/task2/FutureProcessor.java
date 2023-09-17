package org.example.multithreading.task2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureProcessor implements Runnable {
    Future<Long> future;

    public FutureProcessor(Future<Long> future) {
        this.future = future;
    }

    @Override
    public void run() {
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
