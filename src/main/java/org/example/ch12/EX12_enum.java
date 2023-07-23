package org.example.ch12;



/**
 * 열거형
 * : 서로 관련된 상수들을 같이 묶어 놓아 편리하게 사용할 수 있다.
 *   ex)        enum 활용 전             ->              enum 활용 후
 *   class Card {                              class Card {      0      1       2      3
 *       static final int CLOVER = 0;              enum Kind { CLOVER, HEAR, DIAMOND, SPADE}
 *       static final int HEART = 1;
 *       static final int DIAMOND = 2;
 *       static final int SPADE = 3;
 *
 *       static final int TWO = 0;                 enum Value { TWO,   THREE,  FOUR }
 *       static final int THREE = 1;
 *       static final int FOUR = 2;
 *
 *       final int kind;                            final int kind;
 *       final int num;                             final int num;
 *   }                                         }
 *   
 *   
 *   자바는 타입에 안전한 열거형을 제공한다. (즉, 값&타입 모두 체크함)
 *   ex) if (CardKind.CLOVER == Card.Value.TWO) // 컴파일 에러 : 서로 같은 값이지만 타입이 달라서 비교 불가
 */

/**
 * 열거형의 정의와 사용
 * - 열거형을 정의하는 방법
 * enum 열거형이름 { 상수명1, 상수명2, ... }
 * ex) enum Direction { EAST, SOUTH, WEST, NORTH }
 * 
 * - 열거형 타입의 변수를 선언하고 사용하는 방법
 * ex)
 * class Unit {
 *     int x, y;
 *     Direction dir;            // 열거형의 인스턴스 변수를 선언 (EAST, SOUTH, WEST, NORTH만 들어올 수 있음)
 *     
 *     void init() {
 *         dir = Direction.EAST; // 유닛의 방향을 EAST로 초기화
 *     }
 * }
 * 
 * - 열거형 상수의 비교에 ==와 compareTo() 사용가능
 * ex) 
 * if (dir==Direction.EAST) {
 *     x++;
 * } else if (dir > Direction.WEST) {              // 에러 : 열거형 상수에 비교연산자 사용불가
 *     ...
 * } else if (dir.compareTo(Direction.WEST) > 0) { // 열거형 상수에 비교연산자를 사용하고 싶으면 compareTo()는 사용가능
 *     ...
 * }
 */

/**
 * 열거형의 조상 - java.lang.Enum클래스
 * : 모든 열거형은 Enum의 자손이며 아래의 메서드를 상속 받는다.
 *
 *        메서드의 종류                                         설명
 * Class<E> getDeclaringClass() (잘 안씀)              열거형의 Class 객체를 반환
 *
 * String name()                                      열거형 상수의 이름을 문자열로 반환
 *
 * int ordinal()                                      열거형 상수가 정의된 순서를 반환(0부터 시작)
 *
 * T valueOf(Class<T> enumType, String name)          지정된 열거형에서 name과 일치하는 열거형 상수를 반환
 *
 *
 *   values(), valueOf() 메서드는 컴파일러가 자동으로 추가한다.
 *   static E[] values()
 *   static E valueOf(String name)
 *
 *   ex) values() 사용 예시
 *   Direction[] dArr = Direction.values();   // 열거형 상수의 모든 값을 배열로 반환
 *
 *   for(Direction d : dArr) // for(Direction d : Direction.values())
 *       System.out.printf("s=%d%n", d.name(), d.ordinal());
 *                                    이름         순서
 *
 *   ex) valueOf() 사용 예시
 *   Direction d = Direction.valueOf("WEST"); // 열거형 상수 이름을 넣는다. (=Direction.WEST와 같음)
 */

/**
 * 열거형에 멤버 추가하기
 * : 불연속적인 열거형 상수의 경우, 원하는 값을 괄호() 안에 적는다.
 *   괄호 안의 값은 여러 개가 들어갈 수 있다.
 *   단, 이렇게 한 경우 괄호 안의 값을 사용하려면 1.정수를 저장할 필드(인스턴스 변수)를 추가해야 한다.
 *                                         2.생성자를 추가해야 한다.(여기서의 생성자는 항상 private)
 *   ex)
 *   enum Direction { EAST(1), SOUTH(5), WEST(-1), NORTH(10) }
 *
 *   원하는 값을 저장한 괄호() 안의 값을 사용하려면, 인스턴스 변수와 생성자를 새로 추가해 줘야 한다.
 *   ex)
 *   enum Direction {
 *       EAST(1), SOUTH(5), WEST(-1), NORTH(10); // 끝에 ';'를 추가해야 한다.
 *
 *       private final int value;               // 1.정수를 저장할 필드(인스턴스 변수)를 추가해야 한다.
 *
 *       Direction(int value) {
 *           this.value = value;                // 2.생성자를 추가해야 한다.(여기서의 생성자는 항상 private)
 *       }
 *       public int getValue( {
 *           return value;
 *       }
 *   }
 *   
 *   열거형의 생성자는 묵시적으로 private이므로 외부에서 객체 생성 불가능
 *   ex)
 *   Direction d = new Direction(1); // 에러, 열거형의 생성자는 private라서 외부에서 호출불가
 *   
 *   
 */

enum Direction {
    // 0    1     2     3
    EAST, SOUTH, WEST, NORTH
}

enum Direction2 {
    EAST(1, ">"), SOUTH(2,"V"), WEST(3,"<"), NORTH(4,"^");
    
    private static final Direction2[] DIR_ARR = Direction2.values();
    private final int value;
    private final String symbol;

    Direction2(int value, String symbol) { // 접근 제이자 private 생략됨
        this.value = value;
        this.symbol = symbol;
    }

    public int getValue() { return value; }
    public String getSymbol() { return symbol; }

    // 위 열거형 상수의 값을 여러 개 선언했으므로 이들 중 하나를 가져오기 위한 메서드
    public static Direction2 of(int dir) {
        if (dir < 1 || dir > 4) // 0~3범위를 벗어나면 예외발생 처리
            throw new IllegalArgumentException("Invalid value : " + dir);

        return DIR_ARR[dir - 1];
    }

    // 방향을 회전시키는 메서드. num의 값만큼 90도씩 시계방향으로 회전한다.
    public Direction2 rotate(int num) {
        num = num % 4; // 회전 방향은 결국 총 4가지 방향이므로

        if (num < 0)
            num += 4;  // num이 음수일 때는 시계반대 방향으로 회전

        return DIR_ARR[(value-1+num) % 4];
    }

    @Override
    public String toString() {
        return name() + getSymbol();
    }
}

public class EX12_enum {
    public static void main(String[] args) {
        // 예시 1)
        // 열거형 상수 선언 3가지 방법
        Direction d1 = Direction.EAST; // 주로 이 방식 사용
        Direction d2 = Direction.valueOf("WEST");
        Direction d3 = Enum.valueOf(Direction.class, "EAST");

        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d3 = " + d3);

        System.out.println("(d1==d2) ? " + (d1 == d2));                   // false
        System.out.println("(d1==d3) ? " + (d1 == d3));                   // true
        System.out.println("d1.equals(d3) ? " + d1.equals(d3));           // true, 즉 equals로도 가능하므로 열거형 상수는 단순한 기본형 값이 아닌 객체이다!!
//        System.out.println("(d1 > d3) ? " + (d1 > d3));                 // 에러, 열거형 상수가 객체이므로 비교연산자 사용 불가능한 것
        System.out.println("(d1.compareTo(d3)) ? " + (d1.compareTo(d3))); // 즉, compareTo를 사용하여 비교해야함
        System.out.println("(d1.compareTo(d2)) ? " + (d1.compareTo(d2)));

        switch (d1) {
            case EAST : // Direction.EAST라고 쓸 수 없다!
                System.out.println("The direction is EAST.");
                break;
            case SOUTH:
                System.out.println("The direction is SOUTH.");
                break;
            case WEST:
                System.out.println("The direction is WEST.");
                break;
            case NORTH:
                System.out.println("The direction is NORTH.");
                break;
            default:
                System.out.println("Invalid Direction.");
                break;
        }

        Direction[] dArr = Direction.values(); // .values()는 열거형의 모든 상수를 배열로 반환

        for (Direction d : dArr) {
            System.out.printf("%s=%d%n", d.name(), d.ordinal()); // ordinal()은 순서를 반환(즉, 상수가 가진 값이 아닌 상수의 순서)
        }

        System.out.println();
        
        // 예시 2)
        for (Direction2 d : Direction2.values()) {
            System.out.printf("%s=%d%n", d.name(), d.getValue());
        }

        Direction2 d21 = Direction2.EAST;
        Direction2 d22 = Direction2.of(1);

        System.out.printf("d21=%s, %d%n", d21.name(), d21.getValue());
        System.out.printf("d22=%s, %d%n", d22.name(), d22.getValue());

        System.out.println(Direction2.EAST.rotate(1));
        System.out.println(Direction2.EAST.rotate(2));
        System.out.println(Direction2.EAST.rotate(-1));  // 음수는 시계 반대방향
        System.out.println(Direction2.EAST.rotate(-2));
    }
}
