package com.denis.golovach.multhithreading.course.lecture_15;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Not th bst way
 * Recursive runnable
 */
public class Recursive_1 {
    public static void main(String[] args) {
        AtomicLong result = new AtomicLong(0);
        new ForkJoinPool().invoke(new Task(0, 1_000_000, result));
        System.out.println(result.get());
    }

    private static class Task extends RecursiveAction {
        private final int from;
        private final int to;
        private final AtomicLong result;

        public Task(int from, int to, AtomicLong result) {
            this.from = from;
            this.to = to;
            this.result = result;
        }

        @Override
        protected void compute() {
            if (to - from < 10_000) {
                result.addAndGet(directCalc());
            }
            else {
                int mid = (from + to) >>> 1;
                Task taskLeft = new Task(from, mid, result);
                Task taskRight = new Task(mid, to, result);
                invokeAll(taskLeft, taskRight);
            }
        }

        private long directCalc() {
            long result = 0;
            for (int index = from; index < to; index++) {
                if (index % 3 != 0 && index % 5 != 0) {
                    result += index;
                }
            }
            return result;
        }
    }
}
