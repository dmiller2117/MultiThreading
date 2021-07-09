package com.github.dmiller2117.collections.CopyOnWriteArray;

import java.util.List;

public class ReadTask implements Runnable {
    private final List<Integer> list;

    public ReadTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            try {
                Thread.sleep(100);
                System.out.println(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}