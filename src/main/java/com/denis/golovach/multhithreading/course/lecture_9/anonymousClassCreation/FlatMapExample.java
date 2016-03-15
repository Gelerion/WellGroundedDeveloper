package com.denis.golovach.multhithreading.course.lecture_9.anonymousClassCreation;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FlatMapExample {
    public static void main(String[] args) {
        //Ничего не принимает а что то произвоидт
        Supplier<Double> d = () -> Math.random();
        Supplier<Double> d1 = Math::random;

        //Только потребляет
        Consumer<String> c = (s) -> Math.random(); //NO return

        //Берёт String возвращяет Integer
        Function<String, Integer> f = (s) -> s.length();
        Function<String, Integer> f1 = String::length;

        Predicate<Double> p = arg -> arg > 1;

        //Хочу сделать разбивку:
        //"1" -> "1"
        //"2 33" -> "2","33"
        //"4 55 666" -> "4", "55", "666"
        List<String> list = Arrays.asList("1", "2 33", "4 55 666");

        //flatMap может менять ТИП и КОЛЛИЧЕСТВО
        //из любого T делает Stream типа R
        Function<String, Stream<String>> fs = s -> {
            return Stream.of(s);
        };

        Function<String, Stream<String>> fs1 = s -> Arrays.asList(s.split(" ")).stream();
        list.stream().flatMap(fs1).forEach(System.out::println);
    }
}



































