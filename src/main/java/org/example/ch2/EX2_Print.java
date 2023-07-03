package org.example.ch2;

public class EX2_Print {
    public static void main(String[] args) {
        /**
         * 형식화 되지 않은 출력 및 자동 개행 : println()
         */
        System.out.println(5+3);   // 덧셈
        System.out.println(5-3);   // 뺄셈
        System.out.println(5*3);   // 곱셈
        System.out.println(5/3);   // 나눗셈 : 정수까지 반환
        System.out.println(5/3.0); // 나눗셈 : 소수점까지 반환


        /**
         * 형식화된 출력 : printf()
         * println()은 1.출력형식 지정이 불가능하다.
         *            2. 10진수로만 출력된다.
         */
        // ex) 정수 / 정수이므로 소수점 없이 정수로 나옴
        System.out.println(10/3);   // >> 3

        // ex) 실수 / 정수는 실수로 나오므로 실수의 자리수 조절불가
        System.out.println(10.0/3); // >> 3.333333...

        // ex) 10진수로만 출력된다.
        System.out.println(0x1A);   // >> 26


        // printf()로 출력형식 지정가능
        System.out.printf("%.2f", 10.0/3); // >> 3.33
        System.out.printf("%d", 0x1A);     // >> 26
        System.out.printf("%x", 0x1A);     // >> 1A

        // 지시자          설명
        // %b          boolean 형식으로 출력
        // %d          10진수 형식
        // %o           8진수 형식
        // %x, %X      16진수 형식

        // %f          부동 소수점 형식     실수 출력할 때 이를 사용
        // %e, %E      지수 표현식의 형식   소수점에 숫자 0이 많을 때 이를 사용

        // %c          문자 형식
        // %s          문자열 형식
        
        // %n          printf는 자동개행을 하지 않아 /n 또는 %n 사용 (%n 사용 권장)

        System.out.printf("%d%n", 15);
        System.out.printf("#%o%n", 15);  // 접두사도 같이 출력하려면 #를 붙여줘야함
        System.out.printf("#%x%n", 15);
        System.out.printf("%s", Integer.toBinaryString(15)); // 이진수 출력

        float f = 123.456789f;        // float는 정밀도가 7자리까지만 정확하다. 즉 여기서는 89가 정확히 안나올 수 있음
        System.out.printf("%f%n", f);
        System.out.printf("%e%n", f); // %e는 지수형태로 출력됨 >> 1.234568e+02

        double d = 123.456789;        // double은 정밀도가 15자리까지라 정확히 나온다.
        System.out.printf("%f%n", d);
        
        
        // printf()의 지시자
        System.out.printf("[%5d]%n", 10);  // >> [   10] 해당숫자 칸만큼 비우고 오른쪽 정렬 (들어오는 숫가 해당 숫자보다 더 크면 모두 출력됨)
        System.out.printf("[%-5d]%n", 10); // >> [10   ] 해당숫자 칸만큼 비우고 왼쪽 정렬
        System.out.printf("[%05d]%n", 10); // >> [00010] 해당숫자 칸만큼 비워진 곳은 0으로 채우고 오른쪽 정렬

        // %전체자리.소수점아래자리.f : 전체자리부분(소수점 포함)의 빈자리 공백 소수점아래자리의 빈부분은 0으로 채운다.
        System.out.printf("[%15.10f%n]", 123.456789); // >> [  1.2345678900]

        System.out.printf("[%s]%n", "www.codechobo.com");    // >> [www.codechobo.com]
        System.out.printf("[%20s]%n", "www.codechobo.com");  // >> [   www.codechobo.com]
        System.out.printf("[%-20s]%n", "www.codechobo.com"); // >> [www.codechobo.com   ]
        System.out.printf("[%.10s]%n", "www.codechobo.com"); // >> [www.codech]

    }
}
