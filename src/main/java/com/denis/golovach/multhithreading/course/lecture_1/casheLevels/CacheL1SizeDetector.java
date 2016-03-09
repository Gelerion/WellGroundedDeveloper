package com.denis.golovach.multhithreading.course.lecture_1.casheLevels;

public class CacheL1SizeDetector
{
    public static void main(String[] args)
    {
//        64 kb
        byte[] array = new byte[64 * 1024];
        //10 запусков
        for (int testIndex = 0; testIndex < 10; testIndex++)
        {
            testFunction(array);
            System.out.println("------");
        }
    }

    private static void testFunction(byte[] array)
    {
//        len - предположения о размере кэша 8кб ++
//        вначале работает долго потом ява разгоняет
//        после разгона буду видеть примерно следующее -
//            время о какого-то кол-ва памяти одинаковое потом скачок
        for (int len = 8192; len < array.length; len += 8192)
        {
            long t0 = System.nanoTime();
            for (int i = 0; i < 100; i++)
            {
//                иду с шагом 64 и просто записываю туда 1
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
