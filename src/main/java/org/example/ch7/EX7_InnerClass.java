package org.example.ch7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 내부 클래스
 * : 클래스 안의 클래스
 *
 * - 내부 클래스의 장점
 * 1.내부 클래스에서 외부 클래스의 멤버들을 쉽게 접근할 수 있다.
 * 2.코드의 복잡성을 줄일 수 있다.(캡슐화)
 *
 */
class A1 {    // 외부 클래스
     int i = 100;

    // class B가 class A1에서만 사용된다면 이렇게 내부 클래스로 한다.
    class B { // 내부 클래스로 다른 클래스는 B클래스를 알 수 없고 사용할 수 없다.(캡슐화)
              // 억지로 외부에서 상요하려면 외부 클래스를 생성 후 내부 클래스를 생성해야 한다.
              // (단, static 내부 클래스는 외부 객체 생성 없이 사용 가능 ex) new 외부클래스이름.static내부클래스이름();)
        void method() {
            System.out.println(i);  // A1 객체 생성 없이도 A1의 멤버에 접근 및 사용이 가능하다.
        }
    }
}


/**
 * 내부 클래스의 종류와 특징
 * : 내부 클래스의 종류와 유효범위(scope)는 변수(iv, cv, 지역변수)와 동일하다.
 *
 *      종류                            특징
 *  인스턴스 클래스        = 인스턴스 변수(iv)와 동일
 *  스태틱 클래스          = static 클래스 변수(cv)와 동일
 *  지역 클래스            = 지역변수와 동일
 *  익명 클래스           클래스의 선언과 객체의 생성을 동시에 하는 이름없는 클래스(일회용)
 *                       이벤트 처리할 때 사용
 *
 *
 * 내부 클래스의 제어자와 접근성
 * : 내부 클래스의 제어자는 변수에 사용했던 제어자와 동일하다.
 *
 * ex)
 * class Outer { // 외부 클래스에는 (default), public만 가능
 *     // 내부 클래스는 public, protected, private, (default) 가능
 *     private class InstanceInner {      // 인스턴스 클래스
 *
 *     }
 *     protected static class StaticInner { // 스태틱 클래스
 *
 *     }
 *     void myMethod() {
 *         class LocalInner {}    // 지역 클래스
 *     }
 * }
 */
public class EX7_InnerClass {

    class InstanceInner {             // 인스턴스 클래스
        int iv = 100;
//        static int cv = 100;         // 에러: static변수를 선언할 수 없다.
        final static int CONST = 100; // final static은 상수이므로 허용
    }

    static class StaticInner { // 스태틱 클래스
        int iv = 200;
        static int cv = 200;   // static클래스만 static 멤버를 정의할 수 잇따.
    }

    void myMethod() {
        class LocalInner {               // 지역 클래스
            int iv = 300;
//            static int cv = 300;        // 에러: static변수를 선언할 수 없다.
            final static int CONST = 300; // final static은 상수이므로 허용
        }

        int i = LocalInner.CONST;         // 지역 내부 클래스는 메서드 내에서만 불러올 수 있다.
    }

    public static void main(String[] args) {
        System.out.println(InstanceInner.CONST); // 상수 불러오기: 클래스명.상수변수명
        System.out.println(StaticInner.cv);      // cv 불러오기: 클래스명.cv명
//        System.out.println(LocalInner.CONST);  // 지역 내부 클래스는 다른 곳에서 불러올 수 없다.

        // 익명 클래스 ex2)
        Button b = new Button("Start");
        b.addActionListener(new ActionListener() { // 클래스의 정의와 객체 생성을 동시에 1회성 사용
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionEvent 발생!");
            }
        });
    }
//    위 익명 클래스로 했기에 아래 클래스가 필요 없어짐
//    class EventHandler implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("ActionEvent 발생!");
//        }
//    }


    /**
     * 익명 클래스
     * : 1.이름이 없는 일회용 클래스로
     *   2.정의와 생성을 동시에 한다.
     *   즉, 1회성으로 사용할 경우 이 클래스를 사용
     *
     * - 선언 방법 1
     * new 조상클래스이름() {
     *     // 클래스 내용
     *     // 멤버 선언
     * }
     *
     * - 선언 방법 2
     * new 구현인터페이스이름() {
     *     // 멤버 선언
     * }
     */
    Object iv = new Object() {
        void method(){}
    };
    static Object cv = new Object() {
        void method(){}
    };
    void myMethod2() {
        Object iv = new Object() {
            void method(){}
        };
    }
}


class Outer {
    private int outerIv = 0;
    private static int outerCv = 0;

    class InstanceInner {
        int iiv = outerIv;    // * 1.외부 클래스의 private 멤버도 접근 가능하다.
        int iiv2 = outerCv;
    }

    static class StaticInner {
        // 스태틱 클래스는 외부 클래스의 인스턴스 멤버에 접근할 수 없다.
//        int siv = outerIv;

        static int scv = outerCv;
    }

    void myMethod() {
        int lv = 0;
        final int LV = 0;  // JDK 1.8부터 final 생략 가능하지만 오해할 수 있으므로 fianl을 붙이자

        class LocalInner {  // 이 클래스가 위 메서드보다 오래 살아남을 수 있다.
            int liv = outerIv;
            int liv2 = outerCv;

            // 외부 클래스의 지역변수는 final이 붙은 변수(상수)만 접근 가능하였지만
            // JDK 1.8부터는 생략 가능하여 final이 없어도 가능함
            // 즉, * 2.지역 내부 클래스를 감싸고 있는 메서드의 상수만 사용 가능하다.
            int liv3 = lv;
            int liv4 = LV;
        }

    }
}