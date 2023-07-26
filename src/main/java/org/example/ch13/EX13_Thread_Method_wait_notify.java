package org.example.ch13;

import java.util.ArrayList;


/**
 * wait()과 notify()
 * : 동기화는 한 쓰레드에 한 임계영역만 가능한 문제로 비효율적이라
 *   동기화의 효율을 높이기 위해 wait(), notify()를 사용한다.
 *   Object클래스에 정의되어 있으며 동기화 블록 내에서만 사용할 수 있다.
 *
 *   - wait()      : 객체의 lock을 풀고 쓰레드를 해당 객체의 waiting pool에 넣어 대기시킨다.
 *   - notify()    : waiting pool에서 대기중인 쓰레드 중의 랜덤으로 하나를 알려서 깨워 다시 작업을 진행하게 한다.
 *   - notifyAll() : waiting pool에서 대기중인 모든 쓰레드 중의 하나를 깨운다.
 *   => notifyAll로 공평하게 모두를 깨우고 그중 하나만 락을 걸어 진행하게 하는 것이 더 균등하여
 *      notifyAll로 사용 권장
 *   
 *
 * ex)
 * class Account {
 *     private int balance = 1000; // private로 해야 동기화가 의미가 있다.
 *
 *     public synchronized int getBalance() { // 읽는 동안 값이 바뀌면 안되므로 여기도 synchronized 활용해야함
 *         return balance;
 *     }
 *
 *     public synchronized void withdraw(int money) { // 1) 메서드 전체를 임계영역으로 지정 방법
 *         // 임계영역은 1개의 1쓰레드만 들어올 수 있다.
 *         while (balance < money) { // 잔고가 적으면
 *             try {
 *                 wait() // 대기 : lock을 풀고 대기하는 pool에 넣어 기다린다. 통지를 받으면 락을 재획득함
 *             } catch (InterruptedException e) {}
 *
 *             balance -= money;  // 출금
 *         }
 *     }
 *     
 *     public synchronized void deposit(int money) {
 *         balance += money;
 *         notify();         // 통지 - 대기중인 쓰레드 중 하나에게 알려 다시 작업을 진행하게 함
 *     }
 * }
 */

/**
 * wait()와 notify()의 단점
 * : 여러 쓰레드가 waiting pool에서 대기중이면 어떤 쓰레드를 지정하는 것이 없이
 *   랜덤으로 하나를 가져온다.
 * 
 * => 이를 해결하기 위해 Lock&Condition이 나옴
 */

class Customer implements Runnable {
    private Table table;
    private String food;

    Customer(Table table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}

            String name = Thread.currentThread().getName();

            // 음식을 소비해서
            try {
                table.remove(food);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(name + " ate a " + food);
        }
    }
}

class Cook implements Runnable {
    private Table table;

    Cook(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        while (true) {
            // 임의의 요리를 하나 선택해서
            int idx = (int) (Math.random() * table.dishNum());

            //table에 추가한다.
            try {
                table.add(table.dishNames[idx]);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }
    }
}

class Table {
    String[] dishNames = {"donut", "donut", "burger"};  // donut의 확률을 높였음
    final int MAX_FOOD = 6;

    private ArrayList<String> dishes = new ArrayList<>();

    /**
     * Customer와 Cook에서 같은 Table 객체를 사용하여 공유하므로 동기화가 필요!
     * 
     * 동기화를 사용안하면 진행하다가 에러가 발생한다.
     *
     * 단, 동기화만 걸면 해당 객체의 내용이 빌 때 lock을 쥐고 안놓는다.
     * 그러면 다른곳에서 접근을 더이상 못하여 내용에 추가를 못한다.
     * => 즉, 내용이 빌 때는 wait()으로 lock을 풀고 기다리게 하고
     *    추가하면 notify()로 알려서 다시 작업을 진행하게 하자! (lock을 재획득)
     */
    public synchronized void add(String dish) throws InterruptedException { // 동기화 추가   1) 메서드 전체를 임계영역으로 지정 방법
        // 테이블이 가득찼으면 음식을 추가 안함
        while (dishes.size() >= MAX_FOOD) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is waiting.");
            try {
                wait(); // Cook 쓰레드를 기다리게 한다.
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }

        // 테이블이 가득차있지 않으면
        dishes.add(dish); // 음식 추가
        notify();         // wait()로 대기시켜놓은 쓰레드(여기선 Customer)를 깨운다.
        System.out.println("Dishes: " + dishes.toString());
    }

    public void remove(String dishName) throws InterruptedException {
        synchronized (this) {                   // 동기화 추가  2) 특정한 영역을 임계영역으로 지정 방법 (best)
            String name = Thread.currentThread().getName();

            // 음식이 없으면
            while (dishes.size() == 0) {
                System.out.println(name + " is waiting.");
                try {
                    wait(); // Customer 쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e){}
            }

            // 음식이 있으면
            while (true) {
                // 지정된 요리와 일치하는 요리를 테이블에서 제거한다.
                for (int i = 0; i < dishes.size(); i++) {
                    if (dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        notify();  // wait()로 대기시켜놓은 쓰레드(여기선 COOK)를 깨운다.
                        return;
                    }
                }

                // 원하는 음식이 없는 경우
                try {
                    System.out.println(name + " is waiting.");
                    wait();       // 원하는 음식이 없는 Customer 쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        }
    }

    public int dishNum() {
        return dishNames.length;
    }

}

public class EX13_Thread_Method_wait_notify {
    public static void main(String[] args) throws Exception {
        Table table = new Table();
        new Thread(new Cook(table), "COOK").start();
        new Thread(new Customer(table,"donut"), "CUSTOMER1").start();
        new Thread(new Customer(table,"burger"), "CUSTOMER2").start();

        Thread.sleep(2000);
        System.exit(0);
    }
}
