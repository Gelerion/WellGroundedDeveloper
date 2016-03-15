package com.denis.golovach.multhithreading.course.lecture_7;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

public class ExecutorExample {
    public static void main(String[] args) {
        //задачи для экзекутора
        Executor executor = getExecutor();
        executor.execute(getTask());
        executor.execute(getTask());

        System.out.println("Hello from: " + Thread.currentThread());
    }

    //Thread per task executor
    public static Executor getExecutor() {
        return new Executor() {

            //Генератор имён
            private final AtomicLong index = new AtomicLong();

            private final ThreadGroup group = new ThreadGroup("My group");

            @Override
            public void execute(Runnable command) {
                //Это сделанно для добавления какой то магии потокам
                //Thread.setPriority(5)...
                Thread thread = new Thread(group, command);
                thread.setDaemon(false);
                thread.setPriority(Thread.NORM_PRIORITY + 1);
                thread.setName("Thread#" + index.getAndIncrement());
                thread.start();
            }
        };
    }

    public static Runnable getTask() {
        return () -> System.out.println("Hello from: " + Thread.currentThread());
    }
}
