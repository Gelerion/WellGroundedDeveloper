package com.denis.golovach.multhithreading.course.lecture_5.customHandler;

/**
 * ����� ����� ����������� ������ ����� � �������
 */
public class ThreadExHandler {
    public static void main(String[] args) {
        Thread me = Thread.currentThread();

        me.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            //t - ��� ����� ������� � ������
            //e - ������
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // ������ stackTrace
                System.out.println("Bad");
            }
        });

        throw new Error();

/*        ThreadGroup group = new ThreadGroup("stub");
        new Thread(group, () -> {
            System.out.println("Never");
        });*/
    }
}
