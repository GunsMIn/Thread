package com.example.javathread.thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static com.example.javathread.util.MyLogger.log;
import static com.example.javathread.util.ThreadUtils.sleep;

public class LockSupportMainV1  {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new ParkTask(), "Thread-1");
        thread1.start();
        // 잠시 대기하여 Thread-1이 park 상태에 빠질 시간을 준다.
        sleep(100);
        log("Thread-1 state: " + thread1.getState());
        log("main -> unpark(Thread-1)");
        LockSupport.unpark(thread1); // 1. unpark 사용
        //thread1.interrupt(); // 2. interrupt() 사용
        //waiting 상태의 쓰레드는 인터럽트를 걸어서 중간에 깨울 수 있다.
    }
    static class ParkTask implements Runnable {
        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }
}


