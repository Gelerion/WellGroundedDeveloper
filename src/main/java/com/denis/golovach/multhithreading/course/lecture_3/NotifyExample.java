package com.denis.golovach.multhithreading.course.lecture_3;

/**
 * throwsException ���������� ���������� IllegalMonitorStateException
 * ��� ������ ����� �������� ������ �������� ����������
 */
public class NotifyExample {
    //��� �������� ��� ���������?
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
