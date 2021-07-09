package com.github.dmiller2117.collections.CopyOnWriteArray;

import java.util.List;
import java.util.Random;

public class WriteTask implements Runnable {
    private final List<Integer> list;
    private final Random random;

    public WriteTask(List<Integer> list) {
        this.list = list;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            try {
                Thread.sleep(100);
                list.set(random.nextInt(list.size()), random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}