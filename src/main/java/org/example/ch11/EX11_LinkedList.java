package org.example.ch11;


/**
 * 배열의 장점
 * : 구조가 간단하고 데이터를 읽는 시간이 짧다.(연속적인 특징이므로)
 * 
 * 배열의 단점
 * 1.크기를 변경할 수 없다. 그렇다고 큰 크기로 미리 선언하면 메모리 낭비
 *   크기를 변경해야할 경우 1.크기를 변경한 새로운 배열을 생성후 
 *                      2.기존 데이터들을 복사하고
 *                      3.참조변수를 새로운 배열로 변경해주어야 한다.
 *
 * 2.비순차적인 데이터의 추가, 삭제에 시간이 많이 걸린다.
 *   (연속적이라 빈자리를 채우려고 자리를 옮겨야하기 때문)
 *   단, 순차적인 데이터 추가/삭제(= 끝에 추가 / 끝부터 삭제)는 데이터 이동이 없어 빠르다.
 */

/**
 * LinkedList - 배열의 단점을 보완
 * : 배열과 달리 불연속적으로 존재하는 데이터를 연결한다.
 *
 *   요소 하나 하나를 연결한 것
 *   이 각 하나를 노드라고 불림
 *   class Node {
 *       Node next;  다음 노드 정보
 *       Object obj; 현재 노드의 데이터
 *   }
 *   ex)
 *   linkedlist    0x200    0x300    0x350    0x380    0x400
 *   0x200      -> 0x300 -> 0x350 -> 0x380 -> 0x400 ->  null
 *                   0        1        2        3        4
 *
 * - LinkedList의 장점
 * 1.변경(중간에 삭제/추가)에 유리하다.
 *   삭제는 단 1번의 참조변경으로 가능
 *   추가는 1번의 Node 객체 생성과 2번의 참조변경만으로 가능
 *
 * 2.크기가 제한적이지 않아 메모리 낭비가 없다.
 *   능동적으로 노드를 생성/삭제하여 연결해주면 되기에
 *
 * - LinkedList의 단점
 * 불연속적이므로 데이터 접근성이 나빠서 데이터 검색할 때 불리함
 */

/**
 * doubly LinkedList - 이중 연결리스트
 * : 서로 근접한 원소를 앞뒤로 연결할 수 있게하여
 *   LinkedList의 단점인 접근성을 향상 시킨다.
 *
 *   class Node {
 *       Node next;     // 다음 요소
 *       Node previous; // 이전 요소가 생김
 *       Object obj;
 *   }
 *
 *   doubly linkedlist    0x200    0x300    0x350    0x380    0x400
 *   0x200        ->      0x300 -> 0x350 -> 0x380 -> 0x400 -> null
 *                        null  <- 0x200 <- 0x300 -> 0x350 <- 0x380
 *                         0        1        2        3        4
 *
 *  단, 아직 조회할 때의 단점은 그대로
 */

/**
 * doubly circular LinkedList - 이중 원형
 * : 맨 마지막 요소를 맨 앞에 연결
 *   맨 앞의 요소를 맨 마지막 요소에 연결함
 *
 *   doubly circular linkedlist    0x200          0x300    0x350    0x380    0x400
 *   0x200        ->               0x300       -> 0x350 -> 0x380 -> 0x400 -> 0x200(맨앞)
 *                                 0x400(맨뒤)  <- 0x200 <- 0x300 -> 0x350 <- 0x380
 *                                  0              1        2        3        4
 */

/**
 * ArrayList(배열(연속)기반) vs LinkedList(연결(불연속)기반)
 * 성능 비교
 * - 순차적으로 추가/삭제하기
 * : ArrayList가 더 빠름
 *
 * - 비순간적(중간에)으로 추가/삭제하기
 * : LinkedList가 더 빠름
 *
 * - 데이터 접근시간(검색)
 * : ArrayList가 더 빠름
 *
 */

public class EX11_LinkedList {
    public static void main(String[] args) {

    }

}
