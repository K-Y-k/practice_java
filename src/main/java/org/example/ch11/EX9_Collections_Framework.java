package org.example.ch11;



/**
 * 컬렉션 프레임워크
 * - 컬렉션
 * : 여러 객체(데이터)를 모아 놓은 것(유지보수)
 *
 * - 프레임워크
 * : 표준화, 정형화된 체계적인 프로그래밍 방식(작업, 생산성 향상)
 * 
 * - 컬렉션 프레임워크
 * : 컬렉션(다수 객체)을 다루기 위한 표준화된 프로그래밍 방식
 *   컬렉션을 쉽고 편리하게 다룰 수 있는 다양한 클래스를 제공한다.
 *   java.util패키지에 포함된다.(JDK1.2부터 제공)
 *
 * - 컬렉션 클래스
 * : 다수의 데이터를 저장할 수 있는 클래스(ex) Vector(옛날 버전), ArrayList(새 버전), HashSet)
 *
 * - * 컬렉션 프레임워크의 핵심 인터페이스 3가지 *
 * 1) List (순서 O, 중복 O)
 * : 순서가 있는 데이터의 집합, 데이터의 중복을 허용
 *   ex) 대기자 명단
 *   구현 클래스 : ArrayList, LinkedList, Stack, Vector
 *   
 * 2) Set (순서 X, 중복 X)
 * : 순서를 유지하지 않는 데이터의 집합, 데이터의 중복을 허용 안함
 *   ex) 양의 정수집합, 소수의 집합 
 *   구현 클래스 : HashSet, TreeSet 등
 * 
 * 3) Map (순서 X, 키 중복 X, 값 중복 o)
 * : 키(key)와 값(value)을 쌍으로 이루어진 데이터의 집합
 *   순서는 유지되지 않으며 키는 중복을 허용하지 않고 값은 중복을 허용
 *   ex) 우편번호, 지역번호(전화번호), id/pwd
 *   구현 클래스 : HashMap(자손의 LinkedHashMap은 순서 O)(새 버전),TreeMap, Hashtable(옛날 버전), Properties 등
 */


/**
 * Collection 인터페이스의 메서드
 *
 *   메서드 종류                                          설명
 * boolean add(Object o)                 지정한 객체(o) 또는 Collection(c)의 객체들을 Collection에 추가
 * boolean addAll(Collection c)          합집합
 *
 * void clear()                          Collection의 모든 객체를 삭제
 *
 * boolean contains(Object o)            지정된 객체(o) 또는 Collection의 객체들이 Collection에 포함되어 있는지 확인
 * boolean containsAll(Collection c)     부분집합
 *
 * boolean equals(Object o)              동일한 Collection인지 비교
 *
 * int hashCode()                        Collection의 hash code를 반환
 *
 * boolean isEmpty()                     Collection이 비어있는지 확인
 *
 * Iterator iterator()                   Collection의 Iterator를 얻어서 반환
 *
 * boolean remove(Object o)              지정된 객첼르 삭제
 * boolean removeAll(Collection c)       지정된 Collection에 포함된 객체들을 삭제 (차집합)
 *
 * boolean retainAll(Collection c)       지정된 Collection에 포함된 객체만을 남기고 다른 객체들은 Collection에서 삭제
 *                                       이 작업으로 인해 Collection에 변화가 있으면 true 그렇지 않으면 false (교집합)
 *
 * int size()                            Collection에 저장된 객체의 개수를 반환
 *
 * Object[] to Array()                   Collection에 저장된 객체를 객체배열(Object[])로 반환
 * Object[] to Array(Object[] a)         지정된 배열에 Collection의 객체를 저장해서 반환
 */

/**
 * List 인터페이스의 메서드(조상인 Collection 인터페이스의 메서드 제외)
 *
 *   메서드 종류                                          설명
 * void add(int index, Object element)     지정된 위치(index)에 객체(element) 또는 컬렉션에 포함된 객체들을 추가
 * boolean addAll(int index, Collection c)
 *
 * Object get(int index)                   지정된 위치(index)에 있는 객체를 반환
 *
 * int indexOf(Object o)                   지정된 객체의 위치(index)를 반환
 *                                         (List의 첫번째 요소부터 순방향 조회)
 *
 * int lastIndexOf(Object o)               지정된 객체의 위치(index)를 반환
 *                                         (List의 마지막 요소부터 역방향 조회)
 *
 * ListIterator listIterator()             List의 객체에 접근할 수 있는 ListIterator를 반환
 * ListIterator listIterator(int index)
 *
 * Object remove(int index)                지정된 위치(index)에 있는 객체를 삭제하고 삭제된 객체를 반환
 *
 * Object set(int index, Object element)   지정된 위치(index)에 있는 객체(element)를 저장한다.
 *
 * void sort(Comparator c)                 지정된 비교자(comparator)에 있는 List를 정렬
 *
 * List subList(int fromIndex, int toIndex) 지정된 위치(fromIndex~toIndex)에 있는 객체를 반환
 */

/**
 * Set 인터페이스 메서드(Collection 인터페이스의 메서드와 동일)
 */

/**
 * Map 인터페이스 메서드(조상인 Collection 인터페이스의 메서드 제외)
 *
 *   메서드 종류                                          설명
 * boolean contatinsKey(Object key)        지정된 key 객체와 일치하는 Map의 key 객체가 있는지 확인
 * boolean contatinsValue(Object value)    지정된 value 객체와 일치하는 Map의 value 객체가 있는지 확인
 *
 * Set entrySet()                          Map에 저장되어 있는 key-value쌍(=엔트리라고 부름)을 Map.Entry타입의 객체로 저장한 Set으로 반환
 **
 * Object get(Object key)                  지정한 key 객체에 대응하는 value 객체를 찾아서 반환
 *
 * Set keySet()                            Map에 저장된 모든 key 객체를 반환
 *
 * Object put(Object key, Object value)    Map에 value 객체를 key 객체에 연결하여 저장
 *
 * void putAll(Map t)                      지정된 Map의 모든 key-value 쌍을 추가
 *
 * Object remove(Object key)               지정된 key 객체와 일치하는 key-value 객체를 삭제
 *
 * Collection values()                     Map에 저장된 모든 value 객체를 반환
 */

public class EX9_Collections_Framework {
    public static void main(String[] args) {
        
    }

}
