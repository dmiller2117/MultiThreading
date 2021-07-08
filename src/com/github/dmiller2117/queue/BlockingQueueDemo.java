package com.github.dmiller2117.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <h2>BlockingQueue</h2>
 * <p>
 * <code>BlockingQueue</code> -> an interface that represents a queue that is thread safe<br>
 * Put items in to take items from it.
 * </p>
 * <p>For Example: one thread pulling items into the queue and another thread taking away from it at the same time<br>
 * We can do it with the <strong>producer-consumer</strong> pattern</p>
 *
 * <ul>
 *     <li><code>put()</code>putting items to the queue</li>
 *     <li><code>taking()</code>taking items from the queue</li>
 * </ul>
 * <p>Vary the <code>Thread.sleep()</code>times to see how it affects the threads</p>
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        FirstWorker firstWorker = new FirstWorker(blockingQueue);
        SecondWorker secondWorker = new SecondWorker(blockingQueue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}

class FirstWorker implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int counter = 0;
        try {
            for (int i = 0; i < 1000; i++) {
                blockingQueue.put(counter);
                System.out.println("putting the counter on the queue:" + counter);
                counter++;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                int counter = blockingQueue.take();
                System.out.println("taking the counter from the queue:" + counter);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}