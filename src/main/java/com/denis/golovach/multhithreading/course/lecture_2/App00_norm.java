package com.denis.golovach.multhithreading.course.lecture_2;

//JMM - java memory model
public class App00_norm
{
    //JMM ����������� ��� ���� ���� volatile �� ���������
    //�������������� �����������
    static volatile boolean run = true;
    static int data = 0;

    public static void main(String[] args)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (run) ;
                //��� ��� ��� �������� �� ������ volatile ���������� ����� ����� ����� happens-before
                //
                System.out.println(data); //1 !!!
            }
        }).start();

        data = 1;
        //��� volatile ���������� ��� �� ������ �������� ���
        // ������� � ��� �� �������� �� �� ������ �����
        run = false;
    }
}
