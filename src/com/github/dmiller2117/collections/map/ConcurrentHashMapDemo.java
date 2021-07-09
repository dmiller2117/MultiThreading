package com.github.dmiller2117.collections.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 *
 * <h2>ConcurrentHashMap</h2>
 * We can make a map synchronized with defining sections of the underlying array.<br>
 * These segments(16 items) can be updated only by a single thread.<br>
 * We assign a lock to every segment instead of using a single lock?<br>
 * Every thread can read any item from the underlying array without restrictions<br>
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentMap.html">https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentMap.html</a><br>
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html">https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html</a>
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        new Thread(new MapProducer(concurrentMap)).start();
        new Thread(new MapConsumer(concurrentMap)).start();
    }

}

class MapProducer implements Runnable {
    private final ConcurrentMap<String, Integer> concurrentMap;

    public MapProducer(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {
        try {
            concurrentMap.put("B", 1);
            concurrentMap.put("H", 2);
            TimeUnit.SECONDS.sleep(1);
            concurrentMap.put("F", 3);
            concurrentMap.put("A", 4);
            concurrentMap.put("E", 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MapConsumer implements Runnable {
    private final ConcurrentMap<String, Integer> concurrentMap;

    public MapConsumer(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(concurrentMap.get("A"));
            System.out.println(concurrentMap.get("E"));
            System.out.println(concurrentMap.get("F"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}