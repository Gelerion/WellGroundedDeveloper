package com.denis.golovach.multhithreading.course.lecture_1.ilp;

public class ILP_1_2
{
    public static void main(String[] args)
    {
        long t0 = System.currentTimeMillis();

        double d0 = 0;
//        будет примерно удвоение, умножений в два раза больше
        for (int i = 0; i < 100_000_000; i++)
        {
            d0 = d0 * d0;
            d0 = d0 * d0;
        }

        long t1 = System.currentTimeMillis();
        System.out.println("Total time: " +  (t1 - t0) + " ms");
        System.out.println(d0);
    }
}
