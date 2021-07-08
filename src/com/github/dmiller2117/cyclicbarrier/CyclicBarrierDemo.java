package com.github.dmiller2117.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * A CyclicBarrier is used in situations where you want to create a group of tasks to perform work in parrallel and
 * wait until they are all finished before moving onto the next step:
 *   <ul>
 *       <li>something like a <code>join()</code></li>
 *       <li>something like a CountDownLatch</li>
 *   </ul>
 * <code>CountDownLatch</code>: one-shot event<br>
 * <code>CyclicBarrier</code>: it can be reused over and over again<br>
 * - Plus <code>CyclicBarrier</code> has a barrier action: a runnable that will run automatically when the count reaches
 * 0!!
 * </p>
 * <p>
 *    <code>new CyclicBarrier(N)</code> -> N threads will wait for each other.
 * </p>
 * <p>We can not reuse <code>CountDownLatch</code> but we can reuse <code>CyclicBarrier</code> --> <code>reset()</code></p>
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Last());

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(i, cyclicBarrier));
        }
        executorService.shutdown();
    }
}

class Worker implements Runnable {

    private final int id;
    private final Random random;
    private final CyclicBarrier cyclicBarrier;

    public Worker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID:" + id + " starts the task...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with ID:" + id + " finished");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("after await");
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}

class Last implements Runnable {

    @Override
    public void run() {
        System.out.println("All tasks are finished");
    }
}