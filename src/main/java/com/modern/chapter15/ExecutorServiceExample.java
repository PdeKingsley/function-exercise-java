package com.modern.chapter15;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1337;
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        Future<Integer> future = executorService.submit(() -> f(x));
        Future<Integer> future2 = executorService.submit(() -> g(x));
        System.out.println(future.get() + " " + future2.get());
        executorService.shutdown();
    }

    private static int f(int x) {
        return x * x;
    }

    private static int g(int x) {
        return x * x * x;
    }
}
