package org.example.ch9;

/**
 * StringBuilder 클래스
 * : StringBuilder는 동기화가 안되어 있는 StringBuffer이다.
 *   StringBuffer는 동기화되어 있다.
 *   (동기화되어 있다. = 멀티 쓰레드에 안전하게 하는 것)
 *   
 *   싱글 쓰레드 : 한번에 1개 작업
 *   멀티 쓰레드 : 한번에 n개 작업 (ex) 파일 다운중에 채팅치기)
 *              - 장점: 동시 진행 가능
 *              - 단점: 데이터 공유하는데 이 작업중 다른 곳에서 사용하여 데이터가 꼬일 수 있다.
 *                     (이를 막아주는 것이 동기화(=데이터 보호))
 *
 *   멀티 쓰레드가 아닌 경우 동기화는 불필요한 성능저하이므로
 *   싱글 쓰레드이면 StringBuffer 대신 StringBuilder 사용하여 성능 향상
 */
public class EX9_StringBuilder {
    public static void main(String[] args) {
        // 싱글 쓰레드일 경우 StringBuilder사용
        StringBuilder sb = new StringBuilder();
        sb.append("abc");

    }
}
