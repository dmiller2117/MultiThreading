package com.github.dmiller2117.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskApp {

    /*
     * fork():  asynchronously execute the given task in the pool
     *  We can call this on RecursiveAction or RecursiveTask<T>
     *
     * join(): returns the result of the computation when it is done
     *
     * invoke(): execute the given task and return it's result on completion
     */
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        RecursiveTask<Integer> simpleRecursiveTask = new SimpleRecursiveTask(1200);
        System.out.println("Total = " + pool.invoke(simpleRecursiveTask));

    }
}