package org.example.ch12;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 애너테이션
 * : 주석처럼 프로그래밍 언어에 영향을 미치지 않으며 특정 프로그램에게 유용한 정보를 제공한다.
 * 
 * 과거에는 프로그램에 대한 문서를 작성했다.
 * 소스코드를 변경하면 그에 대한 문서도 변경해야하는데
 * 문서 변경을 안할 때가 많아 버전 불일치가 자주 발생했다.
 * 즉, 관리가 잘 안되어 소스코드와 문서(주석)를 하나로 합쳤다.
 * 이 합친 것을 javadoc.exe가 주석(소스코드에 대한 설명)을 읽어 보기 좋게 문서로 나타내주었다.
 *
 * 또한 소스코드와 설정 파일이 분리되어 있는데
 * 소스코드에 설정에 대한 정보를 넣기 위해 애너테이션이 나온 것이다.
 * 즉, 설정 파일을 따로 만들지 않고 애너테이션을 활용하는 경우가 많아졌다.
 *
 * 사용 예시)
 * @Test  // 이 메서드가 테스트 대상임을 JUnit 단위 테스트 프로그램에게 알린다.
 * public void method() {
 *     ...
 * }
 */

/**
 * 자바에서 제공하는 애너테이션
 *
 *   애너테이션 종류                      설명
 *   표준 애너테이션
 * @Override                  컴파일러에게 오버라이딩하는 메서드라는 것을 알린다.
 * @Deprecated                앞으로 사용하지 않을 것을 권장하는 대상에 붙인다.
 * @SuppressWarnings          컴파일러의 특정 경고메시지가 나타나지 않게 해준다.
 * @SafeVarargs               지네릭스 타입의 가변인자에 사용한다.(JDK1.7)
 * @FunctionalInterface       함수형 인터페이스라는 것을 알린다.(JDK1.8)
 * @Native                    native 메서드에서 참조되는 상수 앞에 붙인다.(JDK1.8)
 *
 *  메타 애너테이션(애너테이션을 만들 때 사용)
 * @Target*                   애너테이션이 적용가능한 대상을 지정하는데 사용한다.
 * @Documented*               애너테이션 정보가 javadoc으로 작성된 문서에 포함되게 한다.
 * @Inherited*                애너테이션이 자손 클래스에 상속되도록 한다.
 * @Retention*                애너테이션이 유지되는 범위를 지정하는데 사용한다.
 * @Repeatable*               애너테이션을 반복해서 적용할 수 있게 한다.(JDK1.8)
 */

/**
 * 표준 애너테이션
 * - @Override
 * : 오버라이딩을 올바르게 했는지 컴파일러가 체크하게 한다.
 *   오버라이딩 할 때 메서드 이름을 잘못적는 실수로 원하는 작동이 되지 않는 경우가 많다.
 *   이 애너테이션을 선언하면 해결해준다.
 *   ex)
 *   @Override
 *   void parentMethod(){
 *       ...
 *    }
 *
 * 
 * - @Deprecated
 * : 앞으로 사용하지 않을 것을 권장하는 필드나 메서드에 붙인다.
 *   ex) Date클래스의 getDate()
 *   @Deprecated // 해당 메서드 사용 권장 X (과거 버전의 프로그램을 위해서 없앨 수 없음(하위 호환성))
 *   pubic int getDate() {
 *       return normalize().getDayOfMonth();
 *   }
 *   
 *   @Deprecated가 붙은 대상이 사용된 코드를 컴파일하면 나타나는 경고 메시지
 *   Note: C:\Users\KOR\IdeaProjects\practice_java\src\main\java\org\example\ch12\EX12_Annotation.java uses or overrides a deprecated API
 *   Note: Recompile with -Xlint:deprecation for details.
 *
 *
 * - @FunctionalInterface
 * : 함수형 인터페이스에 붙이면 컴파일러가 올바르게 작성했는지 체크한다.
 *   함수형 인터페이스에는 하나의 추상메서드만 가져야 한다는 제약이 있다.
 *   ex)
 *   @FunctionalInterface  // @Override처럼 안붙여도 되지만 붙이면 컴파일러가 체크해준다.
 *   public interface Runnable {
 *       public abstract void run(); // 추상 메서드
 *   }
 *
 *
 * - @SuppressWarnings
 * : 컴파일러의 경고 메시지가 나타나지 않게 억제한다.
 *   괄호()안에 억제하고자하는 경고의 종류를 문자열로 지정하면 된다.
 *   경고 메시지를 이미 확인하여 인지하고 있을 때 사용
 *   ex)
 *   @SuppressWarnings("unchecked")     // 지네릭스와 관련된 경고를 억제함
 *   ArrayList list = new ArrayList();  // 지네릭 타입을 지정하지 않았음
 *   list.add(obj);                     // 여기서 경고 메시지가 발생하지만 @SuppressWarnings("unchecked")로 경고 메시지가 안뜨게 함
 *
 *   둘 이상의 경고를 동시에 억제하려면 아래와 같이 한다.
 *   ex) @SuppressWarnings({"deprecation", "unchecked", "varargs"})
 *
 *   '-Xlint'옵션으로 컴파일하면, 경고메시지를 확인할 수 있다.
 *   괄호안에 경고의 종류를 넣으면 경고메시지가 안나온다.
 *
 */


/**
 * 메타 애너테이션
 * : 애너테이션을 위한 애너테이션 (애너테이션을 만들 때 사용)
 *   java.lang.annotation 패키지에 포함한다.
 *
 * - @Target
 * : 애너테이션을 정의할 때, 적용대상 지정에 사용된다.
 *   즉, 애너테이션을 어디에 적용할 수 있는지 지정할 때 사용
 *   ex)
 *   @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
 *   @Retention(RetentionPolicy.SOURCE)
 *   public @interface SuppressWarnings {
 *       String[] value();
 *   }
 *
 *   @Target({TYPE, FIELD, TYPE_USE})    // 적용대상이 TYPE, FIELD, TYPE_USE
 *   public @interface MyAnnotation {    // MyAnnotation을 정의
 *   }
 *
 *   @MyAnnotation  // 적용대상이 TYPE인(클래스,인터페이스) 경우
 *   class MyClass {
 *       @MyAnnotation // 적용대상이 FIELD인 경우
 *       int i;        // iv 멤버변수
 *
 *       @MyAnnotation // 적용대상이 TYPE_USE인 경우
 *       MyClass mc;
 *   }
 *     대상 타입                     의미
 *  ANNOTATION_TYPE            애너테이션
 *  CONSTRUCTOR                생성자
 *  FIELD                      필드
 *  LOCAL_VARIABLE             지역변수
 *  METHOD                     메서드
 *  PACKAGE                    패키지
 *  PARAMETER                  매개변수
 *  TYPE                       타입(클래스, 인터페이스, enum)
 *  TYPE_PARAMETER             타입 매개변수(JDK1.8)
 *  TYPE_USE                   타입이 사용되는 모든 곳(JDK1.8)
 *
 *
 * - @Retention
 * : 애너테이션이 언제까지 유지되는지, 유지되는 기간을 지정하는데 사용
 *    유지 정책                      의미
 *  SOURCE                      소스 파일에만 존재, 클래스 파일에는 존재하지 않음
 *  CLASS (잘 안씀)              클래스 파일에 존재, 실행시에 사용불가, 기본값
 *  RUNTIME                     클래스 파일에 존재, 실행시에 사용가능
 *
 *   컴파일러에 의해 사용되는 애너테이션의 유지 정책은 SOURCE이다.
 *   ex)
 *   @Target (ElementType.METHOD)
 *   @Retention (RetentionPolicy.SOURCE) // @Override는 실행시에는 필요 없고 컴파일할 때만 필요하므로
 *   public @interface Override {}
 *
 *   실행시에 사용 가능한 애너테이션의 정책은 RUNTIME이다.
 *    ex)
 *    @Documented
 *    @Retention (RetentionPolicy.RUNTIME) // @FuntionalInterface은 컴파일러 ~ 실행까지 필요하므로
 *    @Target (ElementType.TYPE)
 *    public @interface FuntionalInterface {}
 *
 * 
 * - @Doucmented, @Inherited (둘 다 잘 안씀)
 * : javadoc으로 작성한 문서에 포함시키려면 @Documented를 붙인다.
 *   ex)
 *   @Documented
 *   @Retention (RetentionPolicy.RUNTIME)
 *   @Target (ElementType.TYPE)
 *   public @interface FuntionalInterface {}
 *   
 *   애너테이션을 자손 클래스에 상속하고자 할 때, @Inherited를 붙인다.
 *   ex)
 *   @Inherited
 *   @interface SuperAnno {}
 *   
 *   @SuperAnno                    // 조상에 상속 애너테이션을 붙인 것을
 *   class Parent {}
 *   
 *   class Child extends Parent {} // Child에도 @SuperAnno 애너테이션이 붙은 것으로 인식
 *
 *
 * - @Repeatable (잘 안씀)
 * : 반복해서 붙일 수 있는 애너테이션을 정의할 때 사용
 *   ex)
 *   @Repeatable(ToDos.class) // ToDo애너테이션을 여러 번 반복해서 쓸 수 있게 한다.
 *   @interface ToDo {
 *       String value();
 *   }
 *
 *   @Repeatable이 붙은 애너테이션은 반복해서 붙일 수 있다.
 *   ex)
 *   @ToDo("delete test codes.")
 *   @ToDo("override inherited methods")
 *   class MyClass {
 *       ...
 *   }
 *
 *   단, @Repeatable인 @ToDo를 하나로 묶을 컨테이너 애너테이션도 정의해야 한다.
 *   ex)
 *   @interface ToDos {  // 여러 개의 ToDo애너테이션을 담을 컨테이너 애너테이션 ToDos
 *       ToDo[] value(); // ToDo애너테이션 배열타입의 요소를 선언, 이름이 반드시 value이어야 한다.
 *   }
 */

/**
 * 애너테이션 타입 정의하기
 * - 애너테이션을 직접 만들어 쓸 수 있다.
 * @interface 애너테이션이름 {
 *     타입 요소이름();  // 애너테이션의 요소를 선언한다.
 *     ...
 * }
 *
 * - 애너테이션의 메서드는 추상 메서드이며 애너테이션을 적용할 때 지정(순서X)
 * ex)
 * @interface DateTime {
 *     // 2개의 요소
 *     String yymmdd();  // 날짜 추상 메서드
 *     String hhmmss();  // 시간 추상 메서드
 * }
 *
 * @interface TestInfo {
 *     int count();
 *     String testedBy();
 *     String[] testTools();
 *     TestType testType();  // enum TestType { FIRST, FINAL }
 *     DateTime testDate();  // 자신이 아닌 다른 애너테이션(@DateTime)을 포함할 수 있다.
 * }
 *
 * // 요소에 이름과 타입에 맞게 값을 넣어준다.
 * @TestInfo(
 *     count=3, testedBy="Kim",
 *     testTools={"JUnit", "AutoTester"},
 *     testType=TestType.FIRST,
 *     testDate=@DateTime(yymmdd="160101", hhmmss="235959")
 * )
 * publioc class NewClass {...}
 * => 위처럼 선언하고 해당 애너테이션에 추상 메서드를 호출하면 그 값이 나온다.
 *    ex) anno.count() = 3이 나옴
 */

/**
 * 애너테이션의 요소
 * - 적용시 값을 지정하지 않으면, 사용될 수 있는 기본값 지정 가능(null 제외)
 * ex)
 * @interface TestInfo {
 *     int count() default 1;  // 기본 값을 1로 지정
 * }
 * @TestInfo  // default값이 있을 경우 생략이 가능해진다. @TestInfo(count=1)과 동일
 * public class NewClass {...}
 *
 *
 * - 요소가 하나이고 이름이 value일 때는 요소의 이름 생략가능
 * ex)
 * @interface TestInfo {
 *     String value();
 * }
 * @TestInfo("passed")  // 요소가 하나일 때는 @TestInfo(value="passed")와 동일
 * public class NewClass {...}
 *
 *
 * - 요소 타입이 배열인 경우 괄호 {}를 사용해야 한다.
 * ex)
 * @interface TestInfo {
 *     String[] testTools();
 * }
 * @Test(testTools={"JUnit", "AutoTester"}) // 값이 여러 개이면 괄호 사용
 * @Test(testTools=JUnit")                  // 값이 하나이면 괄호 생략가능
 * @Test(testTools={})                      // 값이 없을 때는 빈 괄호{}가 반드시 필요
 */


/**
 * 모든 애너테이션의 조상 - java.lang.annotaion.Annotation
 * - Annotation은 모든 애너테이션의 조상이지만 상속은 불가능하다.
 * ex) 허용되지 않는 표현
 * @interface TestInfo extends Annotation { // 에러, extends Annotation은 허용되지 않는 표현
 *     String value();
 * }
 *
 * - 사실 Annotation은 인터페이스이다.
 * public interface Annotation {
 *     // 즉, 아래 추상 메서드들은 구현하지 않아도 모든 애너테이션에 사용 가능하다.
 *     boolean equals(Object obj);
 *     int hashCode();
 *     String toString();
 *
 *     Class<\? extends Annotation> annotationType(); // 애너테이션의 타입을 반환
 * }
 */


/**
 * 마커 애너테이션
 * : 요소가 하나도 정의되지 않은 애너테이션
 *   ex)
 *   @Target(ElementType.METHOD)
 *   @Retention(RetentionPolicy.SOURCE)
 *   public @interface Test {} // 마커 애너테이션으로 정의된 요소가 하나도 없다.
 *
 *   @Test // 마커 애너테이션은 이 메서드가 테스트 대상임을 테스트 프로그램에게 알리는 역할만 함
 *   public void method() {
 *       ...
 *   }
 *
 */


/**
 * 애너테이션 요소의 규칙
 * : 애너테이션의 요소를 선언할 때 아래의 규칙을 반드시 지켜야 한다.
 *   1.요소의 타입은 기본형, String, enum, 애너테이션, Class(설계도 객체)만 허용됨
 *   2.추상 메서드의 괄호() 안에 매개변수를 선언할 수 없다.
 *   3.예외를 선언할 수 없다.
 *   4.요소를 타입 매개변수(ex) <T>)로 정의할 수 없다.
 */

class Parent {
    void parentMethod() {}
}

class Child extends Parent {
    @Override   // 메서드 이름이 실수로 잘못 적혀 발생하는 문제를 해결해줌
    @Deprecated // 앞으로 사용하지 않을 것을 권장
    void parentMethod() { }
}

@FunctionalInterface // 함수형 인터페이스는 하나의 추상 메서드만 가능한데
                     // 여기서 2개가 선언되었으므로 컴파일러가 에러 식별해줌
interface Testable {
    void test(); // 추상 메서드1
//    void check(); // 추상 메서드2
}

enum TestType { FIRST, FINAL }

@interface DateTime {
    // 2개의 요소
    String yymmdd();  // 날짜 추상 메서드
    String hhmmss();  // 시간 추상 메서드
}

@Retention(RetentionPolicy.RUNTIME) // 실행 시에 사용가능하도록 지정
@interface TestInfo {
    int count()            default 1;
    String testedBy();
    String[] testTools()   default "JUnit";
    TestType testType()    default TestType.FIRST;  // enum TestType { FIRST, FINAL }
    DateTime testDate();  // 자신이 아닌 다른 애너테이션(@DateTime)을 포함할 수 있다.
}

@Deprecated
@SuppressWarnings("1111") // 유효하지 않은 애너테이션은 무시된다.
// 애너테이션의 요소 값 정의하기
@TestInfo(testedBy="aaa", testTools = {"JUnit5", "JUnit"}, testDate=@DateTime(yymmdd="160101", hhmmss="235959"))
public class EX12_Annotation {
    @SuppressWarnings("deprecation") // @Deprecated에 대한 경고 메시지를 나오지 않게 지정
    public static void main(String[] args) {
        Child c = new Child();
        c.parentMethod(); // Deprecated된 메서드 사용하여 작동은 하나 경고 메시지 나옴

        
        // 예제 2) 애너테이션 가져오기 
        Class<EX12_Annotation> cls = EX12_Annotation.class;

        // 애너테이션의 요소 값 가져오기
        TestInfo anno = cls.getAnnotation(TestInfo.class);
        System.out.println("anno.testedBy() = " + anno.testedBy());
        System.out.println("anno.testDate().yymmdd() = " + anno.testDate().yymmdd());
        System.out.println("anno.testDate().hhmmss() = " + anno.testDate().hhmmss());

        for (String str : anno.testTools()) {
            System.out.println("testTools = " + str);
        }

        System.out.println();

        // EX12_Annotation에 적용된 모든 애너테이션을 가져온다. (유효하지 않은 애너테이션은 제외)
        Annotation[] annoArr = cls.getAnnotations();

        for (Annotation a : annoArr) { // 유효하지 않은 @SuppressWarnings("1111") 애너테이션 빼고 나온다.
            System.out.println(a);
        }
    }
}
