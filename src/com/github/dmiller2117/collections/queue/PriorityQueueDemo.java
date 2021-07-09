package com.github.dmiller2117.collections.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <h2>PriorityBlockingQueue</h2>
 * <p>It implements the <code>BlockingQueue</code> interface
 * <ul>
 *     <li>unbounded concurrent queue</li>
 *     <li>it uses the same ordering rules as the <code>java.util.PriorityQueue</code> class -> meaning you need to
 *     implement the <code>Comparable</code> interface
 *          <ul>
 *              <li>The <code>Comparable</code> interface will define the order in the queue</li>
 *          </ul></li>
 *     <li>no null items</li>
 * </ul></p>
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<Person> blockingQueue = new PriorityBlockingQueue<>();

        new Thread(new PutWorker(blockingQueue)).start();
        new Thread(new TakeWorker(blockingQueue)).start();
    }

}

class PutWorker implements Runnable {

    private final BlockingQueue<Person> blockingQueue;

    public PutWorker(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put(new Person(19, "Bob"));
            blockingQueue.put(new Person(56, "Tam"));
            blockingQueue.put(new Person(7, "Wullie"));
            TimeUnit.SECONDS.sleep(3);
            blockingQueue.put(new Person(45, "Wee Eck"));
            blockingQueue.put(new Person(55, "Soapy"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TakeWorker implements Runnable {

    private final BlockingQueue<Person> blockingQueue;

    public TakeWorker(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Person implements Comparable<Person> {
    private final int age;
    private final String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        return name.compareTo(anotherPerson.getName());
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}