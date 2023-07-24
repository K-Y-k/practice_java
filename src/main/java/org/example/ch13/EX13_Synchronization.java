package org.example.ch13;


/**
 * 쓰레드의 동기화
 * : 멀티 쓰레드 프로세스에서는 다른 쓰레드의 작업에 영향을 미칠 수 있다.
 *   진행중인 작업이 다른 쓰레드에게 간섭받지 않게 하려면 동기화가 필요하다.
 *   즉, 한 쓰레드가 진행중인 작업을 다른 쓰레드가 간섭하지 못하게 막는 것
 *
 *   동기화하려면 간섭받지 않아야 하는 문장들을 '임계영역'으로 설정한다.
 *   임계영역은 락을 얻은 단 하나의 쓰레드만 출입가능하다. (객체 1개에 락 1개)
 *   즉, 어떤 쓰레드가 객체의 락을 가지고 있으면 다른 쓰레드에서 해당 객체의 임계영역에 접근하지 못한다.
 */

/**
 * synchronized를 이용한 동기화
 * - synchronized로 임계영역(lock이 걸리는 영역)을 설정하는 방법 2가지
 *   1) 메서드 전체를 임계영역으로 지정 방법
 *      public synchronized void calcSum() { ---|
 *          ...                                 | 임계영역
 *      }                                    ---|
 *      
 *      => 임계영역은 1개의 쓰레드에 1개의 임계영역만 가능하므로
 *         임계영역을 최소화해야 한다.
 * 
 * 
 *   2) 특정한 영역을 임계영역으로 지정 방법 (best)
 *      synchronized(객체의 참조변수) { ---|
 *          ...                         | 임계영역
 *      }                            ---|
 */

class Account {
    private int balance = 1000; // private로 해야 동기화가 의미가 있다.

    public synchronized int getBalance() { // 읽는 동안 값이 바뀌면 안되므로 여기도 synchronized 활용해야함
        return balance;
    }

    public synchronized void withdraw(int money) { // 1) 메서드 전체를 임계영역으로 지정 방법
        // 임계영역은 1개의 1쓰레드만 들어올 수 있다.
        if (balance >= money) { // 잔고가 더 커야
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            balance -= money;  // 출금가능
        }
    }
}

class RunnableEX13 implements Runnable {
    Account acc = new Account();

    @Override
    public void run() {
        while (acc.getBalance() > 0) {
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance(): " + acc.getBalance());
        }
    }
}

public class EX13_Synchronization {
    public static void main(String[] args) {
        RunnableEX13 r = new RunnableEX13();
        new Thread(r).start();
        new Thread(r).start();
    }
}
