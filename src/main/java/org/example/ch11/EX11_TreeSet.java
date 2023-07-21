package org.example.ch11;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


/**
 * TreeSet
 * : 이진 탐색 트리로 구현하고 범위 탐색과 정렬에 유리함
 *   이진 트리는 모든 노드가 최대 2개의 하위 노드를 가짐
 *   각 요소(node)가 나무(tree)형태로 연결(LikedList의 변형)
 *   class TreeHash {
 *       TreeNode left;  // 왼쪽 자식노드
 *       Object element; // 지정할 객체 노드
 *       TreeNode right  // 오른쪽 자식 노드
 *   }
 *   
 *   이진 탐색 트리
 *   : 이진트리의 종류 중 하나로 부모보다 작은 값은 왼쪽 큰 값은 오른쪽
 *     단점은 데이터가 많아질 수록 추가/삭제에 시간이 더 걸림(비교횟수 증가로)
 *
 *
 * TreeSet 데이터 저장 과정 boolean add(Object o)
 * : HashSet은 equals(), hashCode()로 중복을 확인했지만
 *   TreeSet은 compare를 이용해서
 *   루트부터 트리를 따라 내려가며 값을 비교, 작으면 왼쪽, 크면 오른족에 저장함
 *   ex) 7,4,9,1,5의 순서로 저장
 *   1.  7
 *   2.  7
 *      4
 *   3.  7
 *      4  9
 *   4.  7
 *      4  9
 *     1
 *   5.  7
 *      4  9
 *     1 5
 */


/**
 * TreeSet - 주요 생성자와 메서드(add,size,remove,isEmpty,iterator 같은 기본적적인 것은 제외함)
 *   생성자 또는 메서드                                             설명
 * TreeSet()                                               기본 생성자
 * TreeSet(Collection c)                                   주어진 컬렉션을 저장하는 TreeSet을 생성
 * TreeSet(Comparator comp)                                주어진 정렬기준으로 정렬하는 TreeSet을 생성
 *
 * Object first()                                          정렬된 순서에서 첫 번째 객체를 반환
 * Object last()                                           정렬된 순서에서 마지막 객체를 반환
 *
 * Object ceiling(Object o)                                지정된 객체와 같은 객체를 반환
 *                                                         없으면 큰 값을 가진 객체 중 제일 가까운 값의 객체를 반환 없으면(없으면 null)
 *
 * Object floor(Object o)                                  지정된 객체와 같은 객체를 반환
 *                                                         없으면 작은 값을 가진 객체 중 제일 가까운 값의 객체를 반환 없으면(없으면 null)
 *
 * Object higher(Object o)                                 지정된 객체보다 큰 값을 가진 객체 중 제일 가까운 값의 객체를 반환(없으면 null)
 * Object lower(Object o)                                  지정된 객체보다 작은 값을 가진 객체 중 제일 가까운 값의 객체를 반환(없으면 null)
 * 
 * SortedSet subSet(Object fromElement, Obejct toElement)  범위 검색(fromElement와 toElement사이)의 결과를 반환 (끝 범위는 포함 X)
 *
 * SortedSet headSet(Obejct toElement)                     범위 검색(fromElement와 toElement사이)의 결과를 반환 (끝 범위는 포함 X)
 *
 * SortedSet tailSet(Object fromElement)                   범위 검색(fromElement와 toElement사이)의 결과를 반환 (끝 범위는 포함 X)
 */

/**
 * 트리 순회
 * : 이진트리의 모든 노드를 한번씩 읽는 것
 *
 * - 트리 순회 종류
 * 1) preorder (전위순회)
 * 2) inorder  (중위순회)
 * 3) postorder(후위순회)
 * 4) 레벨순회
 *
 */

public class EX11_TreeSet {
    public static void main(String[] args) {
        // 예제 1) 비교기준이 들어가서 정렬기능이 있는 TreeSet
        // TreeSet은 둘 중에 하나는 비교 기준이 있어야 정렬되어 정상 작동한다.
        // 1. new TreeSet();에 생성할 때 괄호 안에 비교 기준이 있는 객체가 들어가 있거나
        // 2. set.add();에 넣을 때 비교 기준이 있는 객체가 들어가거나
        Set set = new TreeSet();   // 범위 검색을 하고 정렬이 되어 정렬이 따로 필요없음
//        Set set = new HashSet(); // HashSet인 경우 리스트로 변환한 후 정렬이 필요함

//        set.add(new Test());
//        set.add(new Test());

        for (int i = 0; set.size() < 6; i++) {
            int num = (int)(Math.random()*45) + 1;
            set.add(num);
        }

        System.out.println(set);
        System.out.println();


        // 예제 2) 범위 검색에 유리한 예시
        // 여기서 subSet은 TreeSet전용 메서드이므로 다형성으로 Set 생성하면 안된다.
        TreeSet set2 = new TreeSet(); // 범위검색을 위해 TreeSet 생성

        String from = "b";
        String to = "d";

        set2.add("abc");      set2.add("alien");      set2.add("bat");
        set2.add("car");      set2.add("Car");        set2.add("disc");
        set2.add("dance");    set2.add("dZZZZ");      set2.add("dzzzz");
        set2.add("elephant"); set2.add("elevator");   set2.add("fan");
        set2.add("flower");

        System.out.println(set2);
        System.out.println("range search from = " + from + " to " + to);
        System.out.println("result1 = " + set2.subSet(from, to));     // b ~ d로 시작하는 단어들 모두 출력
        System.out.println("result2 = " + set2.subSet(from, to + "zzz")); // b ~ dzzz의 단어들 모두 출력 dzzz는 d의 거의 끝부분이다.
        System.out.println();


        // 예제 3) 범위 검색 활용 예시
        // 여기서 headSet, tailSet은 TreeSet전용 메서드이므로 다형성으로 Set 생성하면 안된다.
        TreeSet set3 = new TreeSet();
        int[] score = {80, 95, 50, 35, 45, 65, 10, 100};

        for (int i = 0; i < score.length; i++) {
            set3.add(score[i]);
        }

        System.out.println("50보다 작은 값 : " + set3.headSet(50));
        System.out.println("50보다 큰 값 : " + set3.tailSet(50));
    }
}

// 비교 기준이 없으면 set.add(new Tes());와 같은 추가에 형 변환 에러가 발생
// 즉, 비교 기준을 넣어야 한다.
class Test implements Comparable{
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}


// 비교 기준을 따로 이렇게 만들었을 경우
// 위 TreeSet 생성할 때 Set set = new TreeSet(new TestComp());
// 이렇게 안에 이 구현한 클래스를 넣어 정렬 기준을 바꿈
class TestComp implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
