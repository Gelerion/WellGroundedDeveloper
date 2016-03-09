package com.denis.golovach.multhithreading.course.lecture_1.casheLevels;

public class CacheL1SizeDetector
{
    public static void main(String[] args)
    {
//        64 kb
        byte[] array = new byte[64 * 1024];
        //10 ��������
        for (int testIndex = 0; testIndex < 10; testIndex++)
        {
            testFunction(array);
            System.out.println("------");
        }
    }

    private static void testFunction(byte[] array)
    {
//        len - ������������� � ������� ���� 8�� ++
//        ������� �������� ����� ����� ��� ���������
//        ����� ������� ���� ������ �������� ��������� -
//            ����� � ������-�� ���-�� ������ ���������� ����� ������
        for (int len = 8192; len < array.length; len += 8192)
        {
            long t0 = System.nanoTime();
            for (int i = 0; i < 100; i++)
            {
//                ��� � ����� 64 � ������ ��������� ���� 1
                for (int index = 0; index < len; index += 64)
                {
                    array[index] = 1;
                }
            }
            long dT = System.nanoTime() - t0;

            System.out.println("len: " + len + ", dT: " + dT + ", 10*dT/len " + (10*dT)/len);
        }
    }
}
