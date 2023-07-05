package org.example.ch2;

import java.util.Date;

public class EX2_TypeChange {
    public static void main(String[] args) {
        /**
         * 타입간의 변환방법
         * 1. 문자와 숫자 간의 변환
         *    - 숫자 -> 문자 : 숫자 + '0'
         *    - 문자 -> 숫자 : '문자형인 숫자' - '0'   int a = '3'-'0';
         *
         * 2. 문자열로의 변환
         *    : 숫자 + ""
         *
         * 3. 문자열을 숫자로 변환
         *    : Integer.parseInt("문자열인 숫자")
         *      Integer.parseDouble("문자열인 실수")
         *
         *    문자열을 문자로 변환
         *    : "하나의 문자".charAt(0)
         */
        int a = '3'-'0';
        System.out.println(3+'0');                    // 숫자가 문자로 바뀌고 '0'은 숫자로 48이라 '51'이 됨
        System.out.println((char)(3 + '0'));          // 3으로 하고 싶으면 형변환 선언을 해야함
        System.out.println('3'-'0');                  // 문자였던 것이 숫자 3이 됨
        System.out.println(Integer.parseInt("3")); // 문자열이었던 것이 숫자 3이 됨

        char c = "3".charAt(0);                       // 문자열이 문자가 됨



    }
}
