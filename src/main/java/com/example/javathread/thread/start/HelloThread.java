package com.example.javathread.thread.start;

public class HelloThread extends  Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +" 실행중입니다.");
    }
}
