package com.denis.golovach.multhithreading.course.lecture_11;

import java.util.Optional;

import static java.util.stream.Stream.of;

/**
 * Редукция не на моноиде а исключительно на ассоциативной операции
 * необходимо приводит к монаде Optional/ Maybe
 */
public class BasicConcepts {

    public static void main(String[] args) {
        //Редкция на моноиде
        //ассоциативный оператор + нейтральный элемент
        Integer monoidSum = of(1, 2, 3).reduce(0, (x, y) -> x + y);
        System.out.println("monoidSum = " + monoidSum);

        //МОНАДА: Optional / Just
        //Редукция на ассоциативном операторе
        Optional<Integer> monadaJust = of(1, 2, 3).reduce((x, y) -> x + y);
        System.out.println("monadaJust = " + monadaJust);

        //МОНАДА: Optional / NOTHING
        //Редукция на ассоциативном операторе
        Optional<Integer> monadaNothing = of(1, 2, 3)
                .filter(x -> x > 10)
                .reduce((x, y) -> x + y);
        System.out.println("monadaNothing = " + monadaNothing);

        {
            Optional<Integer> sum = of(1, 2, 3).reduce((x, y) -> x + y);
            Optional<Integer> sqrt = sum.map(x -> x * x);
            Optional<String> str = sqrt.map(x -> "#" + x);
            System.out.println(str);

        }
    }


}
