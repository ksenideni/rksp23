package org.example.multithreading.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static org.example.multithreading.task1.Main.processSequentially;

public class RecursiveSumTask extends RecursiveTask<Long> {
    private final int[] arr;

    private static final int THRESHOLD = 1_000;

    public RecursiveSumTask(int[] arr) {
        this.arr = arr;
    }

    @Override
    protected Long compute() {
        if (arr.length > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToLong(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(arr);
        }
    }

    private Collection<RecursiveSumTask> createSubtasks() {
        List<RecursiveSumTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new RecursiveSumTask(
                Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new RecursiveSumTask(
                Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }

    private long processing(int[] arr) {
        return processSequentially(arr);
    }
}