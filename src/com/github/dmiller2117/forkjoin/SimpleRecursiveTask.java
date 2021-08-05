package com.github.dmiller2117.forkjoin;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {

    private Integer simulatedWork;

    public SimpleRecursiveTask(Integer simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected Integer compute() {
        if (simulatedWork > 100) {
            System.out.println("Simulated work large enough for parallel execution: " + simulatedWork);
            SimpleRecursiveTask task1 = new SimpleRecursiveTask(simulatedWork / 2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(simulatedWork / 2);

            task1.fork();
            task2.fork();

            int solution = 0;
            solution += task1.join();
            solution += task2.join();
            return solution;
        } else {
            System.out.println("No need for parallel execution: " + simulatedWork);
            return simulatedWork * 2;
        }
    }
}