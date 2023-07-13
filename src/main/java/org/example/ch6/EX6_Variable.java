package org.example.ch6;

/**
 * 선언위치에 따른 변수의 종류
 *
 * 1. 클래스 영역 : 선언문만 가능, 순서가 없다.
 *    - iv(instance variable) : 객체(=인스턴스)를 생성할 때 만들어진다.
 *    - cv(class variable)    : 클래스가 메모리(RAM)에 올라갈 때 (ex) 설계도가 필요할 때)
 *                              즉, 객체 생성할 필요가 없어 필요하면 사용할 수 있다.
 * 2. 메서드 영역
 *    - lv(local variable)    : 변수 선언문이 수행되었을 때
 */

/**
 * 변수의 초기화
 * - 멤버변수(iv, cv)는 자동 초기화 된다.
 * - 지역변수(lv)는 수동으로 초기화 해야한다.
 */

/**
 * 멤버변수의 초기화
 * - 클래스 변수(cv) 초기화 시점   : 클래스가 처음 로딩될 때 단 1번만
 * - 인스턴스 변수(iv) 초기화 시점 : 인스턴스 생성될 때 마다
 * => 즉, 초기화 순서는 1. cv -> iv
 *                   2. 자동 -> 간단 -> 복잡
 *
 * 1. 자동 초기화
 *
 * 2. 명시적(간단) 초기화(=)
 *     ex) 
 *     class Car {
 *         int door = 4;            // 기본형 변수의 초기화
 *         Engine e = new Engine(); // 참조형 변수의 초기화
 *     }
 *
 * 3. 복잡한 초기화
 * 3-1. 초기화 블럭
 * - 인스턴스 초기화 블럭 : { }
 * - 클래스 초기화 블럭   : static { }
 *
 * 3-2.생성자 : iv 초기화
 *    ex)
 *    Car(String color, String gearType, int door) {
 *        this.color = color;
 *        this.gearType = gearType;
 *        this.door = door;
 *    }
 *
 * => 주로 자동 초기화, 명시적 초기화룰 사용하고
 *    복잡한 경우 cv일 때는 static { }, iv일 때는 생성자를 사용한다.
 */
class Variables {   /* 클래스 영역*/
    int iv;         // 인스턴스 변수이고 이는 자동으로 초기화 된다.
    static int cv;  // 클래스 변수(static변수, 공유변수)


    void method() { /* 메서드 영역*/
        int lv = 0; // 지역변수
                    // 지역변수는 무조건 수동으로 초기화하고 사용해야한다.
    }
}


/**
 * 클래스 변수(cv)와 인스턴스 변수(iv)
 *
 * 개별적인 값을 가지는 것은 iv
 * 공통적인 값을 가지는 것은 cv
 *
 */
class Card {
    // 개별적인 값 = iv
    String kind;
    int number;

    // 공통적인 값 = cv
    static int width = 100;
    static int height = 250;
}


public class EX6_Variable {

    public static void main(String[] args) {
        // 클래스 변수 선언 방법 : 객체 생성 없이 사용가능
        System.out.println("Card.width = " + Card.width);
        System.out.println("Card.height = " + Card.height);

        Card.width = 200;
        Card.height = 300;
        System.out.println("Card.width = " + Card.width);
        System.out.println("Card.height = " + Card.height);


        Card card = new Card();
        // 인스턴스 변수 선언 방법 : 객체 생성이 있어야 사용가능
        card.kind = "HEART";
        card.number = 5;
        System.out.println("card.kind = " + card.kind);
        System.out.println("card.number = " + card.number);

        card.width = 50;  // 클래스 변수를 이렇게 할 수는 있지만 추천하지 않는다.
        card.height = 100;
        System.out.println("Card.width = " + Card.width);
        System.out.println("Card.height = " + Card.height);
    }
}

