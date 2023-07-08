package org.example.ch5;

import java.util.Arrays;

public class EX5_Array {
    public static void main(String[] args) {
        /**
         * 배열
         * : 같은 타입의 여러 변수를 하나의 묶음으로 다루는 것
         *
         *   배열은 참조변수가 필요하다.
         *   각 저장공간에 이름대신 참조변수의 이름을 붙히기 때문이다.
         *   ex) int[] score = new int[5];
         *   score      score[0] score[1]
         *   0x100  -> |   0   |    0    |   0   |   0   |   0   |
         *
         *   1) 배열은 연속적이다.
         *   2) 배열은 한번 생성하면 실행동안 그 길이를 바꿀 수 없다.
         *      => 즉, 공간 부족하면 방지를 위해 공간을 늘린 새로운 배열을 생성하고 기존 배열 값을 붙여넣고 추가하기
         */
        int[] score = new int[5]; // int형의 배열을 다루기 위한 참조변수 선언과 저장 공간 5개를 생성을 동시에 한것


        /**
         * 배열의 선언과 생성
         * : 변수는 선언과 동시에 저장공간이 생성되지만
         *   배열은 배열의 저장공간들이 생성되는 것이 아닌 배열을 다루기 위한 참조변수가 선언된 것이다.
         *
         *   - 선언 스타일 1) 자바 스타일
         *   타입[] 변수이름;
         *
         *   - 선언 스타일 2) C언어 스타일
         *   타입 변수이름[];
         *
         *   => 선언 스타일 2개 모두 가능하다.
         */
        int[] score1;        // 1. int타입의 배열을 다루기 위한 참조변수 선언
        score1 = new int[5]; // 2. int타입의 값 5개를 저장할 수 있는 배열 생성


        /**
         * 배열의 인덱스
         * : 각 요소(저장공간)에 자동으로 붙는 일련번호이다.
         *   인덱스의 범위는 0 ~ (배열길이-1)까지이다
         */
        score[3] = 100;
        System.out.println("score[0]="+score[0]);
        System.out.println("score[0]="+score[1]);
        System.out.println("score[0]="+score[2]);
        System.out.println("score[0]="+score[3]);
        System.out.println("score[0]="+score[4]);

        int value = score[3];
        System.out.println("value="+value);


        /**
         * 배열의 길이
         * - 배열이름.length
         */
        int arrLength = score.length;
        System.out.println("arrLength = " + arrLength);

        for (int i = 0; i < arrLength; i++) {
            System.out.println("score["+ i + "] = " + score[i]);
        }


        /**
         * 배열의 초기화
         * : 배열의 각 요소에 처음으로 값을 저장하는 것
         *   배열은 기본적으로 자동 초기화(ex) int형이면 0)된다.
         */
        int[] arr = { 50, 60, 70, 80, 90 }; // 주의점: 이 경우 참조변수 선언과 동시에 같이 해야한다!
        char[] chArr = {'a', 'b', 'c', 'd'};

        /**
         * 배열의 출력
         */
        System.out.println("arr = " + arr);       // [I@15db974와 같이 출력된다.

        for (int j : arr) {                       // for문 사용 방식으로 하나씩 출력
            System.out.print(j + ", ");
        }
        System.out.println();

        System.out.println(Arrays.toString(arr)); // 배열 모든 요소를 한번에 출력하는 방식

        System.out.println(chArr);                // char인 경우에만 이 방식으로 출력이 된다.


        /**
         * 배열의 활용
         */
        // ex1) 총합과 평균
        int sum = 0;
        float average = 0f;

        int[] score3 = {99, 88, 100, 100, 90};

        for (int j = 0; j < score3.length; j++) {
            sum += score3[j];
        }

        average = sum / (float)score3.length;

        System.out.println("총합 = " + sum);
        System.out.println("평균 = " + average);


        // ex2) 최대값과 최솟값
        int max = score3[0];
        int min = score3[0];

        for (int i = 1; i < score3.length; i++) {
            if (score3[i] > max) {
                max = score3[i];
            } else if (score3[i] < min) {
                min = score3[i];
            }
        }

        System.out.println("최대값 = " + max);
        System.out.println("최솟값 = " + min);


        // ex3) 배열 섞기 : 배열의 오소의 순서를 반복해서 바꾼다. (숫자 섞기, 로또번호 생성)
        int[] numArr = {0,1,2,3,4,5,6,7,8,9};
        System.out.println(Arrays.toString(numArr));

        for (int i = 0; i < numArr.length; i++) { // 100번 바꾼다.
            int n = (int)(Math.random() * 10);
            int temp = numArr[i];
            numArr[i] = numArr[n];
            numArr[n] = temp;
        }

        System.out.println(Arrays.toString(numArr));
        System.out.println();


        // ex4) 로또 번호
        int[] ball = new int[45];                // 배열의 참조변수 생성 및 45개의 저장공간 생성

        for (int i = 0; i < ball.length; i++) {  // 45까지 값 할당
            ball[i] = i+1;
        }

        System.out.println(Arrays.toString(ball));

        int temp = 0;  // 두 값을 바꾸는데 사용할 임시 변수
        int j = 0;     // 임의의 값을 얻어서 저장할 변수

        // 배열의 i번째 요소와 임의의 요소에 저장된 값을 서로 바꿔서 값을 섞는다.
        for (int i = 0; i < 6; i++) {       // 0번째부터 5번째 요소까지 총 6개만 바꾼다.
            j = (int)(Math.random()  * 45); // 0~44까지의 임의의 값을 얻음
            temp = ball[i];
            ball[i] = ball[j];
            ball[j] = temp;
        }

        // 배열 ball의 앞에서 부터 6개의 요소를 출력한다.
        for (int i = 0; i < 6; i++) {
            System.out.printf("ball[%d]=%d%n", i, ball[i]);
        }


        /**
         * String 배열의 선언과 생성
         * : 여러 개의 문자열을 담을 수 있는 배열이다.
         *   String은 참조형이라서 참조형의 기본 값 null이 들어간다.
         *
         * ex) String[] name = new String[3];
         *      name              name[0]      name[1]     name[2]
         *   0x100(참조변수) ->  |  null     |    null   |    null  |
         */
        // 스타일 1
        String[] name = new String[3];
        name[0] = "Kime";
        name[1] = "Park";

        // 스타일 2
        String[] strArr = {"가위", "바위", "보"};
        System.out.println(Arrays.toString(strArr));

        for (int i = 0; i < 10; i++) {
//            System.out.println((int)(Math.random()*3));
            int temp2 = (int)(Math.random()*3);
            System.out.println(strArr[temp2]);
        }

    }
}
