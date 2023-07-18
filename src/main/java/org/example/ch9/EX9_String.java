package org.example.ch9;

/**
 * String 클래스 
 * : 문자열을 다루기 위한 클래스
 *   데이터(char[]) + 메서드(문자열을 다루는)
 *
 *   public final class String implements java.io.Serializable, Comparable {
 *       private char[] value;
 *       ...
 *   }
 *
 *   내용을 변경할 수 없는 불변 클래스이다.
 *   ex)
 *   String a = "a"
 *   String b = "b"
 *   a = a + b       // 기존 a에서 바뀌는 것이 아닌 새로운 객체 주소로 생성되어 새문자에 저장된다.
 *
 *   즉, +연산자로 이용한 문자열 결합은 새로운 객체를 생성해야하므로 성능이 떨어진다.
 *
 *   * 문자열 결합이나 변경이 잦으면 내용을 변경가능한 StringBuffer를 사용한다. *
 */

/**
 * 문자열 비교
 * ex) String str = "abc";와 String str = new String("abc");의 비교
 *
 * String str1 = "abc";
 * String str2 = "abc";
 * 기존에 생성되었던 "abc"의 참조변수를 가리킨다.
 * String 클래스는 내용이 불변이기에 참조변수를 공유할 수 있는 것이다.
 * str1 [0x100] -> 0x100["abc"]
 * str2 [0x100] -> 0x100["abc"]
 *
 * String str3 = new String("abc");
 * String str4 = new String("abc");
 * 항상 새로 생성한다.
 * str3 [0x200] -> 0x200["abc"]
 * str4 [0x300] -> 0x300["abc"]
 *
 * => 즉, 같은 문자열은 새로 생성할 필요가 없다.
 *
 * str1 == str2 ?      -> true
 * str3 == str4 ?      -> false
 *
 * str1.equals(str2) ? -> true
 * str3.equals(str4) ? -> true
 * => 또한 == 연산자는 주소로 비교하므로 문자열 비교는 equals()로 내용을 비교해야한다!
 *
 */

/**
 * PS) 리터럴 = 상수
 * 
 * 문자열 리터럴 (ex) new String();)
 * : 프로그램 실행시 자동으로 생성된다.(constant pool(상수 저장소)에 저장)
 *   ex) String s1 = "AAA"; => new String("AAA);
 */

/**
 * 빈 문자열("")
 * : 내용이 없는 문자열, 크기가 0인 char형 배열을 저장하는 문자열
 *   숫자를 문자열로 바꿀 때나 null 값보다는 ""로 초기화하는 것이 더 유리하기에 사용
 * ex) String str = ""; // str을 빈 문자열로 초기화
 * 
 * PS) 배열의 길이 vs 크기
 * 배열의 길이는 [3]같은 저장 공간 크기를 뜻함
 * 배열의 크기는 [3] = 3 x 해당타입의 byte를 뜻함
 *
 *
 * 문자(char)와 문자열(String)의 초기화
 * 잘못된 ex1)
 * String s = null;
 * char c = '\u0000';
 *
 * 맞는 ex1)
 * String s = "";
 * char c = '';
 *
 * 잘못된 ex2)
 * String s1 = new String("");  // 계속 새로 생성되기 때문
 * String s2 = new String("");
 *
 * 맞는 ex2)
 * String s1 = "";              // 하나 생성했던 것을 공유
 * String s2 = "";
 */

/**
 * String 클래스의 생성자와 메서드
 *
 *    메서드 / 설명                                                     예제                                  결과
 * String(String s) (사용 X)                             String s = new String("Hello");                 s = "Hello"
 * 주어진 문자열(s)을 갖는 String인스턴스 생성
 *
 * String(char[] value) (주로 사용)                       char[] c {'H','e','l','l','o'}                  s = "Hello"
 * 주어진 문자열(value)을 갖는 String인스턴스 생성            String s = new String(c);
 * 문자 -> 문자열로 바꿀 때 사용
 * 
 * String(StringBuffer buf)                             StringBuffer sb = new StringBuffer("Hello");    s = "Hello"
 * StringBuffer인스턴스가 갖고 있는 문자열과                 String s = new String(sb);
 * 같은 내용의 String 인스턴스를 생성
 * StringBuffer -> 문자열로 바꿀 때 사용
 * 
 * char charAt(int index)                               String s = "Helo";                               c = 'e'
 * 지정된 위치(index)에 있는 문자를 알려줌                   String n = "0123456";                            c2 = '1'
 *                                                      char c = s.charAt(1);
 *                                                      char c2 = n.charAt(1);
 *
 * int compareTo(String str)                            int i = "aaa".compareTo("aaa");                  i = 0
 * 문자열(str)과 사전순서로 비교한다.                        int i2 = "aaa".compareTo("bbb");                 i2 = -1 (왼쪽이 작으면)
 * 같으면 0,                                             int i3 = "bbb".compareTo("aaa");                 i3 = 1  (오른쪽이 작으면)
 * 사전순으로 이전이면 음수,이후면 양수로 반환
 *
 * String concat(String str)                            String s = "Hello";                              s2 = "Hello World"
 * 문자열(str)을 뒤에 덧붙인다.                             String s2 = s.concat(" World");
 * 
 * boolean contains(CharSequence s)                     String s = "abcedfg";                            b = true
 * 지정된 문자열(s)이 포함되었는지 검사한다.                   boolean b = s.contains("bc");
 *
 * boolean startsWith(String prefix)                     String s = "java.lang.Object";                  b = true
 * 지정된 문자열(prefix)로 시작하는지 검사한다.                boolean b = s.startsWith("java");               b2 = false
 *                                                       boolean b2 = s.startsWith("lang");
 *
 * boolean endsWith(String suffix)                      String file = "Hello.txt";                       b = true
 * 지정된 문자열(suffix)로 끝나는지 검사한다.                 boolean b = file.endsWith("txt");
 * 
 * boolean equals(Object obj)                           String s = "Hello";                              b = true
 * 매개변수로 받은 문자열(obj)과 String인스턴스의 문자열을 비교 boolean b = s.equals("Hello);                    b2 = false
 * obj가 String이 아니거나 문자열이 다르면 false를 반환       boolean b2 = s.equals("hello);
 *  
 * boolean equalsIgnoreCase(String str)                 String s = "Hello";                              b = true
 * 문자열과 String인스턴스의 문자열을 대소문자 구분없이 비교     boolean b = s.equalsIgnoreCase("Hello);          b2 = true
 *                                                      boolean b2 = s.equalsIgnoreCase("hello);
 *
 * int indexOf(int ch)                                  String s = "Hello";                              idx1 = 4
 * 주어진 문자(ch)가 문자열에 존재하는지 확인하여 위치 알려줌    int idx1 = s.indexOf('o');                       idx2 = -1
 * 못찾으면 -1을 반환                                      int idx2 = s.indexOf('k');
 *
 * int indexOf(int ch, int pos)                          String s = "Hello";                             idx1 = 1
 * 주어진 문자(ch)가 문자열에 존재하는지 지정된 위치(pos)부터    int idx1 = s.indexOf('e', 0);                   idx2 = -1
 * 확인하여 위치(index)를 알려준다. 못찾으면 -1 반환           int idx2 = s.indexOf('e', 2');
 *
 * int indexOf(String str)                               String s = "ABCDEFG";                           idx = 2
 * 주어진 문자열이 존재하는지 확인하여 위치 알려줌               int idx = s.indexOf("CD");
 * 위치(index)를 알려준다. 못찾으면 -1 반환
 *
 * int lastIndexOf(int ch)                               String s = "java.lang.Object";                  idx1 = 9
 * 지정된 문자 또는 문자코드를 문자열의 오른쪽 끝에서부터 찾아서   int idx1 = s.lastIndexOf('.');                  idx2 = 4
 * 위치를 알려준다. 못찾으면 -1을 반환                        int idx2 = s.indexOf('.');
 *
 * int lastIndexOf(String str)                           String s = "java.lang.java";                    idx1 = 10
 * 지정된 문자열을 인스턴스의 문자열 끝에서 부터 찾아서          int idx1 = s.lastIndexOf("java");                idx2 = 0
 * 위치를 알려준다. 못찾으면 -1을 반환                        int idx2 = s.indexOf("java");
 *
 * int length()                                          String s = "Hello";                             length = 5
 * 문자열의 길이를 알려줌                                    int length = s.length();
 *
 * String[] split(String regex)
 * 문자열을 지정된 분리자(regex)로 나누어 문자열 배열에 담아 반환  String animals = "dog,cat,bear";               arr[0] = "dog"
 *                                                        String[] arr = animals.split(",");             arr[1] = "cat"
 * String[] split(String regex, int limit)
 * 문자열을 지정된 분리자(regex)로 나누어 문자열 배열에 담아 반환  String animals = "dog,cat,bear";               arr[0] = "dog"
 * 단, 문자열 전체를 지정된수(limit)로 자른다.                  String[] arr = animals.split(",", 2);          arr[1] = "cat,bear"
 *
 * String substring(int begin)                                 String s = "java.lang.Object";            c = "Object"
 * String substring(int begin, int end)                        String c = "s.substring(10);              p = "lang"
 * 주어진 시작위치(begin)부터 끝 위치(end) 범위에 포함된 문자열을 얻음  String c = "s.substring(5, 9);
 * 시작위치의 문자는 범위에 포함되지만 끝 위치의 문자는 포함되지 않는다.
 *
 * String toLowerCase()                                      String s = "Hello";                         s1 = "hello"
 * String인스턴스에 저장되어있는 모든 문자열을 소문자로 변환          String s1 = s.toLowerCase();
 *
 * String toUpperCase()                                      String s = "Hello";                         s1 = "HELLO"
 * String인스턴스에 저장되어있는 모든 문자열을 대문자로 변환          String s1 = s.toUpperCase();
 *
 * String trim()
 * 문자열의 왼쪽 끝과 오른쪽 끝에 있는 공백을 없앤 결과를 반환
 * 이 때 문자열 중간에 있는 공백은 제거되지 않는다.
 *
 * static String valueOf(boolean b)                          String b = String.valueOf(true);            b = "true"
 * static String valueOf(char c)                             String c = String.valueOf('a');             c = "a"
 * static String valueOf(int i)                              String i = String.valueOf(100);             i = "100"
 * static String valueOf(long l)                             String l = String.valueOf(100L);            l = "100"
 * static String valueOf(float f)                            String f = String.valueOf(10f);             f = "10.0"
 * static String valueOf(double d)                           String d = String.valueOf(10.0);            d = "10.0"
 * static String valueOf(Object o)                           java.util.Date dd = new java.util.Date();   date = "Wed Jan 27 21:26:29 KST 2016
 * 지정된 값을 문자열로 변환하여 반환                              String date = String.valueOf(dd);
 * 참조변수의 경우 toString()을 호출한 결과를 반환
 */
public class EX9_String {
    public static void main(String[] args) {

    }
}
