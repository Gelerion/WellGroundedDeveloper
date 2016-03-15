package com.denis.golovach.multhithreading.course.lecture_11;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ComplFutureExample {
    public static void main(String[] args) {
        CompletableFuture<String> f0 = CompletableFuture.supplyAsync(() -> {
            int k = 0;
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                k = i;
            }
            return "42";
        });

        CompletableFuture<Integer> f1 = f0.thenApply(Integer::valueOf);
        CompletableFuture<Double>  f2 = f1.thenApply(x -> Math.PI * x * x);
        f2.thenAccept(System.out::println);

        System.out.println("end");
    }
}

class Second {

    public static void main(String[] args) throws IOException {
        CompletableFuture<String> f0 = CompletableFuture.supplyAsync(() -> {
            sleep(3000);
            return "A";
        });

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "B";
        });

        //first of them print
//        f0.acceptEitherAsync(f1, System.out::println);

        //wait for both
        f0.thenAcceptBothAsync(f1, (a, b) -> System.out.println(a + "#" + b));
        System.in.read();

    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ignore) {
            /*NOP*/
        }
    }
}
