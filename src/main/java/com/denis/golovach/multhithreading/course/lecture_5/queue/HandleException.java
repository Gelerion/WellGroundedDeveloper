package com.denis.golovach.multhithreading.course.lecture_5.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class HandleException {
    //MAX capacity
    static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(16);

    public static void main(String[] args) {

        //Producer
        new Thread(() -> {
            int counter = 0;
            for (int i = 0; i < 128; i++) {
                try {
                    Thread.sleep(300);
                    if (queue.offer(++counter)) {
                        //wait
                    }
                    System.out.println("Put: " + counter);
                } catch (InterruptedException ignore) {/*NOP*/}
            }
        }).start();

        //Consumer
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Integer data = queue.take();
                    System.out.println("Take: " + data);
                } catch (InterruptedException ignore) {/*NOP*/}
            }

        }).start();

    }
}
