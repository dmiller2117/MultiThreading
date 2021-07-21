package com.github.dmiller2117.librarySim;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

    private final int id;
    private final Lock lock;

    public Book(int id) {
        this.id = id;
        lock = new ReentrantLock();
    }

    public void read(Student student) throws InterruptedException {
        // if (lock.tryLock(10L, TimeUnit.MILLISECONDS))  if using tryLock then we need to use its boolean return value
        lock.lock();
        System.out.println(student + " starts reading " + this);
        Random random = new Random();
        Thread.sleep(random.nextInt(1000));
        lock.unlock();
        System.out.println(student + " had finished reading " + this);
    }

    @Override
    public String toString() {
        return " Book #" + id;
    }
}