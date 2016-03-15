package com.denis.golovach.multhithreading.course.lecture_9.anonymousClassCreation;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class Java8Anonymous {

    public static void main(String[] args) {


        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        //Этот вызов могу заменить на следующий:
        //Указываю имя и тип после стрелочки укзываю тело
        Consumer<String> c1 = (String s) -> { System.out.println(s); };
        //Можно даже убрать скобки
        Consumer<String> c3 = s -> { System.out.println(s); };
        //OR
        Consumer<String> c2 = System.out::println;

        Stream.iterate(0L, k -> k + 1)
                .parallel()
                .filter(k -> k % 3 == 2)
                .map(k -> "~" + k + "~")
                .limit(10)
                .forEach(consumer);
//                .forEach(s -> System.out.println(Thread.currentThread()));


        Test test = (int x, int y) -> { return x + y; };
        //OR
        Test test1 = (int x, int y) -> x + y;
        //поскольку  интерфейса указаны типо то могу сделать так
        Test test2 = (x, y) -> x + y;

        //!!!! НЕ существует ТИПА у лямбды.
        //Тип приходит только тогда когда ее подгоняют под функциональный интрефейс
//        System.out.println((x, y) -> x + y);
        System.out.println((Test)(x, y) -> x + y);

        //так не работает нужно указать тип
//        TestGeneric testGeneric = (x, y) -> x + y;
        //type inference
        TestGeneric<Integer> testGenericInt = (x, y) -> x + y;

    }

    interface Test {
        int add(int k, int p);
    }

    interface TestGeneric<T> {
        T add(T k, T p);
    }
}
