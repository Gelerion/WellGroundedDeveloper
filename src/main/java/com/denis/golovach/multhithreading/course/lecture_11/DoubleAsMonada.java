package com.denis.golovach.multhithreading.course.lecture_11;

public class DoubleAsMonada {
    public static void main(String[] args) {
        //! ONLY with double AND float
        double a = 1;
        double b = a + 1;
        double c = b / 0; //!! Infinity
        double d = c + 1;
        System.out.println("d = " + d); //infinity
        double e = 1 - d; //!! -Infinity
        double f = d / e; //!! NAN
        System.out.println("f = " + f);

        int a1 = 1;
        int b2 = a1 + 1;
        int c3 = b2 / 0; //EXCEPTION!
        int d4 = c3 + 1;
        System.out.println("d = " + d4);
        int e5 = 1 - d4;
        int f6 = d4 / e5;
        System.out.println("f = " + f6);
    }

}
