package com.github.dmiller2117.forkjoin.maxfinding;

public class SequentialMaxFinding {

    // 0(n) linear
    public int sequentialMaxFinding(int[] numbers, int highIndex) {
        int max = numbers[0];

        for (int i = 1; i < highIndex; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }
}