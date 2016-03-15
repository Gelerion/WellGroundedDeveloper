package com.denis.golovach.multhithreading.course.lecture_5.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FirstExample {
    //MAX capacity
    static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(16);

    public static void main(String[] args) {

        //Producer
        new Thread(() -> {
            int counter = 0;
            for (int i = 0; i < 128; i++) {
                try {
                    Thread.sleep(300);
                    queue.put(++counter);
                    System.out.println("Put: " + counter);
                } catch (InterruptedException ignore) {/*NOP*/}
            }
        }).start();

        //Consumer
        new Thread(() -> {
            while (true) {

                Integer data = queue.poll();
                if (data != null) {
                    System.out.println("Pool: " + data);
                } else {
                    System.out.println("else");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignore) {/*NOP*/}
                }

//                Blocks
/*                try {
                    Integer data = queue.take();
                    System.out.println("Take: " + data);
                } catch (InterruptedException ignore) {*//*NOP*//*}
                */
            }
        }).start();

    }
}
