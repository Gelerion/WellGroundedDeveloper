package com.denis.golovach.multhithreading.course.lecture_12_akka.map_reduce;

public interface Reducer<T> {
    T reduce(T left, T right);
}
