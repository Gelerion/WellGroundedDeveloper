package com.denis.golovach.multhithreading.course.lecture_7.invokeAll;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorsInvokeAllBocks {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newCachedThreadPool();

        Callable<Integer> task0 = () -> 42;

        Callable<Integer> task1 = () -> {
            while (true);
        };

//        Integer integer = service.invokeAny(Arrays.asList(task0, task1));
//        System.out.println("integer = " + integer);

        List<Future<Integer>> results = service.invokeAll(Arrays.asList(task0, task1));
        //BLOCK!
        System.out.println(results);
    }
}
