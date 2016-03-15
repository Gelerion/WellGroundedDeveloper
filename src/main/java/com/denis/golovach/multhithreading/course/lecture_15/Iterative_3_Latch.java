package com.denis.golovach.multhithreading.course.lecture_15;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class Iterative_3_Latch {
    public static void main(String[] args) throws InterruptedException {
        AtomicLong result = new AtomicLong(0);
        int cpu = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cpu);
        final CountDownLatch latch = new CountDownLatch(100);

        //100 - intervals
        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            pool.submit(() -> {
                long localResult = calc(10_000 * finalI, 10_000 * (finalI + 1));
                result.addAndGet(localResult);
                latch.countDown();
            });
        }

        //final barrier
        latch.await();

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
