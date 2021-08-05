package com.github.dmiller2117.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RecursiveActionApp {

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

        RecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(120);
        pool.invoke(simpleRecursiveAction);

    }
}