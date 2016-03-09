package com.denis.algorithms.queue.array;

import com.denis.algorithms.queue.Queue;

/**
 * FIFO
 * CYCLIC BUFFER
 */
@SuppressWarnings("all")
public class ArrayQueue<T> implements Queue<T> {
    private Object[] data;
    private int size;
    private int frontIndex;
    private int rearIndex;
    private int maxSize;

    public ArrayQueue(int maxSize) {
        this.data = new Object[maxSize];
        this.size = 0;
        this.frontIndex = 0;
        this.rearIndex = -1;
        this.maxSize = maxSize;
    }

    @Override
    public void insert(T elem) {
        if (isFull()) {
            System.out.println("[ERROR] Can not insert [" + elem + "], queue is full");
            return;
        }
        //cyclic
        if (rearIndex == maxSize - 1) rearIndex = -1;

        data[++rearIndex] = elem;
        size++;
    }

    @Override
    public T remove() {
        return frontRemove();
    }

    @Override
    public T frontRemove() {
        if(isEmpty()) {
            System.out.println("[ERROR] Can not remove queue is empty ==");
            return null;
        }

        if(frontIndex == maxSize) frontIndex = 0;

        size--;
        return (T) data[frontIndex++];
    }

    @Override
    public T rearRemove() {
        if(isEmpty()) {
            System.out.println("[ERROR] Can not remove queue is empty ==");
            return null;
        }

        if(rearIndex == -1) rearIndex = maxSize - 1;

        size--;
        return (T) data[rearIndex--];
    }

    @Override
    public T frontPeek() {
        return (T) data[frontIndex];
    }

    @Override
    public T rearPeek() {
        return (T) data[rearIndex];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return size == maxSize;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        System.out.println("----- Display -----");
        System.out.println("Queue size: " + size);
        System.out.println("Queue max size: " + maxSize);
        System.out.println("Rear index: " + rearIndex);
        System.out.println("Front index: " + frontIndex);
        System.out.println("---------------");

        for (int i = maxSize - 1; i > -1; i--) {
            if (rearIndex == frontIndex && frontIndex == i) {
                System.out.println("[" + data[i] + "] <-- Front/Rear");
            }
            else if(rearIndex == i) {
                System.out.println("[" + data[i] + "] <-- Rear");
            }
            else if (frontIndex == i) {
                System.out.println("[" + data[i] + "] <-- Front");
            }
            else
                System.out.println("[" + data[i] + "]");
        }
        System.out.println("---------------");
    }
}
