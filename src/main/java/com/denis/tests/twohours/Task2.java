package com.denis.tests.twohours;

public class Task2
{

    public static void main(String[] args)
    {
//        int[] a = new int[]{2, 2, 2, 2, 2, 3, 3, 4, 5, 5};
//        int[] a = new int[]{2, 2, 2, 2, 2, 2, 3, 3, 4, 5, 5};
//        int[] a = new int[]{2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 5, 5};


//        int[] a = new int[]{2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5};

        int[] a = new int[]{2, 2, 2, 2, 2, 3, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5};

        int solution = new Solution().solution(a);
        System.out.println("solution = " + solution);
        System.out.println("length " + a.length);
    }
}
