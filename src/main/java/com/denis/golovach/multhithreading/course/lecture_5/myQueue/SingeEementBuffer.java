package com.denis.golovach.multhithreading.course.lecture_5.myQueue;

/**
 * Реализация на основе монитора
 * Монитором выступает сам класс
 */
public class SingeEementBuffer {
    private Integer elem;

    public synchronized void put(int elem) throws InterruptedException {
        while (this.elem != null) {
            this.wait();
        }

        this.elem = elem;
        this.notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (this.elem == null) {
            this.wait();
        }

        Integer result = this.elem;
        this.elem = null;
        this.notifyAll();
        return result;
    }
}
