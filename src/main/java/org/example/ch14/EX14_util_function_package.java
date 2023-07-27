package org.example.ch14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


/**
 * java.util.function 패키지
 * : 자주 사용되는 다양한 함수형 인터페이스를 제공한다.
 *   즉, 표준화된다는 장점
 *
 *   함수형 인터페이스           메서드                      설명
 * java.lang.Runnable       void run()           매개변수도, 반환값 없을 때 사용
 *
 * Supplier<T>              T get()              매개변수는 없고 반환값만 있을 때 사용
 *
 * Consumer<T>              void accept(T t)     매개변수만 있고 반환값이 없을 때 사용
 *
 * Function<T,R(반환)>       R apply(T t)         일반적인 함수로, 하나의 매개변수를 받아서 결과를 반환할 때 사용
 *
 * Predicate<T>             boolean test(T t)    조건식을 표현할 때 사용
 *                                               매개변수는 하나고 반환타입은 boolean
 *
 * ex)
 * Supplier<Integer> s = () -> (int)(Math.random()*100)+1;
 * Consumer<Integer> c = i -> System.out.print(i+", ");
 * Predicate<Integer> p = i -> i%2==0;
 * Function<Integer, Integer> f = i -> i/10*10;
 *
 * Predicate<String> isEmptyStr = s -> s.length()==0;
 * String s = "";
 * if (isEmptyStr.test(s)) // s가 if (s.length()==0)이다.
 *     System.out.println("This is an empty String.");
 *
 */

/**
 * - 매개변수가 2개인 함수형 인터페이스 (Bi가 Binary로 2개)
 *   Supplier는 매개변수가 없고 반환만하므로 없다.
 *
 *   함수형 인터페이스                메서드                           설명
 * Biconsumer<T,U>           void accept(T t, U u)       두개의 매개변수만 있고, 반환값이 없음
 *
 * BiPredicate<T,U>          boolean test(T t, U u)      조건식을 표현하는데 사용됨
 *                                                       매개변수는 둘, 반환값은 boolean
 *
 * BiFunction<T,U,R(반환)>    R apply(T t, U u)           두개의 매개변수를 받아서 하나의 결과를 반환
 */

/**
 * - 3개 매개변수
 * @FunctionalInterface
 * interface TriFunction<T,U,V,R>< {
 *     R apply(T t, U u, V v)
 * }
 */

/**
 * - 매개변수의 타입과 반환타입이 일치하는 함수형 인터페이스
 *
 *   함수형 인터페이스           메서드                      설명
 * UnaryOperator<T>         T apply(T t)             Function의 자손,
 *  (단항 연산자)                                      Function과 달리 매개변수와 결과의 타입이 같다.
 *
 * BinaryOperator<T>        T apply(T t, T t)        BiFunction의 자손,
 *  (이항 연산자)                                      BiFunction과 달리 매개변수와 결과의 타입이 같다.
 */

public class EX14_util_function_package {
    public static void main(String[] args) {
        // 예제 1) java.util.function 패키지의 다양한 함수형 인터페이스 활용
        Supplier<Integer> s = () -> (int)(Math.random()*100)+1; // 1~100난수
        Consumer<Integer> c = i -> System.out.print(i+", ");
        Predicate<Integer> p = i -> i%2==0;                     // 짝수인지 검사
        Function<Integer, Integer> f = i -> i / 10 * 10;        // i의 일의 자리를 없앤다.


        List<Integer> list = new ArrayList<>();

        makeRandomList(s, list);  // 리스트를 랜덤값으로 채운다.
        System.out.println(list);
        printEvenNum(p, c, list); // 짝수를 출력
        List<Integer> newList = doSomething(f, list);
        System.out.println(newList);
        System.out.println();

    }


    // Function<Integer, Integer> f = i -> i/10*10;
    static <T> List<T> doSomething(Function<T, T> f, List<T> list) {
        List<T> newList = new ArrayList<T>(list.size());

        for (T i : list) {
            newList.add(f.apply(i));  // 일의 자리를 없애서 새로운 리스트에 저장
        }

        return newList;
    }


    // Consumer<Integer> c = i -> System.out.print(i+", ");
    // Predicate<Integer> p = i -> i%2==0;
    static <T> void printEvenNum(Predicate<T> p, Consumer<T> c, List<T> list) {
        System.out.print("[");

        for (T i : list) {
            if (p.test(i))   // 짝수인지 검사
                c.accept(i);
        }

        System.out.println("]");
    }

    static <T> void makeRandomList(Supplier<T> s, List<T> list) {
        for (int i = 0; i < 10; i++) {
            list.add(s.get());  // Supplier로 부터 1~100의 난수를 받아서 list에 추가
        }
    }
}
