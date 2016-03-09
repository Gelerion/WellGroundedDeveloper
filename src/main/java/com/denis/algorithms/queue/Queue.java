package com.denis.algorithms.queue;

public interface Queue<T> {

    /**
     * Inserts element in the rear of the queue
     */
    void insert(T elem);

    T remove();

    //Removes element from front of the queue
    T frontRemove();

    T rearRemove();

    T frontPeek();

    T rearPeek();

    boolean isEmpty();

    boolean isFull();

    int size();

    void display();
}
