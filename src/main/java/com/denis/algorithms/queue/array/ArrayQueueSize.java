package com.denis.algorithms.queue.array;

import com.denis.algorithms.queue.Queue;

@SuppressWarnings("all")
public class ArrayQueueSize<T> implements Queue<T> {
    private Object[] data;
    private int front;
    private int rear;
    private int maxSize;

    public ArrayQueueSize(int size) {
        data = new Object[size];
        maxSize = size;
        front = 0;
        rear = -1;
    }

    @Override
    public void insert(T elem) {
        if(rear == maxSize -1) rear = -1;
        data[++rear] = elem;
    }

    @Override
    public T remove() {
        if(front == maxSize) front = 0;
        return (T) data[front++];
    }

    @Override
    public T frontRemove() {
        return null;
    }

    @Override
    public T rearRemove() {
        return null;
    }

    @Override
    public T frontPeek() {
        return null;
    }

    @Override
    public T rearPeek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        int result = 0;
        if (rear > front) {
            result = rear - front + 1;
        }


        return result;
    }

    @Override
    public void display() {
        System.out.println("----- Display -----");
        System.out.println("Queue size: " + size());
        System.out.println("Queue max size: " + maxSize);
        System.out.println("Rear index: " + rear);
        System.out.println("Front index: " + front);
        System.out.println("---------------");

        for (int i = maxSize - 1; i > -1; i--) {
            if (rear == front && front == i) {
                System.out.println("[" + data[i] + "] <-- Front/Rear");
            }
            else if(rear == i) {
                System.out.println("[" + data[i] + "] <-- Rear");
            }
            else if (front == i) {
                System.out.println("[" + data[i] + "] <-- Front");
            }
            else
                System.out.println("[" + data[i] + "]");
        }
        System.out.println("---------------");
    }
}
