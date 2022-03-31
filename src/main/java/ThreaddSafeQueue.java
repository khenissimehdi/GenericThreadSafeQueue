package main.java;

import java.util.ArrayList;


public class ThreaddSafeQueue<T> {
    private final ArrayList<T> queue = new ArrayList<>();

    public void add(T value) {
        synchronized (queue) {
            queue.add(value);
            queue.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            return queue.remove(0);
        }
    }
}
