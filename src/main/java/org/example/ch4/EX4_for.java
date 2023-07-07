package org.example.ch4;

public class EX4_for {
    public static void main(String[] args) {
        /**
         * 반복문
         * : 조건을 만족하는 동안 { }를 수행한다.
         */
        int i = 10;
        while (i-- > 0) {
            System.out.println(i);
        }

        /**
         * for문
         * : 조건을 만족하는 동안 { }을 반복
         *   조건식이 거짓이 될 때까지 반복
         *   반복횟수를 알 때 적합하다.
         *
         *   for (초기화; 조건식; 증감식) {
         *      수행될 문장
         *   }
         *
         *   조건식을 잘 작성해야 무한반복에 빠지지 않는다.
         */
        for (int j = 0; j < 3; j++) {    // 3번 반복
            System.out.println("Hello");
        }

        for (int j = 1; j < 16; j=j*2) { // j= 1, 2, 4, 8로 4번 반복
            System.out.println("Hello");
        }

        for (int j = 10; j >= 1 ; j--) { // 역순
            System.out.println("Hello");
        }

        // j와 k가 같은 타입이어야 같이 사용가능
        for (int j = 1, k = 10; j <= 10; j++, k--) {
            System.out.println("j=" + j + ", k=" + k);
        }

        for (;;) { // 무한 반복문
            System.out.println("1");
            break;
        }


        /**
         * 중첩 for 문
         * : for문 내에 또 다른 for문을 포함시킬 수 있다.
         */
        for (int j = 1; j <= 9; j++) {
            for (int k = 1; k <= 9; k++) {
                System.out.println(j + "*" + k + "=" + (j*k));
            }
            System.out.println();
        }

        for (int j = 1; j <= 10; j++) {
            for (int k = 1; k <= j; k++) {
                System.out.print("*");
            }
            System.out.println();
        }


    }
}
