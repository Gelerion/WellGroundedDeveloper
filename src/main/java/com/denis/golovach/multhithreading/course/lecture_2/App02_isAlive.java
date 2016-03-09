package com.denis.golovach.multhithreading.course.lecture_2;

public class App02_isAlive {
    static boolean run = true;

    public static void main(String[] args) {
        Thread newThread = new Thread(() ->
                run = false);

        newThread.start();

        while (newThread.isAlive());
//        newThread.join();

        //Здест ВСЕГДА будет false
        //спецификация гарантирует что если прошлый поток
        //заверишлся то тут будут вины его изменения
        while (run);
        //Выдаёт количество ядер
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().maxMemory());

    }
}
