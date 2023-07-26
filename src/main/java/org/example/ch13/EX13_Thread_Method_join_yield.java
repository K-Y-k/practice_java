package org.example.ch13;

/**
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
 */

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

public class EX13_Thread_Method_join_yield {
    static long startTime = 0;

    public static void main(String[] args) {
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
}
