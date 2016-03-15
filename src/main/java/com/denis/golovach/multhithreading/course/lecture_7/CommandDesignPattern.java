package com.denis.golovach.multhithreading.course.lecture_7;

/**
 * Command - обарачивание вызова метода методом
 * самый €ркий пример Ctrl+Z
 *
 */
public class CommandDesignPattern {
    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        new Thread(task).start();
    }
}
