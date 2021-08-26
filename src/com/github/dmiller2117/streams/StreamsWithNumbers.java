package com.github.dmiller2117.streams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamsWithNumbers {

    public static void main(String[] args) {
        int[] nums = {1, 7, -9, 0, 4, 12};
        printArrayWithForEach(nums);

        printSumOfArrayWithForEach(nums);

        // Streams API
        // :: operator for method reference
        Arrays.stream(nums).forEach(System.out::println);
        System.out.println();
        System.out.println("Sum via Stream API = " + Arrays.stream(nums).sum());
        System.out.println();
        // Lambda expression
        IntStream.range(0, 10).filter(x -> x % 2 == 0).forEach(x -> System.out.print(x + " "));
    }

    private static void printArrayWithForEach(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
        System.out.println();
    }

    private static void printSumOfArrayWithForEach(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        System.out.println("Sum with for each= " + sum);
        System.out.println();
    }
}