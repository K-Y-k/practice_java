package org.example.ch7;


public class EX7_Modifier {
    public static void main(String[] args) {
        /**
         * 제어자
         * : 클래스와 클래스의 멤버(멤버 변수, 메서드)에 부가적인 의미를 부여한다.
         *   하나의 대상에 여러 제어자를 같이 사용가능하다.(단, 접근 제어자는 하나만)
         *   ex) public static final int WIDTH = 200;
         *
         * - 접근 제어자
         * public, protected, (default), private
         *
         * - 그외
         * static, final, abstract, native, transient, synchronize, volatile, strictfp
         */

        /**
         * static - 클래스의, 공통적인
         *   대상                         의미
         * 멤버 변수        모든 인스턴스에 공통적으로 사용되는 클래스 변수가 된다.
         *                클래스 변수는 인스턴스를 생성하지 않고도 사용 가능하다.
         *                클래스가 메모리에 로드될 때 생성된다.
         * 
         * 메서드          인스턴스를 생성하지 않고도 호출이 가능한 메서드가 된다.
         *                static 메서드 내에서는 인스턴스 멤버들(iv, cv)을 직접 사용할 수 없다. 지역변수만 사용 가능
         */
        class StaticTest {
            static int width = 200;       // 간단(명시적) 초기화
            static int height = 120;      // 공통으로 사용되는 멤버변수일 때 static을 사용한다.
        
            static {                      // 클래스 초기화 블럭
                // static 변수의 복잡한 초기화 수행 코드
            }

            static int max(int a, int b) { // 클래스 메서드(static 메서드)
                return a > b ? a : b;      // 지역변수 사용, 여기서는 iv와 cv 사용불가
            }
        }

        /**
         * fianl - 마지막의, 변경될 수 없는
         *
         *    대상                        의미
         *   클래스        변경될 수 없는 클래스, 확장될 수 없는 클래스가 된다.
         *                그래서 final로 지정된 클래스는 다른 클래스의 조상이 될 수 없다. (ex) String, Math)
         *
         *   메서드        변경될 수 없는 메서드, fianl로 지정된 메서드는 오버라이딩을 통해 재정의 될 수 없다.
         *
         *  멤버변수        변수 앞에 final이 붙으면 값을 변경할 수 없는 상수가 된다.
         *  지역변수
         */
        final class FinalTest {         // 조상이 될 수 없는 클래스
            final int MAX_SIZE = 10;    // 값을 변경할 수 없는 멤버변수(=상수)

            final void getMaxSize() {   // 오버라이딩 할 수 없는 메서드(변경불가)
                final int LV = MAX_SIZE;
            }
        }

        /**
         * abstract - 추상의, 미완성의
         * : 미완성 클래스이므로 이를 상속한 자식 클래스를 완전한 클래스(= 구상 클래스)로 만들어서 사용한다.
         *
         *   대상                        의미
         *  클래스         클래스 내에 추상 메서드가 선언되어 있음을 의미한다.
         *
         *  메서드         선언부만 작성하고 구현부는 작성하지 않은 추상 메서드임을 알린다.
         */
        abstract class AbstractTest {  // 추상 클래스(추상 메서드를 포함한 클래스)
                                       // = 미완성 클래스
            abstract void move();      // 추상 메서드 ({ } 몸통이 없다 = 구현부가 없는 메서드)
                                       // = 미완성 메서드
        }
//        AbstractTest abstractTest = new AbstractTest(); // 미완성 클래스이므로 에러 (추상 클래스의 인스턴스 생성불가)


        /**
         * 접근 제어자
         * public > protected > (default) > private
         * (default)는 아무 것도 안 붙인 것
         *
         * - privat    : 같은 클래스 내에서만 접근 가능
         * - (default) : 같은 패키지 내에서만 접근 가능
         * - protected : 같은 패키지 내에서, 그리고 다른 패키지의 자손클래스에서 접근이 가능하다.
         * - public    : 접근 제헌이 전혀 없다.
         * 
         * 클래스 앞에는 public, (default)만
         * 메서드 앞에는 public, protected,(default), private 모두 가능
         */
        MyParent p = new MyParent();
//        System.out.println(p.prv);   // EX7_Modifier와 MyParent는 서로 다른 클래스이므로 에러
        System.out.println(p.dft);
        System.out.println(p.prt);
        System.out.println(p.pub);

    }
}

class MyParent {
    private int prv;      // 같은 클래스
    int dft;              // 같은 패키지
    protected int prt;    // 같은 패키지 or 자손(다른 패키지)
    public int pub;       // 접근 제한 없음

    public void printMembers() {
        System.out.println("prv = " + prv);
        System.out.println("dft = " + dft);
        System.out.println("prt = " + prt);
        System.out.println("pub = " + pub);
    }
}
