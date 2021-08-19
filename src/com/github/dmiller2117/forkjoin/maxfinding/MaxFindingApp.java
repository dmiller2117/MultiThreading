package com.github.dmiller2117.forkjoin.maxfinding;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class MaxFindingApp {
    public static int THRESHOLD = 0;

    public static void main(String[] args) {
        int[] nums = initialiseNums(300000000);
        int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        THRESHOLD = nums.length / numberOfProcessors;
        SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();
        long start = System.currentTimeMillis();
        System.out.printf("Max: %d", sequentialMaxFinding.sequentialMaxFinding(nums, nums.length));
        System.out.println();
        System.out.printf("Time taken sequential approach: %dms", (System.currentTimeMillis() - start));
        System.out.println();
        System.out.println();

        ForkJoinPool pool = new ForkJoinPool(numberOfProcessors);
        ParallelMaxFinding parallelMaxFinding = new ParallelMaxFinding(nums, 0, nums.length);
        start = System.currentTimeMillis();
        System.out.printf("Max: %d", pool.invoke(parallelMaxFinding));
        System.out.println();
        System.out.printf("Time taken with parallel approach: %dms", (System.currentTimeMillis() - start));
    }

    private static int[] initialiseNums(int size) {
        Random random = new Random();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt(1000);
        }
        return nums;
    }
}