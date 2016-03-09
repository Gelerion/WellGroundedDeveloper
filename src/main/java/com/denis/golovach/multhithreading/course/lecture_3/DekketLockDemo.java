package com.denis.golovach.multhithreading.course.lecture_3;

public class DekketLockDemo {
    public static void main(String[] args) {
        final DekkerLock lock = new DekkerLock();

        new Thread(() -> {
            while (true) {
                lock.lockA();
                try {

                    System.out.println("A");

                } finally {
                    lock.unlockA();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lock.lockB();
                try {

                    System.out.println("B");

                } finally {
                    lock.unlockB();
                }
            }
        }).start();
    }
}
