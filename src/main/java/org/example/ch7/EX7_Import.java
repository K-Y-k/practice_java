package org.example.ch7;

/**
 * import문
 * : 클래스를 사용할 때 패키지이름을 생략할 수 있다.
 *   ex) java.util.Date today = new java.util.Date();
 *       => import java.util.Date;를 하면
 *           Date today = new Date();
 *
 *   java.lang 패키지의 클래스는 기본 패키지로 import하지 않고도 사용할 수 있다.
 *   ex) String, Object, System, Thread ...
 *
 *   import문은 컴파일 시에 처리되므로 *로 모든 클래스 넣는다고 성능에 영향이 가는 것이 아니다!
 */

/**
 * import문의 선언
 * : 패키지문과 클래스 선언의 사이에서 선언해야 한다.
 *
 * - 단일 클래스 import 방법
 * import 패키지명.클래스명;
 *
 * - 해당 패키지의 모든 클래스 import 방법
 * import 패키지명.*;
 */

/**
 * static import문
 * : static 멤버를 사용할 때 클래스 이름을 생략할 수 있게 해준다.
 *   코드양이 많아질 때 사용될 순 있으나 식별하기 힘들어질 수 있으므로 꼭 필요할 때만 사용한다.
 */

import java.util.Calendar;
import static java.lang.System.out;
import static java.lang.Math.random;

public class EX7_Import {
    public static void main(String[] args) {
//        System.out.println(Math.random());
        out.println(random()); // static import로 사룡하여 클래스 이름을 생략시켰다.
    }
}
