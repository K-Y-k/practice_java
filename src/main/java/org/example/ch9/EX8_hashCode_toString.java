package org.example.ch9;

import java.util.Objects;

/**
 * hashCode()
 * : 객체의 해시코드를 반환하는 메서드
 *
 *   Object 클래스의 hashCode()는 객체의 주소를 int로 변환해서 반환한다.
 *   public class Object {
 *       ...
 *       public native int hashCode(); // native 메서드는 OS가 가지고 있는 메서드로
 *                                     // 이미 있는 메서드이므로 내용이 없다.
 *   }
 *
 *   equals()와 hashCode()는 둘 다 객체의 주소를 가지고 작업하므로
 *   equals()를 주소에서 iv로 작업하게 오버라이딩하면 hashCode()도 iv로 오버라이딩해야 한다.
 *   * equals()의 결과가 true인 두 객체의 해시코드는 같아야 하기 때문이다. *
 *   즉 equals()가 서로 true이면 해시코드도 같아야한다!
 *
 *   ex)
 *   String str1 = new String("abc");
 *   String str1 = new String("abc");
 *
 *   System.out.println(str1.equals(str2));  // true
 *   System.out.println(str1.hashCode);      // 96354
 *   System.out.println(str2.hashCode);      // 96354
 *
 */

/**
 * toString()
 * : 객체를 문자열(String)로 변환하기 위한 메서드
 *    public String toString() {
 *               설계도 객체  클래스이름    위치      객체주소 값을 16진법으로
 *        return getClass().getName() + "@" + Integer.toHexString(hashCode());
 *    }
 *
 */
class Card {
    String kind;
    int number;

    Card() {
        this("SPADE", 1);
    }
    Card(String kind, int number) {
        this.kind = kind;
        this.number = number;
    }

    // Object 클래스의 equals()을 오버라이딩
    public boolean equals(Object obj) { // 오버라이딩은 선언부가 일치해야하므로 매개변수도 Object 클래스인 것
        if (!(obj instanceof Card))
         return false;

        Card c = (Card) obj; // Object인 obj를 Card로 형변환
        return this.kind.equals(c.kind) && this.number == c.number;

    }

    // equals()을 오버라이딩 했으면 hashCode()도 오버라이딩 해야한다.
    public int hashCode() {
        // Objects 클래스는 객체와 관련된 유용한 메서드를 제공하는 유틸 클래스
        return Objects.hash(kind, number); // 한개 여러개 넣어도 상관 없다.
                                           // 매개변수가 가변인자(지정 값의 개수가 정해져있지 않음)이기 때문
    }



    // Object 클래스의 toString()을 오버라이딩
    public String toString() {
        return "kind:"+kind+", number:"+number;
    }
}
public class EX8_hashCode_toString {
    public static void main(String[] args) {
        Card c1 = new Card();
        Card c2 = new Card();

        System.out.println(c1.equals(c2)); // 오버라이딩한 전에는 두 객체의 주소가 다르므로 false였지만
                                           // iv로 오버라이딩 한 후 비교하면 같은 iv이므로 true

        System.out.println(c1.hashCode()); // 오버라이딩한 전에는 두 객체의 주소가 다르므로 두 해시코드 값이 서로 달랐지만
        System.out.println(c2.hashCode()); // 오버라이딩한 후에는 두 해시코드 값이 같다.

        System.out.println(c1.toString()); // Card@19e0bfd
        System.out.println(c2.toString()); // Card@1939a55 각 객체를 생성했으므로 주소가 다름
        // toString을 오버라이딩한 경우 iv값을 출력할 수 있음
        // 객체 주소보다 iv 값을 출력할 필요가 많으므로

    }
}
