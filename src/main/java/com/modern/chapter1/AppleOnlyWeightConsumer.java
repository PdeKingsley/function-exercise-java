package com.modern.chapter1;

public class AppleOnlyWeightConsumer implements AppleConsumer {
    @Override
    public void apply(Apple apple) {
        System.out.println("Apple weight: " + apple.getWeight());
    }
}
