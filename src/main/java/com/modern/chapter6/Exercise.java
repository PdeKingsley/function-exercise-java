package com.modern.chapter6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Exercise {

    public static void main(String[] args) {
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
        long howManyDishes = menu.stream().collect(counting());
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu);
        Map<Dish.Type,List<String>> dishNamesByType = menu.stream().collect(groupingBy(Dish::getType,mapping(Dish::getName,toList())));
        Map<Dish.Type,List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);
        Map<CaloricLevel,List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        System.out.println(dishesByCaloricLevel);
        Map<String,List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", Arrays.asList("greasy", "salty"));
        dishTags.put("beef", Arrays.asList("salty", "roasted"));
        dishTags.put("chicken", Arrays.asList("fried", "crisp"));
        dishTags.put("french fries", Arrays.asList("greasy", "fried"));
        dishTags.put("rice", Arrays.asList("light", "natural"));
        dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
        dishTags.put("pizza", Arrays.asList("tasty", "salty"));
        dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
        dishTags.put("salmon", Arrays.asList("delicious", "fresh"));
        Map<Dish.Type,Set<String>> tagsByType = menu.stream().collect(
                groupingBy(Dish::getType, flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
        System.out.println(tagsByType);
        Map<Dish.Type,Map<CaloricLevel,List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));
        System.out.println(dishesByTypeCaloricLevel);
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, toSet())));
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            Map<Boolean,List<Integer>> primes =
                    IntStream.rangeClosed(2, 1000000).boxed()
                            .collect(partitioningBy(p -> IntStream.rangeClosed(2, (int) Math.sqrt(p)).noneMatch(j -> p % j == 0)));
        }
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(duration);
        start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            Map<Boolean,List<Integer>> primes =
                    IntStream.rangeClosed(2, 1000000).boxed()
                    .collect(new PrimeNumbersCollector());
        }
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(duration);
    }
}
