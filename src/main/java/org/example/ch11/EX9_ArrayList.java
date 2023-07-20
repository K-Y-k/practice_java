package org.example.ch11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * ArrayList
 * : 기존의 Vector를 개선한 것으로 구현원리와 기능적으로 동일하다.
 *   차이점: ArrayList = 동기화 X
 *          Vector    = 동기화 O
 *
 *   List 인터페이스를 구현하므로 순서 O, 중복 O
 *
 *   데이터의 저장공간으로 배열을 사용한다.(배열기반)
 */


/**
 * ArrayList의 메서드
 *
 * - 생성자
 * 1) ArrayList()                    : 기본 생성자
 * 2) ArrayList(Collection c)        : 받아온 다른 컬렉션 타입을 ArrayList로 변환할 때
 * 3) ArrayList(int initialCapacity) : 배열의 길이를 지정하여 생성
 * 
 * - 추가 메서드
 * boolean add(Object o)
 * void add(int index, Object element)
 * boolean addAll(Collection c)
 * boolean addAll(int index, Collection c)
 * 
 * - 삭제 메서드
 * boolean remove(Object o)
 * Object remove(int index)
 * boolean removeAll(Collection c)
 * void clear()
 *
 * - 검색 메서드
 * int indexOf(Object o)
 * int lastIndexOf(Object o)
 * boolean contains(Object o)
 * Object get(int index)
 * Object set(int index, Object element)
 *
 * - 그 외 메서드
 * List subList(int fromIndex, int toIndex)  리스트의 fromIndex~toIndex까지 뽑아낸 것을 새로운 리스트로 만듬
 * Object[] toArray()                         ArrayList의 객체배열을 반환
 * Object[] toArray(Object[] a)
 * boolean isEmpty()                          비어있는지 확인
 * void trimToSize()                          빈공간 제거
 * int size()                                 저장된 객체의 개수 반환
 */

/**
 * ArrayList에 저장된 객체의 삭제과정
 * 1. remove(삭제할 위치)를 호출한다.
 * 2. 삭제할 데이터의 뒤 데이터들을 1칸씩 앞으로 복사해서 삭제할 데이터를 덮어쓴다. (삽입이면 반대로 뒤로 이동하여 복사)
 * 3. 데이터가 모두 1칸씩 이동했으므로 마지막 데이터는 null로 변경한다.
 * 4. 데이터가 삭제되어 데이터의 개수가 줄었으므로 size의 값을 감소시킨다.        (삽입이면 반대로 증가)
 *
 * PS) 마지막 데이터를 삭제하는 경우는 2번과정(복사)이 필요없다.
 * => 즉, 덮어씌우는 과정이 될 경우의 remove는 자제하는 것이 좋다.
 *
 * => 즉, 1칸씩 이동하며 복사하므로 반복문에서의 삭제는 마지막 객체부터 삭제해야한다.
 *    for (int i=list.size()-1; i>=0; i--)
 *        list.remove(i)
 */

public class EX9_ArrayList {
    public static void main(String[] args) {
        // 기본 길이가 10인 ArrayList를 생성
        ArrayList list1 = new ArrayList(10);
        
        // ArrayList에는 객체만 저장가능한데 int형으로 넣었지만 Integer 래퍼 클래스로 오토박싱(기본형->참조형 자동 변환)해준 것
        list1.add(5);
        list1.add(4);
        list1.add(2);
        list1.add(0);
        list1.add(1);
        list1.add(3);

        // subList로 일부만 뽑아서 새로 생성
        ArrayList list2 = new ArrayList(list1.subList(1,4)); // 끝 4는 제외하여 1~3의 원속밧이 들어감

        print(list1, list2);


        // Collection은 인터페이스이고 Collections는 유틸 클래스이다. 착각 X
        // list 정렬
        Collections.sort(list1);
        Collections.sort(list2);
        print(list1, list2);

        // list1이 list2의 모든 요소를 포함하고 있는지 확인
        System.out.println("list1.containsAll(list2): " + list1.containsAll(list2));

        list2.add("B");
        list2.add("C");
        list2.add(3, "A"); // 여기는 추가할 index 위치지정함
        print(list1, list2);

        // index 위치의 값을 변경
        list2.set(3, "AA");
        print(list1, list2);

        // 문자열 1과 숫자 1이 들어간 리스트에 구분해서 위치 찾아보기
        list1.add(0, "1");  
        System.out.println("index=" + list1.indexOf("1"));
        System.out.println("index=" + list1.indexOf(1));
        print(list1, list2);

        list1.remove(new Integer(1)); // 값을 선택해서 삭제
        list1.remove(0);              // 위치를 선택해서 삭제 (즉, 조심해야할 점이다)
        print(list1, list2);

        // list1에서 list2와 겹치는 부분만 남기고 나머지는 삭제 (교집합)
        System.out.println("list1.retainAll(list2): " + list1.retainAll(list2));
        print(list1, list2);

        // list2에서 list1에 포함된 객체들을 삭제
        for (int i = list2.size()-1; i >= 0; i--) { // list2에서 하나씩 꺼낸다.
            if (list1.contains(list2.get(i))) // contains()로 꺼낸 객체가 list1에 있는지 확인
                list2.remove(i);              // remove(i)로 해당 객체를 list2에서 삭제
        }
        print(list1, list2);
    }

    static void print(ArrayList list1, ArrayList list2) {
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        System.out.println();
    }

}
