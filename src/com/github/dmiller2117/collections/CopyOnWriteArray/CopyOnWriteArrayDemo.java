package com.github.dmiller2117.collections.CopyOnWriteArray;

/**
 * <h2>CopyOnWriteArray</h2>
 * <code>List<T> list = new CopyOnWriteArray<>();</></code>
 * <p>This is the efficient implementation of the synchronized <code>ArrayList</code><br>
 * <strong>Any thread can read from the actual version of the list!!</strong><br>
 * There is <strong>no need for locking</strong> when reading from the list</p>
 * <p><strong>Threads that change the value in the list make a copy of the list - 0(N)!!</strong><br>
 * This is how the update (remove or set operations) will be <strong>atomic</strong> - threads must wait for each other
 * to update the list</p>
 */
public class CopyOnWriteArrayDemo {

    public static void main(String[] args) {
        ConcurrentArray concurrentArray = new ConcurrentArray();
        concurrentArray.simulate();
    }
}