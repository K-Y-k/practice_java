package org.example.ch11;

import java.util.*;

/**
 *                   Map 인터페이스
 *  Hashtable           HashMap               SortedMap
 *             LinkedHashMap(순서가 필요하면)    TreeMap(TreeSet과 같은 특징)
 */

/**
 * HashMap과 Hashtable - 순서 X, 중복(키는 중복 X, 값은 중복 O)
 * : Map 인터페이스를 구현한 클래스로 키와 값의 쌍으로 저장
 *   HashMap(동기화 x)은 Hashtable(동기화 O)의 새 버전
 *   
 * - HashMap
 * : Map 인터페이스를 구현한 대표적인 컬렉션 클래스
 *   순서를 유지하려면 LinkedHashMap 클래스를 사용하기
 *
 *   데이터를 키와 값의 쌍(=엔트리)로
 *   해싱기법으로 데이터를 저장하여 데이터가 많아도 검색이 빠르다.
 *   
 * - TreeMap
 * : 범위 검색과 정렬에 유리한 컬렉션 클래스로 TreeSet과 같은 특징이 있다.
 *   HashMap보다 데이터 추가/삭제에 시간이 더 걸림
 */

/**
 * 해싱기법
 * : 해시함수를 이용하여 해시테이블에 데이터를 저장하고 읽어오는 것
 *   저장된 곳과 읽어온 곳은 항상 일치해야 한다.
 *   1.키를 넣으면
 *   2.해시함수가 해시코드(값이 저장된 배열의 위치)를 알려준다.
 *   3.링크드 리스트에서 키와 일치하는 데이터를 찾는다.
 *
 *   - 해시함수를 사용하는 클래스 : Hashtable, HashMap, HashSet에서 hashCode() 메서드 사용
 *
 * 해시테이블
 * : 링크들 리스트를 배열로 묶은 2차원 배열 테이블 형태로
 *   즉, 배열과 링크드 리스트가 조합된 형태
 *   즉, 배열의 접근성과 링크드 리스트의 값 변경이 유리함을 모두 이용한 것
 */

/**
 * HashMap 주요 메서드
 *
 * HashMap()                                           생성자 : 해시테이블(배열+링크드리스트)를 생성
 * HashMap(int initialCapacity)                                - 배열 초기 용량
 * HashMap(int initialCapacity, float loadFactor)
 * HashMap(Map m)
 *
 * Object put(Object key, Object value)                 추가
 * void putAll(Map m)
 * Object remove(Object key)                            삭제
 * Object replace(Object key, Object value)             변경
 * boolean replace(Object key, Object oldValue, Object newValue)
 *
 * Object get(Object key)                               키의 값 반환
 * Object getOrDefault(Object key, Object defaultValue) 해당 키가 없을 때 defaultValue를 반환
 * boolean containsKey(Object key)                      해당 키가 있는지
 * boolean containsKey(Object value)                    해당 값이 있는지
 *
 * int size()
 * boolean isEmpty()
 * void clear()
 * Object clone()
 */

public class EX11_HashMap_Hashtable {
    public static void main(String[] args) {
        // 예제 1) 로그인
        HashMap map = new HashMap();

        map.put("myId", "1234");
        map.put("asdf", "1111");
        System.out.println(map);
        map.put("asdf", "1234");  // 키는 중복이 안되어 같은 키에서 값이 새로 최신화된 것
        System.out.println(map);

        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("id와 pwd를 입력해주세요.");
            System.out.print("id : ");
            String id = s.nextLine().trim(); // trim()은 앞뒤 공백처리해줌
            System.out.println();

            System.out.print("password : ");
            String password = s.nextLine().trim();
            System.out.println();

            if (!map.containsKey(id)) {
                System.out.println("입력하신 id는 존재하지 않습니다. 다시 입력해주세요");
                continue;
            }

            if (!(map.get(id)).equals(password)) {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
                break;
            } else {
                System.out.println("id와 비밀번호가 일치합니다.");
                break;
            }
        }
        System.out.println();


        // 예제 2) 참가자 명단
        HashMap map2 = new HashMap();

        map2.put("김자바", 90);
        map2.put("김자바", 100);           // 이전 같은 키가 있어 값만 최신화됨
        map2.put("이자바", 100);
        map2.put("강자바", 80);
        map2.put("안자바", 90);

        Set set = map2.entrySet();        // entrySet은 키와 값의 쌍을 반환하므로 Set으로 저장
        Iterator it = set.iterator();     // 전체 읽어오기 위해 Iterator 사용하여 출력

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next(); // Map 인터페이스.Entry 인터페이스로 Map 인터페이스 내부에 Entry 인터페이스가 있는 것이다.
            System.out.println("이름 : " + e.getKey() + ", 점수 : " + e.getValue());
        }

        set = map2.keySet();               // keySet으로 키들만 가져올 수 있다.
        System.out.println("참가자 전체 명단 : " + set);

        Collection values = map2.values(); // 값들만 가져온 것
        it = values.iterator();

        int total = 0;

        while (it.hasNext()) {
            int i = (int) it.next();
            total += i;
        }

        System.out.println("총점 : " + total);
        System.out.println("평균 : " + (float)total/set.size());
        System.out.println("쵝고점수 : " + Collections.max(values)); // Collections.max/min은 comparable을 구현한 객체만 정상 작동한다.
        System.out.println("최저점수 : " + Collections.min(values));
        System.out.println();


        // 예제 3) 빈도수 계산
        String[] data = {"A", "K", "A", "K", "D", "K", "A", "K", "K", "K", "Z", "D"};

        HashMap map3 = new HashMap();
        for (int i = 0; i < data.length; i++) {
            if (map3.containsKey(data[i])) {        // 키를 포함하면
                int value = (int)map3.get(data[i]); // 기존값에
                map3.put(data[i], value + 1);       // 카운팅
            } else {                                // 포함하지 않는 처음 키가 오면
                map3.put(data[i], 1);               // 1로 저장
            }
        }

        // 전체 읽어오기 위해 Iterator 사용하여 출력
        Iterator it2 = map3.entrySet().iterator();

        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            int value = (int) entry.getValue();
            System.out.println(entry.getKey() + " : " + " " + value);
        }
    }
}
