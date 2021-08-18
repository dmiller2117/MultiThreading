package com.github.dmiller2117.mergesort;

import java.util.Random;

public class App {
    private static final Random random = new Random();

    public static void main(String[] args) {

        int numberOfThreads = Runtime.getRuntime().availableProcessors();

        int size = 100000000;
        int[] numbers = createRandomArray(size);

        MergeSortParallel mergeSort = new MergeSortParallel(numbers);

        long startTime = System.currentTimeMillis();
        mergeSort.parallelMergeSort(0, numbers.length - 1, numberOfThreads);
        long endTime = System.currentTimeMillis();
        System.out.printf("time taken for %d elements parallel = %d", size, endTime - startTime);

        startTime = System.currentTimeMillis();
        mergeSort.mergeSort(0, numbers.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println();
        System.out.printf("time taken for %d elements sequential = %d", size, endTime - startTime);
    }

    private static int[] createRandomArray(int size) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(size);
        }
        return a;
    }
}