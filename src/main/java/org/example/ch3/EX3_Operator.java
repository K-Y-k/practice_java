package org.example.ch3;

public class EX3_Operator {
    public static void main(String[] args) {
        /**
         *  연산자   : 연산을 수행하는 기호    ex) + - * / 등
         *  피연산자 : 연산자의 연산 수행 대상  ex) x, 3 등
         *
         *  모든 연산자는 연산결과를 반환한다. (즉 괄호 같은 것은 연산자가 아님)
         *
         */
        int x = 23;
        int y = 4 *  x + 3; // 23
        System.out.println(y);


        /**
         * 연산자의 종류 5가지
         * 1. 산술 연산자 : 사칙연산과 나머지 연산                 ex) + - * / % << >>
         * 2. 비교 연산자 : 크고 작음과 같고 다름을 비교            ex) > < >= <= == !=
         * 3. 논리 연산자 : 그리고(AND)와 또는(OR)으로 조건을 연결  ex) && || ! & | ^ ~
         * 4. 대입 연산자 : 우변의 값을 좌변에 저장                ex) =
         * 5. 기타       : 형변환 연산자(type), 삼항 연산자(?:), instanceof 연산자(instanceof)
         */


        /**
         * 연산자의 우선순위
         * : 하나의 식에서 연산자가 둘 이상 있을 때,
         *   어떤 연산을 먼저 수행할지를 자동 결정하는 것
         *
         * - 수동으로 결정하려면? => 괄호 활용 ( )
         *
         * 우선순위
         * 1. 단항 연산자   ++ -- + - ~ !
         *
         * 2. 산술 연산자   * / %
         *                + -
         *                >> <<
         *
         * 3. 비교 연산자   < > <= >= instanceof
         *                == !=
         *
         * 4. 논리 연산자   &
         *                ^
         *                |
         *                &&
         *                ||
         *
         * 5. 상함 연산자   ?:
         *
         * 6. 대입 연산자   = += -= *= /= %=
         *                <<= >>= &= ^= !=
         *
         */


        /**
         * 연산자의 결합 규칙
         * : 우선순위가 같은 연산자가 있을 대 어떤 것이 먼저인가
         *
         * 대입과 단항 연산자만 <- 방향      ex) x = y = 3   <-
         * 나머지 연산자는 모두 -> 방향      ex) 3 + 4 - 5   ->
         *
         */


        /**
         * 증감 연산자
         * - 증가 연산자 ++ : 피연산자의 값을 1 중가시킨다.
         * - 감소 연산자 -- : 피연산자의 값을 1 감소시킨다.
         *
         * 전위형 : 값이 참조되기 전에 증가시킨다. ex) ++i;
         * 후위형 : 값이 참조된 후에 증가시킨다.   ex) i++;
         * -> 독립적인 경우 전위 후위 상관없이 같은 결과
         *
         * 헷갈리면 분리시켜서 생각해보자!
         *  ex) j = ++i;이면 1. ++i;
         *                  2. j = i;
         *
         *  ex) j = i++;이면 1. j = i;
         *                  2. i++;
         */


        /**
         * 부호 연산자
         * '-'는 피연산자의 부호를 반대로 변경
         * '+'는 아무런 일도 하지 않는다.(실제 사용 X)
         */
        int i = -10;


        /**
         * 형변환이란?
         * 뱐수 또는 상수의 타입을 다른 타입으로 변환하는 것
         *
         * 형변환 연산자
         *    변환           수식        결과
         * int -> char    (char)65      'A'    (문자는 유니코드 문자로 숫자로 바뀌어 저장되고 숫자를 다시 읽어서 문자로 바꾸어 표현 된 것이다.)
         * char -> int    (int)'A'       65
         * float -> int   (int)1.6f      1
         * int -> float   (float)10     10.0f
         */
        double d = 85.4;
        int score = (int)d;  // 형변환으로 85가 된다. (변수 d의 값 자체는 그대로고 여기에서만 85가 된것)


        /**
         * 자동 형변환
         * : 저장되는 변수 타입의 범위가 더 넓은 경우 컴파일러가 자동으로 형변환된다.
         *   기존 값을 최대한 보존할 수 있는 타입으로 자동 형변환된다.
         *   큰 범위 값을 작은 타입의 변수에 저장할 때 수동 형변환을 할 경우 값 손실 발생한다.
         *   (short char은 같은 바이트이지만 표현하는 범위가 다르기에 자동 형변환이 불가능하다)
         */
        float f = 1234; // float는 10^38이고 int형은 -20억 ~ +20억이라서 float 범위 내이므로
                        // float f = (float)1234;처럼 자동 형변환이 된다.

        int i1 = (int)3.14f; // 저장되는 int형이 더 범위가 작아서 값손실이 날 수 있기에 자동 형변환을 못하여 에러가 발생하므로
                             // 꼭 수동 형변환을 해주어야 한다.


        /**
         * 사칙 연산자  + - * /
         *   ex) (int) 10 / (int) 4 -> (int) 2
         * 
         * 산술 변환
         * : 연산 전에 피연산자의 타입을 일치시키는 것
         *   규칙 1. 두 피연산자의 타입을 같게 일치시킨다. (보다 큰 타입으로 일치)
         *       long   + int   -> long   + long(큰 타입으로 변환)   -> long
         *       float  + int   -> float  + float(큰 타입으로 변환)  -> float
         *       double + float -> double + double(큰 타입으로 변환) -> double
         *
         *   규칙 2. 피연산자의 타입이 int 보다 작은 타입(byte, char, short)이면 int로 변환된다.
         *      byte + short -> int + int -> int
         *      char + short -> int + int -> int
         *
         *      ex) '2'  - '0'  =  50 - 48   = 2
         *          char - char = int - int
         */
        int a = 1_000_000;
        int b = 2_000_000;

        long c = a * b; // int형 x int형으로 계산되어 int형의 범위를 벗어나서 값손실이 발생한 오버플로우 발생
                        //  (long)a * b 둘 중 하나를 형변환시켜야한다.


        /**
         * 반올림 메서드 - Math.round()
         * : 실수를 소수점 첫 째자리에서 반올림한 정수를 반환한다.
         */
        long result = Math.round(4.52); // 첫 째자리에서 반올림하였으므로 5가 저장된다.

        double pi = 3.141592;

        // 3.142까지만 표현하고 싶으면?
        // 방법 1
        double v1 = (double)Math.round((pi * 1000) / 1000);
        // 방법2
        double v2 = (int) (pi * 1000) / 1000.0;


        /**
         * 나머지 연산자  %
         * : 오른쪽 피연산자로 나누고 남은 나머지를 반환한다.
         *   나누는 피연산자는 0이 아닌 정수만 허용한다.(부호는 무시됨)
         *   ex) 10 % -8이면 -무시되고 10 % 8의 결과가 나온다.
         */


        /**
         * 비교 연산자  > < >= <= == !=
         * : 두 피연선자를 비교해서 true(참) 또는 false(거짓)을 반환한다.
         */
        /**
         * 문자열의 비교
         * : 문자열 비교에는 == 대신 equals()를 사용해야 한다.
         */
        String str1 = "abc";
        String str2 = "abc";
        System.out.println(str1 == str2);      // true
        System.out.println(str1.equals(str2)); // true

        // 아래처럼 객체로 생성하는 방식이면 문자열 비교에는 == 대신 equals()를 사용해야 한다.
        String str3 = new String("abc");
        String str4 = new String("abc");
        System.out.println(str3 == str4);      // false
        System.out.println(str3.equals(str4)); // true


        /**
         * 논리 연산자  && ||
         * : 조건식을 연결할 때 사용하는 연산자
         *
         * || (OR 결합)   : 피연산자 중 어느 한 쪽이 true이면 true
         * && (AND 결합)  : 피연산자 양쪽 모두 true/false이어야 true/false
         */


        /**
         * 조건 연산자  ? :
         * : 조건식의 결과에 따라 연산결과를 달리한다.
         *   간단한 if문을 간단하게 표현할 때 사용한다.
         *
         *    조건식 ? 식1 : 식2  (조건식이 참이면 식1의 결과이고 거짓이면 식2의 결과이다.)
         *     (1)   (2)   (3)
         */
        result = (x > y) ? x : y;


        /**
         * 대입 연산자
         * : 오른쪽 피연산자를 왼쪽 피연산자에 저장 후 저장된 값을 반환한다.
         *
         *    x = y = 3 대입은    <- 방향순으로 진행된다.
         *     (2) (1)
         *
         *       x    =   3
         *     lvalue    ralue
         *      왼쪽 피연산자를 lvalue (저장공간)
         *    오른쪽 피연산자를 rvalue
         */
        System.out.println(x = 3); // 변수 x에 3이 저장되고
        System.out.println(3);     // 연산결과인 3이 출력된다.


        /**
         * 복합 대입 연산자
         * : 대입 연산자와 다른 연산자를 하나로 축양한다.
         *    ex) i += 3;         -> i = i + 3;
         *        i *= (10 + j);  -> i = i * (10 + j);
         */
    }
}
