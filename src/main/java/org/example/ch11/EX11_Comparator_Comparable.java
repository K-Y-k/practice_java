package org.example.ch11;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comparator와 Comparable
 * : 객체 정렬에 필요한 메서드(정렬기준 제공)를 정의한 인터페이스
 * 
 * Comparator : 기본 정렬기준(default)을 구현하는데 사용
 * Comparable : 기본 정렬기준 외에 다른 기준으로 정렬하고자 할 때 사용
 *
 * public interface Comparator {
 *     int compare(Object o1, Object o2); // o1, o2 두 객체를 비교 (왼쪽이 크면 양수, 오른쪽이 크면 음수, 같으면 0 반환)
 *     boolean equals(Object obj);        // equals를 오버라이딩하라는 뜻
 * }
 * public interface Comparable {
 *     int compareTo(Object o);           // 주어진 객체(o)를 자신과 비교
 * }
 *
 *
 * - compare()와 compareTo는 두 객체의 비교결과를 반환하도록 작성
 * ex) Comparable을 상속한 Integer 클래스
 * public final class Integer extends Number implements Comparable {
 *     ...
 *     public int compareTo(Integer anotherInteger) {
 *         int v1 = this.value;
 *         int v2 = anotherInteger.value;
 *
 *         // 같으면 0, 오른쪽 값이 크면 -1, 작으면 1을 반환
 *         return (v1 < v2 ? -1 : (v1==v2 ? 0 : 1);
 *     }
 * }
 *
 */

public class EX11_Comparator_Comparable {
    public static void main(String[] args) {
        String [] strArr = {"cat", "Dog", "lion", "tiger"};

        Arrays.sort(strArr); // String 클래스에서의 Comparable 구현에 의한 정렬 사용으로 정렬기준을 따로 없어도 된 것
        System.out.println("strArr=" + Arrays.toString(strArr)); // 오름차순 기준으로 대문자가 소문자보다 앞임

        //          정렬대상         정렬기준
        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분 안함
        System.out.println("strArr=" + Arrays.toString(strArr));

        Arrays.sort(strArr, new Descending()); // Comparator을 역순으로 정렬하는 기준으로 구현한 클래스
        System.out.println("strArr=" + Arrays.toString(strArr));
    }
        

}

// 역순으로 정렬하는 기준으로 구현한 클래스
class Descending implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Comparable && o2 instanceof Comparable) {
            Comparable c1 = (Comparable) o1;
            Comparable c2 = (Comparable) o2;
            return c1.compareTo(c2) * -1;  // -1을 곱해서 기본 정렬방식의 역으로 변경한다.
                                           // 또는 c2.compareTo(c1)와 같이 순서를 바꿔도 된다.
        }
        return 1;
    }
}