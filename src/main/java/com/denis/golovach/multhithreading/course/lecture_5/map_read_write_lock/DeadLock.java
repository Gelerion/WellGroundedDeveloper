package com.denis.golovach.multhithreading.course.lecture_5.map_read_write_lock;

import java.util.HashMap;
import java.util.Map;

public class DeadLock {
    //важно что она приватная
    private Map<Integer, String> map = new HashMap<>();

    public synchronized String get(Integer key) {
        return map.get(key);
    }

    public synchronized String put(Integer key, String value) {
        return map.put(key, value);
    }

    public static void main(String[] args) {
        DeadLock d0 = new DeadLock();
        DeadLock d1 = new DeadLock();

        //Dead lock если из разных потоков
        synchronized (d0) {
            synchronized (d1) {
                d0.put(0, "A");
                d1.put(0, "A");
            }
        }

    }
}
