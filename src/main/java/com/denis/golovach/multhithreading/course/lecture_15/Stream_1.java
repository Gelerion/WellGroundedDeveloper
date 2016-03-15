package com.denis.golovach.multhithreading.course.lecture_15;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Stream_1 {
    public static void main(String[] args) {
        { //good
            long result = LongStream.range(0, 1_000_000)
                    .parallel()
                    .filter(x -> x % 3 != 0)
                    .filter(x -> x % 5 != 0)
                    //.filter(x -> (x % 3 != 0) & (x % 5 != 0))
                    .sum();
        }

        { //bad: low speed generation + boxing/unboxing
            long result = Stream.iterate(0, k -> k + 1)
                    .parallel()
                    .filter(x -> x % 3 != 0)
                    .filter(x -> x % 5 != 0)
                    .reduce(0, (x, y) -> x + y);

        }
    }
}
