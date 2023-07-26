package org.example.ch13;


/**
 * - suspend(), resume(), stop()
 * : 쓰레드의 실행을 일시정지, 재개, 완전정지 시킨다.
 *
 *   void suspend() : 쓰레드를 일시정지 시킨다.
 *   void resume()  : suspend()에 의해 일시정지된 쓰레드를 실행대기상태로 만든다.
 *   void stop()    : 쓰레드를 즉시 종료시킨다.
 *
 *   suspend(), resume(), stop()은 교착상태에 빠지기 쉬워서 deprecated(사용권장 X)되었다.
 */

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

public class EX13_Thread_Method_suspend_resume_stop {

    public static void main(String[] args) {
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
    }
}
