package org.example.ch14;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * 스트림
 * : 다양한 데이터 소스(ex) 컬렉션(List/Set/Map 등), 배열 등)를 표준화된 방법으로 다루기 위한 것
 *   List/Set/Map 등의 자료 구조 방식이 다르기에 각 사용방법이 달라서
 *   JDK1.8이후 사용법을 통일 시키기 위해 나온 것이다.
 *
 * ex)
 * Stream<T> Collection stream() // 컬렉션을 스트림으로 변환
 *
 * List<Integer> list = Arrays.asList(1,2,3,4,5);
 * Stream<Integer> intStream = list.stream();                        // 컬렉션을 스트림으로 변환
 * Stream<Integer> strStream = Stream.of(new String[]{"a","b","c"}); // 배열은 Stream.of로 스트림 변환
 * Stream<Integer> eventStream = Stream.iterate(0, n->n+2);          //  0,2,4,6,...
 * Stream<Integer> randomStream = Stream.generate(Math::random);     // 람다식을 스트림으로 변환
 * IntStream intStream = new Random().int(5);                        // 난수 스트림(크기가 5)
 *
 * - 스트림 작성 3단계
 * 1.스트림 만들기
 * 2.중간연산 (0~n번 여러번 가능)
 * 3.최종연산 (1번)으로 결과 반환
 *
 * - 스트림이 제공하는 기능 : 중간 연산과 최종 연산
 * 중간 연산 : 연산결과가 스트림인 연산, 반복적(0~n번)으로 적용가능
 * 최종 연산 : 연산결과가 스트림이 아닌 연산, 단 한번만 적용가능(스트림의 요소를 소모)
 *
 *         중복제거    5개자르기   정렬              출력
 * stream.distinct().limit(5).sorted().forEach(System.out::println)
 *         중간연산    중간연산  중간연산          최종연산(1번)
 * ex)
 * String[] strArr = {"dd", "aaa", "CC", "cc", "b"};       // 1.스트림 만들기
 *
 *                                                         // 2.중간연산
 * Stream<String> stream = Stream.of(strArr);              // 문자열 배열이 소스인 스트림
 * Stream<String> filteredStream = stream.filter();        // 걸러내기     (중간 연산)
 * Stream<String> distinctStream = stream.distinct;        // 중복제거     (중간 연산)
 * Stream<String> sortedStream = stream.sort();            // 정렬        (중간 연산)
 * Stream<String> limitedStream = stream.limit(5);         // 스트림 자리기(중간 연산)
 *
 *                                                         // 3.최종연산
 * int total = stream.count();                             // 요소 개수 세기(최종 연산)
 *
 *
 * - 스트림의 특징
 * 1.스트림은 데이터 소스(원본)로부터 데이터를 일기만할 뿐 변경하지 않는다.
 *   List<Integer> list = Arrays.asList(3,1,5,4,2);
 *   List<Integer> sortedList = list.stream().sorted()      // list를 정렬해서
 *                        .collect(Collectors.toList());    // 새로운 List에 저장하기에 원본은 변경하지 않는다.
 *   System.out.println(list);                              // [3,1,5,4,2], 원본 변경 X
 *   System.out.println(sortedList);                        // [1,2,3,4,5]
 *
 *
 * 2.스트림은 Iterator처럼 일회용이다.(필요하면 다시 스트림을 생성해야함)
 *   strStream.forEach(System.out::println);               // 모든 요소를 화면에 출력(최종연산(요소를 소모))
 *   int numOfStr = strStream.count();                     // 에러, 스트림이 이미 닫혔음
 *   
 *   
 * 3.최종 연산 전까지 중간연산이 수행되지 않는다. - 지연된 연산
 * IntStream intStream = new Random().ints(1,46);          // 1~45범위의 무한 스트림
 * intStream.distinct().limit(6).sorted()                  // 중간 연산, 여기서 바로 해당 메서드들이 수행되는게 아닌 표시만 해두고 필요할 때 수행한다.(= 지연된 연산)
 *          .forEach(i->System.out.print(i+","));          // 최종 연산
 *
 *
 * 4. 스트림은 작업을 내부 반복으로 처리한다.
 * for(String str : strList)
 *     System.out.println(str);            =>     stream.forEach(System.out::println);
 *     |
 *     |
 *     v
 * void forEach(Consumer<\? super T> action) {
 *     Objects.requireNonNull(action); // 매개변수의 널 체크
 *
 *     for(T t : src)                  // 내부 반복(for문을 메서드 안으로 넣음)
 *        action.accept(T);
 * }
 *
 *
 * 5.스트림의 작업을 병렬로 처리 - 병렬스트림
 * : 빅데이터가 큰 작업으로 원할하게 수행하려면 멀티 쓰레드를 사용해야하는데, 멀티쓰레드는 병렬스트림으로 처리해야 한다.
 * Stream<String> strStream = Stream.of("dd", "aaa","CC","cc","b");
 * int sum = strStream.parallel()                    // 병렬 스트림으로 전환(속성만 변경), PS)기본처리(기본값)는 sequential()
 *                 .mapToInt(s -> s.length()).sum(); // 모든 문자열의 길이의 합
 *                 
 *                 
 * 6.기본형 스트림 - IntSteam, LongStream, DoubleStream
 * : 오토박싱&언박싱의 변환하는 과정이 비효율로 기본형 스트림이 나옴
 *   즉, 기본형->참조형일 때 사용하면 된다.(Stream<Integer>대신 IntStream 사용)
 *   숫자와 관련된 유용한 메서드를 Stream<T>보다 더 많이 제공한다.
 *   스트림의 성능이 잘 안나와서 성능을 개선하고 싶을 때 이것을 사용하자
 */


/**
 * 스트림 만들기 - 컬렉션
 * : Collection인터페이스의 stream()으로 컬렉션을 스트림으로 변환
 *   Stream<E> stream() // Collection 인터페이스의 메서드
 *
 *   List<Integer> list = Arrays.asList(1,2,3,4,5);
 *   Stream<Integer> intStream = list.stream();       // 리스트를 스트림으로 변환
 *
 *   // 스트림의 모든 요소를 출력
 *   intStream.forEach(System.out::print);            // 12345
 *   intStream.forEach(System.out::print);            // 에러, 위에서 이미 소모했으므로 스트림이 이미 닫혔다.
 */

/**
 * 스트림 만들기 - 배열
 * - 객체 배열로부터 스트림 생성하기
 * Stream<T> Stream.of(T... values) // 방법 1) Stream.of은 가변 인자
 * Stream<T> Stream.of(T[])         //        또는 배열을 넣을 수 있다.
 * Stream<T> Arrays.stream(T[])                                             // 방법 2) Arrays.stream는 배열
 * Stream<T> Arrays.stream(T[] array, int startInclusive, int endExclusive) //        또는 from ~ to(포함안됨) 범위 지정 가능
 *
 * Stream<String> strStream = Stream.of("a","b","c"); // 가변 인자
 * Stream<String> strStream = Stream.of(new String[]{"a","b","c"});
 * Stream<String> strStream = Arrays.stream(new String[]{"a","b","c"});
 * Stream<String> strStream = Arrays.stream(new String[]{"a","b","c"}, 0, 3);
 *
 * - 기본형 배열로부터 스트림 생성하기 (Stream이 아니라 IntStream)
 * IntStream IntStream.of(int... values) // 방법 1) IntStream.of은 가변 인자
 * IntStream IntStream.of(int[])         //        또는 배열
 * IntStream Arrays.stream(int[])                                              // 방법 2) Arrays.stream는 배열
 * IntStream Arrays.stream(int[] array, int startInclusive, int, endExclusive) //        또는 from ~ to(포함안됨) 범위 지정 가능
 */

/**
 * 스트림 만들기 - 임의의 수
 * : 난수를 요소로 갖는 스트림 생성하기
 *   IntStream intStream = new Random().ints();        // 무한 스트림
 *   intStream.limit(5).forEach(System.out::println);  // 무한 스트림은 잘라줘야한다, 5개의 요소만 출력한다.
 *
 *   IntStream intStream = new Random().ints(5);      // 크기가 5인 난수 스트림을 반환
 *
 * - Random 클래스에 정의된 메서드
 *         최소값                            최대값
 *   Integer.MIN_VALUE <=  ints()  <= Integer.MAX_VALUE
 *     Long.MIN_VALUE <=  longs()  <= Long.MAX_VALUE
 *                  0 <= doubles() < 1.0
 *
 * - 지정된 범위의 난수를 요소로 갖는 스트림을 생성하는 메서드(Random 클래스)
 * // 무한 스트림
 * IntStream ints(int begin, int end)
 * LongStream longs(long begin, long end)
 * DoubleStream doubles(double begin, double end)
 
 * // 유한 스트림, long streamSize가 크기 제한
 * IntStream ints(long streamSize, int begin, int end)
 * LongStream longs(long streamSize, long begin, long end)
 * DoubleStream doubles(long streamSize, double begin, double end)
 */

/**
 * 스트림 만들기 - 특정 범위의 정수
 * : 특정 범위의 정수를 요소로 갖는 스트림 생성하기(IntStream, LongStream)
 *   IntStream IntStream.range(int begin, int end)       // end는 포함 x
 *   IntStream IntStream.rangeClosed(int begin, int end)
 *
 *   IntStream intStream = IntStream.range(1,5);        // 1,2,3,4
 *   IntStream intStream = IntStream.rangeClosed(1,5);  // 1,2,3,4,5
 */

/**
 * 스트림 만들기 - 람다식 iterate(), generate()
 * - 람다식을 소스로 하는 스트림 생성하기
 *                              초기값         람다식
 * static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)     // 이전 요소에 종속적
 *
 *                                  람다식
 * static <T> Stream<T> generate(Supplier<T> s)                 // 이전 요소에 독립적
 *
 * - iterate()는 이전 요소를 seed로 해서 다음 요소를 계산 한다.
 *                                            초개값  람다식
 * Stream<Integer> evenStream = Stream.iterate(0,   n->n+2);    // 0,2,4,6, ...
 *
 * - generate()는 seed를 사용하지 않는다.
 * Stream<Double> randomStream = Stream.generate(Math::random); // Math클래스의 random()메서드를 호출한 깂을 계속 생성하는 무한 스트림
 * Stream<Integer> oneStream   = Stream.generate(()->1);        // 계속 1이 나옴
 */

/**
 * 스트림 만들기 - 파일과 빈 스트림
 * - 파일을 소스로 하는 스트림 생성하기
 * Stream<Path> Files.list(Path dir)    // Path는 파일 또는 디렉토리로
 *                                      // 폴더 경로를 주면 그 폴더 안의 파일들로 이루어진 스트림으로 변환하여 파일들을 쉽게 다룰 수 있다.
 *
 * // lines()는 파일 내용을 라인 단위(한줄씩)로 읽어서 String인 Stream으로 만든다.
 * // 즉, 로그 파일 분석 및 다량의 파일을 관리할 때 사용
 * Stream<String> Files.lines(Path path)
 * Stream<String> Files.lines(Path path, Charset cs)
 * Stream<String> lines()               // BufferedReader 클래스(텍스트 파일을 읽을 때 편리한 클래스)의 메서드
 *
 * - 비어있는 스트림 생성하기
 * Stream emptyStream = Stream.empty(); // empty()는 빈 스트림을 생성해서 반환한다. (잘 안쓰임)
 * long count = emptyStream.count();    // count의 값은 0
 */

public class EX14_Stream {
    public static void main(String[] args) {
        // 예제 1) 스트림 만들기 - 컬렉션
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> intStream = list.stream();     // 리스트를 스트림으로 변환
        intStream.forEach(System.out::print);          // forEach() 최종연산

        // stream은 1회용으로 stream에 대해 최종연산을 수행하면 stream이 닫힌다!
        intStream = list.stream();                     // 리스트로부터 스트림을 재생성해서 사용해야 한다.
        intStream.forEach(System.out::print);

        System.out.println();


        // 예제 2) 스트림 만들기 - 배열
        // 객체 배열로부터 스트림 생성하기
        String[] strArr = {"a", "b", "c", "d"};            // == String[] strArr = new String[] {"a","b","c","d"};
//        Stream<String > strStream = Stream.of(strArr);   // 방법 1) == Stream<String> strStream = Stream.of(new String[]{"a","b","c"});
        Stream<String > strStream = Arrays.stream(strArr); // 방법 2)
        strStream.forEach(System.out::println);

        // 기본형 배열로부터 스트림 생성하기
        // 객체 배열로부터 Stream을 사용한 방식
        Integer[] intArr = {1,2,3,4,5};
        Stream<Integer> intStream1 = Arrays.stream(intArr);   // Stream<T>는 숫자외에도 여러 타입의 스트림이 가능해야하므로 숫자 스트림에만 사용할 수 있는 sum(), average()를 넣지 않은 것
        intStream1.forEach(System.out::println);

        // 기본형 스트림으로 사용한 방식
        int[] intArr2 = {1,2,3,4,5};
        IntStream intStream2 = Arrays.stream(intArr2);
        intStream2.forEach(System.out::println);
//        System.out.println("sum = " + intStream2.sum());            // 기본형 스트림은 관련된 메서드들이 추가적으로 제공한다.
//        System.out.println("count = " + intStream2.count());
//        System.out.println("average = " + intStream2.average());

        System.out.println();


        // 예제 3) 임의의 수
//        IntStream intStream2 = new Random().ints(); // 무한 스트림

        IntStream intStream3 = new Random().ints(10, 5, 10); // 유한 스트림, 5~10 사이 난수의 10개 자르기
        intStream3
//                .limit(10) // 10개만 자르기
                .forEach(System.out::println);

        System.out.println();


        // 예제 4) 람다식
        // iterate(T seed, UnaryOperator f)
        //                 UnaryOperator는 단항 연산자로 매개변수 하나를 넣으면 결과가 하나가 나오는 것
        Stream<Integer> intStream4 = Stream.iterate(0, n->n+2);  // 종속적으로 0,2,4,6, ... 무한 스트림
        intStream4.limit(10).forEach(System.out::println);    // 10개만 자르고 출력

        // generate(Supplier s) : 주기만 하는 것 입력 X, 출력 O
        Stream<Integer> intStream5 = Stream.generate(()->1);          // 독립적으로 계속 1만 나오는 무한 스트림
        intStream5
                .limit(10)                                    // 10개만 자르기
                .forEach(System.out::println);
    }
}
