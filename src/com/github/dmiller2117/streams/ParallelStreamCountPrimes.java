package com.github.dmiller2117.streams;

import java.util.stream.IntStream;

public class ParallelStreamCountPrimes {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        // sequential stream
        long numPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100).filter(ParallelStreamCountPrimes::isPrime).count();
        System.out.println("Number of primes (sequential) : " + numPrimes);
        System.out.println("Time taken (sequential): " + (System.currentTimeMillis() - startTime));

        // parallel stream
        startTime = System.currentTimeMillis();
        numPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100).parallel().filter(ParallelStreamCountPrimes::isPrime).count();
        System.out.println("Number of primes (parallel) : " + numPrimes);
        System.out.println("Time taken (parallel): " + (System.currentTimeMillis() - startTime));

    }

    public static boolean isPrime(long number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        // we can check the number is in the range [0,sqrt(N)]
        long maxDivisor = (long) Math.sqrt(number);

        for (int i = 3; i < maxDivisor; i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
}