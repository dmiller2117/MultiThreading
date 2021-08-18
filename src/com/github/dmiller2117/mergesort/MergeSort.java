package com.github.dmiller2117.mergesort;

public class MergeSort {
    private final int[] numbers;
    private final int[] tempArray;

    public MergeSort(int[] numbers) {
        this.numbers = numbers;
        this.tempArray = new int[numbers.length];
    }


    public void parallelMergeSort(int low, int high, int numberOfThreads) {
        if (numberOfThreads <= 1) {
            mergeSort(low, high);
            return;
        }

        int middleIndex = (low + high) / 2;

        Thread leftSorter = mergeSortParallel(low, middleIndex, numberOfThreads);
        Thread rightSorter = mergeSortParallel(middleIndex + 1, high, numberOfThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(low, middleIndex, high);
    }

    private Thread mergeSortParallel(int low, int high, int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numOfThreads / 2);
            }
        };
    }


    public void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high) / 2;

        mergeSort(low, middle);
        mergeSort(middle + 1, high);
        merge(low, middle, high);
    }

    public void showResult() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println("");
    }

    void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            tempArray[i] = numbers[i];
        }
        int i = low;
        int j = middle + 1;
        int k = low;

        // copy the smallest value from either the left or right side back to the original array
        while ((i <= middle) && (j <= high)) {
            if (tempArray[i] <= tempArray[j]) {
                numbers[k] = tempArray[i];
                i++;
            } else {
                numbers[k] = tempArray[j];
                j++;
            }
            k++;
        }

        // copy the rest of the left side to the original array
        while (i <= middle) {
            numbers[k] = tempArray[i];
            k++;
            i++;
        }

        // copy the rest of the right side to the original array
        while (j <= high) {
            numbers[k] = tempArray[j];
            k++;
            j++;
        }
    }

    public boolean isSorted() {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return false;
            }
        }
        return true;
    }
}