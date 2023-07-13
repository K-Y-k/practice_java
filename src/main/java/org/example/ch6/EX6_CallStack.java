package org.example.ch6;

public class EX6_CallStack {

    public static void main(String[] args) {
        /**
         * 스택(stack): 밑이 막힌 상자로 위에서부터 쌓는다.
         *
         * 호출 스택(call stack)
         * : 메서드 수행에 필요한 메모리가 제공되는 공간이다.
         *   메서드가 호출되면 호출스택에 메모리를 할당하고 종료되면 해제된다.
         *
         *   ex)
         *   1.               main()이 println()을 호출함
         *        |                 |        |                   |
         *        |                 |  --->  | println 메서드(실행)|
         *        | main 메서드(실행) |        | main 메서드(대기)   |
         *
         *   2.                  println()이 종료됨
         *        |                   |        |                   |
         *        | println 메서드(실행)|  --->  |                   |
         *        | main 메서드(대기)   |        | main 메서드(실행)   |
         *
         *   3.                     main()이 종료됨(= 프로그램 종료)
         *        |                   |        |                   |
         *        |                   |  --->  |                   |
         *        | main 메서드(실행)   |        |                   |
         *
         *   => 즉, 1.아래에 있는 메서드가 위의 메서드를 호출하여 메서드들의 호출 관계를 확인할 수 있다.
         *          2.맨 위의 메서드 하나만 실행하고 아래 나머지 메서드들은 대기한다.
         *
         *   싱글 스레드는 이 호출 스택이 1개이지만
         *   멀티 스레드는 이 호출 스택들이 여러 개이다.
         */

        /**
         * 기본형 매개변수 - 타입이 기본형 타입이다.
         *                변수의 값을 읽기만 할 수 있다.(read only)      변경 불가
         */
        Data d = new Data();
        d.x = 10;
        System.out.println("main() : d.x = " + d.x);  // 10

        change(d.x); // 1000으로 바뀔 것 같지만
                     // 사실 생성된 d.x의 참조변수와 change메서드에 d.x로 들어온 매개변수의 참조변수는 다르기에 적용이 안된다!
        System.out.println("After change(d.x)");
        System.out.println("main() : d.x = " + d.x);  // 바뀌지 않고 10 그대로 출력됨

        System.out.println();


        /**
         * 참조형 매개변수 - 타입이 참조형 타입이다.
         *                변수의 값을 읽고 변경할 수 있다.(read & write) 변경 가능
         */
        Data d2 = new Data();
        d2.x = 10;
        System.out.println("main() : d2.x = " + d2.x);  // 10

        change2(d2); // 생성한 객체의 변수를 넣어 현재 객체의 주소로 전달되므로 변경 완료

        System.out.println("After change(d2.x)");
        System.out.println("main() : d2.x = " + d2.x); // 바뀐 것이 적용되어 1000 출력

        Data copyD2 = copy(d2);
        System.out.println("d2.x = " + d2.x);
        System.out.println("copyD2.x = " + copyD2.x);


        // 인스턴스 메서드 vs static 메서드 : iv를 사용하느냐 마느냐에 따른 메서드를 만들면 된다.
        System.out.println(MyMath.add(200L, 100L)); // static 메서드 호출

        MyMath mm = new MyMath();
        mm.a = 200L;
        mm.b = 100L;
        System.out.println(mm.add());
    }

    /**
     * 인스턴스 메서드
     * : 인스턴스 생성 후,'참조변수.메서드이름()'으로 호출한다.
     *   인스턴스 멤버(iv, im)와 관련된 작업을 하는 메서드
     *   메서드 내에서 인스턴스 변수(iv) 사용가능
     *   즉, 인스턴스 변수를 사용하는 메서드를 만들어야할 경우 인스턴스 메서드를 사용한다.
     */
    static class MyMath {
        long a, b;     // 인스턴스 변수 iv
        static int cv; // 클래스 변수 cv

        long add() { // iv를 사용하는 인스턴스 메서드
            return a + b;
        }

        static long add(long a, long b) { // iv가 아닌 지역 매개 변수를 사용하는 static 메서드
            // 클래스 변수 cv 사용 가능
            return a + b;
        }
    }

    /**
     * static 메서드
     * : 객체생성 없이 '클래스이름.메서드이름()'으로 호출한다.
     *   인스턴스 멤버(iv, im)와 관련없는 작업을 하는 메서드
     *   메서드 내에서 인스턴스 변수(iv) 사용불가
     *   즉, 인스턴스 변수를 사용하지 않고 지역변수를 사용해야하는 경우 사용한다.
     *   (단, static으로 선언된 클래스 변수(cv)는 사용 가능하다.)
     */
    // static 메서드는 객체 생성 없이 호출가능하다!
    // 또한 같은 클래스내에 있기에 참조 변수 사용없이 메서드명만으로 사용 가능하다.
    // 그래서 위에서 보면 1.클래스명 참조변수명 new 클래스명;
    //                 2.참조변수명.메서드명;의 순서가 아닌
    // 그냥 메서드명으로 호출할 수 있었던 것이다.
    static void change(int x) { // 기본형 매개변수
        x = 1000;               // 지역변수
        System.out.println("change() : x = " + x);  // 여기서는 1000이 나오지만 바뀌는 것이 적용되지 않는 이유 2가지
                                                    // 1. 지역변수 x를 바꾼 것이기에 결국 바뀐 것이 적용되지 않는다.
                                                    // 2. 기본형 매개변수는 읽기만 가능하기에 바뀐 것이 적용되지 않는다.
                                                    // 바뀐 것을 적용하려면 참조형 매개변수를 사용해야한다.
    }
    static void change2(Data d) { // 참조형 매개변수
        d.x = 1000;               // 지역변수
        System.out.println("change() : x = " + d.x);
    }

    // 참조형 반환 타입 예시
    static Data copy(Data d) { // 참조형 매개변수
        Data temp = new Data(); // 새로운 객체를 생성하고
        temp.x = d.x;           // d.x의 값을 temp.x에 복사한다.
        return temp;            // 복사한 객체의 주소를 반환한다.
    }


    /**
     * 정리
     * 1. static 메서드는 static 메서드 또는 static 클래스 변수 호출이 가능하다.
     * 2. static 메서드는 인스턴스 변수(iv) 사용할 수 없다.
     * 3. static 메서드는 인스턴스 메서드를 호출 할 수 없다.
     *    이유는 객체(iv묶음)가 없을 수도 있기 때문이다.
     */
}

class Data {
    int x;
}
