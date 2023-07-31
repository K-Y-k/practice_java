package org.example.ch14;


/**
 * reduce()와 collect()
 * 역할은 같지만,
 * reduce()는 스트림 전체에서 줄여나갈 때 사용하고
 * collect()는 그룹별로 나누어서 줄여나갈 때 사용한다.
 */

/**
 * collect()와 Collector 인터페이스와 Collectors 클래스
 * - collect()는 Collector를 매개변수로 하는 스트림의 최종연산
 * Object collect(Collector collector)     // Collector 인터페이스를 구현한 클래스의 객체를 매개변수로
 * Object collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner) // 해당 매개변수들이 Collector 인터페이스에 들어가 있어 잘 안쓰임
 *
 * - Collector는 수집(collect)에 필요한 메서드를 정의해 놓은 인터페이스
 * public interface Collector<T, A, R> {   // T(요소)를 A에 누적한 다음, 결과를 R로 변환해서 반환
 *     Supplier<A> supplier();        (핵심)    // StringBuiilder::new            누적할 곳
 *     BiConsumer<A,T> accumulator(); (핵심)    // (sb, s) -> sb.append(s)        누적할 방법
 *     BinaryOperator<A> combiner();           // (sb1, sb2) -> sb1.append(sb2)  결합방법(병렬)
 *     Function<A,R> finisher();               // sb -> sb.toString()            최종변환
 *     Set<Characteristics> characteristics(); // 컬렉터의 특성이 담긴 Set을 반환
 *     ...
 * }
 *
 * - Collectors 클래스는 다양한 기능의 컬렉터(Collector를 구현한 클래스)를 제공하기에
 *   직접 구현할 일이 거의 없다.
 *   해당 기능만 잘 사용할 줄 알면 된다.
 * 변환         - mapping(), toList(), toSet(), toMap(), toCollection(), ...
 * 통계         - counting(), summingInt(), averagingInt(), maxBy(), minBy(), summarizingInt(), ...
 * 문자열 결합   - joining()
 * 리듀싱       - reducing()
 * 그룹화와 분할 - groupingBy(), partitioningBy(), collectingAndThen()
 */

/**
 * 스트림을 컬렉션 또는 배열로 변환할 수 있다.
 * - 스트림을 컬렉션으로 변환 : toList(), toSet(), toMap(), toCollection()
 * List<String> names = stuStream.map(Student::getName)  // Stream<Student> -> Stream<String> 학생들 이름만 뽑아서
 *                        .collect(Collectors.toList()); // Stream<String> -> List<String>    리스트에 넣었다.
 *
 * ArrayList<String> list = names.stream()
 *   .collect(Collectors.toCollection(ArrayList::new));  // Stream<String> -> ArrayList<String>
 *
 *    key: 주민번호  value: Person객체
 * Map<String, Person> map = personStream      항등함수
 *   .collect(Collectors.toMap(p->p.getRegId(), p->p));  // Stream<Person> -> Map<String,Person>
 *                                  key         value
 *                         |    p.getRegId()     p(객체 주소 그대로) |
 *
 *
 * - 스트림을 배열로 변환    : toArray()
 * Student[] stuNames = studentStream.toArray(Student[]::new); // 가능, 잘 쓰이는 방식
 * Student[] stuNames = studentStream.toArray();               // 에러
 * Object[] stuNames = studentStream.toArray();                // 가능, Object은 결국 원하는 객체로 다시 변환해줘야하기에 주로 위처럼 사용
 */

/**
 * 스트림의 통계
 * - 스트림의 통계정보 제공 : counting(), summingInt(), maxBy(), minBy(), ...
 * long count = stuStream.count();             // 전체 카운팅
 * long count = stuStream.collect(counting()); // 그룹별로 카운팅, Collectors.counting()
 *
 * long totalScore = stuStream.mapToInt(Student::getTotalScore).sum();       // 전체의 합 IntStream의 sum()
 * long totalScore = stuStream.collect(summingInt(Student::getTotalScore));  // 그룹별로 합 가능 (ex) 남학생 총점, 여학생 총점)
 *
 * OptionalInt topScore = studentStream.mapToInt(Student::getTotalScore).max();    // 전체 중 최대값
 * Optional<Student> topStudent = stuStream
 *                          .max(Comparator.comparingInt(Student::getTotalScore)); // 그불별로 최대값
 * Optional<Student> topStudent = stuStream
 *                          .collect(maxBy(Comparator.comparingInt(Student::getTotalScore)));
 */

/**
 * 스트림의 리듀싱 - reducing()
 * - 스트림을 리듀싱 : reducing()
 * Collector reducing(BinaryOperator<T> op)
 * Collector reducing(T identity, BinaryOperator<T> op) // 주로 사용
 * Collector reducing(U identity, Function<T,U> mapper, BinaryOperator<T> op)  // T를 U로 변환작업이 필요할 때 사용 map + reduce
 *
 * IntStream intStream = new Random().ints(1,46).distinct().limit(6);
 *
 * OptionalInt max = intStream.reduce(Integer::max);                             // 전체 리듀싱
 * OptionalInt<Integer> max = intStream.boxed().collect(reducing(Integer::max)); // 그룹별 리듀싱
 *
 * long sum = intStream.reduce(0, (a,b) -> a + b);
 * long sum = intStream.boxed().collect(reducing(0, (a,b)->a + b));
 *
 * int grandTotal = stuStream.map(Student::getTotalScore).reduce(0, Integer::sum);
 * int grandTotal = stuStream.collect(reducing(0, Student::getTotalScore, Integer::sum));
 * 
 * 
 * - 문자열 스트림의 요소를 모두 연결 : joining()
 * String studentNames = stuStream.map(Student::getName).collect(joining());
 * String studentNames = stuStream.map(Student::getName).collect(joining(","));           // 구분자로 ,를 줌
 * String studentNames = stuStream.map(Student::getName).collect(joining(",", "[", "]")); // 구분자로 ,를 줌 + 앞 뒤로 [ ]
 * String studentInfo = stuStream.collect(joining());   // Student의 toString()으로 결합
 */

public class EX14_colect_Collectors {
    public static void main(String[] args) {

    }
}
