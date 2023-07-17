package org.example.ch8;

/**
 * 프로그램 오류
 * - 컴파일 에러 : 컴파일 할 때 발생하는 에러 (javac.exe가 자바 컴파일러)
 *                1)구문체크 2)번역 3)최적화
 * - 런타임 에러 : 실행할 때 발생하는 에러 = 프로그램 종료
 * - 논리적 에러 : 작성 의도와 다르게 동작 = 프로그램 종료는 아니고 그 일부만 의도와 다르게 작동함
 *
 *
 * Java의 런타임 에러
 * - 에러(error)     : 프로그램 코드에 의해서 수습될 수 없는 심각한 오류
 * - 예와(exception) : 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류
 *
 * => 에러는 어쩔 수 없지만, 예외는 처리하자!
 *
 * 예외처리의 정의
 * : 프로그램 실행 시 발생할 수 있는 예외의 발생에 대비한 코드를 작성하는 것
 * 
 * 예외처리의 목적
 * : 프로그램의 비정상 종료를 막고 정상적인 실행상태를 유지하는 것
 *
 * 예외 클래스의 계층 구조
 *    Object - Throwable - Exceptiopn(체크드 예외(필수처리)) - IOException(입출력 문제) (체크드 예외(필수처리))
 *                                                        - ClassNotFoundException(클래스가 존재 X) (체크드 예외(필수처리))
 *                                                        - ...
 *                                                        - RuntimeException(언체크드 예외(선택처리))  -  ArithmeticException(산술계산문제), ClassCastException(형변환문제), NullPointerException(null발생), IndexOutOfBoundsException(배열범위벗어남) ...
 *  *
 *                       - Error(심각한 오류)               - OutOfMemoryError
 *                                                        - ...
 *
 *  Exception 클래스들                     : 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외
 *  RuntimeException클래스들 + 자손 클래스들 : 프로그래머의 실수로 발생하는 예외
 */

public class EX8_Error {
    public static void main(String[] args) {
//        system.out.println(args[0]); // 이러한 오타는 기본적으로 개발 IDE에서 체크해준다.
        System.out.println(args[0]);   // main의 String[] args에 넘어온 값이 없기 때문에 실행중 발생하는 런타임 에러 발생

    }
}
