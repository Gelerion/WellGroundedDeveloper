package com.denis.golovach.multhithreading.course.lecture_5.map_read_write_lock;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class My_Cache {

    public <K,V> Map<K, V> synchronizedMap(Map<K, V> m) {
        return new MyMap<>(m);
    }

    private static class MyMap<T0, T1> implements Map<T0, T1> {
        private ReadWriteLock rwLock = new ReentrantReadWriteLock();

        //Возвращяет оин и тот же объект
        //множество потоков может захватить эту блокировку
        private Lock rLock = rwLock.readLock();
        //только один поток может ею обладать
        private Lock wLock = rwLock.writeLock();

        private final Map<T0, T1> m;

        /**
         * Нужно быть внимательными!
         * В документации должно юыть сказанно что методы которые на чтение
         * например size не должны в принципе менять никакую память
         */

        private MyMap(Map<T0, T1> m) {
            this.m = m;
        }

        @Override
        public int size() {
            rLock.lock();
            try {
                return m.size();
            } finally {
                rLock.unlock();
            }
        }

        @Override
        public boolean isEmpty() {
            rLock.lock();
            try {
                return m.isEmpty();
            } finally {
                rLock.unlock();
            }
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public T1 get(Object key) {
            return null;
        }

        @Override
        public T1 put(T0 key, T1 value) {
            wLock.lock();
            try {
                return m.put(key, value);
            } finally {
                wLock.unlock();
            }
        }

        @Override
        public T1 remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends T0, ? extends T1> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<T0> keySet() {
            return null;
        }

        @Override
        public Collection<T1> values() {
            return null;
        }

        @Override
        public Set<Entry<T0, T1>> entrySet() {
            return null;
        }
    }
}

/**
 * напечатает оба, не является мютексом
 * Но! если объект захвачен на чтение то его нельзя менять
 */

class Test {
    public static void main(String[] args) {
        ReadWriteLock rw = new ReentrantReadWriteLock();
        Lock r = rw.readLock();
        Lock w = rw.writeLock();

        new Thread(() -> { //R
            r.lock();
            System.out.println("0");
            while (true){}
        }).start();

        new Thread(() -> { //R
            r.lock();
            System.out.println("1");
            while (true){}
        }).start();

        //НЕ будет напечатанно!!
        new Thread(() -> { //W
            w.lock();
            System.out.println("2");
            while (true){}
        }).start();
    }
}

