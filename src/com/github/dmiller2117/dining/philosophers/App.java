package com.github.dmiller2117.dining.philosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The aim of the simulation is that it is possible to avoid thread starvation<br>
 * - all the threads are going to be executed by the executor service<br>
 * - that we are able to avoid deadlock because we use <code>tryLock()</code>
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;
        Chopstick[] chopsticks;

        try {
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];

            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);

            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_CHOPSTICKS]);
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for (Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }

        } finally {
            assert executorService != null;
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                //noinspection BusyWait
                Thread.sleep(1000);
            }

            for (Philosopher philosopher : philosophers) {
                System.out.println(philosopher + " eat #" + philosopher.getEatingCounter() + " times");
            }
        }
    }
}