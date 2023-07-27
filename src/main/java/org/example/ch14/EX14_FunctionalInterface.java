package org.example.ch14;


/**
 * 함수형 인터페이스
 * : 단 하나의 추상 메서드만 선언된 인터페이스
 *   ex)
 *   @FunctionalInterface // 안붙여도 되지만 붙이면 컴파일할 때 함수형 인터페이스로 작성이 잘 되었는지 검사해주어 붙여주는 것이 좋다.
 *   interface MyFunction {
 *       public abstract int max(int a, int b);
 *   }
 *   => 위를 구현한 것
 *   MyFunction f = new MyFunction() {  // 익명클래스 : 클래스 선언, 객체 생성 동시에
 *                      public int max(int a, int b) {
 *                          return a > b ? a: b;
 *                      }
 *                  };
 *
 *   int value = f.max(3,5); // 호출이 가능해짐, MyFunction에 max()가 있기에
 *
 *   => 함수형 인터페이스 타입의 참조변수로 람다식을 참조할 수 있다.
 *      (단, 함수형 인터페이스의 메서드와 람다식의 매개변수 개수와 반환타입이 일치해야 함)
 *      MyFunction f = (a,b) -> a  > b ? a : b; // 이렇게 람다식으로 줄일 수 있다.
 *      int value = f.max(3,5);                 // 실제로는 람다식(익명 함수)이 호출됨
 * 
 */

/**
 * 익명 객체를 람다식으로 대체
 * List<String> list = Arrays.asList("abc", "aaa"", "bbb", "ddd", "aaa");
 *
 * Collections.sort(list, new Comarator<String>() {
 *                            public int compare(String s1, String s2) {
 *                                return s2.compareTo(s1);
 *                            }
 *                        });
 *
 * => 1.함수형 인터페이스를 선언하고
 *    @FunctionalInterface
 *    interface Comparator<T> {
 *        int compare(T o1, T o2);
 *    }
 *    2. 람다식으로 함수형 인터페이스 객체를 생성하고, 함수형 인터페이스의 추상 메서드를 호출해서 사용
 *    Collections.sort(list, (s1,s2) -> s2.compareTo(s1));
 */


/**
 * 함수형 인터페이스 타입의 매개변수, 반환타입
 * - 함수형 인터페이스 타입의 매개변수
 * @FuntionalInterface
 * interface MyFunction {
 *     void myMethod();
 * }
 *
 * void aMethod(MyFunction f) {
 *     f.myMethod(); // MyFunction에 정의된 람다식 메서드를 호출
 * }
 * 
 * 람다식으로 호출하기
 * 1.MyFunction f = () -> System.out.println("myMethod()");
 * 2.aMethod(f);
 * => 람다식을 참조변수 안에 직접 넣어 간추리기
 *    aMethod(()-> System.out.println("myMethod()"));
 *
 *
 * - 함수형 인터페이스 타입인 반환타입
 * : 이 메서드는 람다식을 반환한다는 뜻
 * ex)
 * MyFunction myMethod() {                         MyFunction myMethod() {
 *     MyFunction f = () -> {};         =>             return () -> {}; // 한줄로 줄이기
 *     return f;                                   }
 * }
 *
 */

@FunctionalInterface
interface MyFunction {
    // 함수형 인터페이스는 단 하나의 추상메서드만 가져야 한다.
    int max(int a, int b);  // == public abstract int max(int a, int b);
}

@FunctionalInterface
interface MyFunction2 {
    void run();            // == public abstract void run();
}

public class EX14_FunctionalInterface {
    static void execute(MyFunction2 f ) { // 매개변수의 타입이 MyFunction2(함수형 인터페이스)인 메서드
        f.run();
    }

    static MyFunction2 getMyFunction() {  // 반환타입이 MyFunction2(함수형 인터페이스)인 메서드
//        MyFunction2 f = () -> System.out.println("f3.run()");
//        return f;
        return () -> System.out.println("f3.run()");  // 람다식으로 줄임

    }

    public static void main(String[] args) {
        // 예제 1)
//        MyFunction obj = new MyFunction() {
//            public int max(int a, int b) {    // 오버라이딩 규칙 - 접근제어자는 좁게 못바꾸어 public을 붙여야함
//                return Math.max(a, b);
//            }
//        };
        // 위 코드를 람다식으로 간추린 것
        // 람다식(익명 객체)을 다루기 위한 참조변수의 타입은 함수형 인터페이스로 해야한다.
        MyFunction f = (a, b) -> a > b ? a : b; // 이 람다식하고 함수형 인터페이스의 추상 메서드의 매개변수 타입과 결과타입이 같아야 한다!

        int value = f.max(3,5); // 람다식에 이름이 없으므로 함수형 인터페이스의 추상 메서드에 이름이 있어야 하는 것!
                                      // 즉, 추상 메서드를 호출하여 람다식을 사용한다.
        System.out.println("value = " + value);
        System.out.println();


        // 예제 2)
        // 람다식으로 MyFunction2의 run()을 구현
        MyFunction2 f1 = () -> System.out.println("f1.run()"); // 함수형 인터페이스의 매개변수타입과 반환타입이 같다.

        MyFunction2 f2 = new MyFunction2() {                   // 익명 클래스로 run()을 직접 구현한 것
            @Override 
            public void run() {              // public을 반드시 붙여야 함
                System.out.println("f2.run()");
            }
        };

        MyFunction2 f3 = getMyFunction();                      // 반환타입이 함수형 인터페이스인 메서드 사용한 것

        f1.run();
        f2.run();
        f3.run();

        execute(f1); // == execute(() -> System.out.println("f1.run()"));
        execute( () -> System.out.println("run()"));
    }

}
