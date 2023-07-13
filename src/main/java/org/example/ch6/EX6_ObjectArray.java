package org.example.ch6;

public class EX6_ObjectArray {

    public static void main(String[] args) {
        /**
         * 객체 배열
         * 객체 배열 == 참조변수 배열
         *
         * ex)
         * Tv tv1, tv2, tv3;
         * ->   Tv[] tvArr = new Tv[3]; 0x100
         *      tvArr[0] = new Tv();    0x200
         *      tvArr[1] = new Tv();    0x300
         *      tvArr[2] = new Tv();    0x400
         *
         *   tvArr(참조변수 배열)      0x100
         * | 0x100 |         ---->  | 0x200 | 0x300 | 0x400 |
         *
         */
        Tv[] tvArr = new Tv[3];  // 길이가 3인 Tv타입의 참조변수 배열 선언 및 저장공간 3개 생성
        
        // 객체를 생성해서 배열의 각 요소에 저장
        tvArr[0] = new Tv();
        tvArr[1] = new Tv();
        tvArr[2] = new Tv();

    }
}
