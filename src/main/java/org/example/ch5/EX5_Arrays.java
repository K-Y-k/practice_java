package org.example.ch5;

import java.util.Arrays;

public class EX5_Arrays {
    public static void main(String[] args) {
        /**
         * Arrays 클래스로 배열 다루기
         *
         * 문자열의 비교와 출력 - equals(), toString()
         */
        int[] arr = {0, 1, 2, 3, 4};
        int[][] arr2D = {
                {11, 12},
                {21, 22}
        };
        System.out.println(Arrays.toString(arr));       // 1차원 배열 한번에 출력
        System.out.println(Arrays.deepToString(arr2D)); // 2차원 배열 한번에 출력


        String[][] str2D = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};
        String[][] str2D2 = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};

        System.out.println(Arrays.equals(arr, arr));     // 1차원 배열 비교시 사용
        System.out.println(Arrays.deepEquals(str2D, str2D2)); // 2차원 배열 비교시 사용


        /**
         * 배열의 복사 - copyOf(배열, 복사할 요소 개수), copyOfRange(배열, from, to) : to요소는 미포함
         */
        int[] arr1 = {0,1,2,3,4};
        int[] arr2 = Arrays.copyOf(arr, arr.length);         // [0,1,2,3,4]
        int[] arr3 = Arrays.copyOf(arr, 3);        // [0,1,2] 
        int[] arr4 = Arrays.copyOf(arr, 7);        // [0,1,2,3,,4,0,0]
        int[] arr5 = Arrays.copyOfRange(arr, 2, 4); // [2,3] 4는 미포함
        int[] arr6 = Arrays.copyOfRange(arr, 0, 7); // [0,1,2,3,4,0,0]


        /**
         * 배열의 정렬 - sort()
         */
        int[] arr_ = {3,2,0,1,4};
        Arrays.sort(arr_);                // 배열을 오름차순으로 정렬한다.
        System.out.println(Arrays.toString(arr_));

    }
}
