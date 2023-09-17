package org.example.multithreading.task2;

import java.util.Scanner;
import java.util.concurrent.*;

import static org.example.multithreading.Utils.sleepInInterval;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = -1;
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        while (a != 0) {
            a = in.nextInt();
            Future<Long> future = executorService.submit(new CallableSquare(a));
            executorService.execute(new FutureProcessor(future));
            System.out.println("new pushing");
        }
        executorService.shutdown();
    }

    public static long squareNumber(int a) {
        sleepInInterval(1_000, 5_000);
        return a * a;
    }
}
