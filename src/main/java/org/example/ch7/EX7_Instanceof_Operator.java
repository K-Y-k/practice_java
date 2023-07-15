package org.example.ch7;


public class EX7_Instanceof_Operator {
    public static void main(String[] args) {
        /**
         * instanceof 연산자
         * : 참조변수의 형변환이 가능한지 확인할 때 사용한다. 가능하면 true를 반환한다.
         *   형변환 전에 반드시 instanceof로 확인해야 한다.
         *   ex) void doWork(Car c) {
         *           if (c instanceof FireEngine) {       // 1.형변환이 가능하지 확인
         *                FireEngine fe = (FireEngine) c; // 2.형변환
         *                fe.water();
         *           }
         *       }
         *
         *       fe instanceof Object        // 조상도 true
         *       fe instanceof Car           // 조상도 true
         *       fe instanceof FireEngine    // 자기 자신 객체 true
         */

    }
}
