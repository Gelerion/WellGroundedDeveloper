package com.denis.golovach.multhithreading.course.lecture_10;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomics {
    private static AtomicInteger index = new AtomicInteger(0);

    public static void main(String[] args) {

        new Thread(() -> {
            int myId;
            for (int i = 0; i < 10; i++) {
                cas:
                while (true) {
                    int dummy = 0;
                    //get - non blocking operation
                    //i could also use index.set(41) - will act as volatile
                    int oldValue = index.get();
                    int newValue = oldValue + 1;
                    //also compareAndSwap -> returns new value
                    //could lose infinity amount of times
                    if (index.compareAndSet(oldValue, newValue)) {
                        System.out.println(index);
                        break cas;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 11; i++) {
                cas:
                while (true) {
                    int oldValue = index.get();
                    int newValue = oldValue + 1;
                    if (index.compareAndSet(oldValue, newValue)) {
                        System.out.println(index);
                        break cas;
                    }
                }
            }
        }).start();

    }
}
