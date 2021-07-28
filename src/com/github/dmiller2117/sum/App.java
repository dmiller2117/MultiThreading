package com.github.dmiller2117.sum;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        Random random = new Random();
        int[] numbers = new int[100000000];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }

        int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("number of processors: " + numberOfProcessors);
        long start = System.currentTimeMillis();

        SequentialSum sequentialSum = new SequentialSum();

        System.out.println(sequentialSum.sum(numbers));

        System.out.println("Sequential sum takes: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();

        ParallelSum parallelSum = new ParallelSum(numberOfProcessors);
        System.out.println("Sum is : " + parallelSum.sum(numbers));
        System.out.println("Parallel sum takes: " + (System.currentTimeMillis() - start) + "ms");
    }
}