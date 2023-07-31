package org.example.ch14;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


/**
 * 스트림의 그룹화와 분할
 * Collectors 클래스의 메서드들로
 * 스트림을 그룹별로 나누어 그들의 통계 정보를 적용할 때 등에 사용된다.
 *
 * - partitioningBy()는 스트림을 2분할한다.
 * Collector partitioningBy(Predicate predicate)
 * Collector partitioningBy(Predicate predicate, Collector downstream)
 *
 * - groupingBy()는 스트림을 n분할한다.
 * Collector groupingBy(Function classifier)
 * Collector groupingBy(Function classifier, Collector downstream)
 * Collector groupingBy(Function classifier, Supplier mapFacotry, Collector downstream)
 */


/**
 * 스트림의 요소를 분할 - partitioningBy()
 * - 스트림의 요소를 2분할 (2분할이라 groupingBy보다 좀 더 빠름)
 * Collector partitioningBy(Predicate predicate)
 * Collector partitioningBy(Predicate predicate, Collector downstream)
 *
 * ex)
 * Map<Boolean, List<Student>> stuBySex = stuStream     // 학생들을 성별(남자(true), 여자(false))로 2분할
 * List<Student> maleStudent = stuBySex.get(true);      // Map에서 남학생 목록을 얻는다.
 * List<Student> femaleStudent = stuBySex.get(false);   // Map에서 여학생 목록을 얻는다.
 * |  key   |        value             |
 * | true   | List<Student>(남학생 목록) |
 * | false  | List<Student>(여학생 목록) |
 *
 * Map<Boolean, Long> stuNumBySex = stuStream
 *                      .collect(partitioningBy(Student::isMale, counting())); // 분할 + 통계 정보(여기서는 각 성별로 학생수) 넣기
 * System.out.println("남학생 수 : " + stuNumBySex.get(true));  // 남학생 수 : 8
 * System.out.println("여학생 수 : " + stuNumBySex.get(false)); // 여학생 수 : 10
 *
 * Map<Boolean, Optional<Student>> topScoreBySex = stuStream
 *   .collect(partitioningBY(Student::isMale, maxBy(comparingInt(Student::getScore))));
 * System.out.println("남학생 1등 : " + topScoreBySex.get(true));  // 남학생 1등 : Optional[[나자바, 남, 1, 1, 300]]
 * System.out.println("여학생 1등 : " + topScoreBySex.get(false)); // 여학생 1등 : Optional[[김지미, 여, 1, 1, 250]]
 *
 *
 * Map<Boolean, Map<Boolean, List<Student>>> failedStuBySex = stuStream   // 다중 분할(여기서는 남자(true), 여자(false)로 나눈 후 또 각 성별로 합격(false), 불합격(true)으로 나눈다.) 중첩 partitioningBy()
 *                      .collect(partitioningBy(Student::isMale,          // 1.성별로 분할(남/녀)
 *                               partitioningBy(s->s.getScore() < 150))); // 2.성적으로 분할(불합격/합격)
 * List<Student> failedMaleStu = failedStuBySex.get(true).get(true);      // 남자, 불합격
 * List<Student> failedFemaleStu = failedStuBySex.get(false).get(true);   // 여자, 불합격
 * |  key       |                value                        |
 * | true(남)   | |   key      |         value              | |
 * |            | |   true    |  List<Student>(불합격 목록)  | |
 * |            | |  false    |  List<Student>(합격 목록)   | |
 * |                                                         |
 * | false(여)  | |   key     |         value              | |
 * |            | |   true    |  List<Student>(불합격 목록) | |
 * |            | |  false    |  List<Student>(합격 목록)  | |
 * |                                                       |
 */


/**
 * 스트림의 요소를 그룹화
 * Collector groupingBy(Function classifier)
 * Collector groupingBy(Function classifier, Collector downstream)
 * Collector groupingBy(Function classifier, Suppplier mapFactory, Collector downstream)
 *
 * Map<Integer, List<Student>> stuByBan = stuStream                     // 학생을 반별로 그룹화
 *               .collect(groupingBy(Student::getBan, toList()));       // toList() 생략가능
 * |  key   |        value         |
 * |   1   | List<Student>(1반 목록) |
 * |   2   | List<Student>(2반 목록) |
 * |   3   | List<Student>(3반 목록) |
 *
 * Map<Integer, Map<Integer, List<Student>>> stuByHakAndBan = stuStream // 다중 그룹화
 *               .collect(groupingBy(Student::getHak,                   // 1.학년별 그룹화
 *                        groupingBy(Student::getBan)                   // 2.반별 그룹화
 *               ));
 * |  key       |                value                    |
 * |  1학년      | |   key   |         value            | |
 * |            | |    1    |   List<Student>(1반 목록) | |
 * |            | |    2    |  List<Student>(2반 목록)  | |
 * |                                                     |
 * |  2학년      | |   key   |         value            | |
 * |            | |    1    |   List<Student>(1반 목록) | |
 * |            | |    2    |  List<Student>(2반 목록)  | |
 *
 *      학년                  반
 * Map<Integer, Map<Integer, Set<Student.Level>>> stuByHakAndBan = stuStream
 *  .collect(
 *       groupingBy(Student::getHak, groupingBy(Student::getBam,        // 다중 그룹화(학년별, 반별)
 *            mapping(s-> {    // 성적등급(Level)으로 변환, List<Student> -> Set<Student.Level>
 *                 if      (s.getScore() >= 200) return Student.Level.HIGH;  // 200점 이상
 *                 else if (s.getScore() >= 100) return Student.Level.MID;   // 100점 이상
 *                 else                          return Student.Level.LOW;   // 그외
 *            } , toSet())                      // enum Level { HIGH, MID, LOW }
 *       ))
 *  };
 */

class Student2 {
    String name;
    boolean isMale; // 성별
    int hak;        // 학년
    int ban;        // 반
    int score;

    Student2(String name, boolean isMale, int hak, int ban, int score) {
        this.name = name;
        this.isMale = isMale;
        this.hak = hak;
        this.ban = ban;
        this.score = score;
    }

    String getName() {
        return name;
    }

    boolean isMale() {
        return isMale;
    }

    int getHak() {
        return hak;
    }
    int getBan() {
        return ban;
    }

    int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %d학년 %d반, %3d점]",
                name, isMale ? "남":"여", hak,ban,score);
    }

    // groupingBy()에서 사용
    enum Level {HIGH, MID, LOW} // 성적을 상,중,하 세 단계로 분류
}

public class EX14_Stream_Grouping {
    public static void main(String[] args) {
        Student2[] stuArr = {
                new Student2("나자바", true, 1, 1, 300),
                new Student2("김지미", false, 1, 1, 200),
                new Student2("김자바", true, 1, 1, 100),
                new Student2("이지미", false, 1, 1, 200),
                new Student2("남자바", true, 1, 1, 300),
                new Student2("안지미", false, 1, 1, 200),
                new Student2("황지미", false, 1, 1, 100),
                new Student2("강지미", false, 1, 1, 200),
                new Student2("이자바", true, 1, 1, 250),
                new Student2("나자바", true, 2, 1, 150),
                new Student2("김지미", false, 2, 1, 50),
                new Student2("김자바", true, 2, 1, 100),
                new Student2("이지미", false, 2, 1, 150),
                new Student2("남자바", true, 2, 1, 250),
                new Student2("안지미", false, 2, 1, 150),
                new Student2("황지미", false, 2, 1, 50),
                new Student2("강지미", false, 2, 1, 100),
                new Student2("이자바", true, 2, 1, 150)
        };

        // 예제 1) 스트림의 요소를 분할 - partitioningBy()
        System.out.printf("1. 단순분할(성별로 분할)%n");
        Map<Boolean, List<Student2>> stuBySex = Stream.of(stuArr)  // Stream.of()로 스트림을 바꾼 다음
                .collect(partitioningBy(Student2::isMale));        // collect()와 partitioningBy()로 성별로 나누었다.

        List<Student2> maleStudent = stuBySex.get(true);           // 남학생 목록만
        List<Student2> femaleStudent = stuBySex.get(false);        // 여학생 목록만

        for (Student2 s : maleStudent) {
            System.out.println(s);
        }
        for (Student2 s : femaleStudent) {
            System.out.println(s);
        }

        System.out.printf("%n2. 단순분할 + 통계(성별 학생수)%n");
        Map<Boolean, Long> stuNumBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale, counting()));  // collect()와 partitioningBy()로 성별로 나눈 후 counting()로 각 성별의 학생수로 넣음

        System.out.println("남학생 수 = " + stuNumBySex.get(true));
        System.out.println("여학생 수 = " + stuNumBySex.get(false));

        System.out.printf("%n3. 단순분할 + 통계(성별 1등)%n");
        Map<Boolean, Optional<Student2>> topScoreBySex = Stream.of(stuArr)  // collect()와 partitioningBy()로 성별로 나눈 후 maxBy()로 각 성별의 점수 최대값을 넣음
                .collect(partitioningBy(Student2::isMale,
                        maxBy(comparingInt(Student2::getScore))
                ));

        System.out.println("남학생 1등 : " + topScoreBySex.get(true));
        System.out.println("여학생 1등 : " + topScoreBySex.get(false));

        Map<Boolean, Student2> topScoreBySex2 = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale,
                        collectingAndThen(maxBy(comparingInt(Student2::getScore)), Optional::get) // Optional::get으로 Optional을 꺼내서 반환
                ));

        System.out.println("남학생 1등 : " + topScoreBySex2.get(true));
        System.out.println("여학생 1등 : " + topScoreBySex2.get(false));

        System.out.printf("%n4. 다중분할(성별 불합격자, 100점 이하)%n");

        Map<Boolean, Map<Boolean, List<Student2>>> failedStuBySex =            // 다중 분할
                Stream.of(stuArr).collect(partitioningBy(Student2::isMale,     // 1.성별로 분할(남(true)/녀(False))
                        partitioningBy(s -> s.getScore() <= 100))              // 2.각 상별로 성적으로 분할(불합격(true)/합격(true))
                );

        List<Student2> failedMaleStu = failedStuBySex.get(true).get(true);
        List<Student2> failedFemaleStu = failedStuBySex.get(false).get(true);

        for (Student2 s : failedMaleStu) {
            System.out.println(s);
        }
        for (Student2 s : failedFemaleStu) {
            System.out.println(s);
        }

        System.out.println();


        // 예제 2) 스트림의 요소를 그룹화 - groupingBy()
        System.out.printf("%n1. 단순그룹화(반별로 그룹화)%n");
        Map<Integer, List<Student2>> stuByBan = Stream.of(stuArr)
                .collect(groupingBy(Student2::getBan));

        for (List<Student2> ban : stuByBan.values()) {
            for (Student2 s: ban) {
                System.out.println(s);
            }
        }

        System.out.printf("%n2. 단순그룹화(반별로 그룹화)%n");
        Map<Student2.Level, List<Student2>> stuByLevel = Stream.of(stuArr)
                .collect(groupingBy(s->{
                         if (s.getScore() >= 200) return Student2.Level.HIGH;
                    else if (s.getScore() >= 100) return Student2.Level.MID;
                    else                          return Student2.Level.LOW;
                }));

        // HIGH부터 나오게하려고 TreeSet 사용
        TreeSet<Student2.Level> keySet = new TreeSet<>(stuByLevel.keySet());

        for (Student2.Level key : keySet) {
            System.out.println("[" + key + "]");

            for (Student2 s : stuByLevel.get(key)) {
                System.out.println(s);
            }
            System.out.println();
        }

        System.out.printf("%n3. 단순그룹화 + 통계(성적별 학생수)%n");
        Map<Student2.Level, Long> stuCntByLevel = Stream.of(stuArr)
                .collect(groupingBy(s->{
                    if (s.getScore() >= 200)      return Student2.Level.HIGH;
                    else if (s.getScore() >= 100) return Student2.Level.MID;
                    else                          return Student2.Level.LOW;
                }, counting())); // counting()으로 각 HIGH, MID, LOW의 총개수로 넣음

        for (Student2.Level key : stuByLevel.keySet()) {
            System.out.printf("[%s] - %d명, ", key,stuCntByLevel.get(key));
        }
        System.out.println();

        for (List<Student2> level : stuByLevel.values()) {
            System.out.println();
            for (Student2 s : level) {
                System.out.println(s);
            }
        }

        System.out.printf("%n4. 다중그룹화(학년별, 반별)");
        Map<Integer, Map<Integer, List<Student2>>> stuByHakAndBan =
                Stream.of(stuArr)
                        .collect(groupingBy(Student2::getHak,
                                groupingBy(Student2::getBan)
                        ));

        for (Map<Integer, List<Student2>> hak : stuByHakAndBan.values()) {
            for (List<Student2> ban : hak.values()) {
                System.out.println();
                for (Student2 s : ban) {
                    System.out.println(s);
                }
            }
        }

        System.out.printf("%n5. 다중그룹화 + 통계(학년별, 반별 1등)%n");
        Map<Integer, Map<Integer, Student2>> topSuByHakAndBan =
                Stream.of(stuArr)
                        .collect(groupingBy(Student2::getHak,
                                groupingBy(Student2::getBan,
                                        collectingAndThen(
                                                maxBy(comparingInt(Student2::getScore))
                                                , Optional::get
                                        )
                                )
                        ));
        for (Map<Integer, Student2> ban : topSuByHakAndBan.values()) {
            for (Student2 s : ban.values()) {
                System.out.println(s);
            }
        }

        System.out.printf("%n6. 다중그룹화 + 통계(학년별, 반별 성적그룹)%n");
        Map<String, Set<Student2.Level>> stuByScoreGroup = Stream.of(stuArr)
                .collect(groupingBy(s->s.getHak() + "-" + s.getBan(),  // 여기서는 학년, 반을 각각 그룹화한 것이 아닌 학년 반을 하나로 묶은 것
                        mapping(s-> {
                                 if (s.getScore() >= 200) return Student2.Level.HIGH;
                            else if (s.getScore() >= 100) return Student2.Level.MID;
                            else                          return Student2.Level.LOW;
                        }, toSet())
                ));

        Set<String> ketSet2 = stuByScoreGroup.keySet();

        for (String key : ketSet2 ) {
            System.out.println("[" + key + "]" + stuByScoreGroup.get(key));
        }

    }
}
