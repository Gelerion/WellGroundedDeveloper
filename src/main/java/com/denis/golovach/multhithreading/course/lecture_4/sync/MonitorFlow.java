package com.denis.golovach.multhithreading.course.lecture_4.sync;

public class MonitorFlow {
    static volatile boolean in = false;

    public static void main(String[] args) {
        Object monitor = new Object();
        new Thread(() -> {
            synchronized (monitor) {
                in = true;
                try {
                    System.out.println("X");
                    monitor.wait();
                    System.out.println("Y");
                } catch (InterruptedException ignore) {/*NOP*/}
            }

        }).start();

        System.out.println("A");
        while (!in) ; //spin lock | busy waiting
        System.out.println("B");
        synchronized (monitor) {
            System.out.println("C");
            //Что тeпeрь будет напечатанно?
            monitor.notify();
            System.out.println("D");
        }
        System.out.println("E");
    }
}
