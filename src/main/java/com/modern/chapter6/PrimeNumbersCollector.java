package com.modern.chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>,Map<Boolean,List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean,List<Integer>>(){
            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean,List<Integer>> acc,Integer candidate) -> {
            acc.get(isPrime(acc.get(true),candidate)).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean,List<Integer>> acc1, Map<Boolean,List<Integer>> acc2) -> {
            acc1.get(true).addAll(acc2.get(true));
            acc1.get(false).addAll(acc2.get(false));
            return acc1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    private boolean isPrime(List<Integer> primes,int candidate){
        int candidateRoot = (int)Math.sqrt(candidate);
        return primes.stream()
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(p -> candidate % p == 0);
    }
}
