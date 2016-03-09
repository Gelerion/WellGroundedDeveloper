package com.denis.algorithms.queue.test;

import com.denis.algorithms.queue.Queue;
import com.denis.algorithms.queue.array.ArrayQueueSize;

public class TestArrayNoSize {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueueSize<>(10);

        System.out.println("=== Inset 1-2-3 elems ===");
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.display();
        System.out.println();

        System.out.println("=== Insert 4 to 10 elems ===");
        for (int i = 4; i <= 10; i++) {
            queue.insert(i);
        }
        queue.display();
        System.out.println();


        System.out.println("=== Remove first 3 elems ===");
        queue.remove();
        queue.remove();
        queue.remove();
        queue.display();
        System.out.println();


        System.out.println("=== Inset 10-11-12 elems ===");
        queue.insert(10);
        queue.insert(11);
        queue.insert(12);
        queue.display();
        System.out.println();

    }
}
