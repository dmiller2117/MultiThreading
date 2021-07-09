package com.github.dmiller2117.collections;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * <h2>Exchanger</h2>
 * <p>With the help of <code>Exchanger</code> 2 threads can exchange objects<br>
 * <code>exchange()</code> -> exchanging objects is done via one of the two <code>exchange()</code> methods</p>
 */
public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(new FirstThread(exchanger)).start();
        new Thread(new SecondThread(exchanger)).start();
    }

}

class FirstThread implements Runnable {

    private int counter;
    private final Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("FirstThread increments:" + counter++);
            try {
                counter = exchanger.exchange(counter);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondThread implements Runnable {

    private int counter;
    private final Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("SecondThread decrements:" + counter--);
            try {
                counter = exchanger.exchange(counter);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}