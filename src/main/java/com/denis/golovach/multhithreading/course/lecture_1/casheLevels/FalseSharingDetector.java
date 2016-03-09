package com.denis.golovach.multhithreading.course.lecture_1.casheLevels;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FalseSharingDetector
{
//    ���� ��� 8 ���� ������� ��� 9 ������ �� ������ ������� � ���� ��� �����
//    ����� ��������� ��� 0 � 1 ������� � ���� �����, �� 0 � 8 ����� �� ������ �������
//    �������� ��� ����� 64 �����
    volatile static long value0 = 0;
    volatile static long value1 = 0;
    volatile static long value2 = 0;
    volatile static long value3 = 0;
    volatile static long value4 = 0;
    volatile static long value5 = 0;
    volatile static long value6 = 0;
    volatile static long value7 = 0;
    volatile static long value8 = 0;

//    ����� ����� �������� 0 � 1 �� ����� ���������� ��������� � �����.
//    ����� � ������ ������ ��������� �������� 0 � �� ������ 8 �� �������� ������ ��������
    public static void main(String[] args) throws InterruptedException
    {
//        ��� �� ��� ������
        ExecutorService pool = Executors.newFixedThreadPool(2);

//        ��� ��������
        final CountDownLatch latch0 = new CountDownLatch(2);
        final CountDownLatch latch2 = new CountDownLatch(2);

        pool.submit(new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
//                ��� ��� �� ������
                latch0.countDown(); //Thread #1 ready
                latch0.await(); //Wait for start signal

                long t0 = System.nanoTime();
                for (int i = 0; i < 100_000_000; i++)
                {
                    value0 = value0 * i;
                }
                long t1 = System.nanoTime();
                System.out.println((t1 - t0) / 1000000 + "ms");
                latch2.countDown(); // Thread #1 finished

                return null;
            }
        });

        pool.submit(new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
//                ��� ��� �� ������
                latch0.countDown(); //Thread #2 ready
                latch0.await(); //Wait for start signal

                long t0 = System.nanoTime();
                for (int i = 0; i < 100_000_000; i++)
                {
                    value8 = value8 * i;
                }
                long t1 = System.nanoTime();
                System.out.println((t1 - t0) / 1000000 + "ms");
                latch2.countDown(); // Thread #2 finished

                return null;
            }
        });

        latch2.await(); // wait for 1 + 2 threads finished

        pool.shutdownNow(); // kill thread pool
    }
}
























































