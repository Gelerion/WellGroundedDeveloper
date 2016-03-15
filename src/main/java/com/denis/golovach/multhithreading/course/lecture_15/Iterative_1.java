package com.denis.golovach.multhithreading.course.lecture_15;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * find all numbers from 0 to 1_000_000
 * we need to find sum of all numbers which not by /3 and /5 (ne kratnye 3 i 5)
 * Example of ITERATIVE multi threading
 */
public class Iterative_1 {

    public static void main(String[] args) throws InterruptedException {
        //one counter is bottle neck
        AtomicLong result = new AtomicLong(0);
        int cpu = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cpu);

        List<Callable<Void>> taskList = new ArrayList<>();

        //100 - intervals
        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            taskList.add(() -> {
                calc(result, 10_000 * finalI, 10_000 * (finalI + 1));
                return null;
            });
        }

        //final barrier
        pool.invokeAll(taskList);
        System.out.println(result);
        pool.shutdown();
    }

    private static void calc(AtomicLong result, int from, int to) {
        for (int index = from; index < to; index++) {
            if (index % 3 != 0 && index % 5 != 0) {
                result.getAndAdd(index);
            }

        }
    }
}
