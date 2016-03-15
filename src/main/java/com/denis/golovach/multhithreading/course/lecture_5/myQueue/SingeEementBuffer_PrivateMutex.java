package com.denis.golovach.multhithreading.course.lecture_5.myQueue;

/**
 * ������ Mutex
 * ������ ����� ��� ������� �� ����� ����
 */
public class SingeEementBuffer_PrivateMutex {

    private final Object lock = new Object();

    private Integer value;

    public void put(int elem) throws InterruptedException {
        synchronized (lock) {
            while (value != null) lock.wait();

            value = elem;
            lock.notifyAll();
        }
    }

    public int take() throws InterruptedException {
        synchronized (lock) {
            while (value == null) lock.wait();

            int result = value;
            value = null;
            lock.notifyAll();
            return result;
        }
    }
}
