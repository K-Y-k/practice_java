package org.example.ch13;


/**
 * 데몬 쓰레드
 * : 일반 쓰레드의 작업을 돕는 보조적인 역할을 수행하는 쓰레드
 *   일반 쓰레드가 모두 종료되면 보조가 필요 없으므로 자동적으로 종료된다.
 *   가비지 컬렉터, 자동저장, 화면 자동갱신 등에 사용된다.
 *
 *   - 작성방법
 *   무한루프와 조건문을 이용해서 실행 후 대기하다가
 *   특정조건이 만족되면 작업을 수행하고 다시 대기하도록 작성한다.
 *
 *   public void run() {
 *       while (true) {  // 무한루프
 *           try {
 *               Thread.sleep(3 * 1000);
 *           } catch (InterruptedException e) { }
 *
 *           // autoSave의 값이 true이면 autoSave()를 호출한다.
 *           if (autoSave) {
 *               autoSave();
 *           }
 *       }
 *   }
 *
 *   boolean isDaemon()         : 쓰레드가 데몬 쓰레드인지 확인한다. 데몬 쓰레드이면 true를 반환한다.
 *   void setDaemon(boolean on) : 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경한다.
 *                                매개변수 on을 true로 지정하면 데몬 쓰레드가 된다.
 *
 *                                단, setDaeomon(boolean on)은 반드시 start()를 호출하기 전에 실행되어야 한다.
 *                                그렇지 않으면 IllegalThreadStateException이 발생한다.
 */

public class EX13_Daemon_Thread implements Runnable{
    static boolean autoSave = false;

    public static void main(String[] args) {
        Thread t = new Thread(new EX13_Daemon_Thread()); // Thread의 기본 생성자에는 Thread(Runnable r)이 있어
                                                         // Runnable 인터페이스를 구현한 클래스가 올 수 있다.
        t.setDaemon(true); // start()전에 데몬 쓰레드로 할지 설정해야한다.
                           // 이 부분이 없으면 무한루프로 종료되지 않는다.
                           // 데몬 쓰레드이기에 main 쓰레드가 종료되어 자동 종료해준 것
        t.start();

        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            System.out.println(i);

            if (i==5) autoSave = true;
        }

        System.out.println("프로그램을 종료합니다.");
    }


    @Override
    public void run() {
        while (true) { // 무한루프
            try {
                Thread.sleep(3 * 1000); // 3초마다
            } catch (InterruptedException e) {}

            // autoSave의 값이 true이면 autoSave()를 호출한다.
            if (autoSave) {
                autoSave();
            }
        }
    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장되었습니다.");
    }
}
