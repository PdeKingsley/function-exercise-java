package com.modern.chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Exercise {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        List<Trader> traders = new ArrayList<>();
        traders.add(raoul);
        traders.add(mario);
        traders.add(alan);
        traders.add(brian);
        List<Transaction> res1 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        List<String> res2 = traders.stream()
                .map(t -> t.getCity())
                .distinct()
                .collect(toList());
        List<Trader> res3 = traders.stream()
                .filter(t -> t.getCity() == "Cambridge")
                .sorted(comparing(Trader::getName))
                .collect(toList());
        String res4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        boolean res5 = traders.stream()
                .map(t -> t.getCity())
                .anyMatch(c -> c == "Milan");
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).forEach(System.out::println);
        Optional<Integer> res7 = transactions.stream()
                .map(t -> t.getValue())
                .reduce(Integer::max);
        Optional<Transaction> res8 = transactions.stream()
                .sorted(comparing(Transaction::getValue))
                .findFirst();
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples.forEach(t -> System.out.println(t[0] + " " + t[1] + " " + t[2]));
        Stream<String> values =
                Stream.of("config", "home", "user") .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        values.forEach(System.out::println);
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println(t[0] + " " + t[1]));
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
