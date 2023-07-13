package org.example.ch6;

import java.sql.Time;

public class EX6_Constructor {

    public static void main(String[] args) {
        /**
         * 생성자
         * : 인스턴스가 생성될 때마다 호출되는 '인스턴스 초기화 메서드'
         *   즉, iv 초기화 메서드
         *
         *  - 가본 생성자로 객체 생성 및 iv 초기화 예시
         *  Time t = new Time();
         *  t.hour = 12;
         *  t.minute = 34;
         *  t.second = 56;
         *
         *  - 생성자 호출하여 초기화 예시 : 더 간단하게 표현 가능해짐
         *  Time t = new Time(12, 34, 56);
         *
         *  - 조건
         *  생성자 이름이 클래스 이름과 같아야 한다.
         *  리턴 값이 없지만 void 안붙인다.
         *  모든 클래스는 반드시 생성자를 가져야 한다.
         *
         *  ex)
         *  class Card {
         *      생성자 오버로딩으로 매개변수가 다르다.
         *      Card() {
         *      }
         *      Card(String kind, int number) {
         *      }
         *  }
         */
        // 생성자 메서드를 이용한 것
        Time t = new Time(12, 34, 56);


        /**
         * 기본 생성자
         * : 매개변수가 없는 생성자
         *   생성자가 하나도 없을 때만 컴파일러가 자동으로 추가한다.
         *   즉, 다른 생성자를 만들면 기본 생성자 추가를 잊으면 언된다.
         *       클래스를 만들 때 기본 생성자를 추가하는 습관을 가지자!
         *
         *   ex) Time t = new Time();의 Time() 이것이 기본 생성자
         */
        Data_1 data1 = new Data_1();
        Data_2 data2_1 = new Data_2();
        Data_2 data2_2 = new Data_2(3);


    }
}

class Data_1 {
    int value;

    // 다른 생성자가 하나도 없으므로 기본 생성자를 자동으로 만들어줌
}

class Data_2 {
    int value;

    // 아래 다른 생성자를 만들었으므로 기본 생성자 추가한 것
    Data_2() {
    }

    Data_2(int x) {
        value = x;
    }
}

class Car {
    String color;
    String gearType;
    int door;

    Car() {
        // 코드의 중복이 되는 코드
        // color = "white";
        // gear = "auto";
        // door = 4;

        /**
         * 생성자 this()와 참조변수 this는 완전히 다르다.
         */

        /**
         * - 생성자 this
         * : 생성자에서 다른 생성자 호춣할 때 사용
         *   같은 클래스 안의 생성자끼리는 this()로 표현할 수 있다!
         *   즉, 여기서 this가 Car이다.
         */
        // Car() 안에 값을 넣지 않으면
        // 기본 생성자에 기본으로 해당 값이 넣어지게 하여
        // 아래 Car(String color, String gearType, int door) 생성자를 호출하게 한 구조로
        // 코드의 중복을 제거한 코드가 된다!
        this("white", "auto", 4); // = Car("white", "auto", 4);
    }
    Car(String color) {
        this("white", "auto", 4);
    }
    Car(String color, String gearType, int door) {
        /**
         * - 참조변수 this
         * : 인스턴스 자신을 가리키는 참조변수이다.
         *   인스턴스 메서드(생성자 포함)에서만 사용가능하다. (즉, static 클래스 메서드 사용 불가)
         *   지역변수(lv)와 인스턴스 변수(iv)를 구별할 때 사용한다.
         */
        this.color = color;        // 지역변수명과 인스턴스 변수명과 같으면 구분해야하므로 참조변수(iv) this를 사용해야한다.
        this.gearType = gearType;  // 지역변수명과 인스턴스 변수명이 다르면 this가 생략 가능하긴 하다.
        this.door = door;
    }

}