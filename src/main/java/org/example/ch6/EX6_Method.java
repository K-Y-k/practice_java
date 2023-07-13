package org.example.ch6;

public class EX6_Method {
    /**
     * 메서드란?
     * 1. 작업단위로 문장들을 묶어서 이름 붙인 것 { }
     * 2. 값(입력)을 받아서 처리하고 결과를 반환(출력)할 수 있다.
     * 3. 메서드는 함수와 같지만 메서드는 클래스 안에 있어야한다.
     *
     * - 장점)
     * 코드의 중복을 줄일 수 있다.
     * 코드의 관리가 쉽다.
     * 코드를 재사용할 수 있다.
     * 코드가 간결해지고 이해하기 쉬워진다.
     *
     * - 메서드의 작성
     * 반복적으로 수행되는 여러 문장을 메서드로 작성한다.
     * 하나의 메서드는 한 가지 기능만 수행하도록 작성한다. (최소의 단위로 만들어야 유지보수할 때 유리하고 재사용성이 높아진다.)
     */
    //                    매개변수     매개변수
    // 반환타입 메서드이름 (타입 변수명, 타입 변수명, ...) {  // 선언부
    //           구현부
    //     메서드 호출시 수행될 코드
    // }
    int add(int a, int b) { // 선언부
        // 구현부
        int result = a + b;
        return result;
    }
    public static void main(String[] args) {
        /**
         *  메서드 호출
         *
         *  메서드이름(값1, 값2, ...);
         */

        /**
         * 객체 사용하여 매서드 호출
         */
        MyMath mm = new MyMath();
        long result1 = mm.add(5L, 3L);
        long result2 = mm.subtract(5L, 3L);
        long result3 = mm.multiply(5L, 3L);
        double result4 = mm.divide(5L, 3L);

        mm.printGugudan(3);

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        System.out.println("result3 = " + result3);
        System.out.println("result4 = " + result4);
    }
}

/**
 * 1. 클래스 작성
 */
class MyMath {

    /**
     * 2. 메서드 작성
     */
    /**
     * return문
     * : 실행중인 메서드를 종료하고 호출한 곳으로 되돌아간다.
     */
    long add(long a, long b) {
        long result = a+b;  // 반환 타입이 void가 아니면 return 생략불가
        return result;
    }
    long subtract(long a, long b) {
        return a-b;
    }
    long multiply(long a, long b) {
        return a * b;
    }
    double divide(double a, double b) {
        return a / b;
    }

    void printGugudan(int dan) {
        if(!(2 <= dan && dan <= 9))
            return;
        for (int i = 1; i < 9; i++) {
            System.out.printf("%d * %d = %d%n", dan, i, dan * i);
        }
        // return;  반환 타입이 void이므로 생략 가능
    }
}
