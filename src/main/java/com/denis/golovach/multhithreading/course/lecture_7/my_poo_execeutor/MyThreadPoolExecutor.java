package com.denis.golovach.multhithreading.course.lecture_7.my_poo_execeutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolExecutor {
    public static void main(String[] args) {
        int corePoolSize = 4;
        int maximumPolSize = 64;
        long keepAliveTime = 10;

        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(256);

        ThreadFactory factory = new ThreadFactory() {

            private AtomicInteger counter = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setPriority(Thread.NORM_PRIORITY);
                thread.setName("MyPool-NORM_PRIORITY-" + counter.incrementAndGet());
                return thread;
            }
        };

        RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("Al threads are busy + task queue is full");
            }
        };

        Executor executor = new ThreadPoolExecutor(
                corePoolSize, maximumPolSize,
                keepAliveTime, unit,
                workQueue,
                factory,
                rejectedHandler
        );

        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
    }


    public static Runnable getTask() {
        return () -> System.out.println("Hello from: " + Thread.currentThread());
    }
}
