package com.denis.golovach.multhithreading.course.lecture_9.anonymousClassCreation;

import java.util.Optional;
import java.util.stream.Stream;

public class MonoidFlatMap {
    //Моноид:
    // 1) ассоциотивная операция
    // 2) нейтральный элемент
    public static void main(String[] args) {
        Integer sum = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .parallel()
                .reduce(0, (x, y) -> x + y);

        Integer minus = Stream.iterate(1, i -> i + 1)
                .limit(10)
                //вычетание НЕ ассоциативная операция
//                .parallel() //без паральности всё будет работать.
                .reduce(0, (x, y) -> x - y);

        System.out.println("sum = " + sum);
        System.out.println("minus = " + minus);


        //Монада что-то возвращяется, что именно не знаем
        Optional<Integer> monada = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .parallel()
                .reduce((x, y) -> x + y); //Для монады не нужен нейтральный элемент

        System.out.println("monada = " + monada.get());
    }

}
