package com.denis.golovach.multhithreading.course.lecture_2;

public class App02_isAlive {
    static boolean run = true;

    public static void main(String[] args) {
        Thread newThread = new Thread(() ->
                run = false);

        newThread.start();

        while (newThread.isAlive());
//        newThread.join();

        //����� ������ ����� false
        //������������ ����������� ��� ���� ������� �����
        //���������� �� ��� ����� ���� ��� ���������
        while (run);
        //����� ���������� ����
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().maxMemory());

    }
}
