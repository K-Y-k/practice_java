package org.example.ch7;


/**
 * 클래스의 관계 1.상속 관계
 *             2.포함 관계
 * => 포함 관계가 90%정도 많고 상속은 꼭 필요할 때만 한다.
 *
 * 포함이란?
 * : 클래스의 멤버로 참조변수를 선언하는 것
 *
 * ex)
 * class Point {
 *     int x;
 *     int y;
 * }
 *
 * class Circle {
 *     Point c = new Point(); // Point가 Circle과 포함 관계로
 *                            // 복잡도가 더 줄어든다.
 *                            // 접근할 때 Circle변수명.Point변수명.x 이런식으로 접근
 *     int r;
 * }
 */
class Point {
    int x;
    int y;
}

class Circle {
    Point p = new Point(); // 포함
    int r;
}

public class EX7_Composite {
    public static void main(String[] args) {
        Circle c = new Circle();
        c.p.x = 1;
        c.p.y = 2;
        c.r = 3;

        System.out.println("c.p.x = " + c.p.x);
        System.out.println("c.p.y = " + c.p.y);
        System.out.println("c.r = " + c.r);
    }
}
