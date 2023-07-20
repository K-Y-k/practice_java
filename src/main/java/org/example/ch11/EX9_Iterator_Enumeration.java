package org.example.ch11;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Iterator(새 버전), ListIterator(Iterator의 접근성 향상시킴), Enumeration(옛날 버전)
 * : 컬렉션에 저장된 데이터를 접근(=읽어오기)하는데 사용되는 인터페이스
 *
 *   Iterator의 메서드 종류                            설명
 * boolean hasNext() (핵심) 1.확인        읽어 올 요소가 남아있는지 확인, 있으면 true 없으면 false
 *
 * Object next()     (핵심) 2.읽기        다음 요소를 읽어옴
 *                                       next()를 호출하기 전에 hasNext()를 호출해서 읽어 올 요소가 있는지 확인하는 것이 안전함
 *
 * void remove()                         next()로 읽어 온 요소를 삭제
 *                                       next()를 호출한 다음에 remove()를 호출해야함(선택적 기능)
 *
 * void forEachRemaining(Consumer<\? super E> action  컬렉션에 남아 있는 요소들에 대해 지정된 작업(action)을 수행
 *                                                    람다식을 사용하는 디폴트 메서드(JDK1.8부터)
 *
 *
 *  ListIterator은 Iterator의 접근성 향상 시켰다.(메서드에 previous()도 추가되어 양방향임)
 *  (잘 안씀)
 *
 *
 *  - Iterator가 필요한 이유
 *  컬렉션(List, Set, Map)들의 구조가 다르기에(ex) ArrayList는 get()메서드가 있지만 HashSet은 없다.)
 *  저장된 요소들을 읽어오는 방법을 표준화 한 것
 *  즉, 성능 문제로 컬렉션을 교체해도 잘 작동한다.
 *  1.boolean hasNext()로 확인하고
 *  2.Object next()로 읽어온다.
 *  
 *  Iterator는 일회용이다. 한번 다음 요소 넘기면서 모두 진행시키면 끝
 *  (다시 사용하려면 새로 생성해야함)
 *  
 *  ex)
 *  List list = new ArrayList();       // 다른 컬렉션으로 변경할 때는 이 부분만 고친다.
 *  Iterator it = list.iterator();     // 조상인 Collection에 public Iterator iterator() 메소드룰 사용
 *  while(it.hasNext()) {              // 요소가 없을 때까지 반복하여
 *      System.out.println(it.next()); // 요소 읽어오기
 *  }
 *
 */

/**
 * Map은 Collection이 조상이 아니라서
 * Map에는 iterator()가 없다.
 *
 * 즉, Map은 keySet(), entrySet(), values()를 이용해서 Set이나 Collection을 얻은 다음에 거기에 iterator를 호출해야한다.
 * ex)
 * Map map = new HashMap();
 * ...
 * Iterator it = map.entrySet().iterator();  = Set eSet = map.entrySet();
 *                                             Iterator it = eSet.iterator();
 */

public class EX9_Iterator_Enumeration {
    public static void main(String[] args) {
        // 유연한 코드가 되는법
        // ArrayList list = new ArrayList();가 아니고
        // Collection list = new ArrayList();로 한 이유는
        // 아래 메서드들이 조상 Collection의 메서드들만 사용되었기에
        // 즉, ArrayList를 나중에 성능문제로 TreeSet()으로 바꾸어도 아래 메서드들을 따로 검증할 필요가 없어 유연한 코드가 된다.
        Collection list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator it = list.iterator(); // 조상인 Collection에 public Iterator iterator()를 사용하여 Iterator로 생성

        while (it.hasNext()) {
            Object obj = it.next();
            System.out.println(obj);
        }
        
        // 위에 이미 다 진행되었기에 다시 사용하려면 Iterator를 다시 생성해야한다.  
        // 즉, Iterator는 일회용이다.
//        while (it.hasNext()) {
//            Object obj = it.next();
//            System.out.println(obj);
//        }


    }

}
