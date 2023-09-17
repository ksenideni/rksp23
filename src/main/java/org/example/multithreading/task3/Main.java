package org.example.multithreading.task3;

import java.util.concurrent.*;

public class Main {

    public static ArrayBlockingQueue<CustomFile> customFileQueue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService[] executorServices = new ExecutorService[3];
        Object[] mutex = new Object[3];
        FileHandler[] fileHandlers = new FileHandler[3];
        for (int i = 0; i < 3; i++) {
            fileHandlers[i] = new FileHandler(i);
            executorServices[i] = new ThreadPoolExecutor(1, 1,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(1));
            mutex[i] = new Object();
        }
        Runnable route = () -> {
            while (true) {
                CustomFile poll = null;
                try {
                    poll = customFileQueue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                CustomFile finalPoll = poll;
                synchronized (mutex[poll.getType()]) {
                    executorServices[poll.getType()].execute(() ->
                            fileHandlers[finalPoll.getType()].process(finalPoll));
                }

            }
        };
        new Thread(route).start();
        while (true) {
            customFileQueue.put(FileGenerator.generateFile());
        }
    }

}
