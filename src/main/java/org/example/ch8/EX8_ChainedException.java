package org.example.ch8;

/**
 * 연결된 예외
 * : 한 예외가 다른 예외를 발생시킬 수 있다.
 *   예외 A가 예외 B를 발생시키면 A는 B의 원인 예외(cause exception)이다.
 *
 * (Throwable은 Excepion과 Error의 조상)
 *
 * Throwable initCause(Throwable cause) : () 매개변수에는 지정한 예외를 원인 예외로 등록
 * Throwable getCause()                 : 원인 예외를 반환
 */

/**
 * Throwable 클래스의 구조 파악해보기
 * //    여기서 Throwable은 Excepion클래스임
 * public class Throwable implements Serializable {
 *     // ...
 *     private Throwable cause = this; // 객체 자신(this)을 원인 예외로 등록
 *                                     // 즉 하나의 예외 안에 다른 예외도 포함시킨 것이다.
 *
 *     // ...
 *
 *     public synchronized Throwable initCause(Throwable cause) {
 *         // ...
 *         this.cause = cause;
 *         return this;
 *     }
 *
 *     // ...
 * }
 */

/**
 * ex) 원인 에외인 A예외(SpaceException)를 B예외(InstallException)에 원인 에외로 포함 시키기
 *     = 연결된 예외
 *
 * void install() throws InstallException {  // 5.최종적으로 발생하는 예외가 InstallException이므로
 *     try {
 *         startInstall();     // 1.SpaceException 발생
 *         copyFiles();
 *     } catch (SpaceException e) { //
 *         InstallException ie = new InstallException("설치중 예외발생"); // 2.예외 생성
 *         ie.initCause(e); // 3.SpaceException 때문에 InstallException이 생성되었으므로
 *                               InstallException의 원인 예외를 SpaceException으로 지정
 *         throw ie;        // 4.InstallException을 발생시킨다.
 *     } 
 *     ...
 * }
 */

/**
 * 연결된 예외를 사용하는 이유
 * 1.여러 예외를 하나로 묶어서 다룰 때 사용
 *   ex)
 *   try {
 *       install();
 *   } catch(InstallException e) {
 *     // SpaceException, MemoryException, ... 등 많은 예외가 있어 cathc블럭이 많아질 경우
 *     // 이 예외 하나로 묶어서 작성하기 편리하게 함
 *   } catch(Exception e) {
 *
 *   }
 *   void install() throws InstallException {  // 5.최종적으로 발생하는 예외가 InstallException이므로
 *       try {
 *           startInstall();     // 1.SpaceException 발생
 *           copyFiles();
 *       } catch (SpaceException e) { //
 *           InstallException ie = new InstallException("설치중 예외발생"); // 2.예외 생성
 *           ie.initCause(e); // 3.SpaceException 때문에 InstallException이 생성되었으므로
 *                               InstallException의 원인 예외를 SpaceException으로 지정
 *           throw ie;        // 4.InstallException을 발생시킨다.
 *       } catch (MemoryException e) {
 *       ...
 *
 * 2.checked 예외를 unchecked 예외로 변경할 때 사용한다.
 *   (알고보니 try-catch문으로 필수 처리하지 않아도 될 때 사용)
 *   ex)
 *   static void startInstall() throws SpaceException { // 원래 ,MemoryException도 있었지만 RuntimeException으로 바꾸어 필수로 넣지 않아도 된다.
 *       if (!enoughSpace())
 *           throw new SpaceException("설치할 공간이 부족합니다.");
 *
 *       if (!enoughMemory())
 *           // checked 예외였던 MemoryException을 unchecked 예외로 변경
 *           // 원인 예외로 등록한 것
 *           throw new RuntimeException(new MemoryException("메모리가 부족합니다."));
 *   }
 */
public class EX8_ChainedException {
    public static void main(String[] args) {

    }
}