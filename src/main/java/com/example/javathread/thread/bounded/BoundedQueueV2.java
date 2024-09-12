package com.example.javathread.thread.bounded;


import java.util.ArrayDeque;
import java.util.Queue;

import static com.example.javathread.util.MyLogger.log;
import static com.example.javathread.util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {


    private final Queue<String> queue = new ArrayDeque<>();

    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }



    /**
     * 무한대기 문제 발생
     * */
    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] 큐가 가득 참, 생산자 대기"); // 큐가 가득차면 큐ㅂ의 빈자리가 나올때 까지 대기한다는 문제점이 있다.
            sleep(1000);
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 큐에 데이터가 없음, 소비자 대기"); // 임계영역이니까 락을 얻어야하는데, 락은 put에서 락을 계속 갖고잇다. 따라서 blocker 상태가된다.
            sleep(1000);
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
