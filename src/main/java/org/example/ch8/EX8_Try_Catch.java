package org.example.ch8;

/**
 * 예외 처리하는 방법 3가지
 * : 1)try-catch문  2)예외 선언하기  3)은폐(빈 catch(){}로 무시처리)
 * 1.try-catch문(직접처리)
 * try {
 *     // 예외가 발생할 가능성이 있는 문장들을 넣는다.
 * } catch (Exception1 e1) {  ex) 돈이 부족한 경우
 *     // Exception1이 발생했을 경우, 이를 처리하기 위한 코드
 * } catch (Exception2 e2) {  ex) 물건이 부족한 경우
 *     // Exception2이 발생했을 경우, 이를 처리하기 위한 코드
 * } catch (Exception3 e3) {  ex) 가게가 문닫았을 경우
 *     // Exception3이 발생했을 경우, 이를 처리하기 위한 코드
 * }
 */

 /**
 * 예외 처리의 흐름
 * try의 코드가 진행되면서
 * 에러가 난 경우 그 에러 이후 코드는 실행 안되고 catch문에서 관련된 예외를 찾고 실행한다.
 * 만약 catch문에서 찾지 못하면 프로그램이 비정상적으로 종료한다.
 *
 */

/**
 * 예외가 발생하면 catch문에 관련된 예외 객체가 있으면
 * 객체가 만들어지고 그 안에는 발생한 예외 정보와 printStackTrace()와 getMessage() 메서드 등이 들어있다.
 *
 * printStackTrace() : 예외발생 당시의 호출스택에 있었던 메서드의 정보와 예외 메시지를 화면에 출력한다.
 *
 * getMessage()      : 발생한 예외 클래스의 인스턴스에 저장된 메시지를 얻을 수 있다.
 */

/**
 * 멀티 catch 블럭
 * : 내용이 같은 catch블럭들을 하나로 합친 것(JDK 1.7부터)
 *   주의 같이 |로 묶는 예외 클래스가 부모 자식 관계는 의미 없다.
 *   (왜냐하면 조상 객체만 있어도 포함되기에 이때는 조상타입 예외객체만 작성해도 됨)
 *
 * ex)
 * try {
 *     ...
 * } catch (ExceptionA e) {                          => } catch (ExceptionA | ExceptionB e) {
 *     e.printStrackTrace();     // 내용이 같음                 // 단, 묶인 예외 객체들에서 공통된 메서드만 사용 가능, 각 예외 객체의 특정 메서드는 사용 불가
 * } catch (ExceptionB e2) {                                  e.printStackTrace();
 *     e2.printStrackTrace();    // 내용이 같음           }
 * }
 */

/**
 * 예외 발생시키기
 * 1.연산자 new를 이용해서 발생시키려는 예외 클래스의 객체를 만든 다음
 *   ex) Exception e = new Exception("고의로 발생시켰음");
 *
 * 2.키워드 throw를 이용해서 예외를 발생시킨다.
 *   ex) throw e
 */

/**
 * checked 예외   : 컴파일러가 예외 처리 여부를 체크(예외 처리 필수)     = Exception 또는 그 자손 객체들
 *
 * unchecked 예외 : 컴파일러가 예외 처리 여부를 체크 안함(예외 처리 선택) = RuntimeException 또는 그 자손 객체들
 */


/**
 * 예외 처리하는 방법
 * 2.예외 선언하기(=호출한 쪽에 떠넘기기(알리기))
 *   : 하지만 결국 해결하려면 떠넘긴 곳에서 try-catch문이 있어야 해결이 된다.
 *     즉, 호출한 곳에서 try-catch문으로 해결할지
 *        발생한 곳에서 try-catch문으로 해결할지
 *
 * ex) 호출한 곳에서 try-catch문으로 해결 예시
 *     // 호출한 곳
 *     try {
 *         startInstall();
 *     } catch (SpaceException e) {
 *         // SpaceException일 때 떠넘겨진 것을 여기서 처리
 *     } catch (MemoryException e2) {
 *         // MemoryException일 때 떠넘겨진 것을 여기서 처리
 *     }
 *                     ^
 *                     |         (필수로 처리해야하는 checked 에외만 적는다.)
 *     static void startInstall() throws SpaceException, MemoryException {
 *         // 처리할 수 없으면 본인이 처리하지 않고 자신을 호출한 곳에 알려주는 것
 *         if(!enoughSpace())
 *             throw new SpaceException("설치할 공간이 부족합니다,");
 *         if(!enoughMemory())
 *             throw new MemoryException("메모리가 부족합니다,");
 *     }
 */

/**
 * finally 블럭
 * : 예외 발생여부와 관계없이 항상 수행되어야 하는 코드를 넣는다.
 *
 * ex)
 * try {
 *     // 예외 발생 가능성이 있는 코드
 * } catch (Exception1 e1) {
 *     // 예외처리를 위한 코드
 * } finally {
 *     // 예외의 발생여부에 관계없이 항상 수행되어야하는 코드들
 *     // fianlly 블럭은 try-catch문의 맨 마지막에 위치해야 한다.
 * }
 */

/**
 * 예외 되던지기
 * : 예외를 처리한 후에 다시 예외를 발생시키는 것
 *   호출한 메서드와 호출된 메서드 양쪽 모두에서 예외처리하는 것
 *   (즉, 양쪽에서 처리할 것을 서로 분배하여 처리해야하는 일이 있을 때 사용)
 *
 * ex)
 * try {
 *     method1(); // 0.호출
 * } catch(Exception e) {
 *     // 4.예외 다시 처리
 *     System.out.println("main메서드에서 예외가 처리되었습니다.");
 * }
 *
 * static void method1() throws Exception {
 *     try {
 *         throw new Exception(); // 1.예외 강제 발생
 *     } catch (Exception e) {
 *         // 2.예외 처리
 *         System.out.println("method1메서드에서 예외가 처리되었습니다.");
 *
 *         throw e; // 3.다시 예외 발생
 *     }
 * }
 */

public class EX8_Try_Catch {
    public static void main(String[] args) {
        System.out.println(1);
        System.out.println(2);

        // 고의로 예외 발생하려했지만 try catch문이 없어 Exception은 컴파일 할때 필수로 체크되는 예외라 컴파일 에러
//        throw new Exception();

        // RuntimeException은 언체크 예외라 필수로 예외 처리해야하는 것이 아니므로 컴파일되고 에러 발생
//        throw new RuntimeException();

        try {
//            고의 예외 발생법
//            1. Exception e = new Exception("고의로 예외 발생");
//            2. throw e;

            System.out.println(3);
            System.out.println(0/0);       // 예외 발생
            System.out.println(4);         // 위 예외로 실행되지 않는다.
        } catch (ArithmeticException ae) { // 위 예외와 관련된 예외 처리구문으로 이 catch문이 실행됨
            if (ae instanceof ArithmeticException)
                System.out.println("true");
            System.out.println("ArithmeticException");
            ae.printStackTrace();
            System.out.println("예외 메시지: " + ae.getMessage());
        } catch (Exception e) {
            System.out.println("Exception");
        }

        System.out.println(6);
    }
}
