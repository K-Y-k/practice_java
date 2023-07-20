package org.example.ch11;

import java.util.*;

/**
 * 스택
 * : LIFO(Last In First Out) 구조로 마지막에 저장된 것을 제일 먼저 꺼내게 된다.
 *   밑이 막힌 상자
 *   저장(push) / 추출(pop)
 *   
 *   스택은 클래스 객체라서 Stack st = new Stack();처럼 객체 생성해서 사용가능
 *
 *   배열/링크드 리스트 둘 다 가능하지만 배열이 효율적이다.(순차적으로 저장/삭제하므로)
 *
 * - 활용 예)
 * 수식계산, 수식괄호검사, 워드프로세서의 undo/redo, 웹브라우저의 뒤로/앞으로
 *
 *
 *
 */

/**
 * 스택의 메서드
 *
 *  메서드 종류                          설명
 * boolean empty()            스택이 비어있는지 알려줌
 *
 * Object peek()              스택의 맨 위에 저장된 객첼를 반환
 *                            pop()과 달리 스택에서 객체를 꺼내지는 않고 보기만하는 것 (비어있으면 EmptyStackException 발생) (즉, 예외처리를 위해 try-catch문 필요)
 *
 * Object pop()               스택의 맨 위에 저장된 객체를 꺼냄 (비어있으면 EmptyStackException 발생) (즉, 예외처리를 위해 try-catch문 필요)
 *
 * Object push(Object item)   스택에 객체(item)를 저장
 *
 * int search(Object o)       스택에서 주어진 객체(o)를 찾아서 그 위치를 반환, 못찾으면 -1
 *                            (배열과 달리 인덱스 위치는 0이 아닌 1부터 시작)
 */


/**
 * 큐
 * : FIFO 구조로 제일 먼저 저장한 것을 제일 먼저 꺼내게 된다.
 *   양쪽이 뚫린 상자
 *   저장(offer) / 추출(poll)
 *
 *   큐는 클래스 객체가 아니고 인터페이스라서 스택처럼 객체 생성하면 안된다.
 *   => 그러면 어떻게 큐를 사용해야하나?
 *   1. 큐를 직접 구현한다.
 *   2. 큐를 구현한 클래스를 사용한다. (베스트)
 *      ex) AbstractQueue, ArrayBlockingQueue, ArrayDeque, ConcurrentLinkedDeque,
 *          ConcurrentLinkedQueue, DelayQueue, LinkedBlockingDeque, LinkedBlockingQueue,
 *          LinkedList, LinkedTransferQueue, PriortyBlockingQueue, PriorityQueue, SynchronousQueue
 *     ex) Queue q = new LinkedList();
 *         왜  LinkedList q = new LinkedList();로 하지 않냐면 
 *         큐의 기능만 사용했을 것을 확신하기에 다른 큐 리스트 클래스로 변경할 때 검증단계가 필요없이 교체 가능
 * 
 * 
 *   배열/링크드 리스트 둘 다 가능하지만 링크드 리스트가 효율적이다.(배열로 삭제하면 데이터 복사로 이동해야 해서 비효율)
 *
 * - 활용 예)
 * 최근사용문서, 인쇄작업 대기목록, 버퍼
 */

/**
 * 큐의 메서드
 *
 *  메서드 종류                          설명
 * boolean add(Object o)        지정된 객체를 큐에 추가, 성공하면 true 반환
 *                              (저장공간 부족하면 IllegalStateException 발생) (즉, 예외처리를 위해 try-catch문 필요)
 *
 * Object remove()              큐에서 객체를 꺼내 반환
 *                              (비어있으면 NoSuchElementException 발생) (즉, 예외처리를 위해 try-catch문 필요)
 *
 * Object element()             삭제없이 요소를 읽어온다.
 *                              (peek과 달리 큐가 비었을 때 NoSuchElementException 발생) (즉, 예외처리를 위해 try-catch문 필요)
 *
 * boolean offer(Object o)      큐에 객체를 저장, 성공하면 true 실패하면 false 반환
 *
 * Object poll() (주로 사용)      큐에서 객체를 꺼내서 반환, 비어있으면 null 반환
 *
 * Object peek() (주로 사용)      삭제없이 요소를 읽어온다. 비어있으면 null 반환
 */

public class EX9_Stack_Queue {
    static Queue q2 = new LinkedList();
    static final int MAX_SIZE = 5; // 기록 최대 크기 지정

    public static void main(String[] args) {
        // 예제 1)
        Stack st = new Stack();
        Queue q = new LinkedList();  // Queue 인터페이스의 구현체 클래스들 중 하나를 사용
        
        st.push("0");
        st.push("1");
        st.push("2");

        q.offer("0");
        q.offer("1");
        q.offer("2");

        System.out.println("= Stack =");
        while (!st.empty()) {
            System.out.println(st.pop()); // 스택에서 요소 하나를 꺼냄(마지막 저장된 요소 먼저)
        }

        System.out.println("= Queue =");
        while (!q.isEmpty()) {
            System.out.println(q.poll()); // 큐에서 요소 하나를 꺼냄(맨 앞에 저장된 요소 먼저)
        }


        // 예제 2) 스택으로 괄호 검사
        Stack stack = new Stack();
        String expression = "((3+5)*8-2)";

        System.out.println("expression = " + expression);

        try {
            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                if (ch == '(') {
                    stack.push(ch + "");
                } else if (ch == ')') {
                    stack.pop();
                }
            }
            if (stack.isEmpty()) {        // 짝이 맞아서 비어있으면
                System.out.println("괄호가 일치합니다. Yes");
            } else {                      // 여는 괄호가 남아서 비어있지않으면
                System.out.println("괄호가 일치하지 않습니다. No");
            }
        } catch (EmptyStackException e) { // 닫는 괄호가 더 많아서 pop()할 경우 비어있어 예외 발생
            System.out.println("괄호가 일치하지 않습니다. EmptyStackException");
        }
        
        
        // 예제 3) 큐로 최근 5개의 명령 이력 확인
        System.out.println("help를 입력하면 도움말을 볼 수 있습니다.");

        while (true) {
            System.out.print(">>");

            try {
                // 화면으로부터 라인단위로 입력받는다.
                Scanner s = new Scanner(System.in);
                String input = s.nextLine().trim();

                if ("".equals(input))
                    continue;

                if (input.equalsIgnoreCase("q")) {
                    System.exit(0);
                } else if (input.equalsIgnoreCase("help")) {
                    System.out.println(" help - 도움말을 보여줍니다.");
                    System.out.println(" q 또는 Q - 프로그램을 종료합니다.");
                    System.out.println(" history - 최근에 입력한 명령어를 " + MAX_SIZE + "개 보여줍니다.");
                } else if (input.equalsIgnoreCase("history")) {
                    save(input);

                    // 큐 인터페이스의 메서드로는 전체를 볼 수 없어 LinkedList로 다시 형변환한 것
                    LinkedList list = (LinkedList) q2;

                    final int SIZE = list.size();
                    for (int i = 0; i < SIZE; i++) { // 여기에 list.size()로 넣으면 반복을 도는 동안 리스트의 크기가 변할 수 없기에 그 전에 상수로 지정한 후 넣어야 한다!
                        System.out.println((i+1) + "." + list.get(i));
                    }
                } else {
                    save(input);
                    System.out.println(input);
                }
            } catch (Exception e) {
                System.out.println("입력오류입니다.");
            }
        }
    }

    public static void save(String input) {
        // 큐에 저장한다.
        if (!"".equals(null))     // 입력 값이 있으면  (= input기준으로 하면 NPE가 발생할 수 있어 if(input!=null && !input.equals(""))로 길어지기에 저렇게 한 것)
            q2.offer(input);      // 큐에 명령어 저장

        if (q2.size() > MAX_SIZE) // 큐의 크기가 기록할 최대 크기보다 클 경우
            q2.poll();            // 맨 앞의 것을 삭제
    }

}
