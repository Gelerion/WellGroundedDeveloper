package com.denis.golovach.multhithreading.course.lecture_15;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Recursive callable
 * no shared memory
 */
public class Recursive_2 {
    public static void main(String[] args) {
        long result = 0;
        result = new ForkJoinPool().invoke(new Task(0, 1_000_000));
        System.out.println(result);
    }

    private static class Task extends RecursiveTask<Long> {
        private final int from;
        private final int to;

        public Task(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            //small task
            if (to - from < 10_000) {
                return directCalc();
            }
            else {
                int mid = (from + to) >>> 1;
                Task taskLeft = new Task(from, mid);
                Task taskRight = new Task(mid, to);
                //async start of thread
                taskLeft.fork(); //start
                taskRight.fork(); //start
                return taskLeft.join() + taskRight.join(); //+ , * , max, min
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

