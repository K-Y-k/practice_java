package org.example.ch2;

import java.util.Scanner;

public class EX2_Scan {
    public static void main(String[] args) {
        /**
         * 화면에서 입력 받기 - Scanner
         * 화면으로부터 데이터를 입력받는 기능을 제공하는 클래스
         * 사용 조건
         * 1. import문 추가      : import java.util.*;
         * 2. Scanner 객체의 생성 : Scanner scanner = new Scanner(System.in);
         * 3. Scanner 객체를 사용 : int num = scanner.nextInt();       // 화면에서 입력받은 정수를 num에 저장
         *
         *                        String input = scanner.nextLine(); // 화면에서 입력받은 내용을 행단위로 input에 저장
         *                        int num = Integer.parseInt(input); // 문자열을 숫자로 변환
         */
        Scanner scanner = new Scanner(System.in);

        // 100 200 입력시 100은 num1 200은 num2에 저장됨 
//        int num1 = scanner.nextInt();
//        int num2 = scanner.nextInt();
//
//        System.out.println(num1);
//        System.out.println(num2);

        // 행 단위로 저장됨
        String inputLine = scanner.nextLine();
        System.out.println(inputLine);

        int num = Integer.parseInt(inputLine); // 입력받은 문자열(이 문자는 숫자여야함)을 숫자로 변환
        System.out.println(num);



    }
}
