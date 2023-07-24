package org.example.ch13;

import javax.swing.*;


/**
 * 프로세스와 쓰레드
 * - 프로세스 : 실행 중인 프로그램, 자원(메모리,CPU 등)과 쓰레드로 구성되어있다.
 *
 * - 쓰레드  : 프로세스 내에서 실제 작업을 수행한다.
 *            모든 프로세스는 최소한 하나의 쓰레드를 가지고 있다.
 *
 * ex) 프로세스 = 공장
 *     쓰레드  = 일꾼
 *
 * > 싱글 쓰레드 프로세스          > 멀티 쓰레드 프로세스
 * = 자원 + 쓰레드                = 자원 + 쓰레드 + 쓰레드 + ... + 쓰레드
 *
 * 
 * 하나의 새로운 프로세스를 생성하는 것보다
 * 하나의 새로운 쓰레드를 생성하는 것이 더 적은 비용이 든다.
 * ex) 2프로세스 + 1쓰레드(싱글 쓰레드 x 2) vs 1프로세스 + 2쓰레드(멀티 쓰레드 x 1)
 */

/**
 * 멀티쓰레드의 장단점
 * : 대부분의 프로그램이 멀티쓰레드로 작성되어 있다.
 *   그러나 멀티쓰레드 프로그래밍이 장점만 있는 것은 아니다.
 *
 * - 장점
 * 1.시스템 자원을 보다 효율적으로 사용할 수 있다.
 * 2.사용자에 대한 응답성이 향상된다.
 * 3.작업이 분리되어 코드가 간결해 진다.
 *
 * - 단점 (공유함으로써의 뮨제들)
 * 1.동기화에 주의해야 한다.
 * 2.교착상태가 발생하지 않도록 주의해야 한다.
 * 3.각 쓰레드가 효율적으로 고르게 실행될 수 있게 해야 한다. (기아현상(어떠한 작업을 계속 못하게 되어 굶어죽는 현상) 주의)
 *
 * 즉, 멀티쓰레드를 만드는 것은 쉽지만 멀티쓰레드를 효율적으로 진행되어야하는 것이 중요
 */


/**
 * 쓰레드의 구현과 실행 2가지 방법
 * 1) Thread 클래스를 상속하는 방법
 *   class MyThread extends Thread {
 *       public void run() {  // Thread 클래스의 run()을 오버라이딩
 *           작업할 내용의 코드
 *       }
 *   }
 *
 * 2) Runnable 인터페이스를 구현하는 방법 (best, 추가로 다른 클래스를 상속받을 수 있으므로 유연하다)
 *   class MyThread2 implements Runnable {
 *       public void run() { // Runnable 인터페이스의 추상 메서드 run()을 구현
 *           작업할 내용의 코드
 *       }
 *   }
 *
 * - 생성 및 실행 ex)
 * 1번 방식
 * MyThread t1 = new MyThread(); // 쓰레드의 생성
 * t1.start();                   // 쓰레드의 실행
 *
 * 2번 방식
 * Runnable r = new MyThread2();
 * Thread t2 = new Thread(r); // Thread(Runnable r)
 * // 위를 한줄로 표현하기: Thread t2 = new Thread(new MyThread2());
 * t2.start();
 *
 *
 * - start()와 run()
 * : 작성한건 run()인데 왜 start()로 호출하냐면
 *    1.호출 스택에서 start()가 들어가고
 *            Call stack
 *            | start|
 *            | main |
 *
 *    2.여기서 start()가 새로운 호출 스택을 생성하고 run()을 넣고 start()를 종료한다.
 *           Call stack     new Call stack
 *            |      |         |     |
 *            | main |         | run |
 *
 *    => 즉, main()과 run()이 서로 독립적이게 된다.
 *
 * class ThreadTest {
 *     public static void main(String args[]) {
 *         MyThread t1 = new MyThread();
 *         t1.start();
 *     }
 * }
 *
 * class MyThread extends Thread {
 *     public void run() {
 *         ...
 *     }
 * }
 */


/**
 * main쓰레드
 * : main메서드의 코드를 수행하는 쓰레드
 *   쓰레드는 '사용자 쓰레드'와 '데몬 쓰레드' 2종류가 있다.
 *   => 실행 중인 사용자 쓰레드가 하나도 없을 때 프로그램은 종료된다.
 *      즉, 멀티 쓰레드의 호출 스택이 여러 개에서 main 쓰레드가 종료되어도 다른 호출 스택의 메서드가 남아있으면 종료되지 않는다.
 */


/**
 * 쓰레드의 I/O블락킹
 * : 입출력으로 인한 작업 중단
 *   즉, 멀티 쓰레드로 입력을 기다리는 동안 아무 일도 하지 않는 것을 막는다.
 *
 * 싱글 쓰레드에서 실행은
 * 먼저 입력이 끝날 때까지 다른 작업이 진행되지 않지만
 *
 * 멀티 쓰레드에서 실행은
 * 입력 중에 다른 작업이 실행될 수 있다.
 */


/**
 * 쓰레드의 우선순위
 * : 작업의 중요도에 따라 쓰레드의 우선순위를 다르게 하여
 *   특정 쓰레드가 더 많은 작업시간을 갖게 할 수 있다.
 *   단, 희망사항인 것이지 OS 스케줄러에서는 효율적으로 균등하게 실행하려고 하기에 순서가 다르게 실행될 수 있다. (참고 정도)
 *   
 *   void setPriority(int newPriority)  : 쓰레드의 우선순위를 지정한 값으로 변경한다.
 *   int getPriority()                  : 쓰레드의 우선순위를 반한한다.
 *   
 *   public static final in MAX_PPRIORITY = 10  // 최대우선순위 
 *   public static final in MIN_PPRIORITY = 1   // 최소우선순위
 *   public static final in NORM_PPRIORITY = 5  // 보통우선순위
 */


/**
 * 쓰레드 그룹
 * : 서로 관련된 쓰레드를 그룹으로 묶어서 다루기 위한 것
 *   모든 쓰레드는 반드시 하나의 쓰레드 그룹에 포함되어 있어야 한다.
 *   쓰레드 그룹을 지정하지 않고 생성한 쓰레드는 기본적으로 'main쓰레드 그룹'에 속한다.
 *   자신을 생성한 쓰레드(부모 쓰레드)의 그룹과 우선순위를 상속받는다.
 *
 *   => 즉, 쓰레드들은 쓰레드 그룹으로 묶여서 다루고 관리한다.
 *
 *   Thread(ThreadGroup group, String name)
 *   Thread(ThreadGroup group, Runnable target)
 *   Thread(ThreadGroup group, Runnable target, String name)
 *   Thread(ThreadGroup group, Runnable target, String name, long stackSize)
 *
 *   ThreadGroup getThreadGroup()                  : 쓰레드 자신이 속한 쓰레드 그룹을 반환한다.
 *   void uncaughtException(Thread t, Throwable e) : 처리되지 않은 예외에 의해 쓰레드 그룹의 쓰레드가 실행이 종료되었을 때,
 *                                                   JVM에 의해 이 메서드가 자동으로 호출된다.
 *
 * - 쓰레드 그룹의 메서드
 *   메서드의 종류                                                     설명
 * ThreadGroup(String name)                             지정된 이름의 새로운 쓰레드 그룹을 생성
 * ThreadGroup(ThreadGroup parent, String name)         지정된 쓰레드 그룹에 포함되는 새로운 쓰레드 그룹을 생성
 *
 * int activeCount()                                    쓰레드 그룹에 포함된 활성상태에 있는 쓰레드의 수를 반환
 * int activeGroupCount()                               쓰레드 그룹에 포함된 활성상태에 있는 쓰레드 그룹의 수를 반환
 *
 * void checkAccess()                                   현재 실행중인 쓰레드가 쓰레드 그룹을 변경할 권한이 있는지 체크
 * void destroy()                                       쓰레드 그룹과 하위 쓰레드 그룹까지 모두 삭제한다. 단, 비어있어야 삭제 가능
 *
 * int enumerate(Thread[] list)                         쓰레드 그룹에 속한 쓰레드 또는 하위 쓰레드 그룹의 목록을 지정된 배열에 담고 그 개수를 반환
 * int enumerate(Thread[] list, boolean recurse)
 * int enumerate(ThreadGroup[] list)                    두번째 매개변수인 recurse의 값을 true로 하면
 * int enumerate(ThreadGroup[] list, boolean recurse)   스레드 그룹에 속한 하위 쓰레드 그룹에 쓰레드 또는 쓰레드 그룹까지 배열에 담는다.
 *
 * int getMaxPriority()                                 쓰레드 그룹의 최대우선순위를 반환
 * String getName()                                     쓰레드 그룹의 이름을 반환
 * void interrupt()                                     쓰레드 그룹의 상위 쓰레드 그룹을 반환
 *
 * boolean isDaemon()                                   쓰레드 그룹이 데몬 쓰레드 그룹인지 확인
 * boolean isDestroyed()                                쓰레드 그룹이 삭제되었는지 확인
 *
 * void list()                                          쓰레드 그룹에 속한 쓰레드와 하위 쓰레드 그룹에 대한 정보를 출력
 * boolean parentOf(ThreadGroup g)                      지정된 쓰레드 그룹의 상위 쓰레드 그룹인지 확인
 *
 * void setDaemon(boolean daemon)                       쓰레드 그룹을 데몬 쓰레드 그룹으로 설정/해제
 * void setMaxPriority(int pri)                         쓰레드 그룹의 최대 우선순위를 설정
 */


/**
 * 쓰레드의 상태
 *
 *    상태                            설명
 *    NEW               쓰레드가 생성되고 아직 start()가 호출되지 않은 상태
 *
 *  RUNNABLE            실행 중 또는 실행 가능한 상태
 *
 *  BLOCKED             동기화블럭에 의해서 일시정지딘 상태(lock이 풀릴 때까지 기다리는 상태)
 *
 *  WAITING,            쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은 일시정지상태
 *  TIMED_WAITING
 *
 *  TERMINATED          쓰레드의 작업이 종료된 상태
 *
 *                        |  ㅇ  ㅇ  ㅇ   |
 *                          일시정지(WAITING,BLOCKED)
 *           start()                                 stop()
 *      ㅇ     ->      ㅇ   ㅇ   ㅇ   ㅇ      ->   ㅇ    ->      ㅇ
 *   생성(NEW)       줄서기  실행대기(RUNNABLE)     실행           소멸(TERMINATED)
 */

class Thread1 extends Thread {              // 1) Thread클래스를 상속해서 쓰레드를 구현한 방식
    public void run() {                     // 쓰레드가 수행할 작업을 자성
        for (int i = 0; i < 10; i++) {
            System.out.println(getName());  // 조상인 Thread의 getName()을 호출
        }
    }
}
class Thread2 implements Runnable {         // 2) Runnable인터페이스를 구현해서 쓰레드를 구현한 방식
    public void run() {                     // 쓰레드가 수행할 작업을 자성
        for (int i = 0; i < 10; i++) {
            // 여기서는 Thread를 상속받지 않았으므로
            // Thread.currentThread()로 사용해야함 - 현재 실행중인 Thread를 반환한다.
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class Thread3 extends Thread {                           // 1) Thread클래스를 상속해서 쓰레드를 구현한 방식
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("-"));
            for (int x = 0; x < 10000000; x++);          // 시간지연용 for문
        }
    }
}
class Thread4 extends Thread {                           // 1) Thread클래스를 상속해서 쓰레드를 구현한 방식
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("|"));
            for (int x = 0; x < 10000000; x++);          // 시간지연용 for문
        }
    }
}

class Thread5 extends Thread {                           // 1) Thread클래스를 상속해서 쓰레드를 구현한 방식
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println(i);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }
}
public class EX13_Thread {
    static long startTime = 0;

    public static void main(String[] args) throws Exception {  // main 쓰레드
        // 예제 1)
        // 멀티 쓰레드에서 작업한 경우
        // 각 쓰레드의 동작이 서로 섞이며 작업이 이루어진다.
        Thread1 t1 = new Thread1();

        Runnable r = new Thread2();
        Thread t2 = new Thread(r);  // 생성자 Thread(Runnable target)

        t1.start(); // 먼저 실행이 시작됐다고 실행되는 것은 아님 (OS 스케줄러가 실행순서를 결정)
        t2.start();


        // 싱글 쓰레드로 실행할 경우
        // 순차적으로 첫 작업이 끝날 때까지 2번째 작업은 대기한다.
        // 즉, 절대로 섞이지 않는다.
//        for (int i = 0; i < 10; i++) {
//            System.out.println(0);
//        }
        //  아래 작업은 대기해야 된다.
//        for (int i = 0; i < 10; i++) {
//            System.out.println(1);
//        }

        System.out.println();


        // 예제 2) 멀티 쓰레드의 호출 스택이 여러 개에서 main 쓰레드가 종료되어도
        //        다른 호출 스택의 메서드가 남아있으면 종료되지 않는다.
        // 멀티 쓰레드에서 작업한 경우
        Thread3 t3 = new Thread3();
        Thread4 t4 = new Thread4();
        t3.start();
        t4.start();
        startTime = System.currentTimeMillis();

        try {
            t3.join();  // join 메서드 역할이 main 쓰레드가 t3의 작업이 끝날 때까지 기다린다.
            t4.join();  
        } catch (InterruptedException e){ }

        System.out.println("time : " + (System.currentTimeMillis() - startTime));


        // 싱글 쓰레드로 실행한 경우
        // 두 작업이 절대 겹치지 않음
        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("-"));
        }

        System.out.print("time : " + (System.currentTimeMillis() - startTime));

        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("|"));
        }

        System.out.print("time : " + (System.currentTimeMillis() - startTime));

        System.out.println();


        // 예제 4) 멀티 쓰레드로 입력 중의 blocking 방지
        // 싱글 쓰레드로 실행한 경우
//        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
//        System.out.printf("입력하신 값은 " + input + "입니다.");
//
//        for (int i = 10; i > 0; i--) {
//            System.out.println(i);
//
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {}
//        }


        // 멀티 쓰레드에서 작업한 경우
        // 입력하는 동안 다른 쓰레드의 동작이 진행된다.
        Thread5 t5 = new Thread5();
        t5.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.printf("입력하신 값은 " + input + "입니다.");

        System.out.println();
        System.out.println();


        // 예제 7) 쓰레드 우선순위
        Thread3 t6 = new Thread3();
        Thread4 t7 = new Thread4();

        t7.setPriority(7);          // 지정해주지 않으면 기본 값은 5

        System.out.println("Priority of t6(-) = " + t6.getPriority()); // 기본 값 5
        System.out.println("Priority of t7(|) = " + t6.getPriority()); // 7, 이 쓰레드가 더 일찍 끝날 것 같지만 꼭 그런건 아니고 그럴 가능성이 높은 것
        t6.start();
        t7.start();
    }
}
