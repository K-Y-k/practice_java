package org.example.ch2;

import java.util.Date;

public class EX2_Var {
    public static void main(String[] args) {
        System.out.println(6+3);     // int형으로 인한 덧셈후 출력
        System.out.println("6+3");   // 문자형으로 인한 출력

        System.out.println("======================");



        /**
         * 변수란? 변수 저장 방식
         */
        // 변수는 하나의 값을 저장할 수 있는 메모리 공간이다.

        // 메모리 공간은 컴퓨터의 RAM을 의미하고 램은 물리적으로 하나의 연속된 공간이 있다.
        // 메모리 공간은 각 1바이트로 0부터 연속적으로 있고 이를 메모리 주소라고 한다.
        // 값을 저장할 때 이 메모리 주소를 이용한다.
        // 특정 메모리 영역에 이름을 붙이고 주소대신에 이름을 붙여 읽을 수 있게 하는 것이 변수이다.

        // 변수는 같은 이름 단 하나만 저장가능하여 새로 저장 값의 변수가 기존에 값이 있으면 새로 저장된 값으로 저장된다.

        /**
         * 변수 선언
         */
        // 변수의 선언 이유 : 값을 저장할 공간을 마련하기 위해
        // 변수의 선언 방법 : 변수타입 변수이름;
        
        int x = 4, y = 2;
        System.out.println(x+y);   // 덧셈
        System.out.println(x-y);   // 뺄셈
        System.out.println(x*y);   // 곱셈
        System.out.println(x/y);   // 나눗셈 : 정수까지 반환
        System.out.println(x/2.0); // 나눗셈 : 소수점까지 반환

        System.out.println("======================");

        /**
         * 변수 타입 : 기본형 8개
         */
        // 값 - 문자 - char
        //    - 숫자 - 정수 - byte, short, int, long
        //          - 실수 - float, double
        //      논리 - boolean - true
        //                    - false


        /**
         * 상수, 리터럴
         */
        // 상수는 1번만 값을 저장가능하다.
        // 즉, 변수는 새로 값을 변경할 수 있었지만 상수는 변경 X
        // 상수 선언 방법 : final 변수타입 변수이름;
        //                final int MAX = 100;
        
        // 리터럴은 그 자체로 값을 의미하는 것으로 상수와 같다.
        // ex) int score = 100; 이면 100이 리터럴이다!
        
        int score = 100;   // 변수 초기화 및 선언    리터럴 : 100
        score = 200;       // 변수 새로 값 저장      리터럴 : 200

        final int MAX = 300;                    // 리터럴 : 300
//        MAX = 200; 상수에 딱 한번만 선언이 가능하여 에러 발생

        System.out.println(score);


        /**
         * 리터럴의 접두사와 접미사
         */
        //         종류        /                리터럴                /       접미사(같은 종류에서 타입을 구분하기 위해)
        //        논리형           false, true                                없음
        //        정수형           int 타입 : 123, 0b0101, 077, 0xFF           없음
        //                        long 타입: 100L                             L(long 타입) (접미사는 대소문자 구분을 안하지만 숫자 1이랑 혼동 할 수 있어 대문자 L로)
        //        실수형           3.14, 3.0e8, 1.4f, 0x1.0p-1                f(float 타입). d(double 타입) (주로 f로 구분하기에 d는 생략함)
        //        문자형           'A', '1', '\n'                             없음
        //        문자열           'ABC', '123', 'A', 'true'                  없음

        byte b = 127; // 바이트 범위는 -128 ~ 127까지

        int i = 100;     // 10진수
        int oct = 0100;  //  8진수, 10진수로 8이고 println을 사용하면 10진수로 나옴, 8진수로 하려면 printf 사용
        int hex = 0x100; // 16진수, 10진수로 16

        long l1 = 100;             // int 범위이므로 L 안붙일 수 있음
        long l2 = 10_000_000_000L; // int 타입은 20억까지이므로 100억은 long만 가능하므로 L을 꼭 붙여야 한다. _은 100억을 알아보기 쉽게 하려고 기능 도입된 것

        float f = 3.14f;   // f를 무조건 넣어야한다.
        double d = .14;    // f로 구분했기에 double형은 d생략 가능한 것 .앞/뒤에 숫자가 없으면 그 앞/뒤의 값은 0이다.
        float f2 = 1e3f;   // e는 10^n고 실수형에 사용되므로 1e3은 1000.0이다.


        // 변수와 리터럴의 타입이 불일치 할 때 변수의 타입 범위 > 리터럴의 타입 범위이면 사용 가능하다.
        int a = 'A';       // A는 65이므로 int 범위 > char 범위
        long l = 123;      // long 범위   > int 범위
        double d2 = 3.14f; // double 범위 > float 범위


        /**
         * 문자와 문자열
         */
//        char ch = 'AB'; // char은 연속된 문자인 문자열은 불가능
        String s1 = "AB"; // String 클래스로 문자열 사용

        // 문자열 결합
        // 문자열 + 아무 타입 -> 문자열
        // 아무타입 + 문자열  -> 문자열
        // ex1) "" + 7     = "7"
        // ex2) "" + 7 + 7 = "7" + 7
        // ex3) "7" + "7"  = "77"
        // ex4) 7 + 7 + "" =  14 + ""
        System.out.println(""+7+7);
        System.out.println(7+7+"");


        /**
         * 두 변수의 값 교환하기
         */
        // 변수 값을 담아둘 변수를 하나(temp) 생성하여 담아서 교환해야 한다.
        int s = 4, z = 2;
        int tmp;

        tmp = z;
        s = z;
        z = tmp;

        System.out.println("s="+s);
        System.out.println("z="+z);


        /**
         * 기본형과 참조형
         * 기본형 8개 : boolean(논리형), char(문자형),
         *            byte, short, int, long (정수형),
         *            float, double (실수형)
         *            실제 값을 저장한다.
         *
         * 참조형    : 기본형을 제외한 나머지 (String, System 등 우리가 무한히 추가할 수 있는 것)
         *            메모리 주소를 저장한다. (4 byte(32bit JVM) 또는 8 byte(64bit JVM))
         *                                메모리 최대 40억       최대 40억 x 40억 = 1600만 TB이지만
         */
        Date today;         // 참조형 변수 선언
        today = new Date(); // 객체생성 : today에 객체의 주소를 저장한다.
                            //          만약 생성된 객체(Date)가 100번지에 저장되어있다면 today는 100이 저장

        /**
         * 기본형 종류와 크기와 범위
         */
        //     크기(byte)      1         2       4       8
        //  종류
        // 논리형            boolean
        // 문자형                       char
        // 정수형              byte     short    int    long
        // 실수형                       float   double

        /**
         * 기본형 표현 범위
         * 1bit = 2진수 1자리
         * 1byte = 8bit
         *
         * n비트로 표현할 수 있는 값의 개수           : 2^n개
         * n비트로 표현할 수 있는 부호없는 정수의 범위  : 0 ~ 2^n - 1             ex) 8비트 1byte라면 정수는 -127 ~ 127
         *                                        (양수는 1 ~ 2^n)                            양수는   0  ~ 256
         * n비트로 표현할 수 있는 부호있는 정수의 범위  : -2^(n-1) ~ 2^(n-1) - 1
         *
         * ex2) 같은 2 byte short와 char이지만 char은 양수만 나타내므로 다른 범위
         * short: -2^15 ~ 2^15 - 1 -> -32768 ~ 32767
         * char :   0   ~ 2^16     ->    0   ~ 65535
         *
         * int  : -2^31 ~ 2^31 - 1 -> 20억 ~ 20억
         * long : -2^63 ~ 2^63 - 1 -> -800경 ~ 800경
         * BigInteger : long의 범위를 벗어날 경우 사용
         */
        




    }
}
