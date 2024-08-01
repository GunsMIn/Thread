package com.example.javathread.thread.sync.v3;

import com.example.javathread.thread.sync.v1.BankAccount;

import static com.example.javathread.util.MyLogger.log;
import static com.example.javathread.util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount {
    private int balance;

    public BankAccountV3(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        /*
        synchronized (this) : 여기서 괄호 () 안에 들어가는 값은 락을 획득할 인스턴스의 참조이다.
        여기서는 BankAccountV3(x001) 의 인스턴스의 락을 사용하므로 이 인스턴스의 참조인 this 를 넣어
        주면 된다.
        이전에 메서드에 synchronized 를 사용할 때와 같은 인스턴스에서 락을 획득한다.*/

        synchronized (this) {
            log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
            if (balance < amount) {
                log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
                return false;
            }
            log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
            sleep(1000);
            balance = balance - amount;
            log("[출금 완료] 출금액: " + amount + ", 변경 잔액: " + balance);
        }
        log("거래 종료");
        return true;

    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}