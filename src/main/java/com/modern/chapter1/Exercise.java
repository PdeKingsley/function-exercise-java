package com.modern.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class Exercise {
    private static int port = 8080;

    public static void main(String[] args) {
        Exercise exercise = new Exercise();
        int portNumber = 1337;
        Runnable r = () -> System.out.println("Hello, world! I am running on port " + port);
        r.run();
    }

    public <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    private boolean isValidName(String name) {
        return Character.isUpperCase(name.charAt(0));
    }

    private void testLambda(){
        Predicate<String> p = this::isValidName;
        this.filter(new ArrayList<String>(), p);
        comparing(String::length);
    }
}
