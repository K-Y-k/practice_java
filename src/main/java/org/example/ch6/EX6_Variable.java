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
class Variables {   /* 클래스 영역*/
    int iv;         // 인스턴스 변수
    static int cv;  // 클래스 변수(static변수, 공유변수)


    void method() { /* 메서드 영역*/
        int lv = 0; // 지역변수
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

