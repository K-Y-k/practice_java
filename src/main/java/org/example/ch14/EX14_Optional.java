package org.example.ch14;

import java.util.Optional;
import java.util.OptionalInt;


/**
 * Optional<T>
 * : T 타입 객체의 래퍼클래스이다.
 *   간접적으로 null을 다룰 수 있어져 NPE가 발생하지 않고 null 체크 코드를 생략할 수 있다.
 *   (직접 다룰 때는 null 체크를 위한 if문 필수로 코드가 지저분해짐)
 *
 * public final Class Optional<T> {
 *     private final T value; // T타입의 참조 변수로 모든 종류의 객체를 저장할 수 있다.
 *                               즉, null도 넣을 수 있다.
 *                               null은 NPE가 발생하여 직접 다루는 것은 위험하여
 *                               이렇게 하여 간접적으로 null을 다룰 수 있다.
 *     ...
 * }
 *
 * - 장점
 * : 간결하고 명확한 코드가 된다.
 *
 * - 단점
 * : 성능이 조금 느려진다.
 */

/**
 * Optional<T> 객체 생성하기
 * - Optional<T> 객체를 생성하는 다양한 방법
 * String str = "abc";
 * Optional<String> optVal = Optional.of(str);
 * Optional<String> optVal = Optional.of("abc");
 * Optional<String> optVal = Optional.of(null);          // NPE 예외 발생, null을 그냥 넣을 수 없다.
 * Optional<String> optVal = Optional.ofNullable(null);  // 가능, ofNullable로 null을 넣을 수 있다.
 *
 *      1.만약 null이면
 * str    |0x100| -> |"abc"|
 *
 *                  Optional객체
 * optval |0x200| -> |0x100| -> |"abc"|
 *                  2.여기가 null로
 *     3.optval은 null이 절대 될 수 없게됨!
 *
 *
 * - null 대신 빈 Optional<T>객체를 사용하자
 * Optional<String> optVal = null;                    // X, 널로 초기화(바람직하지 않음)
 * Optional<String> optVal = Optional.empty();        // O, 빈 객체로 초기화
 *
 * iv에서 초기화할 때 Object[] abjArr = null; 말고
 *                          objArr = new Object[0];로 초기화 해야하는 것과 같다.
 */

/**
 * Optional<T> 객체의 값 가져오기
 * - Optional 객체의 값 가져오기 : get(), orElse(), orElseGet(), orElseThrow()
 * Optional<String> optVal = Optional.of("abc");
 * String str1 = optVal.get();                                  // optVal에 저장된 값을 반환, null이면 NPE 예외 발생 (잘 안씀)
 * String str1 = optVal.orElse("");                             // optVal에 저장된 값이 null일 때는 ""를 반환       (잘 씀)
 * String str1 = optVal.orElseGet(String::new);                 // 람다식 사용가능 () -> new String()             (잘 씀)
 * String str1 = optVal.orElseThrow(NullPointerException::new); // null이면 NPE 예외 발생
 *
 *
 * - isPresent() - Optional 객체의 값이 null이면 false, 아니면 true 반환
 * if (Optional.ofNullable(str).isPresent()) { // == if (str!==null)
 *     System.out.println(str);
 * }
 *    // ifPresent(Consumer) : null이 아닐 때만 작업 숭행, null이면 넘어감
 * => Optional.ofNullable(str).ifPresent(System.out::println);
 */


/**
 * OptionalInt, OptionalLong, OptionalDouble
 * : 기본형 값을 감싸는 래퍼클래스
 *   Optional<T>로 사용해도 되지만, 성능 향상이 필요할 때 사용하는 것이다.
 * 
 * public final class OptionalInt {
 *     ...
 *     private final boolean isPresent; // 값이 저장되어 있으면 true
 *     private final int value;         // 기본형 int 타입의 변수
 * }
 *
 *
 * - OptionalInt/Long/Double의 값 가져오기 : int getAsInt/Long/Double()
 *     Optional 클래스            값을 반환하는 메서드
 *      Optional<T>             T  get()
 *      OptionalInt             int getAsInt()
 *      OptionalLong            long getAsLong()
 *      OptionalDouble          double getAsDouble()
 *
 *
 * - 빈 Optional 객체와의 비교
 *  value는 0으로 같지만, isPresent()의 값은 다르게 저장됨
 *
 * OptionalInt opt = OptionalInt.of(0);     // OptionalInt에 0을 저장, isPresent()의 값은 true
 * OptionalInt opt2 = OptionalInt.empty();  // OptionalInt에 빈 객체를 저장, isPresent()의 값은 false
 *
 * System.out.println(opt.isPresent());     // true
 * System.out.println(opt2.isPresent());    // false
 * System.out.println(opt.equals(opt2));    // false
 */

public class EX14_Optional {
    public static void main(String[] args) {
        // 예제 1) Optional
//        int[] arr = null;             // 이렇게 하면 NPE발생
        int[] arr = new int[0];
        System.out.println("arr.length = " + arr.length);

//        Optional<String> opt = null;  // 가능하지만 바람직하지 않다.
        Optional<String> opt = Optional.empty();
//        Optional<String> opt = Optional.of("abc");
        System.out.println("opt = " + opt);
//        System.out.println("opt = " + opt.get());
        // NoSuchElementException 발생하여 .get()을 사용 안함
        String str = "";
        // 그래서 try-catch로 감싸주고
//        try {
//            str = opt.get();
//        } catch (Exception e) {
//            str = "";           // 예외가 발생하면 빈문자열("")로 초기화하는 방식으로 할 수 있다.
//        }

        // 하지만 try-catch문이 번거로워 orElse()를 이용하여 쉽게 할 수 있다.
        str = opt.orElse("EMPTY");       // Optional에 저장된 값이 null이면 ""반환
        //                (()->new String())
        str = opt.orElseGet(String::new); // Optional에 저장된 값이 null이면
        System.out.println("str = " + str);

        System.out.println();


        // 예제 2)
        Optional<String> optStr = Optional.of("abcde");

//                               람다식 버전   (s->s.length())
        Optional<Integer> optInt = optStr.map(String::length);

        System.out.println("optStr = " + optStr.get());
        System.out.println("optInt = " + optInt.get());

        int result1 = Optional.of("123")
                .filter(x -> x.length() > 0)   // 0보다 큰 것만 적용
                .map(Integer::parseInt).get(); // 숫자로 변환하고 저장

        int result2 = Optional.of("")
                .filter(x -> x.length() > 0)   // ""로 빈 값이라
                .map(Integer::parseInt).orElse(-1); // 숫자로 변환하면 값이 null이므로 -1로 나옴

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);

        Optional.of("456").map(Integer::parseInt) // 값 "456"를 숫자로 바꾸고
                .ifPresent(x->System.out.printf("result3 = %d%n", x)); // 값이 있으므로 출력

        OptionalInt optInt1 = OptionalInt.of(0);  // 0을 저장 isPresent:true
        OptionalInt optInt2 = OptionalInt.empty();      // 빈 객체를 생성 isPresent:false

        System.out.println(optInt1.isPresent());        // true
        System.out.println(optInt2.isPresent());        // false

        System.out.println(optInt1.getAsInt());         // 0
//        System.out.println(optInt2.getAsInt());       // 빈 객체인데 꺼내려고 하여 NoSuchElementException 예외발생
        System.out.println("optInt1 = " + optInt1);
        System.out.println("optInt2 = " + optInt2);
        System.out.println("optInt1.equals(optInt2)?" + optInt1.equals(optInt2)); // 값이 0인 것과 비어있는 것이 서로 다르므로

        System.out.println();
    }
}
