package org.example.ch9;

/**
 * join()과 StringJoiner
 * : join()은 여러 문자열 사이에 구분자를 넣어서 결합한다.
 *
 * ex)
 * String animals = "dog, cat, bear";
 * String[] arr = animals.split(",");  // 문져열을 ','를 기준으로 나눠서 배열에 저장
 * String str = String.join("-", arr)  // 배열의 문자열을 '-'로 구분해서 결합
 * System.out.println(str);            // dog-cat-bear
 *
 */

/**
 * 문자열과 기본형 간의 변환
 * - 숫자를 문자열로 바꾸는 방법 2가지
 * ex)
 * int i = 100;
 * String str1 = i + "";                 // 100을 "100"으로 변환방법1(편리)
 * String str2 = String.valueOf(i);      // 100을 "100"으로 변환방법2(속도빠름)
 *
 *
 * - 문자열을 숫자로 바꾸는 방법 2가지
 * int i = Integer.parseInt("100");      // "100"을 100으로 변환방법1(오래된 방법)
 * int i2 = Integer.valueOf("100");      // "100"을 100으로 변환방법2(새로나온 방법)
 * Integer i2 = Integer.valueOf("100");  // 반환타입이 Integer(참조형)인 경우
 */

public class EX9_join_StringJoiner {
    public static void main(String[] args) {
        // 숫자 -> 문자열
        int iVal = 100;
        String strVal1 = iVal + "";            // 방법1, 편리
        String strVal2 = String.valueOf(iVal); // 방법2, 속도 빠름

        double dVal = 200.0;
        String strVal3 = iVal + "";


        // 문자열 -> 숫자
        double sum = Integer.parseInt("+" +strVal1) + Double.parseDouble(strVal3);  // 방법1, 오래된 방식
        double sum2 = Integer.valueOf(strVal1) + Double.valueOf(strVal3);              // 방법2, 최근 나온 방식

        // 문자열 합치기
        System.out.println(String.join("", strVal1, " + ", strVal2, "= ")+sum); // 속도 향상
        System.out.println(strVal1+" + "+strVal2+"= "+sum2); // 문자를 새로 생성하므로 느림 조금 사용할 때만
    }
}
