package org.example.ch5;

import java.util.Arrays;

public class EX5_String {
    public static void main(String[] args) {
        /**
         * String 클래스
         * 1. String 클래스는 char[]와 메서드(기능)를 결합한 것 (문자열이 여러 개의 문자가 나열도니 것이기에)
         *    String 클래스 = char[] + 메서드(문자 배열에 다룰 때 필요한 기능들)
         *
         * 2. String 클래스는 내용을 변경할 수 없다. (read only)
         *    ex) String a = "a", String b = "b";
         *         a = a + b이면
         *    => 기존의 a 참조변수 값이 그대로 있고
         *       즉, 바뀌는 것이 아닌 새로운 참조변수의 값으로 생성하여 적용된 것이다!
         */

        /**
         * String 클래스의 주요 메서드
         *
         *         메서드                                        설명
         * char charAt(int index)                 문자열에서 해당 위치(index)에 있는 문자를 반환한다.
         * int length()                           문자열의 길이를 반환한다.
         * String substring(int from, int to)     문자열에서 해당 범위(from~to)의 문자열을 반환한다.(to는 포함 안 됨)
         * boolean equals(Object obj)             문자열의 내용이 같은지 확인한다. 같으면 결과는 true, 다르면 false
         * char[] toCharArray()                   문자열을 문자배열(char[])로 변환해서 반환한다.
         */
        String str = "ABCDE";

        char ch = str.charAt(3);                    // 문자열 str의 4번째 문자 'D'를 ch에 저장한다.

        int len = str.length();

        String str2 = "012345";
        String tmp = str2.substring(1, 4);          // index범위 1~3의 문자들을 반환
        String tmp2 = str2.substring(1);  // 1개이면 해당 범위에서 끝까지 반환
        System.out.println(tmp);                    // 123이 출력된다.

    }
}
