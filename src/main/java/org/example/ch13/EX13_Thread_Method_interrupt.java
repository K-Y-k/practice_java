package org.example.ch13;

import javax.swing.*;

/**
 * interrupt() (static 메서드라서 자신의 쓰레드만 적용가능)
 * : 대기상태(WAITING)인 쓰레드를 실행대기 상태(RUNNABLE)로 만든다.
 *   즉, 쓰레드를 중단하거나 중단되어있는 작업을 다시 진행시키고 싶을 때 사용
 *
 *   void interrupt()              : 쓰레드의 interrupted상태를 false에서 true로 변경
 *   boolean isInterrupted()       : 쓰레드의 interrupted상태를 반환
 *   static boolean interrupted()  : 현재 쓰레드의 interrupted상태를 알려주고 false로 초기화
 *
 */

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

public class EX13_Thread_Method_interrupt {

    public static void main(String[] args) {
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

    }
}
