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
 * - 매개변수가 3개인 함수형 인터페이스
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


/**
 * Predicate의 결합
 * - and(), or(), negate()로 두 Predicate를 하나로 결합(default메서드, static메서드, 추상메서드)할 수 있다.
 *   ex)
 *   Predicate<Integer> p = i -> i < 100;
 *   Predicate<Integer> q = i -> i < 200;
 *   Predicate<Integer> r = i -> i%2 == 0;
 *
 *   Predicate<Integer> notP = p.negate();        // i >= 100
 *   Predicate<Integer> all = notP.and(q).or(r);  // 100 <= i && i < 200 || i%2==0
 *   Predicate<Integer> all2 = notP.and(q.or(r)); // 100 <= i && (i < 200 || i%2==0)
 *
 *   System.out.println(all.test(2));  // true   test가 Predicate의 추상메서드
 *   System.out.println(all2.test(2)); // false
 *
 * 
 * - 등가비교를 위한 Predicate의 작성에는 isEqual()를 사용(static 메서드)
 *   Predicate<String> p = Predicate.isEqual(str1)  // isEquals()은 static 메서드
 *   Boolean result = p.test(str2);                 // str1과 str2가 같은지 비교한 결과를 반환
 *   => 한줄로 표헌: boolean result = Predicate.isEqual(str1).test(str2);
 */


/**
 * 컬렉션 프레임워크와 함수형 인터페이스
 * : 함수형 인터페이스를 사용하는 컬렉션 프레임워크의 메서드(와일드 카드 생략)
 *
 *   인터페이스                 메서드                                       설명
 * Collection         boolean removeIf(Predicate<E> filter)          조건에 맞는 요소를 삭제
 *
 * List               void replaceAll(UnaryOperator<E> operator)     모든 요소를 변환하여 대체
 *
 * Iterable           void forEach(Consumer<T> action)               모든 요소에 작업 action을 수행
 *
 * Map                V compute(K key, BiFunction<K.V.V f>           지정된 키의 값에 작업 f를 수행
 *                    V computeIffAbsent(K key, Function<F.V> f)     키가 없으면, 작업 f 수행 후 추가
 *                    V computeIfPresent(K key, BiFunction<K.V.V> f) 지정된 키가 있을 때 작업 f 수행
 *                    V merge(K key, V value. BiFunction<K.V.V> f)   모든 요소에 병합작업 f를 수행
 *                    void forEach(BiConsumer<K.V> action)           모든 요소에 작업 action을 수행
 *                    void replaceAll(BiFunction<K.V.V> f)           모든 요소에 치환작업 f를 수행
 *
 *
 * ex)
 * list.forEach(i->System.out.print(i+","));  // list의 모든 요소를 출력
 * list.removeIf(x->x%2==0 || x%3==0);        // 2 또는 3의 배수를 제거
 * list.replaceAll(i->i*10);                  // 모든 요소에 10을 곱한다.
 * // map의 모든 요소를 {k,v}의 형식으로 출력
 * map.forEach((k,v)->System.out.print("{"+k+","+v+"},"));
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


        // 예제 2) Predicate의 결합 활용
        Function<String, Integer> f2 = (s2)->Integer.parseInt(s2,16);  // 16진수 -> 10진수로 변경
        Function<Integer,String> g2 = (i)->Integer.toBinaryString(i);

        // 람다식을 하나로 연결
        //    <f2의 매개변수, g2의 반환타입>
        Function<String,String> h2 = f2.andThen(g2);   // 각 Function을 합친 것, f2의 반환 값이 Integer라서 g2의 매개밴수가 Integer라서 적용가능한 것
        //    <g2의 매개변수, f2의 반환타입>
        Function<Integer,Integer> h3 = f2.compose(g2); // 위와 반대로 g2.andThen(f2) 즉 andThen만 알아도 됨

        System.out.println(h2.apply("FF"));        // "FF" -> 255 -> "11111111"
        System.out.println(h3.apply(2));           // 2 -> "10" -> 16

        Function<String, String> f3 = x -> x;         // 항등 함수(매개변수의 값이 그대로 반환 값으로 나오는 것)
        System.out.println(f3.apply("AAA"));       // AAA가 그대로 출력됨

        Predicate<Integer> p2 = i -> i < 100;
        Predicate<Integer> q2 = i -> i < 200;
        Predicate<Integer> r2 = i -> i % 2 == 0;
        Predicate<Integer> notP = p2.negate();        // i <1 00을 반대로 하므로 i >= 100

        Predicate<Integer> all = notP.and(q2.or(r2)); // (i >= 100) && (i < 200 || i % 2 == 0)
        System.out.println(all.test(150));         // true

        String str1 = "abc"; // abc 새로 생성
        String str2 = "abc"; // 위 생성한 abc의 주소를 가리킴

        // str1과 str2가 같은지 비교한 결과를 반환  
        // 만약 한 쪽은 new String("abc")였으면 새로 주소가 생성되므로 equals는 주소를 비교하므로 false가 나옴
        Predicate<String> p3 = Predicate.isEqual(str1);
        boolean result = p3.test(str2);
        System.out.println(result);


        // 예제 3) 함수형 인터페이스를 사용하는 컬렉션 프레임워크의 메서드 활용
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list2.add(i);
        }

        // 람다식으로 리스트의 모든 요소를 출력
        list2.forEach(i-> System.out.print(i+","));
        System.out.println();
//      리스트의 모든 요소를 출력 : 과거 버전
//        Iterator it = list2.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }


        // 리스트에서 2 또는 3의 배수를 제거한다.
        list2.removeIf(x-> x%2==0 || x%3==0);
        System.out.println(list2);

        list2.replaceAll(i->i*10); // 리스트의 각 요소에 10을 곱한다.
        System.out.println(list2);

        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        // map의 모든 요소를 {k,v}의 형식으로 출력한다.
        map.forEach((k,v)-> System.out.print("{"+k+","+v+"},"));
//      모든 요소 출력 과거 버전
//        Iterator it = list2.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
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
