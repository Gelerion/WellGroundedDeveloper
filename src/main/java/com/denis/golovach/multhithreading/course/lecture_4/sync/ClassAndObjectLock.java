package com.denis.golovach.multhithreading.course.lecture_4.sync;

public class ClassAndObjectLock {
    public static void main(String[] args) {
            new ClassAndObjectLock().lock(); //false
    }

    public synchronized void lock() {
        System.out.println(Thread.holdsLock(ClassAndObjectLock.class));
    }
}
