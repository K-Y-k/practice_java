package org.example.ch4;

import java.util.Scanner;

public class EX4_Switch {
    public static void main(String[] args) {
        /**
         * switch문
         * : 처리해야 하는 경우의 수가 많을 때 유용한 조건문
         *   경우의 수가 많을 때 if-else if문 보다 유리하다.
         *   단, switch문은 제약조건이 있는데 이 경우는 무조건 if-else if문을 사용해야한다.
         *   
         *   - 제약조건
         *   1. switch문의 조건식 결과는 정수 또는 문자열(1.7부터 허용됨)이어야 한다.
         *   2. case문의 값은 정수 상수(문자 포함), 문자열만 가능하며 중복되지 않아야 한다. (즉, case문에 변수는 안됨)
         */
        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();

        // 조건식을 계산하고 조건식의 결과와 일치하는 case문으로 이동하고 이후의 문장들을 수행한다.
        // break문이나 switch문의 끝을 만나면 전체를 빠져나온다.
        switch (month) {
            case 3:  
            case 4:
            case 5:
                System.out.println("봄");
                break;
            case 6: case 7: case 8:
                System.out.println("여름");
                break;
            case 9: case 10: case 11:
                System.out.println("가을");
                break;
            default: // default는 else처럼 생량 가능함
                System.out.println("겨울");
        }
        
        
//        int num, result;
//        final int ONE = 1;
//
//        switch (result) {
//            case '1':   // 문자 리터럴 가능(정수 49와 동일)
//                break;
//            case ONE:   // 변수이지만 상수 값이 저장되었으므로 가능
//                break;
//            case "YES": // JDK 1.7부터 허용
//                break;
//            case num:   // 일반 변수는 불가
//                break;
//            case 1.0:   // 실수는 불가
//                break;
//            default: // default는 else처럼 생량 가능함
//        }


    }
}
