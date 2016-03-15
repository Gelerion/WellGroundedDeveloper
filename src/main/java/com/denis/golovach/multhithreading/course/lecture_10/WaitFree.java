package com.denis.golovach.multhithreading.course.lecture_10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Path to Wait-free
public class WaitFree {
    private static int index = 1;
    private static final Object mutex = new Object();

    public static void main(String[] args) throws InterruptedException {

        //1) mutex
        Mutex.run();
        //moving next...
        //2) lock
        ReEntrant.run(); //Iys OBSTRUCTION free lock

    }

    private static class ReEntrant {
        private static int index = 1;
        private static final Lock lock = new ReentrantLock();

        private static void run() throws InterruptedException {
            System.out.println("*************[ReEntrant START]*************");
            Thread t1 = new Thread(() -> {
                int myId;
                for (int i = 0; i < 10; i++) {
                    while (true) {
                        int dummy = 0;
                        if (lock.tryLock()) {
                            myId = index++;
                            System.out.println("[ReEntrant Thread 1] myId = " + myId);
                            lock.unlock();
                            break;
                        }
                    }
                }
            });

            Thread t2 = new Thread(() -> {
                int myId;
                for (int i = 0; i < 11; i++) {
                    while (true) {
                        if (lock.tryLock()) {
                            myId = index++;
                            System.out.println("[ReEntrant Thread 2] myId = " + myId);
                            lock.unlock();
                            break;
                        }
                    }
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("*************[ReEntrant STOP]*************");

        }
    }

    private static class Mutex {
        private static int index = 1;
        private static final Object mutex = new Object();

        private static void run() throws InterruptedException {
            System.out.println("*************[MUTEX START]*************");

            Thread t1 = new Thread(() -> {
                int myId;
                for (int i = 0; i < 10; i++) {
                    synchronized (mutex) {
                        myId = index++;
                        System.out.println("[Mutex Thread 1] myId = " + myId);
                    }
                }
            });

            Thread t2 = new Thread(() -> {
                int myId;
                for (int i = 0; i < 11; i++) {
                    synchronized (mutex) {
                        myId = index++;
                        System.out.println("[Mutex Thread 2] myId = " + myId);
                    }
                }
            });

            t1.start();
            t2.start();

            t2.join();
            t1.join();

            System.out.println("*************[MUTEX STOP]*************");

        }
    }

}
