package org.example.ch8;

/**
 * 사용자 정의 예외 만들기
 * : 우리가 직접 예외 클래스를 정의할 수 있다.
 *   조상은 Exception과 RuntimeException중에서 선택한다.
 *   (필수 처리를 해야할 경우만 Exception 그외는 RuntimeException으로 선택하자)
 *
 */
class MyException extends Exception {  // extends Exception이므로 필수 처리해야 하는 예외
    MyException(String msg) { // 메시지를 전달하는 매개변수로 String msg가 있어야한다.
        super(msg);           // 조상인 예외 클래스의 생성자를 호출한다.
    }
}


public class EX8_MyException {
    public static void main(String[] args) {

    }
}
