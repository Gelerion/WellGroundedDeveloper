package com.denis.golovach.multhithreading.course.lecture_4.volatileLimitation;

/**
 * НЕ будет 20 миллионов!
 * ++ не атаморное действие
 */
public class VolatileLimitation {
    static volatile int counter = 0;
    static volatile boolean finish0 = false;
    static volatile boolean finish1 = false;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                counter++;
                //в памяти вот это
                //int tmp = counter;
                //tmp = tmp + 1;
                //counter = tmp;
            }
            finish0 = true;
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                counter++;
            }
            finish1 = true;
        }).start();

        while (!finish0 || !finish1);
        System.out.println(counter);
    }
}
