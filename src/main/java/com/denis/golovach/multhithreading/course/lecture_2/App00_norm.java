package com.denis.golovach.multhithreading.course.lecture_2;

//JMM - java memory model
public class App00_norm
{
    //JMM гарантирует что если флаг volatile то программа
    //гарантированно остановится
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
                //так как все действия до чтения volatile переменный будут иметь свзяь happens-before
                //
                System.out.println(data); //1 !!!
            }
        }).start();

        data = 1;
        //без volatile компилятор мог бы просто выкинуть эту
        // строчку и она не повлияла бы на второй поток
        run = false;
    }
}
