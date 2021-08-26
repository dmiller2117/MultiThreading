package com.github.dmiller2117.streams;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamsWithStrings {

    public static void main(String[] args) {
        String[] names = {"Adam", "Daniel", "Martha", "David", "Tracey", "Libby", "Maisy"};
        Stream.of(names).forEach(System.out::println);
        System.out.println();
        Stream.of(names).sorted().forEach(System.out::println);
        System.out.println();
        Stream.of(names).sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println();
        Stream.of(names).filter(x -> x.startsWith("D")).forEach(System.out::println);
    }
}