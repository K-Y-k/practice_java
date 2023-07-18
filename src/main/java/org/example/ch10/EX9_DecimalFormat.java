package org.example.ch10;

import java.text.DecimalFormat;

/**
 * 형식화 클래스
 * : java.text 패키지의 DecimalFormat(10진수 숫자 처리 형식화), SimpleDateFormat(날짜 형식화)이 있다.
 *   - 숫자와 날짜를 원하는 형식으로 쉽게 출려하는 것
 *   ex1) 10진수 -> 지수로 표현 변경
 *   double number = 1234567.89;
 *   DecimalFormat df = new DecimalFormat("#.#E0"); // 변화하고 싶은 문자열 형식 지정: #.#E0은 지수형식
 *   String result = df.format(number);             // 1.2E6  (1.2 x 10^6)
 *
 *   - 형식 문자열에서 숫자와 날짜를 뽑아내는 기능(형식 문자열 -> 숫자, 날짜)
 *   ex2)
 *   DecimalFormat df = new DecimalFormat("#,###.##"); // 변화하고 싶은 문자열 형식 지정
 *   Number num = df.parse("1,234,567.89");            // 변화하고 싶은 문자열, Number타입 객체가 래퍼클래스의 조상이므로 사용
 *   double d = num.doubleValue();                     // 1234567.89
 *
 *   PS) parse는 ,가 있으면 작동이 안된다.
 *       즉, 이때는 DecimalFormat을 이용해야 한다.
 */


/**
 * DecimalFormat
 * : 숫자(10진수)를 형식화할 때 사용(숫자 -> 형식 문자열)
 *
 * 기호           의미                    패턴               결과(1234567.89)
 * 0      10진수(값이 없을 때는 0)        0                   1234568
 *                                     0.0                 1234567.9
 *                                     0000000000.0000     0001234567.8900 (남는 부분 0으로 채워짐)
 *
 * #      10진수                        #                   1234568
 *                                     #.#                 1234567.9
 *                                     ##########.####     1234567.89 (4자리가 없어 2자리까지만 나옴)
 *
 * E      지수기호                       #.#E0               1.2E6
 *                                     0.0E0                1.2E6
 *                                     0.000000000E0        1.234567890E6
 *                                     00.00000000E0        12.34567890E5
 *                                     #.#########E0        1.23456789E6
 *                                     ##.########E0        1.23456789E6
 */
public class EX9_DecimalFormat {
    public static void main(String[] args) throws Exception{
        DecimalFormat df1 = new DecimalFormat("#,###.##"); // 형식1
        DecimalFormat df2 = new DecimalFormat("#.###E0");  // 형식2

        try {
            Number num = df1.parse("1,234,567.89"); // 처음 문자열
            System.out.print("1,234,567.89" + " -> ");

            double d = num.doubleValue();                 // 형식 1의 숫자로 변환
            System.out.print(d + " -> ");

            System.out.println(df2.format(num));          // 다시 형식2의 문자열로 변환
        } catch (Exception e){}

        System.out.println();

        double number = 1234567.89;
        String[] pattern = {
                "0",
                "#",
                "0.0",
                "#.#",
                "0000000000.0000",
                "##########.####",
                "#.#-",
                "-#.#",
                "#,##.##",
                "#,####.##",
                "#E0",
                "0E0",
                "##E0",
                "00E0",
                "####E0",
                "0000E0",
                "#.#E0",
                "0.0E0",
                "0.0000000000E0",
                "00.000000000E0",
                "000.00000000E0",
                "#.##########E0",
                "##.#########E0",
                "#,###.##+;#M,###.##-",
                "#.#%",
                "#.#\u2030",
                "\u00A4 #,###",
                "'#'#,###",
                "''#,###"
        };

        for (int i = 0; i < pattern.length; i++) {
            DecimalFormat df = new DecimalFormat(pattern[i]);
            System.out.printf("%19s : %s\n", pattern[i], df.format(number));
        }

    }
}
