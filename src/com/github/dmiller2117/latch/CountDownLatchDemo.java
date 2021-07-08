package com.github.dmiller2117.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <h2>CountDown Latches</h2>
 * This is used to synchronize one or more tasks by forcing them to wait for the completion of a set of operations being performed by other tasks.
 * <ul>
 * <li>You give the initial count to a <code>CountDownLatch</code> object, and any task that calls <code>await()</code> on that
 * object will block until the latch reaches zero</li>
 * <li>Other tools may call <code>countDown()</code>on the object to reduce the count, presumably when a task finishes its job</li>
 * <li>The count cannot be rest for a <code>CountDownLatch</code>
 *      <br>If you need a version that resets the count, you can use <code>CyclicBarrier</code>instead</li>
 * <li>The tasks that call <code>countDown()</code> are not blocked when they make that all. <br>Only the call to <code>await()</code>is blocked until the count reaches zero</li>
 * </ul>
 * <p>
 * A typical use is to divide a problem into <strong>N</strong> independently solvable tasks and create a <code>CountDownLatch</code>
 * with a value of <strong>N</strong>
 * <p>When each task is finished it calls <code>countDown()</code>on the latch. Tasks waiting for the problem to be solved call
 * <code>await()</code>on the latch to hold themselves back until it is completed.</p>
 * <p>For example: You want to trigger something something after 10 000 users download an image.</p>
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            singleThreadExecutor.execute(new Worker(i + 1, countDownLatch));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all work done");
        singleThreadExecutor.shutdown();

    }
}

class Worker implements Runnable {

    private final int id;
    private final CountDownLatch countDownLatch;

    public Worker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }

    private void doWork() {
        System.out.println("Thread with id:" + id + " starts working...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}