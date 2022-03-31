package main.test;

import main.java.ThreaddSafeQueue;

import java.util.stream.IntStream;

public class UnBoundedSafeQueueTest {
    public static void main(String[] args) throws InterruptedException {
        var queue = new ThreaddSafeQueue<String>();

        IntStream.range(0, 3).forEach(i -> {
            new Thread(() -> {
                try {
                    for(;;) {
                       Thread.sleep(1000);
                       queue.add(Thread.currentThread().getName());
                    }
                } catch (InterruptedException e) {
                    throw new AssertionError(e.getMessage());
                }
            }).start();
        });

        for(;;) {
            System.out.println(queue.take());
        }
    }
}
