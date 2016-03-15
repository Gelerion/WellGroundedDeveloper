package com.denis.algorithms.queue.test;

import com.denis.algorithms.queue.array.ArrayBasedPriorityQueue;

public class TestPriorityQueue {
    public static void main(String[] args) {
        ArrayBasedPriorityQueue queue = new ArrayBasedPriorityQueue(10);

        queue.insert0(567);
        queue.display();
        queue.insert0(423);
        queue.insert0(133);
        queue.insert0(131);
        queue.insert0(123);
        queue.display();

        queue.insert0(300);
        queue.display();

        queue.insert0(132);
        queue.display();

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        queue.display();

        queue.insert0(999);
        queue.display();
        queue.insert0(888);
        queue.insert0(777);
        queue.insert0(666);
        queue.insert0(555);
        queue.display();

        queue.insert0(987);
        queue.display();

        queue.insert0(767);
        queue.display();
    }
}
