package org.example.ch13;

import javax.swing.*;


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
 * - sleep() (static 메서드라서 자신의 쓰레드만 적용가능)
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
 *
 *
 * - interrupt() (static 메서드라서 자신의 쓰레드만 적용가능)
 * : 대기상태(WAITING)인 쓰레드를 실행대기 상태(RUNNABLE)로 만든다.
 *   즉, 쓰레드를 중단하거나 중단되어있는 작업을 다시 진행시키고 싶을 때 사용
 *
 *   void interrupt()              : 쓰레드의 interrupted상태를 false에서 true로 변경
 *   boolean isInterrupted()       : 쓰레드의 interrupted상태를 반환
 *   static boolean interrupted()  : 현재 쓰레드의 interrupted상태를 알려주고 false로 초기화
 *
 *
 * - suspend(), resume(), stop()
 * : 쓰레드의 실행을 일시정지, 재개, 완전정지 시킨다.
 *
 *   void suspend() : 쓰레드를 일시정지 시킨다.
 *   void resume()  : suspend()에 의해 일시정지된 쓰레드를 실행대기상태로 만든다.
 *   void stop()    : 쓰레드를 즉시 종료시킨다.
 *
 *   suspend(), resume(), stop()은 교착상태에 빠지기 쉬워서 deprecated(사용권장 X)되었다.
 *
 *
 * - join()
 * : 지정된 시간동안 특정 쓰레드가 작업하는 것을 기다린다.
 *
 *   void join()                       : 작업이 모두 끝날 때까지 기다림
 *   void join(long millis)            : 1/1000초 동안 기다림
 *   void join(long millis, int nanos) : 1/1000초 + 나노초 동안 기다림
 *
 *   항상 try-catch로 예외처리를 해야한다.
 *
 *   사용 ex)
 *   GC에서 메모리가 부족한 경우 interrupt로 gc를 깨우는데
 *   해당 gc가 작업처리해야하는 시간을 줘야하므로 이때 join()이 필요
 *
 *
 * - yield() (static 메서드라서 자신의 쓰레드만 적용가능)
 * : 현재 실행 중인 쓰레드의 남은 시간을 다음 쓰레드에게 양보하고 자신(현재 쓰레드)은 실행대기한다.
 *   yield()와 interrupt()를 적절히 사용하면 응답성과 효율을 높일 수 있다.
 *
 *   ex) 한 쓰레드의 run()일부
 *   public void run() {
 *       while(!stopped) {
 *           if(!suspended) {
 *               // 작업수행
 *
 *               try {
 *                   Thread.sleep(1000);
 *               } catch(InterruptedException e) {}
 *           }
 *       } else {  // suspend()로 일시정지일 때 while문은 무한반복되므로
 *                 // 이 반복의 의미가 없어 여기서 yield로 다음 쓰레드에게 양보
 *           Thread.yield(); // OS 스케줄러에게 알려주는 것 뿐이므로 쓰나 안쓰나의 차이가 별로 없다.
 *       }
 *   }
 *
 *
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

class Thread8 extends Thread {                           // 1) Thread클래스를 상속해서 쓰레드를 구현한 방식
    public void run() {
        int i = 10;

        while (i!=0 && !isInterrupted()) {
            System.out.println(i--);
            for (long x=0; x<25000000000L; x++);        // 시간 지연
        }

        // main 쓰레드에서 이 쓰레드를 확인할 수 없으므로 여기서 확인해야함
        System.out.println("isInterrupted(): " + this.isInterrupted()); // true
        System.out.println("isInterrupted(): " + this.isInterrupted()); // true

        // isInterrupted()와 달리 interrupted()는 interrupted라는 상태변수를 false로 초기화
        System.out.println("isInterrupted(): " + Thread.interrupted()); // true
        System.out.println("isInterrupted(): " + Thread.interrupted()); // false

        System.out.println("카운트가 종료되었습니다.");
    }
}

class Thread9 implements Runnable {
    // volatile은 쉽게 바뀌는 변수일 때 붙여야 한다.
    // CPU의 코어들 안에 메모리(RAM)에 변수의 값이 복사본들이 들어가는데(효율적이게 하려고)
    // volatile으로 복사본을 사용안하고 원본만 사용하게 하여 값을 바로 읽을 수 있게하는 것
    volatile boolean suspended = false;  // 일시정지 상태
    volatile boolean stopped = false;    // 정지 상태

    Thread t;

    Thread9(String name) {
        t = new Thread(this, name);  // Thread(Runnable r, String name)
    }

    void start() {
        t.start();
    }

    // 호출되면 true
    void stop() {
        stopped = true;
    }

    // 호출되면 true
    void suspend() {
        suspended = true;
    }

    // 호출되면 false
    void resume() {
        suspended = false;
    }

    @Override
    public void run() {
        while (!stopped) {
            if (!suspended) {
                System.out.println(Thread.currentThread().getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){}
            }
        }
    }
}

class Thread10 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("-"));
        }
    }
}

class Thread11 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print(new String("|"));
        }
    }
}

public class EX13_Thread_Method {
    static long startTime = 0;

    public static void main(String[] args) {
        // 예제 1) sleep() 활용
        Thread6 t1 = new Thread6();
        Thread7 t2 = new Thread7();
        t1.start();
        t2.start();

        delay(2*1000); // 항상 작성해야하는 try-catch 때문에 메서드 활용

        System.out.println("<<main 종료>>");

        System.out.println();


        // 예제 2) interrupt() 활용
        Thread8 t3 = new Thread8();
        t3.start();

        System.out.println("isInterrupted(): " + t3.isInterrupted()); // false
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
        t3.interrupt(); // interrupt()를 호출하면 interrupted 상태가 true가 된다.


        System.out.println("isInterrupted(): " + t3.isInterrupted()); // true
        System.out.println("isInterrupted(): " + t3.isInterrupted()); // true

        // interrupted()는 쓰레드 자기 자신만 사용가능하므로 이렇게 쓰면 안됨
        System.out.println("interrupted(): " + t3.interrupted());     // true

        // 자기 자신 쓰레드인 main 쓰레드가 interrupt되었는지 확인
        System.out.println("interrupted(): " + Thread.interrupted()); // false


        // 예제 3) suspend(), resume(), stop()
        Thread9 t4 = new Thread9("*");
        Thread9 t5 = new Thread9("**");
        Thread9 t6 = new Thread9("***");
        t4.start();
        t5.start();
        t6.start();

        try {
            Thread.sleep(2000);
            t4.suspend(); // 쓰레드 t4을 잠시 중단 시킴

            Thread.sleep(2000);
            t5.suspend();

            Thread.sleep(3000);
            t4.resume();   // 쓰레드 t4을 다시 동작하도록 함

            Thread.sleep(3000);
            t4.stop();    // 쓰레드 t4을 강제종료
            t5.stop();

            Thread.sleep(2000);
            t6.stop();

        } catch (InterruptedException e){}


        // 예제 4) join 활용
        Thread10 t7 = new Thread10();
        Thread11 t8 = new Thread11();
        t7.start();
        t8.start();

        startTime = System.currentTimeMillis();

        try {
            t7.join();  // main 쓰레드가 t7의 작업이 끝날 때까지 기다린다.
            t8.join();  // main 쓰레드가 t8의 작업이 끝날 때까지 기다린다.
        } catch (InterruptedException e) {}


        System.out.println("소요시간: " + (System.currentTimeMillis() - startTime));
    }

    static void delay(long millis) {
        try { // sleep()은 InterruptedException이 필수 예외라서 try-catch는 필수이다.
            Thread.sleep(millis); // 2초동안 잠자게 함, sleep은 자기 자신의 쓰레드만 가능
        } catch (InterruptedException e) {}
    }

}
