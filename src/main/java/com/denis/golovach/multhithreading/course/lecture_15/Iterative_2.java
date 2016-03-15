package com.denis.golovach.multhithreading.course.lecture_15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Some speed boost, new calc method
 */
public class Iterative_2 {
    public static void main(String[] args) throws InterruptedException {
        AtomicLong result = new AtomicLong(0);
        int cpu = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cpu);

        List<Callable<Void>> taskList = new ArrayList<>();

        //100 - intervals
        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            taskList.add(() -> {
                long localResult = calc(10_000 * finalI, 10_000 * (finalI + 1));
                result.addAndGet(localResult);
                return null;
            });
        }

        //final barrier
        pool.invokeAll(taskList);
        System.out.println(result);
        pool.shutdown();
    }

    private static long calc(int from, int to) {
        long result = 0;
        for (int index = from; index < to; index++) {
            if (index % 3 != 0 && index % 5 != 0) {
                result += index;
            }
        }
        return result;
    }
}
