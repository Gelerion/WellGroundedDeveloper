package com.denis.golovach.multhithreading.course.lecture_2;

public class App01_method_variables {
    static int run = 0;

    public static void main(String[] args) {
        run = 1;

        //программи ГАРАНТИРОВАННО напечатает 1
        //действие которое стартует поток всегда синхронизированно
        new Thread(() ->
                System.out.println(run))
                .start();

        //run = 2 -> тут уже не гарантированно
    }
}
