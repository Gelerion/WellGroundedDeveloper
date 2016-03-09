package com.denis.algorithms.itvdn.basics_01;

public class IncreaseLevel_2 {

    // increase level O(n ^ 3)
    static void doSlowly(int inum, int jnum, int knum) {
        int a = 0;
        for (int i = 0; i < inum; i++) {
            for (int j = 0; j < jnum; j++) {
                for (int k = 0; k < knum; k++) {
                    a++;
                }
            }
        }

        System.out.printf("a = %d", a);
    }

    // increase level O(n ^ 2)
    static void doFastly(int inum, int jnum) {
        int a = 0;
        for (int i = 0; i < jnum; i++) {
            for (int j = 0; j < jnum; j++) {
                doSlowly(5,6,7);
            }
        }
    }

    //Используем ПРАВИЛО ПРОИЗВЕДЕНИЙ что бы посчитать степень роста этой функции
    // O(n ^ 2) * O(n ^ 3) = O(n ^ 5)
    public static void main(String[] args) {
        doFastly(2,2);
    }
}
