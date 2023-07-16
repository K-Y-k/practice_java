package org.example.ch7;

/**
 * 인터페이스
 * : * 추상 메서드의 집합 * (상수, static 메서드, default 메서드도 가능)
 *   즉, 구현된 것이 전혀 없는 설계도 껍데기(모든 멤버가 public)
 *
 * - 추상 클래스와 인터페이스의 차이
 * : 추상 클래스는 멤버 변수(iv, cv) 등이 있는 일반 클래스인데 추상 메서드를 가지고 있지만
 *   인터페이스는 추상 메서드만 가지고 있다.
 *
 * - 선언 방법
 * interface 인터페이스이름 {
 *     public static final 타입 상수이름 = 값;  // 멤버 변수는 안되지만 상수는 있을 수 있다.
 *     public abstract 메서드이름(매개변수목록); // 추상 메서드로 구현부(몸통 {}이 없다)
 * }
 *
 * - 인터페이스의 장점
 * 1.두 대상(객체) 간의 '연결, 대화, 소통'을 돕는 '중간 역할'을 한다.
 * 2.선언(설계)와 구현을 분리시킬 수 있게 한다.
 *   직접적인 관계의 두 클래스였을 때는 한쪽이 바뀌면 다른 한쪽에 메서드의 매개변수가 변경된 클래스여서 변경을 해야했지만
 *   인터페이스 덕분에 인터페이스를 구현한 곳이 변경되어도 인터페이스와 연관된 클래스쪽은 바꾸지 않아도 된다.(=느슨한 결합)
 * 3.개발 시간을 단축할 수 있다.
 * 4.변경에 유리한 유연한 설계가 가능하다.
 * 5.표준화가 가능하다. (ex) JDBC)
 * 6.서로 관계없는 클래스들을 관계를 맺어줄 수 있다.
 */

/**
 * 인터페이스의 default 메서드와 static 메서드
 * : 인터페이스는 추상 메서드만 가능했지만,
 *   JDK1.8부터 인터페이스에 해당 메서드들이 추가되었다. (단, 부가적인 것일 뿐 주된 것이 아님)
 *
 *   인터페이스에 새로운 메서드(추상 메서드)를 추가하면 인터페이스에 상속된 모든 클래스들이 추가되어야하므로
 *   추가가 어려웠기에 해결책으로 default 메서드가 나온 것이다.
 *   ex) default void new Method() {} // 몸통이 있어야한다.(인터페이스 규칙 위반해야함)
 *   => 디폴트 메서드가 기존의 메서드와 충돌할 때의 해결책
 *     1.여러 인터페이스의 default 메서드 간의 충돌인 경우
 *       : 인터페이스를 구현한 클래스에서 default 메서드를 오버라이딩 하낟.
 *     2.default 메서드와 조상 클래스의 메서드 간의 충돌인 경우
 *       : 조상 클래스의 메서드가 상속되고 default 메서드는 무시된다.
 *     => 그냥 직접 오버라이딩 하면 된다고 생각하기
 */
class A {
    // 선언(설계)와 구현을 분리 : 구현
    public void method(I i) {  // * 인터페이스를 구현한 객체만 들어올 수 있다. *
        i.method();            // 즉, 여기는 다른 한쪽이 변경이 일어나도 코드를 변경하지 않아도 된다.
    }
}
// 선언(설계)와 구현을 분리 : 선언(설계)
interface I {
    void method();
}
class B implements I {
    @Override
    public void method() {
        System.out.println("B클래스의 메서드");
    }
}

// 서로 관계 없는 클래스의 관계를 맺어주기 예시
// Tank와 Dropship은 서로 관계가 없지만
// 공통된 기능이 필요할 때 인터페이스에 공통된 기능을 작성하여 연관짓게 해준다.
interface Repairable {
    default void repair(Repairable r) { // Repairable을 구현한 객체만 들어올 수 있다.
        if (r instanceof Unit2) {
            Unit2 u = (Unit2) r;
//            while (u.hitPoint != u.MAX_HP){
//                u.hitPoint++;
//            }
        }
    }
}
class Unit2 {
}
class GroundUnit extends Unit2 {
}
class AirUnit extends Unit2 {
}
class Tank extends GroundUnit implements Repairable  {
    @Override
    public void repair(Repairable r) {
    }
}
class Dropship extends AirUnit implements Repairable  {
    @Override
    public void repair(Repairable r) {
    }
}


/**
 * 인터페이스의 상속
 * : 인터페이스의 조상은 인터페이스만 가능하다.(즉, Object 클래스가 최고 조상이 아니다.)
 *   객체 클래스는 단일 상속만 가능했지만
 *   인터페이스는 다중 상속이 가능하다. (추상 메서드는 충돌해도 몸통이 없어 충돌문제가 없기 때문)
 */
interface Movable {
    void move(int x, int y);
}

interface Attackable {
    void attack(int a);
}
interface PlayCard extends Movable, Attackable { // 다중 상속
    public static final int SPADE =4;
    final int DIAMOND = 3;             // 생략 가능(항상 final static이라서)  = public static final int DIAMOND = 3;
    static int HEART = 2;              // 생략 가능 = public static final int HEART = 2;
    int CLOVER = 1;                    // 생략 가능 = public static final int CLOVER = 1;

    public abstract String getCardNumber();
    String getCardKind();              // 생략 가능(항상 public abstract라서) = public abstract String getCardKind();
}

/**
 * 인터페이스의 구현
 * : 인터페이스에 정의된 추상 메서드를 완성하는 것
 *   일부만 구현할 경우 abstract class이다.
 *
 * - 선언 방법
 * class 클래스이름 implements 인터페이스이름 {
 *   // 인터페이스에 정의된 추상 메서드를 모두 구현해야 한다.
 * }
 */
class Player1 implements PlayCard {

    @Override
    public void move(int x, int y) {
        // 내용 완성하여 인터페이스의 추상 메서드를 구현
    }

    @Override
    public void attack(int a) {

    }

    @Override
    public String getCardNumber() {
        return null;
    }

    @Override
    public String getCardKind() {
        return null;
    }
}


/**
 * 인터페이스를 이용한 다형성
 * : 인터페이스를 메서드의 리턴타입으로 지정할 수 있다.
 *   인터페이스를 구현한 클래스의 객체를 반환해야한다.
 *   ex)
 *    반환타입
 *   Fightable method() {
 *       ...
 *       Fighter f = new Fighter();  // Fightable 인터페이스르 구현한 객체로 반환해야한다.
 *       return f; // Fightable이 Fighter의 조상이기 때문에 형변환이 가능하다.
 *   }
 *
 *  ex)
 *  interface Fightable {
 *      void move(int x, int y);
 *      void attack(Fightable f);  // * Fightable인터페이스를 구현한 클래스의 인스턴스(객체)만 가능하다. *
 *  }
 *
 *           부모 객체 상속      인터페이스 상속
 *  class Fighter extends Unit implements Fightable {
 *      public void move(int x, int y) {
 *         // 인터페이스의 추상 메서드는 구현되어 있지 않으므로 가능
 *      }
 *      public void attack(Fightable f) {
 *
 *      }
 *  }
 *  
 *  Unit u = new Fighter();
 *  Fightable f = new Fighter();  // 인터페이스 Fightable은 Fighter에서 자신의 추상 메서드였던 것만 사용 가능
 *  f.move(100, 200)
 *  f.attack(new Fighter());
 */

abstract class Unit {
    int x, y;
    abstract void move(int x, int y);
    void stop() {
        System.out.println("멈춥니다.");
    }
}

interface Fightable {
    void move(int x, int y);   // public abstract 생략한 것
    void attack(Fightable f);  // public abstract 생략한 것
}

class Fighter extends Unit implements Fightable {
    @Override // 오버라이딩 규칙은 조상보다 접근 제어자가 좁으면 안되기에
              // 인터페이스에 생략되었지만 public이므로 public으로 해야한다.
    public void move(int x, int y) {
        System.out.println("[" + x + ", " + y + "]로 이동");
    }

    @Override
    public void attack(Fightable f) {
        System.out.println(f + "를 공격");
    }

    // 싸울 수 있는 상대를 불러온다.
    Fightable getFightable() {
        Fighter f = new Fighter(); // Fighter를 생성해서 반환
        return f; // Fightable(조상 인터페이스)로 형변환되고 반환된다.
    }
}

public class EX7_Interface {
    public static void main(String[] args) {
        Fightable f1 = new Fighter(); // 또는 Unit f1 = new Fighter();도 되지만 attack부분은 없기에
        f1.move(100, 200);
        Fighter f2 = new Fighter();
        f1.attack(f2);
//        f1.stop();  Fightable에는 stop()이 없어서 호출 불가
    }
}
