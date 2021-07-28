package com.github.dmiller2117.sum;

public class ParallelWorker extends Thread{

    private int[] numbers;
    private int low;
    private int high;
    private int partialSum;

    public ParallelWorker(int[] numbers, int low, int high){
        this.numbers = numbers;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run(){
        partialSum = 0;
        for (int i = low; i < high ; i++) {
            partialSum = partialSum + numbers[i];
        }
    }

    public int getPartialSum(){
        return partialSum;
    }
}