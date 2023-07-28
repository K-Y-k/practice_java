package org.example.ch14;


import java.util.function.Function;

/**
 * 메서드 참조 = 클래스이름::메서드이름
 * : 하나의 메서드만 호출하는 람다식은 메서드 참조로 더 간단히 할 수 있다.
 *
 *       종류                                 람다식                          메서드 참조
 * static 메서드 참조                   (x) -> ClassName.method(x)        ClassName::method
 * 인스턴스 메서드 참조                   (x) -> ClassName.method(x)        ClassName::method
 * 특정 객채 인스턴스 메서드 참조 (안씀)    (x) -> ClassName.method(x)        obj::method
 *
 *
 * - static 메서드 참조
 * Integer method(String s) {   // 그저 Integer.parseInt(String s)만 호출
 *     return Integer.parseInt(s);
 * }
 *
 * int result = obj.method("123");      // 메서드 호출
 * int result = Integer.parseInt("123"); // 그저 Integer.parseInt(String s)만 호출하므로 이렇게 가능
 * => 람다식으로 바꾸기 Function<String, Integer> f = (String s) -> Integer.parseInt(s);  // 입력정보<String>이 있어 (String s), (s)이 필요없다.
 * =>                Function<String, Integer> f = Integer::parseInt;                  // 다 생략해버린 메서드 참조
 *                      이렇게 메서드 참조로 바꾸는 것을 연습해야함
 */


/**
 * - 생성자의 메서드 참조
 * // 객체를 생성해서 주기
 * Supplier<MyClass> s = () -> new MyClass();    // 람다식 방식, Supplier는 반환값만 있어 매개변수가 ()로 빈 것이다.
 * Supplier<MyClass> s = MyClass::new;           // 메서드 참조 방식 (메서드 대신 new 연산자를 넣음)
 *
 * Function<Integer, MyClass> s = (i) -> new MyClass(i);
 * Function<Integer, MyClass> s = MyClass::new;
 *
 *
 * - 배열과 메서드 참조
 * Function<Integer, int[]> f = x -> new int[x];  // 람다식 방식
 * Function<Integer, int[]> f2 = int[]::new;      // 메서드 참조 방식
 */

public class EX14_Method_Reference {
    public static void main(String[] args) {
        // 예제 1) 메서드 참조 활용
//        Function<String, Integer> f = (String s) -> Integer.parseInt(s);
//        Function<String, Integer> f = 클래스이름::메서드이름
        Function<String, Integer> f = Integer::parseInt; // 람다식을 메서드 참조로 바꾼 것
//      람다식 바꾸기 연습
//      1.입력값을 표현하기 (String s)
//      : Function<String, Integer> f = (String s) -> Integer::parseInt;
//      2.메서드.으로 바꾸고 (매개변수)도 추가하기
//      : Function<String, Integer> f = (String s) -> Integer.parseInt(s);
        System.out.println(f.apply("100")+200);


        // 예제 2) 생성자의 메서드 참조 활용
        // Supplier는 입력X, 출력O
        // 즉, 만약생성자에 입력이 있으면 Supplier 사용불가
//        Supplier<MyClass> s = () -> new MyClass();
//        Supplier<MyClass> s = MyClass::new;

        // 입력O, 출력O인 경우 Function을 사용
//        Function<Integer, MyClass> f2 = (i) -> new MyClass(i); // 람다식 방식
        Function<Integer, MyClass> f2 = MyClass::new;            // 메서드 참조 방식

        MyClass mc = f2.apply(100);           // == new MyClass(100)
        System.out.println(mc.iv);
        System.out.println(f2.apply(200).iv); // 위를 한줄로 표현한 것

        // 배열과 메서드 참조
        // 배열 길이가 주어져야하므로 Function을 사용
//        Function<Integer, int[]> f3 = x -> new int[x];
        Function<Integer, int[]> f3 = int[]::new;
        int[] arr = f3.apply(100);

        System.out.println("arr.length = " + arr.length);
    }
}

class MyClass {
    int iv;

    public MyClass() {
        this.iv = iv;
    }

    public MyClass(int iv) {
        this.iv = iv;
    }
}
