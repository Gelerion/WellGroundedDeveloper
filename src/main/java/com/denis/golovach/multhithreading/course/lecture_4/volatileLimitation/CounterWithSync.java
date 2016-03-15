package com.denis.golovach.multhithreading.course.lecture_4.volatileLimitation;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterWithSync {
    //volatile больше не нужен т.к. между synchronized есть связи happens-before
    static int counter = 0;
    static AtomicInteger atomicCounter = new AtomicInteger();
    static volatile boolean finish0 = false;
    static volatile boolean finish1 = false;

    public static void main(String[] args) {
        long start = System.nanoTime();
        new Thread(() -> {
            for (int i = 0; i < 10_000_000; i++) {
//                inc();
                atomicCounter.getAndIncrement();
            }
            finish0 = true;
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10_000_000; i++) {
//                inc();
                atomicCounter.getAndIncrement();

            }
            finish1 = true;
        }).start();

        while (!finish0 || !finish1);
        System.out.println(atomicCounter); // -> 20_000_000
        long end = System.nanoTime();
        System.out.println("Time: " + (end - start));

        System.out.println("Difference between atomic(258627971) and sync(1114289164): " + (1114289164 - 216064612));
    }

    private synchronized static void inc() {
        int tmp = counter;
        tmp = tmp + 1;
        counter = tmp;
    }

    // Тут есть эффект - "lost update"
    private /*synchronized*/ static void inc0() {
        int tmp = counter;
        tmp = tmp + 1;
        counter = tmp;
    }
}
