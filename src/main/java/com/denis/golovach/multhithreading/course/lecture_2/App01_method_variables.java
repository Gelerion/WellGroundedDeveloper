package com.denis.golovach.multhithreading.course.lecture_2;

public class App01_method_variables {
    static int run = 0;

    public static void main(String[] args) {
        run = 1;

        //��������� �������������� ���������� 1
        //�������� ������� �������� ����� ������ �����������������
        new Thread(() ->
                System.out.println(run))
                .start();

        //run = 2 -> ��� ��� �� ��������������
    }
}
