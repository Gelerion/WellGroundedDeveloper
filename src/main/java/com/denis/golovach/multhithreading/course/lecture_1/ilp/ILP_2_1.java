package com.denis.golovach.multhithreading.course.lecture_1.ilp;

public class ILP_2_1
{
    public static void main(String[] args)
    {
        long t0 = System.currentTimeMillis();

//        можно вычислить сколько модулей добавляю числа и смотреть на время
//        пока время одиноковое работают разные модули
        double d0 = 0;
        double d1 = 0;
//        две ветки, процессор их парралеллит (два разных числа)
//        есть как минимум два модуля умножение чисел
        for (int i = 0; i < 100_000_000; i++)
        {
            d0 = d0 * d0;
            d1 = d1 * d1;
        }

        long t1 = System.currentTimeMillis();
        System.out.println("Total time: " +  (t1 - t0) + " ms");
        System.out.println(d0);
    }
}
