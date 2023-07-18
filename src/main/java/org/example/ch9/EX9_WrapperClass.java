package org.example.ch9;

/**
 * 래퍼 클래스
 * : 8개의 기본형을 객체로 다뤄야할 때 사용한느 클래스 (기본형 값을 감싸는 클래스)
 *   자바는 객체지향 언어이기 때문에 기본형도 객체가 있다.
 *   자바는 90%가 객체이다.(10%가 객체가 아닌 기본형 이유는 성능향상 때문)
 *
 *   래퍼 클래스는 모두 equals()가 오버라이딩 되어 있어서 해당 클래스의 equals는 주소가 아닌 객체 값을 비교한다.
 *
 *   ex) Integer 클래스
 *   public final class Integer extends Number implements Comparable {
 *      ...
 *      private int value; // 기본형(int)을 감싸고 있음
 *      ...
 *  }
 *
 *  기본형       래퍼클래스              생성자                           활용예
 *  boolean     Boolean          Boolean(boolean value)    Boolean b = new Boolean(true);
 *                               Boolean(String s)         Boolean b2 = new Boolean("true");
 *
 *  char        Character        Character(char value)     Character c = new Character('a');
 *
 *  byte        Byte             Byte(byte value)          Byte b = new Byte(10);
 *                               Byte(String s)            Byte b2 = new Byte("10");
 *
 *  short       Short            Short(short value)        Short s = new Short(10);
 *                               Short(String s)           Short s2 = new Short("10");
 *
 *  int         Integer          Integer(int value)        Integer i = new Integer(100);
 *  int         Integer          Integer(String s)         Integer i = new Integer("100");
 *
 *  long        Long             Long(long value)          Long l = new Long(100);
 *                               Long(String s)            Long l2 = new Long("100");
 *
 *  float       Float            Float(double value)       Float f = new Float(1.0);
 *                               Float(float value)        Float f2 = new Float(1.0f);
 *                               Float(String s)           Float f3 = new Float("1.0f");
 *
 *  double      Double           Double(double value)      Double d = new Double(1.0);
 *                               Double(String s)          Double d2 = new Double("1.0");
 */

/**
 * Number 클래스
 * : 모든 숫자 래퍼 클래스의 조상
 *
 *                  Object
 *    Boolean      Character       Number
 *                              Byte Short Integer Long Float Double BigInteger(아주 큰 정수) BigDecimal(아주 큰 실수)
 */
public class EX9_WrapperClass {
    public static void main(String[] args) {
        Integer i = Integer.valueOf(100);
        Integer i2 = Integer.valueOf(100);

        System.out.println("i==i2 ? " + (i==i2));                   // false
        System.out.println("i.equals(i2) ? " + i.equals(i2));       // true (wrapper 클래스는 오버라이딩 되어 있기에)
        System.out.println("i.compareTo(i2) ? " + i.compareTo(i2)); // 정렬에 사용됨 + 같으면 0, 오른쪽이 작으면 양수, 오른쪽이 크면 음수
        System.out.println("i.toString() ? " + i.toString());       // 100

        System.out.println("MAX_VALUE = " + Integer.MAX_VALUE);     // +20억
        System.out.println("MIN_VALUE = " + Integer.MIN_VALUE);     // -20억
        System.out.println("SIZE = " + Integer.SIZE + " bits");     // 32비트
        System.out.println("BYTES = " + Integer.BYTES + " bytes");  // 4byte
        System.out.println("TYPE = " + Integer.TYPE);               // int


    }
}
