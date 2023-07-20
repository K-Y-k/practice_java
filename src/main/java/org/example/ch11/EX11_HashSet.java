package org.example.ch11;

import java.util.*;


/**
 *                     Set
 *   HashSet(기억해두기)        SortedSet
 *                             TreeSet(기억해두기)
 */

/**
 * HashSet - 순서 X, 중복 X
 * : Set 인터페이스를 구현한 대표적인 컬렉션 클래스
 *   즉, 일반적인 Set을 사용하고 싶을 때 사용하는 클래스
 *   순서를 유지하려면, LinkedHashSet 클래스를 사용하면 된다.
 *
 *   HashSet은 객체를 저장하기 전에 기존에 같은 객체가 있는지 확인한다.
 *   같은 객체가 없으면 저장하고, 있으면 저장하지 않는다.
 *   즉, boolean add(Object o)는 저장할 객체의 equals()와 hashCode()를 이용해 확인
 *   단, equqls()와 hashCode()가 오버라이딩 되어있어야 정상 작동한다.
 *
 *
 * TreeSet
 * : 범위 검색과 정렬에 유리한 컬렉션 클래스
 *   HashSet보다 데이터 추가, 삭제에 시간이 더 걸림
 */

/**
 * HashSet 주요 메서드
 * HashSet()                                      - 기본 생성자
 * HashSet(Collection c)                          - 컬렉션 클래스를 넣은 생성자
 * HashSet(int initialCapacity)                   - 초기 용량을 지정한 생성자
 * HashSet(int initialCapacity, float loadFactor) - 용량이 부족할 때 2배로 늘리는데 용량의 loadFactor까지(ex)0.8=>80%) 찼을 때 2배로 늘릴지
 *
 * boolean add(Object o)                          - 추가
 * boolean addAll(Collection c)                   - 합집합
 *
 * boolean remove(Object o)                       - 삭제
 * boolean removeAll(Collection c)                - 교집합
 *
 * boolean retainAll(Collection c)                - 조건부 삭제(차집합)
 * void clear()                                   - 모두 삭제
 *
 * boolean contains(Object o)                     - 객체가 포함되어 있는지
 * boolean containsAll(Collection c)              - 컬렉션의 여러 객체가 모두 포함되어 있는지
 * Iterator iterator()                            - 컬렉션 요소 읽기
 *
 * boolean isEmpty()                              - 비어있는지 확인
 * int size()                                     - 저장된 객체의 개수
 * Object[] toArray()                             - 객체 배열로 반환
 * Object[] toArray(Object[] a)
 */

public class EX11_HashSet {
    public static void main(String[] args) {
        // 예제 1)
        Object[] objArr = {"1", 1, "2", "2", "3", "3", "4", "4", "4"};
        Set set = new HashSet();

        // 중복된 클래스 및 값은 지워진다. "1"과 1은 클래스가 다르므로 둘 다 저장됨
        // 순서가 유지되지 않아 위 순서로 들어가지지 않을 수도 있다.
        for (int i = 0; i < objArr.length; i++) {
            System.out.println(objArr[i] + "=" + set.add(objArr[i])); // HashSet에 objArr의 요소들을 저장한다.
        }

        // HashSet에 저장된 요소들을 출력한다.
        System.out.println(set);

        // HashSet에 저장된 요소들을 출력한다.(Iterator 이용)
        Iterator it = set.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }


        // 예제 2)
        Set set2 = new HashSet();

        // set2의 크기가 6보다 작은 동안 1~45사이의 난수를 저장
        for (int i = 0; set2.size() < 6; i++) {
            int num = (int)(Math.random()*45) + 1;
            set2.add(num);
        }

        List list = new LinkedList(set2);  // Set은 정렬할 수 없어 set2의 모든 요소를 List로 저장
        Collections.sort(list);            // List를 오름차순 정렬
        System.out.println(list);


        // 예제 3)
        HashSet set3 = new HashSet();

        set3.add("abc");
        set3.add("abc"); // 중복이라 저장안됨
        set3.add(new Person("kyk", 10));
        set3.add(new Person("kyk", 10)); // equals()와 hashCode()를 오버라이딩 한 후 중복처리 됨
        System.out.println(set3);


        // 예제 4)
        HashSet setA = new HashSet();
        HashSet setB = new HashSet();
        HashSet setHab = new HashSet();
        HashSet setKyo = new HashSet();
        HashSet setCha = new HashSet();

        setA.add("1"); setA.add("2"); setA.add("3");
        setA.add("4"); setA.add("5");
        System.out.println("A = " + setA);

        setB.add("4"); setB.add("5"); setB.add("6");
        setB.add("7"); setB.add("8");
        System.out.println("B = " + setB);

        // 교집합
        Iterator it2 = setB.iterator();
        while (it2.hasNext()) {
            Object tmp = it2.next();  // setB에서 하나씩 꺼내서
            if (setA.contains(tmp))   // setA에 있는지 확인하여 있으면 추가
                setKyo.add(tmp);        
        }
        // 위 코드 = setA.retainAll(setB); // 공통된 요소만 남기고 삭제

        // 차집합 A-B
        it2 = setA.iterator();
        while (it2.hasNext()) {
            Object tmp = it2.next();
            if (!setB.contains(tmp))  // A-B이므로 setB에 없는 것만 차집합에 저장
                setCha.add(tmp);
        }
        // 위 코드 = setA.removeAll(setB); // setA와 setB의 공통 요소를 제거

        // 합집합
        it2 = setA.iterator();        // A와
        while (it2.hasNext())
            setHab.add(it2.next());

        it2 = setA.iterator();        // B 모두 넣으면 set이 자동으로 중복은 걸러서 다 넣어지게 된다.
        while (it2.hasNext())
            setHab.add(it2.next());
        // 위 코드 = setA.addAll(setB); // setA에 setB의 모든 요소를 추가(중복제외)


        System.out.println("A n B = " + setKyo);
        System.out.println("A U B = " + setHab);
        System.out.println("A - B = " + setCha);


    }

}

// equals()와 hashCode()를 오버라이딩해야 HashSet이 바르게 동작
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + ":" + age;
    }

    // equals()와 hashCode()는 같이 오버라이딩 되어야한다
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
