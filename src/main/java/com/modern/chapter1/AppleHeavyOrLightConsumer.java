package com.modern.chapter1;

public class AppleHeavyOrLightConsumer implements AppleConsumer{
    @Override
    public void apply(Apple apple) {
        if(apple.getWeight() > 150){
            System.out.println(apple.toString() + "heavy apple");
        }else{
            System.out.println(apple.toString() + "light apple");
        }
    }
}
