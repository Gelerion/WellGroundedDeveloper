package com.denis.golovach.multhithreading.course.lecture_8;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsFlow {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        Stream<Integer> intStream = set.stream();
        Stream<String> stringStream = intStream.map(v -> "~~~~" + v);
        List<String> list = stringStream.collect(Collectors.toList());

        System.out.println("list = " + list);

        Iterator<Long> iterator = new Iterator<Long>() {
            private long current = 0;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Long next() {
                return current++;
            }

        };

    }

}
