package org.example.ch13;


/**
 * 쓰레드의 실행제어 메서드
 * : 쓰레드의 실행 및 제어할 수 있는 메서드가 제공된다.
 *   이들을 활용해서 보다 효율적인 프로그램의 작성을 할 수 있다.
 *
 *    메서드 종류                            설명
 * static void sleep(long millis)     지정된 시간(1/1000)동안 쓰레드를 일시정지시킨다.
 *                                    지정한 시간이 지나고 나면 자동적으로 다시 실행대기상태가 된다.
 *
 * void join()                        지정된 시간동안 쓰레드가 실행되도록 한다.
 * void join(long millis)             지정된 시간이 지나거나 작업이 종료되면 join()을 호출한 쓰레드로 다시 돌아와 실행을 계속한다.
 * void join(long millis, int nanos)
 *
 * void interrupt()                   sleep()이나 join()에 의해 일시정지상태인 쓰레드를 깨워서 실행대기상태로 만든다.
 *                                    해당 쓰레드에서는 Interrupted Exception이 발생함으로써 일시정지 상태를 벗어나게 된다.
 *
 * void stop()                        쓰레드를 즉시 종료
 * void suspend()                     쓰레드를 일시정지시킨다.
 * void resume()                      resume()을 호출하면 다시 실행대기상태가 된다.
 * static void yield()                실행 중에 자신에게 주어진 실행시간을 다른 쓰레드에게 양보하고 자신은 실행대기상태가 된다.
 *
 * => 여기서 static이 붙은 sleep()과 yield()는
 *    다른 쓰레드에 적용할 수 없고 자기 자신의 쓰레드에게만 호출이 가능하다.
 */

/**
 * sleep() (static 메서드라서 자신의 쓰레드만 적용가능)
 * : 현재 쓰레드를 지정된 시간동안 멈추게 한다.
 *   static void sleep(long millis)            // 1/1000초 단위
 *   static void sleep(long millis, int nanos) // 1/1000초 + 나노초
 *
 *   항상 예외처리를 해야 한다.(InterruptedException이 발생하면 깨어남)
 *   try {
 *       Thread.sleep(1, 500000);        // 쓰레드를 0.0015초 동안 멈추게 한다.
 *   } catch(InterruptedException e) {}  // Exception의 자손이므로 필수 예외처리로 멈추고 있을 때 깨우면 발생한다.
 *   => 메서드를 만들어 사용하면 try-catch문을 계속 작성할 필요가 없어진다.
 *      void delay(long millis) {
 *          try {
 *              Thread.sleep(millis);
 *          } catch(InterruptedException e) {}
 *      }
 *
 *   특정 쓰레드를 지정해서 멈추게 하는 것은 불가능하다.
 *   try {
 *       th1.sleep(2000);  // 특정 쓰레드 지정하는 것으로 오해하므로   ->  Thread.sleep(2000);로 사용해야한다.
 *   }
 */

class Thread6 extends Thread {                           // 1) Thread클래스를 상속해서 쓰레드를 구현한 방식
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("|"));
        }
        System.out.println("<<t1 종료>>");
    }
}

class Thread7 extends Thread {                           // 1) Thread클래스를 상속해서 쓰레드를 구현한 방식
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("|"));
        }
        System.out.println("<<t2 종료>>");
    }
}

public class EX13_Thread_Method_sleep {

    public static void main(String[] args) {
        // 예제 1) sleep() 활용
        Thread6 t1 = new Thread6();
        Thread7 t2 = new Thread7();
        t1.start();
        t2.start();

        delay(2 * 1000); // 항상 작성해야하는 try-catch 때문에 메서드 활용

        System.out.println("<<main 종료>>");

        System.out.println();
    }

    static void delay(long millis) {
        try { // sleep()은 InterruptedException이 필수 예외라서 try-catch는 필수이다.
            Thread.sleep(millis); // 2초동안 잠자게 함, sleep은 자기 자신의 쓰레드만 가능
        } catch (InterruptedException e) {}
    }
}
