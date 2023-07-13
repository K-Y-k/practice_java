package org.example.ch6;

public class EX6_Overloading {

    public static void main(String[] args) {
        /**
         * 오버로딩
         * : 한 클래스 안에 같은 이름의 메서드 여러 개를 정의하는 것
         *   ex)
         *   void println()
         *   void println(char x)
         *   void println(int x)
         *
         *
         * - 오버로딩이 성립하기 위한 조건 3가지
         * 1. 메서드 이름이 같아야한다.
         * 2. 매개변수의 개수 또는 타입이 달라야한다.
         * 3. 반환 타입은 영향 없다.
         *
         * => 즉, 매개변수는 다르지만 같은 의미의 기낭을 수행하면 오버로딩을 한다!
         *
         * 틀린 ex) 매개변수 타입이 동일하므로 중복이라 오버로딩이 아니다.
         *     int add(int a, int b) {
         *         return a + b;
         *     }
         *     int add(int x, int y) {
         *         return x + y;
         *     }
         *
         * 틀린 ex) 매개변수 반환 타입은 영향이 없으므로 중복이라 오버로딩이 아니다.
         *     int add(int a, int b) {
         *         return a + b;
         *     }
         *     long add(int x, int y) {
         *         return (long)(x + y);
         *     }
         *
         * 맞은 ex) 1.메서드 이름 같고 2. 매개변수 타입이 다르므로 오버로딩 성립
         *     long add(int a, int b) {
         *         return a + b;
         *     }
         *     long add(long x, long y) {
         *         return x + y;
         *     }
         */
        MyMath2 mm = new MyMath2();
        System.out.println("mm.add(3, 3) 결과: " + mm.add(3, 3));
        System.out.println("mm.add(3L, 3) 결과: " + mm.add(3L, 3));
        System.out.println("mm.add(3, 3L) 결과: " + mm.add(3, 3L));
        System.out.println("mm.add(3L, 3L) 결과: " + mm.add(3L, 3L));
        
        int[] a = {100, 200, 300};
        System.out.println("mm.add(a) = " + mm.add(a));

    }
}

class MyMath2 {
    int add(int a, int b) {
        System.out.print("int add(int a, int b) - ");
        return a+b;
    }
    long add(long a, int b) {
        System.out.print("int add(long a, int b) - ");
        return a+b;
    }
    long add(int a, long b) {
        System.out.print("int add(int a, long b) - ");
        return a+b;
    }
    long add(long a, long b) {
        System.out.print("int add(long a, long b) - ");
        return a+b;
    }
    int add(int[] a) {
        System.out.print("int add(int []a) - ");
        int result = 0;

        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }

        return result;
    }

}
