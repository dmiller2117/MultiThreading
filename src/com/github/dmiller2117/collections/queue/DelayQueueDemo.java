package com.github.dmiller2117.collections.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <h2>DelayQueue</h2>
 * <p>This is an unbounded <code>BlockingQueue</code> of objects that implement the <code>Delayed</code> interface
 * <ul>
 *     <li><code>DelayQueue</code> keeps the elements internally until a certain delay has expired</li>
 *     <li>An object can only be taken from the queue when it's delay has expired</li>
 * </ul></p>
 * <p>We cannot place <code>null</code>items in the queue - The queue is sorted so that the object at the head has a delay
 * that has expired for the longest time</p>
 * <p>If no delay had expired, then there is no head element and <code>poll()</code>will return <code>null</code></p>
 * <p><code>size()</code> returns the count of both expired and unexpired items!!</p>
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();

        try {
            queue.put(new DelayedWorker(1000, "First message"));
            queue.put(new DelayedWorker(10000, "Second message"));
            queue.put(new DelayedWorker(4000, "Third message"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DelayedWorker implements Delayed {
    private final long duration;
    private final String message;

    public DelayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed otherDelayed) {
        return Long.compare(this.duration, ((DelayedWorker) otherDelayed).getDuration());
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    public String getMessage() {
        return message;
    }

}