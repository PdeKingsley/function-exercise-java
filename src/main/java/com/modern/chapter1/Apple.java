package com.modern.chapter1;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Apple {
    private String color;
    private double weight;

    public Apple(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleConsumer consumer) {
        for (Apple apple : inventory) {
            consumer.apply(apple);
        }
    }

    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
