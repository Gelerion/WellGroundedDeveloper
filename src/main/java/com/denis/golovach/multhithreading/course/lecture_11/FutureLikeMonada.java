package com.denis.golovach.multhithreading.course.lecture_11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.*;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class FutureLikeMonada {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = newCachedThreadPool();

        Future<byte[]> futBytes0 = pool.submit(() -> readAllBytes(get("d:/tmp0.txt")));
        Future<byte[]> futBytes1 = pool.submit(() -> readAllBytes(get("d:/tmp1.txt")));
        Future<byte[]> futBytes2 = pool.submit(() -> readAllBytes(get("d:/tmp2.txt")));

        System.out.println(futBytes0.isDone());
        byte[] bytes = futBytes0.get();
        System.out.println(futBytes0.isDone());
        System.out.println(Arrays.toString(bytes));

        //Blocks until ALL of the futures are return
        List<Future<Object>> futureList = pool.invokeAll(asList(
                () -> readAllBytes(get("d:/tmp0.txt")),
                () -> readAllBytes(get("d:/tmp1.txt")),
                () -> readAllBytes(get("d:/tmp2.txt"))
        ));


        System.out.println(futureList.get(0).isDone());
        System.out.println(futureList.get(1).isDone());
        System.out.println(futureList.get(2).isDone());

        //return first DONE
        byte[] anyBytes = pool.invokeAny(asList(
                () -> readAllBytes(get("d:/tmp0.txt")),
                () -> readAllBytes(get("d:/tmp1.txt")),
                () -> readAllBytes(get("d:/tmp2.txt"))
        ));
    }

}
