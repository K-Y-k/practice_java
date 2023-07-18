package org.example.ch9;

/**
 * Math 클래스
 * : 수학관련 static 메서드의 집합
 *   (iv가 없고 메서드들만 있으므로 static 메서드로 한 것)
 *
 *   public static final double E = 2.7182818284590452354;   // 자연로그의 밑
 *   public static final double PI = 3.14159265358979323846; // 원주율
 *
 *  - round() : 소수점 아래 첫자리에서 반올림
 *   ex) 90.7552를 round()로 원하는 소수점 아래 3번째 자리에서 반올림하기
 *   1. 원래 값에 100을 곱한다.
 *      90.7552 * 100 -> 9075.52
 *   2. 위의 결과에 Math.round()를 사용한다.
 *      Math.round(9075.52) -> 9076
 *   3. 위의 결과를 다시 100.0으로 나눈다.
 *      9076 / 100.0 -> 90.76
 *      9076 / 100   -> 90
 *
 *    메서드  / 설명                                             예제                                결과
 *  static double abs(double a)                       int i = Math.abs(-10);                     i = 10
 *  static double abs(float f)                        double d = Math.abs(-10.0);                d = 10.0
 *  static double abs(int f)
 *  static double abs(long f)
 *  주어진 값의 절대값을 반환
 *
 *  static double ceil(double a)                       double d = Math.ceil(10.1);                d = 11.0
 *  주어진 값을 올림하여 반환                              double d2 = Math.ceil(-10.1);              d2 = -10.0
 *                                                     double d3 = Math.ceil(10.000015);          d3 = 11.0
 *
 *  static double floor(double a)                      double d = Math.floor(10.8);               d = 10.0
 *  주어진 값을 버림하여 반환                              double d2 = Math.floor(-10.8);             d2 = -11.0
 *
 *  static double max(double a, double b)              double d = Math.max(9.5, 9.50001);         d = 9.5001
 *  static float max(float a, float b)                 int i = Math.max(0, -1);                   i = 0
 *  static int max(float a, float b)
 *  static long max(float a, float b)
 *  주어진 두 값을 비교하여 큰 쪽을 반환
 *
 *  static double min(double a, double b)               double d = Math.min(9.5, 9.50001);        d = 9.5
 *  static float min(float a, float b)                  int i = Math.min(0, -1);                  i = -1
 *  static int min(int a, int b)
 *  static long min(long a, long b)
 *  주어진 두 값을 비교하여 작은 쪽을 반환
 *
 *  static double random()                               double d = Math.random();                 0.0<=d<1.0
 *  0.0~1.0범위의 임의의 double 값을 반환                    int i = (int)(Math.random()*10)+1         1<=i<11
 *  (1.0은 포함 아님)
 *
 *  (반올림 메서드 여러개 있음(HALF_UP/HALF_DOWN/EVEN)
 *  static double rint(double a) (잘 안쓰임)                double d = Math.rint(1.2);                d = 1.0
 *  주어진 double값과 가장 가까운 정수값을 double형으로 반환     double d2 = Math.rint(2.6);              d2 = 3.0
 *  단 두 정수의 정가운데 있는 값(1.5, 2.5, 3.5 등)은 짝수 반환  double d3 = Math.rint(3.5);              d3 = 4.0
 *                                                        double d4 = Math.rint(4.5);              d4 = 4.0
 *
 *  static long round(double a)                           long l = Math.round(1.2);                  l = 1
 *  static long round(float a)                            long l2 = Math.round(2.6);                l2 = 1
 *  소수점 첫째자리에서 반올림한 정수값을 반환                   long l3 = Math.round(3.5);                l3 = 1
 *  두 정수의 정가운데있는 값은 항상 큰 정수를 반환               long l4 = Math.round(4.5);                l4 = 1
 *                                                         double d = 90.7552;                       d = 90.7552
 *                                                         double d2 = Math.round(d*100)/100.0;     d2 = 90.76
 */
public class EX9_Math {
    public static void main(String[] args) {
        for (double d = 0.0; d <= 2.0; d+=0.1) {
            double d1= Math.round(d);
            double d2= Math.rint(d);

            System.out.printf("%4.1f %4.1f %4.1f%n", d, d1, d2);
        }
    }
}
