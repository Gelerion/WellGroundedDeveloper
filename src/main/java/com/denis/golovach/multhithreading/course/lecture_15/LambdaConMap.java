package com.denis.golovach.multhithreading.course.lecture_15;

import java.util.concurrent.ConcurrentHashMap;

public class LambdaConMap {
    public static void main(String[] args) {
        ConcurrentHashMap<Long, Long> map = new ConcurrentHashMap<>();
        int cpu = Runtime.getRuntime().availableProcessors();

        Long search = map.search(cpu, (key, value) -> key.equals(value) ? key : null);

        Long aLong = map.reduceKeys(cpu, (key1, key2) -> key1 + key2);
    }
}
