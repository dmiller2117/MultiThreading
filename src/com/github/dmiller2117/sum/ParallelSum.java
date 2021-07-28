package com.github.dmiller2117.sum;

public class ParallelSum {
    private ParallelWorker[] workers;

    private int numberOfThreads;

    public ParallelSum(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        this.workers = new ParallelWorker[numberOfThreads];
    }

    public int sum(int[] numbers) {
        int steps = (int) Math.ceil(numbers.length * 1.0 / numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            workers[i] = new ParallelWorker(numbers, i * steps, (i + 1) * steps);
            workers[i].start();
        }
        try {
            for (ParallelWorker parallelWorker : workers) {
                parallelWorker.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sum = 0;
        for (ParallelWorker parallelWorker : workers) {
            sum = sum + parallelWorker.getPartialSum();
        }
        return sum;
    }
}