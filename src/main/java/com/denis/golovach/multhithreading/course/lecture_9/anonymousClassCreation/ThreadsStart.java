package com.denis.golovach.multhithreading.course.lecture_9.anonymousClassCreation;

public class ThreadsStart {

    public static void someMethod() {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        new Thread(ThreadsStart::someMethod).start();

        //->
        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadsStart.someMethod();
            }
        }).start();
    }
}
