package com.modern.chapter8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise {
    public static void main(String[] args) {
        List<String> friends = List.of("Bob", "Alice", "John", "Mary");
        List<String> friends2 = new ArrayList<>(friends);
        List<String> friends3 = List.copyOf(friends);
        friends2.add("Mike");
        System.out.println(friends);
        System.out.println(friends2);
        Map<String,Integer> ages = Map.of("Bob", 20, "Alice", 25, "John", 30, "Mary", 35);
        Map<String,Integer> ages2 = Map.ofEntries(Map.entry("Bob", 20), Map.entry("Alice", 25), Map.entry("John", 30), Map.entry("Mary", 35));
        ages2.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
        Map<String,Integer> f = new HashMap<>(ages);
        f.computeIfPresent("Bob", (k,v) -> null);
        System.out.println(f);
        Map<String,String> family = Map.ofEntries(Map.entry("Teo","Star Wars"), Map.entry("Cristina", "James Bond"));
        Map<String,String> friend = Map.ofEntries(Map.entry("Te","Star Wars"), Map.entry("Cristina", "Matrix"));
        Map<String,String> combine = new HashMap<>(family);
        combine.putAll(friend);
        System.out.println(combine);
    }
}
