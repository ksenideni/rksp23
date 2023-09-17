package org.example.multithreading.task1;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private static int ARRAY_SIZE = 10_000;
    private static int BUTCH_SIZE = 1_000;


    static int[] array = new int[ARRAY_SIZE];

    private static void init() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
    }

    public static long processSequentially(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sleepOneSecond();
            sum += arr[i];
        }
        return sum;
    }

    public static long processWithFuture(int[] arr) {
        long sum = 0;
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int[] partOfArray = Arrays.copyOfRange(arr, i * BUTCH_SIZE, i * BUTCH_SIZE + BUTCH_SIZE);
            futures.add(executorService.submit(new CallableSummarizer(partOfArray)));
        }
        for (Future<Long> future : futures) {
            try {
                sum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdownNow();
        return sum;
    }

    public static long forkJoinSum(int[] arr) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        var task = new RecursiveSumTask(arr);
        return forkJoinPool.invoke(task);
    }


    public static void main(String[] args) {
        System.out.println("Initializing an array...");
        init();
        var time1 = Instant.now();

        System.out.println("Sequentially processed sum = " + processSequentially(array));
        var time2 = Instant.now();
        System.out.println(Duration.between(time1, time2));

        System.out.println("Addition using FutureTask = " + processWithFuture(array));
        var time3 = Instant.now();
        System.out.println(Duration.between(time2, time3));

        System.out.println("Hello, ForkJoin. Sum = " + forkJoinSum(array));
        var time4 = Instant.now();
        System.out.println(Duration.between(time3, time4));

        System.out.println("End of experiment :)");
    }

//    java -Xms1m -Xmx1024m org.example.task1.Main

//    public static void main(String[] args) {
//        // Get current size of heap in bytes
//        long startMemory = Runtime.getRuntime().totalMemory();
//        init();
////        System.out.println("Sequentially processed sum = " + processSequentially(array));
//
////        System.out.println("Addition using FutureTask = " + processWithFuture(array));
//
//        System.out.println("Hello, ForkJoin. Sum = " + forkJoinSum(array));
//
//        long endMemory = Runtime.getRuntime().totalMemory();
//
//        System.out.println(endMemory-startMemory);
//
//    }

    public static void sleepOneSecond() {

        try {
//            Thread.sleep(1_000);
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
