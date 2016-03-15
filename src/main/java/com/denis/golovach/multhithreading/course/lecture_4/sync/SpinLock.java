package com.denis.golovach.multhithreading.course.lecture_4.sync;

/**
 * ������ ������ �� ������� �� ������� ������
 */
public class SpinLock {
    static volatile boolean in = false;

    public static void main(String[] args) {
        Object monitor = new Object();
        new Thread(() -> {
            synchronized (monitor) {
                in = true;
                try {
                    // �� ��������� ����������!
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException ignore) {/*NOP*/}
            }

        }).start();

        System.out.println("A");
        while (!in) ; //spin lock | busy waiting
        System.out.println("B");
        synchronized (monitor) {
            System.out.println("C");
        }
    }
}
