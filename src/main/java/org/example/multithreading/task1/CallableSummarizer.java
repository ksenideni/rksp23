package org.example.multithreading.task1;

import java.util.concurrent.Callable;

import static org.example.multithreading.task1.Main.sleepOneSecond;

public class CallableSummarizer implements Callable<Long> {
    int[] arr;

    CallableSummarizer(int[] arr) {
        this.arr = arr;
    }

    public Long call() {
        Long sum = 0L;
        for (int a : arr) {
            sum += a;
            sleepOneSecond();
        }
        return sum;
    }
}
