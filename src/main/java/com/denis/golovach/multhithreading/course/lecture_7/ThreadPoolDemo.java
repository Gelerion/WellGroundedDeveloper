package com.denis.golovach.multhithreading.course.lecture_7;

import java.util.concurrent.Executor;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        Executor executor = new MyThreadPool(2);
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());

    }

    public static Runnable getTask() {
        return () -> System.out.println("Hello from: " + Thread.currentThread());
    }
}
