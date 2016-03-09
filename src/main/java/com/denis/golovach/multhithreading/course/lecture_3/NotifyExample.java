package com.denis.golovach.multhithreading.course.lecture_3;

/**
 * throwsException выкидывают исключение IllegalMonitorStateException
 * Эти методы можно вызывать только захватив блокировку
 */
public class NotifyExample {
    //как работает эта программа?
    public static void main(String[] args) throws InterruptedException {

        throwsException();
        withoutException();
    }

    private static void withoutException() {
        Object ref = new Object();
        synchronized (ref) {
            ref.notify();
        }
    }

    private static void throwsException() {
        new Object().notify();
        //new Object().wait();
    }
}
