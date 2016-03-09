package com.denis.tests;

public class TwoHoursTest
{
    public static void main(String[] args)
    {
        IntList D = new IntList(4, null);
        IntList C = new IntList(3, D);
        IntList B = new IntList(2, C);
        IntList A = new IntList(1, B);

        System.out.println(solution(A));
        System.out.println("rec: " + amount(A));


    }

    public static int solution(IntList l)
    {
        int count = 0;

        while (l != null)
        {
            count++;
            l = l.next;
        }
        return count;

    }

    public static int amount(IntList l)
    {
        if (l == null)
        {
            return 0;
        }

        return amount(l.next) + 1;
    }
}
