package org.example.ch4;

import java.util.Scanner;

public class EX4_Random {
    public static void main(String[] args) {
        /**
         * 임의의 정수 만들기
         * - Math.random() : 0.0과 1.0 사이의 임이의 double값을 반환
         */
        // ex1) 1~3 정수로 하려면?
        // 1. 각 변에 3을 곱한다
        // 0.0 * 3 <= Math.random() * 3 < 1.0 * 3

        // 2. 각 변을 int형으로 변환한다.
        // (int)0.0 * 3 <= (int)(Math.random() * 3) < (int)1.0 * 3

        // 3. 각 변에 1을 더한다.
        // 0 + 1 <= (int)(Math.random() * 3) + 1 < 3 + 1
        //   1   <= (int)(Math.random() * 3) + 1 < 4

        // ex2)
        for (int i = 1; i < 4; i++) { // 5번 반복
            System.out.println(Math.random());             //  0 ~ 1.0  사이의 실수 출력
            System.out.println(Math.random()*10);          //  0 ~ 10.0 사이의 실수 출력
            System.out.println((int)(Math.random()*10));   //  0 ~ 10   사이의 정수 출력
            System.out.println((int)(Math.random()*10)+1); //  1 ~ 11   사이의 정수 출력
            System.out.println((int)(Math.random()*11)-5); // -5 ~ 5    사이의 정수 출력
        }
    }
}
