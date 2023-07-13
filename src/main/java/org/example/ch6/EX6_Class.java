package org.example.ch6;

public class EX6_Class {

    public static void main(String[] args) {
        /**
         * 클래스의 정의 3가지 관점
         * 1. 설계도
         * 2. 데이터 + 함수의 결합
         * 3. 사용자 정의 타입
         */

        /**
         * 데이터 + 함수의 결합 관점
         * 1. 변수   : 하나의 데이터를 저장할 수 있는 공간
         * 2. 배열   : 같은 종류의 여러 데이터를 하나로 저장할 수 있는 공간
         * 3. 구조체 : 서로 관련된 여러 데이터(다른 종류 타입 가능)를 하나로 저장할 수 있는 공간
         * 4. 클래스 : 서로 관련된 데이터와 함수의 결합 (구조체 + 함수)
         */

        /**
         * 사용자 정의 타입 관점
         * : 원하는 타입을 직접 만들 수 있다.
         *
         * ex) 시간을 다루기 위한 타입을 만들기 위한 클래스 선언
         * - 클래스를 사용하지 못한경우
         * 1)
         * int hour;
         * int minute;
         * int second;
         *
         * 2)
         * int hour1, hour2, hour3;
         * int minute1, minute2, minute3;
         * int second1, second2, second3;
         *
         * 3)
         * int[] hour = new int[3];
         * int[] minute = new int[3];
         * int[] second = new int[3];
         *
         *
         * - 클래스를 활용한 경우 : 서로 관련있는 것끼리 강한 결합이 되어 객체지향적 코드가 된다.
         * class Time {
         *      int hour;   // 시간
         *      int minute; // 분
         *      int second; // 초
         * }
         *
         * 1)
         * Time t = new Time();
         *
         * 2)
         * Time t1 = new Time();
         * Time t2 = new Time();
         * Time t3 = new Time();
         *
         * 3)
         * Time[] t = new Time[3];
         * t[0] = new Time();
         * t[1] = new Time();
         * t[2] = new Time();
         *
         *
         * => 즉, 코드가 간결해지고 유지보수가 더 쉬워진다.
         *
         */
    }
}
