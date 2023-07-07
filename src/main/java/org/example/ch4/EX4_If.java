package org.example.ch4;

import java.util.Scanner;

public class EX4_If {
    public static void main(String[] args) {
        /**
         * 제어문 (flow control statement)
         * : 프로그램의 실행 흐름을 바꾸어 주는 문장들
         *   조건문, 반복문
         */


        /**
         *  조건문  if문, switch문
         *  : 조건을 만족할 때만 { }를 수행
         */

        /**
         * if문
         * : 조건식이 참일 때, {}안의 문장들을 수행한다.
         *
         * 블럭 { }
         * : 여러 문장을 하나로 묶어주는 것
         *   문장 하나이면 생략 가능
         */
        int score = 70;
        if (score > 60) { // 블럭의 시작
            System.out.println("합격입니다.");
        }  // 블럭의 끝

        /**
         * 조건식 다양한 예시
         * • 90 <= ㅌ && x <= 100
         * • x < 0 || x > 100
         * • x%3==0 && x%2!=0
         * • ch=='y' || ch=='Y'
         * • ch==' ' || ch=='\t' || ch==='\n'   : 공백 또는 탭 또는 개행문자인 경우
         * • 'A' <= ch && ch <= 'Z'
         * • 'a' <= ch && ch <= 'z'
         * • '0' <= ch && ch <= '9'
         * • str.equals("yes")                  : 대소문자 구분함
         * • str.equalsIgnoreCase("yes")        : IgnoreCase로 대소문자 구분안함
         */

        /**
         * if-else문
         * : 둘 중의 하나만 실행하고 조건식이 참일 때와 거짓일 때로 나눠서 처리한다.
         */
        if (score > 60){
            System.out.println("합격");
        } else {
            System.out.println("탈락");
        }

        /**
         * if-else if문
         * : 여러개 중의 하나만 실행하고 여러 개의 조건식을 포함한 조건식이다.
         */
        int a = 0;
        char grade = ' ';

        System.out.println("점수를 입력하세요.>");
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();

        if (a >= 80){
            grade = 'A';
            System.out.println("합격: " + grade);
        } else if (a >= 50) {
            grade = 'B';
            System.out.println("보류: " + grade);
        } else {
            grade = 'C';
        }
        // 리펙토링의 관점에서는
        // else문의 grade를 처음 초기화할 때 C로 초기화하면 else문을 지워 코드가 더 간결해질 수 있다.
        // 좀 더 코드를 줄이고 효율적인 방안을 고민하는 것이 리펙토링 관점

        /**
         * 중첩 if문
         * : if문 안의 if (중첩 1000번 정도 가능하여 제약이 없지만 보통 3~4번)
         */
        char opt = '0';

        if (a >= 80){
            grade = 'A';

            if (a >= 99) {
                opt = '+';
            } else if (a <= 85) {
                opt = '-';
            }
            System.out.println("합격: " + grade + opt);
        } else if (a >= 50) {
            grade = 'B';

            if (a >= 75) {
                opt = '+';
            } else if (a <= 55) {
                opt = '-';
            }
            System.out.println("보류: " + grade + opt);
        } else {
            grade = 'C';
        }
    }
}
