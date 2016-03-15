package com.denis.golovach.multhithreading.course.lecture_12_akka.map_reduce;

public interface Mapper<T, R> {
    R map(T arg);
}
