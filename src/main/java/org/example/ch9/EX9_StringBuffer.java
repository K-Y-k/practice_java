package org.example.ch9;

/**
 * StringBuffer 클래스
 * : String처럼 문자형 배열(char[])을 내부적으로 가지고 있다.
 *   문자열을 저장&다루기 위해 사용한다.
 *   String과 달리 내용을 변경할 수 있다.
 *
 *   public final class StringBuffer implements java.io.Serializable {
 *       private char[] value;
 *       ...
 *   }
 */

/**
 * StringBuffer의 생성자
 * : 배열은 길이 변경불가능하다.
 *   공간이 부족하면 공간을 늘린 새로운 배열을 생성하고 기존 배열을 복사한다.
 *   즉, 빈번해질 수 있으므로 StringBuffer는 저장할 문자열의 길이를 고려해서 적절한 크기로 생성해야 한다.
 *
 * public StringBuffer(int length) {
 *     value = new char[length];
 *     shared = false;
 * }
 * public StringBuffer() {
 *     this(16);                 // 버퍼 크기를 지정하지 않으면 기본 값 16
 * }
 * public StringBuffer(String str) {
 *     this(str.length() + 16);  // 지정한 문자열의 길이보다 16이 더 크게 버퍼를 생성한다.
 *     append(str);
 * }
 *
 */

/**
 * StringBuffer의 비교
 * : StringBuffer는 equals()가 오버라이딩되어있지 않다.(즉, 주소비교)
 *
 * 비교하려면?
 * => String으로 변환 후에 equals()로 비교해야 한다.
 */

/**
 * StringBuffer의 생성자와 메서드
 *    메서드 / 설명                                                         예제                          결과
 * StringBuffer()                                       StringBuffer sb = new StringBuffer();        sb = ""
 * 기본 값 16문자를 담을 수 있는 버퍼를 가진
 * StringBuffer인스턴스 생성
 *
 * StringBuffer(int length) (주로 사용)                  StringBuffer sb = new StringBuffer(10);      sb = ""
 * 지정된 개수의 문자를 담을 수 있는 버퍼를 가진
 * StringBuffer인스턴스 생성
 *
 * StringBuffer(String str)                            StringBuffer sb = new StringBuffer("Hi");     sb = "Hi"
 * 지정된 문자열 값(str)을 갖는 StringBuffer인스턴스 생성
 *
 * StringBuffer append(boolean b)                      StringBuffer sb = new StringBuffer("abc");    sb = "abctrued10.0ABC123"
 * StringBuffer append(char c)                         StringBuffer sb2 = sb.append(true);           sb2 = "abctrued10.0ABC123"
 * StringBuffer append(char[] str)                     sb.append('d').append(10.0f);                 sb3 = "abctrued10.0ABC123"
 * StringBuffer append(double d)                       StringBuffer sb3 = sb.append("ABC")
 * StringBuffer append(float f)                                             .append(123);
 * StringBuffer append(int i)
 * StringBuffer append(long l)
 * StringBuffer append(Object obj)
 * StringBuffer append(String str)
 * 매개변수로 입력된 값을 문자열로 변환하여
 * StringBuffer인스턴스가 저장하고 있는 문자열의 뒤에 덧붙임
 *
 * int capacity()                                      StringBuffer sb = new StringBuffer(100);       bufferSize = 100(배열 길이)
 * StringBuffer인스턴스의 버퍼크기를 알려줌                 sb.append("abcd");
 *                                                     int bufferSize = sb.capacity();
 * int length()                                        int stringSize = sb.length();                  stringSize = 4(배열에 담긴 문자열 길이)
 * 버퍼에 담긴 문자열의 길이를 알려줌
 *
 * char charAt(int index)                              StringBuffer sb = new StringBuffer("abc");     c = 'c'
 * 지정된 위치(index)에 있는 문자를 반환                    char c = sb.charAt(2);
 *
 * StringBuffer delete(int start, int end)             StringBuffer sb = new StringBuffer("0123456"); sb = "0126"
 * 시작위치(start)부터 끝 위치(end) 사이에 있는 문자를 제거   StringBuffer sb2 = sb.delete(3,6);             sb2 = "0126"
 * 단, 끝 위치의 문자는 제외
 *
 * StringBuffer deleteCharAt(int index)                StringBuffer sb = new StringBuffer("0123456"); sb = "012456"
 * 지정된 위치(index)의 문자를 제거                        sb.deleteCharAt(3);
 *
 * StringBuffer insert(int pos, boolean b)             StringBuffer sb = new StringBuffer("0123456"); sb = "0123.456"
 * StringBuffer insert(int pos, char c)                sb.insert(4, '.');
 * StringBuffer insert(int pos, char[] str)
 * StringBuffer insert(int pos, double d)
 * StringBuffer insert(int pos, float f)
 * StringBuffer insert(int pos, int i)
 * StringBuffer insert(int pos, long l)
 * StringBuffer insert(int pos, Object obj)
 * StringBuffer insert(int pos, String str)
 * 두 번째 매개변수로 받은 값을 문자열로 변환하여 지정된 위치(pos)에 추가
 * pos는 0부터 시작
 *
 * StringBuffer replace(int start, int end, String str)      StringBuffer sb = new StringBuffer("0123456"); sb = "012AB6"
 * 지정된 범위(start~end)의 문자들을 주어진 문자열로 바꿈           sb.replace(3, 6, "AB");
 * end위치의 문자는 범위에 포함 X
 *
 * StringBuffer reverse()                                    StringBuffer sb = new StringBuffer("0123456"); sb = "6543210"
 * StringBuffer인스턴스에 저장되어 있는 문자열의 순서를 거꾸로 나열   sb.reverse();
 *
 * void setCharAt(int index, char ch)                        StringBuffer sb = new StringBuffer("0123456"); sb = "01234o6"
 * 지정된 위치의 문자를 주어진 문자(ch)로 바꿈                     sb.setCharAt(5, 'o');
 *
 * void setLength(int newLength)                             StringBuffer sb = new StringBuffer("0123456"); sb = "01234"
 * 지정된 길이로 문자열의 길이를 변경                              sb.setLength(5);                               sb = "0123456    "
 * 길이를 늘리는 경우에 나머지 빈 공간을 널문자 '\u0000'로 채움      StringBuffer sb = new StringBuffer("0123456");
 *                                                           sb.setLength(10);
 *                                                           String str = sb2.toString().trim();            str = "0123456"
 *
 * String toString()                                         StringBuffer sb = new StringBuffer("0123456"); str = "0123456"
 * StringBuffer인스턴스의 문자열을 String으로 반환                String str = sb2.toString();
 *
 * String substring(int start)                               StringBuffer sb = new StringBuffer("0123456"); str = "3456"
 * String substring(int start, int end)                      String str = sb.substring(3);                  str2 = "34"
 * 지정된 범위 내의 문자열을 String으로 뽑아서 반환                 String str2 = sb2.toString(3, 5);
 * 시자위치(start)만 지정하면 시작위치부터 문자열 끝까지 뽑아서 반환
 */
public class EX9_StringBuffer {
    public static void main(String[] args) {
        // 문자열을 변경할 때 용이하다.
        StringBuffer sb = new StringBuffer("abc");
        sb.append("123").append("456"); // 뒤에 추가 이렇게 이어붙일 수 있음(메서드 체이닝)
        sb.insert(0, "s");    // 삽입
        sb.delete(0, 1);                // 삭제

        StringBuffer sb2 = new StringBuffer("abc123456");
        System.out.println(sb==sb2);        // false 주소비교이므로 각 객체는 주소가 다르기에
        System.out.println(sb.equals(sb2)); // false 오버라이딩이 되어있지 않아 equals()도 주소 비교다.

        // String으로 변환 후에 equals()로 비교해야 한다.
        String s = sb.toString();
        String s2 = sb2.toString();
        System.out.println(s.equals(s2));   // true


        // 예제
        StringBuffer sb3 = new StringBuffer("01");    // 01
        StringBuffer sb4 = sb.append(23);             // 0123
        sb3.append('4').append(56);                   // 012345

        StringBuffer sb5 = sb3.append(78);            // 012345678
        sb5.append(9.0);                              // 0123456789.0

        sb5.deleteCharAt(10);                   // 01234567890
        sb5.delete(3,6);                              // 01267890

        sb5.insert(3, "abc");               // 012abc67890
        sb5.replace(6, sb.length(), "END");  // 012abcEND

        sb5.capacity();                               // 배열 길이 18
        sb5.length();                                 // 배열에 저장된 문자의 길이 9
    }
}
