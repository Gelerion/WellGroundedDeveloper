package com.denis.tests.twohours;

public class Task3
{
    public static void main(String[] args)
    {
        int[] A = new int[10];

        A[0] = 0;
        A[1] = 1;
        A[2] = 3;
        A[3] = -2;
        A[4] = 0;
        A[5] = 1;
        A[6] = 0;
        A[7] = -3;
        A[8] = 2;
        A[9] = 3;

        amount(A);
    }

    private static int count(int[] a)
    {
        int p;
        int q;
        int r;

        for (int i = 1; i < a.length; i++)
        {
            if (a[i] > a[i - 1])
            {

            }
        }

        return 0;
    }

    static int amount(int[] A)
    {
        int depth = 0;

        int P = 0, Q = -1, R = -1;

        for (int i = 1; i < A.length; i++)
        {
            if (Q < 0 && A[i] >= A[i-1])
                Q = i-1;

            if ((Q >= 0 && R < 0) &&
                    (A[i] <= A[i-1] || i + 1 == A.length))
            {
                if (A[i] <= A[i-1])
                    R = i - 1;
                else
                    R = i;
                System.out.println(P+"  "+Q+"  "+R);
                depth = Math.max(depth, Math.min(A[P]-A[Q], A[R]-A[Q]));
                P = i - 1;
                Q = R = -1;
            }
        }
        if (depth == 0) depth = -1;
        System.out.println("Depth: "+depth);
        return depth;
    }
}
