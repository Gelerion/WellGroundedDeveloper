package com.denis.golovach.multhithreading.course.lecture_4.sync;

public class ReentrantSync {

    //re-entrant lock
    public static void main(String[] args) {
        Object ref0 = new Object();

        synchronized (ref0) {
            synchronized (ref0) {
                ref0.notify();
            }
        }

        //sample
        g();
    }

    static synchronized void g() {
        h();
    }

    private static synchronized void h() {
        f();
    }

    private static synchronized void f() {
        System.out.println("Hello! + Трижды захватил блокировку " + Thread.holdsLock(ReentrantSync.class));
    }
}
