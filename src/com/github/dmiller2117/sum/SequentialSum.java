package com.github.dmiller2117.sum;

public class SequentialSum {

    int sum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum = sum + number;
        }
        return sum;
    }
}