package org.example.ch4;

import java.util.Scanner;

public class EX4_while {
    public static void main(String[] args) {
        /**
         * while문
         * : 조건을 만족하는 동안 { }를 수행한다.
         *   반복횟수를 모를 때 사용한다.
         *   
         *   while (조건식) {
         *       // 조건식의 연산결과가 참인 동안 반복되는 문장들
         *   }
         */
        int i = 1;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }

        int num = 12345, sum = 0;

        while (num > 0) {
            sum += num % 10;
            System.out.println("sum="+sum +", num%10="+num%10);
            num /= 10;
        }


        /**
         * do-while문
         * : { }을 최소한 1번 이상 반복하게 한다.
         *   한번은 실행되어야할 경우 사용한다.
         *   즉, 사용자 입력을 받을 때 유용하다.
         *
         *   do {
         *      // 조건식의 연산결과가 참일 때 수행도리 문장들 (처음 1번은 무조건 실행됨)
         *   } while (조건식);
         */
        int input = 0, answer = 0;

        answer = (int)(Math.random() * 100) + 1;
        System.out.println("answer="+answer);
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("1과 100사이의 정수를 입력하세요 >");
            input = scanner.nextInt();

            if (input > answer) {
                System.out.println("더 작은 수로 다시 시도해보세요.");
            } else if(input < answer) {
                System.out.println("더 큰 수로 다시 시도해보세요.");
            }
        } while (input != answer);

        System.out.println("정답입니다.");


        /**
         * break문
         * : 자신이 포함된 하나의 반복문을 벗어난다.
         */
        int sum1 = 0;
        int a = 0;

        while (true) {
            if (sum1 > 100)
                break;
            ++a;
            sum1 += a;
        }

        System.out.println("a="+a);
        System.out.println("sum1="+sum1);


        /**
         * continue문
         * : 자신이 포함된 반복문의 끝으로 이동한다. 즉, 다음 반복으로 넘어감
         *   전체 반복 중에서 특정 조건시 반복을 건너뛸 때 유용하다.
         */
        for (int j = 0; j <= 10; j++) {
            if (j % 3 == 0)
                continue;
            System.out.println(j);
        }


        /**
         * 이름붙은 반복문
         * : 반복문에 이름을 붙여서 그 이름의 반복문까지 벗어나게 하여
         *   하나 이상의 반복문을 벗어날 수 있다.
         */
        Loop1 : // 반복문에 이름을 붙인 것
        for (int j = 2; j <= 9 ; j++) {
            for (int k = 1; k <= 9; k++) {
                if (k==5)
                    break Loop1; // 하나의 루프만 빠져나가지 않고 Loop1이름의 반복문까지 벗어난다.
                System.out.println(j + "*" + k + "=" + j*k);
            }
            System.out.println();
        }


    }
}
