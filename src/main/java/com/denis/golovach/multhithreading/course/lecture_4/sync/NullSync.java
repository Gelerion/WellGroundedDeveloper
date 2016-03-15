package com.denis.golovach.multhithreading.course.lecture_4.sync;

public class NullSync {
    public static void main(String[] args) throws InterruptedException {

        syncOnNull(); //java.lang.NullPointerException

        //IllegalMonitorStateEx
        synchronized (new Object()) {
            new Object().wait();
        }

    }

    private static void syncOnNull() throws InterruptedException {
        //нельзя делать по null
        synchronized ((Object)null) {
            new Object().wait();
        }
        //java.lang.NullPointerException
    }
}
