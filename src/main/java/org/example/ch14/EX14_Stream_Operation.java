package org.example.ch14;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * 스트림의 연산
 * - 스트림이 제공하는 기능 : 중간 연산과 최종 연산
 * 중간 연산 : 연산결과가 스트림인 연산, 반복적으로 적용가능
 * 최종 연산 : 연산결과가 스트림이 아닌 연산, 단 한번만 적용가능(스트림의 요소를 소모)
 *
 *         중복제거    5개자르기   정렬              출력
 * stream.distinct().limit(5).sorted().forEach(System.out::println)
 *         중간연산    중간연산  중간연산          최종연산
 * ex)
 * String[] strArr = {"dd", "aaa", "CC", "cc", "b"};
 * Stream<String> stream = Stream.of(strArr);              // 문자열 배열이 소스인 스트림
 * Stream<String> filteredStream = stream.filter();        // 걸러내기     (중간 연산)
 * Stream<String> distinctStream = stream.distinct;        // 중복제거     (중간 연산)
 * Stream<String> sortedStream = stream.sort();            // 정렬        (중간 연산)
 * Stream<String> limitedStream = stream.limit(5);         // 스트림 자리기(중간 연산)
 * int total = stream.count();                             // 요소 개수 세기(최종 연산)
 */

/**
 * 중간 연산
 *
 *          중간 연산                                                     설명
 * Stream<T> distinct()                                          중복을 제거
 *
 * Stream<T> filter(Predicate<T> predicate)                      조건에 안 맞는 요소만 제외
 *
 * Stream<T> limit(long maxSize)                                 스트림의 일부를 잘라낸다.
 *
 * Stream<T> skip(long n)                                        스트림의 일부를 건너뛴다.
 *
 * Stream<T> peek(Consumer<T> action)                            스트림의 요소에 작업수행
 *
 * Stream<T> sorted()                                            스트림의 요소를 정렬한다.
 * Stream<T> sorted(Comparator<T> comparator)
 *
 * Stream<R>       map(Function<T,R> mapper)
 * DoubleStream    mapToDouble(ToDoubleFunction<T> mapper)
 * IntStream       mapToInt(ToIntFunction<T> mapper)
 * LonStream       mapToLong(ToLongFunction<T> mapper)
 *                                                               스트림의 요소를 변환한다.
 * Stream<R>       flatMap(Function<T,Stream<R>> mapper)
 * DoubleStream    flatMapToDouble(Function<T, DoubleStream> m)
 * IntStream       flatMapToInt(Function<T, IntStream> m)
 * LongStream      flatMapToLong(Function<T, LongStream> m)
 */

/**
 * 스트링의 중간 연산
 * - 스트림 자르기 : skip(), limit()
 * Stream<T> skip(long n)                                  // 앞에서부터 n개 건너뛰기
 * Stream<T> limit(long maxSize)                           // maxSize이후의 요소는 잘라냄
 *
 * IntStream intStream = IntStream.rangeClosed(1, 10);     // 12345678910
 * intStream.skip(3).limit(5).forEach(System.out::print);  // 45678
 * 
 * 
 * - 스트림의 요소 걸러내기 : filter(), distinct()
 * Stream<T> filter(Predicate predicate)                   // 조건에 맞지 않는 요소 걸러냄
 * Stream<T> distinct()                                    // 중복제거
 *
 * IntStream intStream = IntStream.rangeClosed(1, 10);     // 12345678910
 * intStream.filter(i->i%2==0).forEach(System.out::print); // 246810, 2의 배수만 만족된 것만 통과됨
 *
 * intStream.filter(i->i%2==0 && i%3!=0).forEach(System.out::print);      // 2의 배수와 3의 배수 제외
 * intStream.filter(i->i%2==0).filter(i%3!=0).forEach(System.out::print); // 위와 같음
 *
 *
 * - 스트림 요소 정렬하기 : sorted()
 * Stream<T> sorted()                      // 스트림 요소의 기본 정렬(Comparable)로 정렬
 * Stream<T> sorted(Comparator comparator) // Comparaotr로 정렬 기준을 지정한 것
 *
 *     문자열 스트림 정렬 방법 예시                                                            출력결과
 * strStream.sorted()                                        // 기본 정렬                 CCaaabccdd
 * strStream.sorted(Comparator.naturalOrder())               // 기본 정렬
 * strStream.sorted((s1,s2)->s1.compareTo(s2))               // 람다식도 가능
 * strStream.sorted(String::compareTo)                       // 위 문장과 동일
 *
 * strStream.sorted(Comparator.reverseOrder())               // 기본 정렬의 역순           ddccbaaaCC
 * strStream.sorted(Comparator.<String>naturalOrder().reversed())
 *
 * strStream.sorted(String.CASE_INSENSITIVE_ORDER)           // 대소문자 구분안함          aaabCCccdd
 * strStream.sorted(String.CASE_INSENSITIVE_ORDER.reversed())                           ddCCccbaaa (역순이어도 대문자가 먼저 나온다!)
 *
 * strStream.sorted(Comparator.comparing(String::length))    // 길이 순 정렬              bddCCccaaa
 * strStream.sorted(Comparator.comparingInt(String::length)) // no 오토박싱               1 2 2 2 3
 * strStream.sorted(Comparator.comparing(String::length).reversed())                    aaaddCCccb
 *
 * - Comparator의 comparing()메서드
 * Comparator의 comparing()으로 정렬 기준을 제공
 * comparing(Function<T,U> keyExtractor)
 * comparing(Function<T,U> keyExtractor, Comparator<U> keyComparator)
 *
 * studentStream.sorted(Comparator.comparing Student::getBan) // Student 객체를 반별로 정렬 (람다식: (Student s)->s.getBan()
 *              .forEach(System.out::println);
 *
 * 추가 정렬 기준을 제공할 때는 thenComparing()을 사용 (정렬 기준이 여러 개일 때)
 * thenComparing(Comparator<T> other)
 * thenComparing(Function<T,U> keyExtractor)
 * thenComparing(Function<T,U> keyExtractor, Comparator<U> keyComp)
 * 
 * studentStream.sorted(Comparator.comparing(Student::getBan)   // 정렬 기준 1) 반별로 정렬
 *                     .thenComparing(Student::getTotalScore)   // 정렬 기준 2) 총점별로 정렬
 *                     .thenComparing(Student::getName)         // 정렬 기준 3) 이름별로 정렬
 *                     .forEach(System.out::println);
 *
 *
 * - 스트림의 요소 변환하기 : map()
 * Stream<R> map(Function mapper)  // Steam<T>->Stream<R>
 * Stream<File> fileStream = Stream.of(new File("Ex1.java"), new File("Ex1"),
 *                                     new File("Ex1.bak"), new File("Ex2.java"), new File("Ex1.txt"));
 * Stream<String> filenameStream = fileStream.map(File::getName); // map()으로 File 스트림의 파일이름을 String 스트림으로 변환
 * filenameStream.forEach(System.out::println);                   // 스트림의 모든 파일의 이름을 출력
 *
 *               map(File::getName)      변환
 * Stream<File>         ->          Stream<String>
 *
 * ex) 파일 스트림(Stream<File>)에서 파일 확장자(대문자)를 중복없이 뽑아내기
 * fileStream.map(File::getName)                // Stream<File> -> Stream<String(파일이름)>
 *    .filter(s->s.indexOf('.') != -1)          // 확장자(.)가 없는 것은 제외
 *    .map(s->s.substring(s.indexOf('.')+1))    // 확장자만 추출 (Stream<String(파일이름전체)> -> Stream<String(파일확장자)>)
 *    .map(String::toUpperCase)                 // Stream<String> -> Stream<String> 모두 대문자로 변환
 *    .distinct()                               // 중복 제거
 *    .forEach(System.out::print);              // ex) JAVABAKTXT
 *
 *
 * - 스트림의 요소를 소비하지 않고 엿보기 : peek()
 * 중간 작업 결과를 확인할 때(디버깅 용도) 사용
 * Stream<T> peek(Consumer action)  // 중간 연산(스트림 요소를 소비 X)
 * void forEach(Consumer action)    // 최종 연산(스트림 요소를 소비 O)
 *
 * fileStream.map(File::getName)                       // Stream<File> -> Stream<String(파일이름)>
 *    .filter(s->s.indexOf('.') != -1)                 // 확장자가 없는 것은 제외
 *    .peek(s->System.out.printf("filename=%s%n", s))  // 파일명을 출력
 *    .map(s->s.substring(s.indexOf('.')+1))           // 확장자만 추출 (Stream<String(파일이름전체)> -> Stream<String(파일확장자)>)
 *    .peek(s->System.out.printf("extension=%s%n", s)) // 확장자를 출력
 *    .forEach(System.out::println);                   // 최종연산 스트림을 소비
 *
 *
 * - 스트림의 스트림을 스트림으로 변환 : flatMap()
 *                                          각 요소가 String 배열인 Stream
 * Stream<String[]> strArrStrm = Stream.of(new String[] {"abc", "def", "ghi"},
 *                                         new String[] {"ABC", "GHI", "JKLMN"});
 *
 * // 그냥 map을 쓴 경우
 *        스트림의 스트림
 * Stream<Stream<String>> strStrStrm = strArrStrm.map(Arrays::stream);
 *
 * // 각 String 배열을 하나의 배열에 합치고 싶을 때 flatmap() 활용
 * Stream<String> strStrStrm = strArrStrm.flatMap(Arrays::stream);  // Arrays.stream(T[])
 *
 */

public class EX14_Stream_Operation {
    public static void main(String[] args) {
        // 예제 1) 중간 연산 : 정렬 Comparator 활용
        Stream<Student> studentStream = Stream.of(
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180)
        );

//        람다식 버전 : studentStream.sorted(Comparator.comparing((Student s)->s.getBan()).reversed()
        studentStream.sorted(Comparator.comparing(Student::getBan).reversed()  // 1.반별 정렬(역순)
                        .thenComparing(Comparator.naturalOrder()))             // 2.기본 정렬
                        .forEach(System.out::println);

        System.out.println();


        // 예제 2) 중간 연산 : map(), peek()
        // 파일 스트림(Stream<File>)에서 파일 확장자(대문자)를 중복없이 뽑아내기
        File[] fileArr = {new File("Ex1.java"), new File("Ex1.bak"),
                new File("Ex2.java"), new File("Ex1"), new File("Ex1.txt")
        };

        Stream<File> fileStream = Stream.of(fileArr);

        // map()으로 Stream<File>을 Stream<String>으로 변환
//      람다식 버전:  Stream<String> filenameStream = fileStream.map((f)->f.getName());
        Stream<String> filenameStream = fileStream.map(File::getName);
        filenameStream.forEach(System.out::println);  // 스트림의 모든 파일의 이름을 출력

        fileStream = Stream.of(fileArr);              // 스트림을 다시 생성

        fileStream.map(File::getName)
                .filter(s -> s.indexOf('.') != -1)                      // 확장자가 없는 것은 제외
                .peek(s -> System.out.printf("filename=%s%n", s))       // 파일명을 출력 (디버깅 용도)
                .map(s -> s.substring(s.indexOf('.') + 1))     // Stream<String(파일이름전체)> -> Stream<String(파일확장자)>
                .peek(s -> System.out.printf("extension=%s%n", s))      // 확장자를 출력 (디버깅 용도)
                .map(String::toUpperCase)                               // Stream<String> -> Stream<String>
                .distinct()                                             // 중복 제거
                .forEach(System.out::println);                          // JAVABAKTXT

        System.out.println();


        // 예제 3) 중간 연산 : flatMap()
        // 각 String 배열이 요소인 경우
        Stream<String[]> strArrStrm = Stream.of(new String[]{"abc", "def", "ghi"},
                new String[]{"ABC", "GHI", "JKLMN"});
        // 각 String 배열이 요소인 경우의 map()은 Stream<Stream<String>>
//        Stream<Stream<String>> strStrStrm = strArrStrm.map(Arrays::stream);

        // flatMap()으로 하나의 Stream으로 합치기
        Stream<String> strStrm = strArrStrm.flatMap(Arrays::stream);  // Arrays.stream(T[])

        strStrm.map(String::toLowerCase)       // 스트림의 요소를 모두 소문자로 변경
                .distinct()                    // 중복제거
                .sorted()                      // 정렬
                .forEach(System.out::println);
        System.out.println();

        // 한줄씩 읽어올 때 각 단어를 요소 하나하나로 하고 싶은 경우
        String[] lineArr = {
                "Believe  or not It is true",
                "Do or do not There is no try"
        };

        Stream<String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line -> Stream.of(line.split(" +"))) // " +"은 정규식으로 공백이 하나이상인 것을 뜻함
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}

class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;

    public Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore);
    }

    public String getName() {
        return name;
    }
    public int getBan() {
        return ban;
    }
    public int getTotalScore() {
        return totalScore;
    }

    // 총점 내림차순을 기본 정렬로 한다.
    @Override
    public int compareTo(Student s) {
        return s.totalScore - this.totalScore;
    }
}
