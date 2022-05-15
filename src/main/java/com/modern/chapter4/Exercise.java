package com.modern.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Exercise {

    public static void main(String[] args) {
        Exercise exercise = new Exercise();
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        int dishSum = menu.stream().map(d -> 1).reduce(0,Integer::sum);
        int calories = menu.stream().map(Dish::getCalories).max(Integer::compare).get();
        List<String> test =
                menu.stream().dropWhile(dish -> dish.getCalories() < 400).map(Dish::getName).collect(toList());
        System.out.println(test);
        List<String> str = new ArrayList<>();
        str.add("Hello,World");
        System.out.println(str.get(0));
        List<String> test2 = str.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .collect(toList());
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(4, 5);
        List<int[]> test3 = num1.stream()
                .flatMap(i ->
                    num2.stream().map(j -> new int[]{i,j}))
                .filter(n -> (n[0] + n[1]) % 3 == 0)
                .collect(toList());
        System.out.println(test3);
        int sum = num1.stream().reduce(0,(a,b) -> a + b);
    }
}
