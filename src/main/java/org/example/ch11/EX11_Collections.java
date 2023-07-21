package org.example.ch11;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static java.util.Collections.*;

/**
 * Collections - 컬렉션을 위한 메서드(static)를 제공 (Objects:객체 / Arrays:배열 / Collections:컬렉션)
 *
 * 1. 컬렉션 채우기, 복사, 정렬, 검색 - fill(), copy(), sort(), binarySearch() 등
 *
 * 2. 컬렉션 동기화 - synchronizedXXX()
 * static Collection synchronizedCollection(Collection c)
 * static List synchronizedList(List list)
 * static Set synchronizedSet(Set s)
 * static Map synchronizedMap(Map m)
 * static SortedSet synchronizedSortedSet(SortedSet s)
 * static SortedMap synchronizedSortedMa(pSortedMap m)
 * ex) 사용 예시 : 동기화되지 않은 리스트를 넣어 동기화된 리스트로 생성
 * List syncList = Collections.synchronizedList(new ArrayList(...));
 * = 위 리스트는 Vector클래스와 동일한 효과를 얻는다.
 *
 * 3. 변경불가(readOnly) 컬렉션 만들기 - unmodifiableXXX()
 * static Collection unmodifiableCollection(Collection c)
 * static List unmodifiableList(List list)
 * static Set unmodifiableSet(Set s)
 * static Map unmodifiableMap(Map m)
 * static NavigableSet unmodifiableNavigableSet(NavigableSet s)
 * static SortedSet unmodifiableSortedSet(SortedSet s)
 * static NavigableMap unmodifiableNavigableMap(NavigableMap m)
 * static SortedMap unmodifiableSortedMap(SortedMap m)
 *
 * 4. 싱글톤 컬렉션(=객체 1개만 저장하는 컬렉션) 만들기 - singletonXXX()
 * static List singletonList(Object o)
 * static Set singleton(Object o)  // singletonSet이 아님
 * static Map singletonMap(Object key, Object value)
 *
 * 5. 한 종류의 객체만 저장하는 컬렉션 만들기 - checkedXXX()  (제네릭스가 이와 같은 역할이지만 JDK1.5이전은 이렇게 해야함)
 * static Collection checkedCollection(Collection c, Class type)
 * static List checkedList(List list, Class type)
 * static Set checkedSet(Set s, Class type)
 * static Map checkedMap(Map m, Class keyType, Class valueType)
 * static Queue checkedQueue(Queue q, Class type)
 * static NavigableSet checkedNavigableSet(NavigableSet s, Class type)
 * static SortedSet checkedSortedSet(SortedSet s, Class type)
 * static NavigableMap checkedNavigableMap(NavigableMap m, Class keyType, Class valueType)
 * static SortedMap checkedSortedMap(SortedMap m, Class keyType, Class valueType)
 *
 * ex) 사용 예시 : 동기화되지 않은 리스트를 넣어 동기화된 리스트로 생성
 * List list = new ArrayList();                    // 한 종류의 객체만 저장가능한 컬렉션 생성
 * List checkedList = checked(list, String.class); // String.class로 지정했으므로 String만 저장가능하게 됨
 * checkedList.add("abc");                         // String이므로 가능
 * checkedList.add(3);                             // String이 아니므로 불가능
 * 
 */

public class EX11_Collections {
    public static void main(String[] args) {
        List list = new ArrayList();
        System.out.println(list);

        addAll(list, 1,2,3,4,5);     // 리스트에 요소를 저장
        System.out.println(list);

        rotate(list, 2);               // 양수는 반시계 방향으로 2칸씩 이동 (음수는 시계방향으로)
        System.out.println(list);

        swap(list, 0, 2);                 // 첫번째와 3번째를 교환
        System.out.println(list);

        shuffle(list);                         // 저장된 요소의 위치를 임의로 변경
        System.out.println(list);

        sort(list, reverseOrder());            // 역순 정렬 reverse(list);와 동일
        System.out.println(list);

        sort(list);                            // 오름차순 정렬
        System.out.println(list);

        int idx = binarySearch(list, 3);   // 3이 저장된 위치를 반환, binarySearch은 항상 정령되고 진행해야함
        System.out.println("index of 3 = " + idx);

        System.out.println("max=" + max(list)); // 최대값
        System.out.println("min=" + min(list)); // 최소값
        System.out.println("min=" + max(list, reverseOrder())); // reverseOrder로 반대가 되어 최솟값

        fill(list, 9);                    // 리스트를 9로 채운다.
        System.out.println("list=" + list);

        // list와 같은 크기의 새로운 list를 생성하고 2로 채운다. 단, 결과는
        List newList = nCopies(list.size(), 2);
        System.out.println("newList = " + newList);

        System.out.println(disjoint(list, newList)); // 공통요소가 없으면 true

        copy(list, newList);                         // newList의 것을 list에 복사
        System.out.println("newList = " + newList);
        System.out.println("list = " + list);

        replaceAll(list, 2, 1);        // 2 -> 1로 변경
        System.out.println("list = " + list);

        Enumeration e = enumeration(list);          // Iterator와 같은 것이다.
        ArrayList list2 = list(e);
        System.out.println("list2 = " + list2);
    }

}
