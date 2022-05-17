package com.modern.chapter15;

import java.util.function.IntConsumer;

public class CallbackStyleExample {
    public static void main(String[] args) throws InterruptedException {
        int x = 1337;
        Result result = new Result();
        f(x, (int y) -> {
            result.left = y;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(result.left + result.right);
        });
        g(x, (int y) -> {
            result.right = y * y;
            System.out.println(result.left + result.right);
        });
    }

    static void f(int x, IntConsumer dealWithResult) {
        dealWithResult.accept(x);
    }

    static void g(int x, IntConsumer dealWithResult){
        dealWithResult.accept(x);
    }

    private static class Result{
        private int left;
        private int right;
    }
}
