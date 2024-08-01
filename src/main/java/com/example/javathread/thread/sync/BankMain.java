package com.example.javathread.thread.sync;

import com.example.javathread.thread.sync.v1.BankAccount;
import com.example.javathread.thread.sync.v1.BankAccountV1;
import com.example.javathread.thread.sync.v1.WithdrawTask;
import com.example.javathread.thread.sync.v2.BankAccountV2;

import static com.example.javathread.util.MyLogger.log;
import static com.example.javathread.util.ThreadUtils.sleep;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {
        //BankAccount account = new BankAccountV1(1000);
        BankAccount account = new BankAccountV2(1000);

        Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");
        t1.start();
        t2.start();

        sleep(500); // 검증 완료까지 잠시 대기
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());
        t1.join();
        t2.join();
        log("최종 잔액: " + account.getBalance());
    }

}