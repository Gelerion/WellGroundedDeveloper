package com.denis.golovach.multhithreading.course.lecture_5.myQueue.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingeEementBuffer_Lock {
    private final Lock lock = new ReentrantLock(true);
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private Integer elem;

    public void put(int newElem) throws InterruptedException {
        lock.lock();
        try {
            while (this.elem != null) notFull.await(); //not wait
            this.elem = newElem;
            notEmpty.signal(); //not signalAll
        } finally {
            lock.unlock();
        }
    }

    public boolean tryPut(int newElem) throws InterruptedException {
        if (lock.tryLock()) {
            try {
                while (this.elem != null) notFull.await(); //not wait
                this.elem = newElem;
                notEmpty.signal(); //not signalAll
                return true;
            } finally {
                lock.unlock();
            }
        }
        else {
            return false;
        }
    }

    public boolean tryPut(int newElem, long timeout, TimeUnit units) throws InterruptedException {
        if (lock.tryLock(timeout, units)) {
            try {
                while (this.elem != null) notFull.await(); //not wait
                this.elem = newElem;
                notEmpty.signal(); //not signalAll
                return true;
            } finally {
                lock.unlock();
            }
        }
        else {
            return false;
        }
    }



    public int take() throws InterruptedException {
        lock.lock();
        try {
            while (this.elem == null) notEmpty.await();
            int result = this.elem;
            this.elem = null;
            notFull.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }
}

