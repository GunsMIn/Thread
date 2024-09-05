package com.example.javathread.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.example.javathread.util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue{

    private final Queue<String> queue = new ArrayDeque<>();

    private final int max;

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    //임계영역 사용
    @Override
    public synchronized void put(String data) {
        if (queue.size() == max) {
            log("[put] 큐가 가득 참, 버림: " + data);
            return;
        }
        queue.offer(data);
    }

    //임계영역 사용
    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
