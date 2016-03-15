package com.denis.golovach.multhithreading.course.lecture_8;

import java.util.stream.Stream;

public class StreamIterator {
    public static void main(String[] args) {
        //infinity
        //does NOT compile with Long because of generics
        Stream<Integer> stream = Stream.iterate(2, k -> k + 3);
        //Если вызову forEach() то программа зависнет  limit() лимитирую стрим
        stream
                .limit(10)
                .forEach(System.out::println);

        //same
        Stream
                .iterate(0, k -> k + 1)
                .filter(k -> k % 3 == 2)
                .limit(10)
                .forEach(System.out::println);

        Stream
                .iterate(0, k -> k + 1)
                .filter(k -> k % 3 == 2)
                //хочу пробразовать просто так
                .map(k -> "~" + k)
                .limit(10)
                .forEach(System.out::println);


    }
}
