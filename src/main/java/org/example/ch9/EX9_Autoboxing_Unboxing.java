package org.example.ch9;

/**
 * - 문자열을 숫자로 변환하기
 * 1. intValue()
 *    int i = new Integer("100").intValue();
 *
 * 2. Integer.parseInt()
 *    int i = Integer.parseInt("100");
 *
 * 3. Integer.valueOf()
 *    Integer(또는 int) i = Integer.valueOf("100");
 *
 *
 * - n진법의 문자열을 숫자로 변환하는 방법
 * int i4 = Integer.parseInt("100", 2);    2진수  -> 4
 * int i5 = Integer.parseInt("100", 8);    4진수  -> 64
 * int i6 = Integer.parseInt("100", 16);   16진수 -> 256
 * int i7 = Integer.parseInt("100", 16);   16진수 -> 255
 */

import java.util.ArrayList;
import java.util.Objects;

/**
 *         JDK1.5 이후 자동으로 변환해주는 것
 *     오토박싱          &           언박싱
 * 기본형 -> 래퍼클래스          래퍼클래스 -> 기본형
 *
 * => 이 덕분에 래퍼클래스와 기본형을 자유롭게 사용되는 것이었다.
 *
 * JDK1.5 이전에는 기본형과 참조형간의 연산이 불가능하다.
 * int i = 5;
 * Integer iObj = new Integer(7);
 * int sum = i + iObj;  // 에러 기본형과 참조형 간의 덧셈 불가(JDK1.5 이전)
 *                      // JDK1.5 이후이면 자동 컴파일로 i + iObj.intValue;로 오토박싱
 */
public class EX9_Autoboxing_Unboxing {
    public static void main(String[] args) {
        int i = Integer.parseInt("100");
        System.out.println("i="+Integer.parseInt("100"));
        System.out.println("i="+Integer.parseInt("100", 10)); // 10진수라 위와 동일
        System.out.println("i="+Integer.parseInt("100", 2)); // 2진수
        System.out.println("i="+Integer.parseInt("FF", 16)); // 16진수


        ArrayList<Integer> list = new ArrayList<>();
//        list.add(new Integer(100)); // 리스트에는 객체만 주가 가능하기에 원래 이런 형태이다.
        list.add(100);                // JDK1.5이전에는 에러이고 1.8이후는 오토박싱으로 new Integer(100) 형태로 자동변환해주어 가능

//        Integer i2 = list.get(0);        // Integer 클래스로 담아야하는데
//        int i2 = list.get(0).intValue(); // intValue()로 int로 변환하여 저장할 수 있다.
        int i2 = list.get(0);              // 언박싱으로 컴파일러가 intValue()로 int로 자동변환 해준다.


        int i3 = 10;
        // 컴파일러가 기본형을 참조형으로 형변환해줌
        Integer intg = (Integer) i3;  // Integer intg = Integer.valueOf(i);로 오토박싱
        Object obj = (Object) i3;     // Object obj = (Object)Integer.valueOf(i);로 오토박싱
        
        Long lng = 100L;              // Long lng = new Long(100L);로 오토박싱
        
        int i4 = intg + 10;           // 참조형과 기본형간의 연산 가능
        long l = intg + lng;          // 참조형 간의 덧셈도 가능
        
//        Integer intg2 = new Integer(20);
//        int i5 = (int)intg2; // 참조형을 기본형으로 형변환도 가능 언박싱
    }
}
