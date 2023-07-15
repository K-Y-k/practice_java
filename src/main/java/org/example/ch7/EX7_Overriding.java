package org.example.ch7;


/**
 * 오버라이딩
 * : 상속받은 조상의 메서드를 자신에 맞게 변경하는 것
 *    = 덮어 쓰는 것
 *
 *  - 조건
 *  1. 선언부(접근제어자, 반환타입, 메서드명, 매개변수 목록)가 조상 클래스 메서드와 동일해야한다.
 *  2. 접근 제억자를 조상 클래스의 메서드보다 좁은 범위로 변경할 수 없다. (public > protected > private)
 *  3. 예외는 조상 클래스의 메서드보다 많이 선언할 수 없다.
 *
 *  => 오버로딩과 오버라이딩은 관계가 전혀 없다.
 *  오버로딩   : 이름이 없지만 기존에 없는 매개변수로 새로운 메서드를 정의하는 것
 *  오버라이딩 : 상속받은 메서드의 내용을 변경하는 것
 */
class Point2 {
    int x;
    int y;

    Point2() {
    }

    Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    String getLocation() {
        return "x : " + x + ", y : " + y;
    }
}

class Point3D2 extends Point2 {
    int z;

    Point3D2() {
    }

    Point3D2(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    String getLocation() { // 오버라이딩하여 내용만 변경 가능
        return "x : " + x + ", y : " + y + ", z : " + z;
    }
}

public class EX7_Overriding {
    public static void main(String[] args) {
        Point3D2 p = new Point3D2(3, 5, 7);
        System.out.println(p.getLocation());
    }
}
