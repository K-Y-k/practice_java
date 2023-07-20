package org.example.ch11;

import java.util.Arrays;

/**
 * Arrays 클래스
 * : 배열을 다루기 편리한 메서드(static) 제공 (Math, Objects, Collections와 같은 유틸 클래스)
 * 
 * 1. 배열의 출력 - toString()
 * static String to String(기본형타입[] a)
 *
 * 2. 배열의 복사 - copyOf(), copyOfRange()(새로운 배열을 생성해서 반환)
 * ex)
 * int[] arr = {0,1,2,3,4};
 * int[] arr2 = Arrays.copyOf(arr, arr.length);         // arr2=[0,1,2,3,4]
 * int[] arr3 = Arrays.copyOf(arr, 3);                  // arr3=[0,1,2]
 * int[] arr4 = Arrays.copyOf(arr, 7);                  // arr4=[0,1,2,3,4,0,0]
 * int[] arr5 = Arrays.copyOfRange(arr, 2, 4);          // arr5=[2,3] (4는 미포함)
 * int[] arr6 = Arrays.copyOfRange(arr, 0, 7);          // arr6=[0,1,2,3,4,0,0]
 *
 * 3. 배열 채우기 - fill(), setAll()(람다식울 이용해서 채우기)
 * ex)
 * int[] arr = new int[5];
 * Arrays.fill(arr, 9);                                 // arr=[9,9,9,9,9]
 * Arrays.setAll(arr, (i) -> (int)(Math.random()*5)+1); // arr=[1,5,2,1,1]  1<= <6범위의 값 랜덤 채움
 *
 * 4. 배열의 정렬-sort()과 검색-binarySearch()
 * ex)
 * int[] arr = {3,2,0,1,4};
 * int idx = Arrays.binarySearch(arr, 2);               // idx=-5      - 잘목된 결과(이진탐색은 정렬되어 있을 때만 정상작동)
 *
 * Arrays.sort(arr);
 * System.out.println(Arrays.toString(arr));            // [0,1,2,3,4] - 정렬 후
 * int idx = Arrays.binarySearch(arr, 2);               // idx=2       - 올바른 결과\
 *
 * PS) 순차 검색 과 이진 검색
 * : 순차 검색은 원소를 하나씩 이동하며 찾는다.
 *   이진 검색은 반을 자르고 그 반을 자른 곳에서 값을 비교하여
 *             자른 곳이 더 크면 자른곳 이전 원소들은 모두 제외대상
 *             즉, 반을 자르면서 범위를 줄여가며 탐색하여 더 효율적이다.
 *
 * 5. 다차원 배열의 출력 - deepToString
 * ex)
 * int[] arr = {0,1,2,3,4};
 * int[][] arr2D = {{11,12}, {21,22}};
 *
 * System.out.println(Arrays.toString(arr));             // [0,1,2,3,4]
 * System.out.println(Arrays.deepToString(arr2D));       // 2차원은 다차원 배열이므로 deepToString사용 [[11,12],[21,22]]
 *
 * 6. 다차원 배열의 비교 - deepEquals()
 * String[][] str2D = new String[][]{{"aaa","bbb"}, {"AAA","BBB"}};
 * String[][] str2D2 = new String[][]{{"aaa","bbb"}, {"AAA","BBB"}};
 *
 * System.out.println(Arrays.equals(str2D, str2D2));      // false
 * System.out.println(Arrays.deepEquals(str2D, str2D2));  // 2차원은 다차원 배열이므로 deepEquals사용 true
 *
 * 7. 배열을 List로 변환 - asList(Object... a(배열이 들어가고 가변 매개변수(=개수가 정해져 있지 않음)임))
 * ex)
 * List list = Arrays.asList(new Integer[]{1,2,3,4,5});   // list = [1,2,3,4,5]
 * List list = Arrays.asList(new Integer[]{1,2,3,4,5});   // list = [1,2,3,4,5]
 * list.add(6);                                           // List는 읽기 전용으로 UnsupportedOperationException 예외 발생
 * List list = new ArrayList(Arrays.asList(1,2,3,4,5));   // 새로운 ArrayList라서 이렇게하면 추가/삭제가능
 *
 * 8. 람다와 스트림(14장) 관련 - parallelXXX(), spliterator(), stream()
 */

public class EX11_Arrays {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4};
        int[][] arr2D = {{11,12,13}, {21,22,23}};
        System.out.println(Arrays.toString(arr));                // 1차원 배열 [0,1,2,3,4]
        System.out.println(Arrays.deepToString(arr2D));          // 2차원은 다차원 배열이므로 deepToString사용 [[11,12],[21,22]]

        int[] arr2 = Arrays.copyOf(arr, arr.length);             // arr2=[0,1,2,3,4]
        int[] arr3 = Arrays.copyOf(arr, 3);             // arr3=[0,1,2]
        int[] arr4 = Arrays.copyOf(arr, 7);             // arr4=[0,1,2,3,4,0,0]
        int[] arr5 = Arrays.copyOfRange(arr, 2, 4);      // arr5=[2,3] (4는 미포함)
        int[] arr6 = Arrays.copyOfRange(arr, 0, 7);      // arr6=[0,1,2,3,4,0,0]
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("arr3 = " + Arrays.toString(arr3));
        System.out.println("arr4 = " + Arrays.toString(arr4));
        System.out.println("arr5 = " + Arrays.toString(arr5));
        System.out.println("arr6 = " + Arrays.toString(arr6));


        int[] arr7 = new int[5];
        Arrays.fill(arr7, 9);                                 // arr=[9,9,9,9,9]
        System.out.println("arr7 = " + Arrays.toString(arr7));

        Arrays.setAll(arr, (i) -> (int)(Math.random()*6)+1);      // arr=[1<= <7범위의 값 랜덤 채우기]

        for (int i : arr7) { // 향상된 for문
            // for (int x=0; x<arr7.length; x++) {
            // int i = arr7[x];
            char[] graph = new char[i];
            Arrays.fill(graph, '*');
            System.out.println(new String(graph)+i);               // char 문자를 String 문자열로 변환
        }


        String[][] str2D = new String[][]{{"aaa","bbb"}, {"AAA","BBB"}};
        String[][] str2D2 = new String[][]{{"aaa","bbb"}, {"AAA","BBB"}};

        System.out.println(Arrays.equals(str2D, str2D2));         // false
        System.out.println(Arrays.deepEquals(str2D, str2D2));     // 2차원은 다차원 배열이므로 deepEquals사용 true

        char[] chArr = {'A','D','C','B','E'};
        System.out.println("chArr = " + Arrays.toString(chArr));
        System.out.println("index of B = " + Arrays.binarySearch(chArr, 'B'));
        System.out.println("= After sorting =");
        Arrays.sort(chArr);
        System.out.println("chArr = " + Arrays.toString(chArr));
        System.out.println("index of B = " + Arrays.binarySearch(chArr, 'B'));

    }

}
