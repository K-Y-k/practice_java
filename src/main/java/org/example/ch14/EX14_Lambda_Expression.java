package org.example.ch14;


/**
 * 함수형 언어
 * : 자바에서는 OOP(객체지향)언어인데 JDK1.8부터 함수형 언어의 기능을 포함시켰다.
 *   대표적인 함수형 언어는 Haskell, Evlang, Scala
 *   엄청난 양의 데이터(빅데이터)를 처리하기 위해 필요해졌다.
 */

/**
 * 람다식
 * : 함수(메서드)를 간단한 '식'으로 표현하는 방법, 함수형 언어
 * ex)   함수(메서드)                                람다식
 * int max(int a, int b) {
 *     return a > b ? a : b;          ->    (a, b) -> a > b ? a : b
 * }
 *
 * - 익명 함수(이름이 없는 함수) (람다식은 익명 함수가 아니라 익명 객체이다.)
 * ex) 반환타입과 이름을 지움
 * (int a, int b) -> {
 *     return a > b ? a : b;
 * }
 *
 * - 함수와 메서드의 차이
 * : 근본적으로 동일하지만
 *   1.함수는 일반적 용어이고 메서드는 객체지향개념 용어이다.
 *   2.함수는 클래스에 독립적(바깥)이고 메서드는 클래스에 종속적(내부)이다.
 */

/**
 * 람다식 작성하기
 * 1.메서드의 이름과 반환타입을 제거하고 '->'를 블록 { 앞에 추가한다.
 *   int max(int a, int b) {               (int a, int b) -> {
 *       return a > b ? a : b;      =>         return a > b ? a : b;
 *   }                                     }
 *
 * 2.반환값이 있는 경우, 식이나 값만 적고 return문 생략 가능(끝에 ; 안 붙임)
 *   (int a, int b) -> {
 *       return a > b ? a : b;      =>     (int a, int b)-> a > b ? a : b
 *   }
 *
 * 3.매개변수의 타입이 추론 가능하면 생략가능(대부분 추론이 가능하여 생략가능)
 *   (int a, int b) -> a > b ? a : b =>    (a, b) -> a > b ? a : b
 */

/**
 * 람다식 작성 주의사항
 * 1.매개변수가 하나인 경우, 타입이 없을 때만 괄호() 생략가능
 *   ex)
 *   (a) -> a * a         => a -> a * a     // 가능
 *   (int a) -> a * a     => int a -> a * a // 에러 : 타입이 있으면 괄호 있어야함
 *
 * 2.블록 안의 문장이 하나뿐 일 때 괄호{} 생략가능(끝에 ; 안붙임)
 *   ex)
 *   (int i) -> {
 *       System.out.println(i)    =>    (int i) -> System.out.println(i)
 *   }
 *
 *   단, 하나뿐인 문장이 return문이면 괄호{} 생략불가 (대부분 return 생략가능하여 이 부분은 넘어가기)
 *   (int a, int b) -> { return a > b ? a : b;} // 가능
 *   (int a, int b) -> return a > b ? a : b     // 에러
 */

/**
 * 람다식의 예
 *     메서드                                 람다식
 *  int(int a, int b) {
 *      return a > b ? a : b;              (a, b) -> a > b ? a : b
 *  }
 *
 *  int printVar(String name, int i) {
 *      System.out.println(name+"="+i);    (name, i) -> System.out.println(name+"="+i)
 *  }
 *
 *  int square(int x) {
 *      return x * x;                       x -> x * x
 *  }
 *
 *  int roll() {
 *      return (int)(Math.random() * 6);    () -> (int)(Math.random() * 6)
 *  }
 */

/**
 * 람다식은 익명 함수가 아니라 익명 객체(익명 클래스)이다.
 * 선언과 생성을 동시에 한다.
 * ex)                    둘이 같음
 * (a, b) -> a > b ? a : b   ==  new Object() {  // 선언과 생성을 동시에 한 것, 원래 이렇게 작성해야 하는 것
 *                                   int max(int a, int b) {
 *                                       return a > b ? a : b;
 *                                   }
 *                               };
 *
 * 람다식(익명 객체)을 다루기 위한 참조변수가 필요한데 참조변수의 타입은?
 * Object obj = new Object() {
 *     int max(int a, int b) {
 *         return a > b ? a : b;
 *     }
 * };
 */

public class EX14_Lambda_Expression {
    public static void main(String[] args) {
        // Object obj = (a, b) -> a > b ? a : b;  // 익명객체를 식으로 표한한 것 : 람다식
                                                  // 하지만 반환타입이 Object가 아니고 다른 반환타입 객체로 반환해야한다.
                                                  // 즉, 반환타입 객체를 위한 함수형 인터페이스가 아직 없어 사용 못함
        Object obj = new Object() {  // 자바에서는 메서드만 존재할 수 없기에 익명 객체안에 넣어서 한 것
            int max(int a, int b) {
                return a > b ? a : b;
            }
        };

//      int value = obj.max(3, 5); // Object 클래스에는 max() 메서드가 없기에 호출 불가
                                   // 그래서 필요한게 함수형 인터페이스이다!
    }

}
