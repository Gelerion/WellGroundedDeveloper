package com.denis.algorithms.queue.test;

import com.denis.algorithms.queue.array.ArrayQueue;

public class TestArrayQueue {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(10);

        System.out.println("=== Inset 1-2-3 elems ===");
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.display();
        System.out.println();

        System.out.println("=== Remove first 3 elems ===");
        queue.remove();
        queue.remove();
        queue.remove();
        queue.display();
        System.out.println();

        System.out.println("=== Insert 1 elems ===");
        queue.insert(4);
        queue.display();
        System.out.println();

        System.out.println("=== Insert 5 to 13 elems ===");
        for (int i = 5; i <= 14; i++) {
            queue.insert(i);
        }
        queue.display();

        System.out.println("=== Remove all elements ===");
        for (int i = 0; i < 10; i++) {
            Integer elem = queue.remove();
            System.out.println("Removed: " + elem);
        }
        queue.display();

        System.out.println("=== Insert 4 elems ===");
        queue.insert(14);
        queue.insert(15);
        queue.insert(16);
        queue.insert(17);
        queue.display();
        System.out.println();

        Integer frontPeek = queue.frontPeek();
        System.out.println("frontPeek = " + frontPeek);
        Integer rearPeek = queue.rearPeek();
        System.out.println("rearPeek = " + rearPeek);

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.rearRemove());
        }
        queue.display();
    }
}
