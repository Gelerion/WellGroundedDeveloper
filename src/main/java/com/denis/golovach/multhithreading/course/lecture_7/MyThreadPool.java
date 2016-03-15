package com.denis.golovach.multhithreading.course.lecture_7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class MyThreadPool implements Executor {

    private final BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(256);
    private final Thread[] pool;

    public MyThreadPool(int threadCount) {
        this.pool = new Thread[threadCount];
        for (int k = 0; k < threadCount; k++) {
            pool[k] = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        //block
                        try {
                            Runnable nextTask = taskQueue.take(); //Block
                            nextTask.run();
                            //NoSuchElementException
                            //Runnable nextTask = taskQueue.remove();
                            //return null;
                            //Runnable nextTask = taskQueue.pool();

                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            });
            pool[k].start();
        }
    }

    @Override
    public void execute(Runnable command) {

        //PUT demo
        try {
            // if full - not reject but block
            taskQueue.put(command);
        } catch (InterruptedException e) {
            throw new Error("NEVER", e);
        }

        // OFFER demo
//        if (!taskQueue.offer(command)) {
//            System.out.println("Rejected!");
//        }

        // ADD demo
//        try {
//            taskQueue.add(command);
//        } catch (IllegalStateException e) {
//            System.out.println("Rejected!");
//        }
    }
}
