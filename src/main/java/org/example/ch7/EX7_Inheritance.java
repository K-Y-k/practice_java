package org.example.ch7;

/**
 * 상속
 * : 기존의 클뢔스를 이용해서 새로운 클래스를 작성하는 것 (코드의 재사용)
 *   두 클래스를 부모와 자식으로 관계를 맺어주는 것
 *   자손은 조상(부모뿐만이 아닌 부모의 부모들 포함)의 모든 멤버를 상속받는다.(생성자, 초기화 블럭 제외)
 *   자손의 멤버 개수는 조상보다 적을 수 없다.(같거나 많다.)
 *
 *   자바는 단일 상속만 허용한다. 
 *   (하나의 부모만 허용,
 *     C++은 다중 상속 가능 하지만 각 부모의 같은 메서드명이 있으면 충돌위험이 있기에 자바는 다중 상속 포기한 것)
 *  
 *   다중 상속처럼 사용하려면
 *   비중이 제일 높은 것을 부모로 상속하고 다른 것은 포함 관계로 표현
 *  
 *   class 자식클래스 extends 부모클래스 [
 *   }
 */

/**
 * Object 클래스 - 모든 클래스의 조상
 * : 부모가 없는 클래스는 자동적으로 Object 클래스를 상속받게 된다.
 *   모든 클래스는 Object 클래스에 정의된 11개의 메서드를 상속받는다.
 *   (ex) toString(), equals(Object obj), hashCode(), ...)
 */
class Tv { // 부모 멤버 총 5개  // 컴파일러가 자동으로 extends Object 클래스를 상속하게 추가한다.
    boolean power;
    int channel;

    void power() {
        power = !power;
    }
    void channelUp() {
        ++channel;
    }
    void channelDown() {
        --channel;
    }

}
class SmartTv extends Tv { // 부모 멤버 5개 + 자식 멤버 2개 = 7개
    // 부모의 멤버 변수들 사용 가능하고
    // 부모 클래스에서의 변경이 영향이 간다.
    boolean caption;

    void displayCaption(String text) { // 부모에서는 사용 불가능
        if (caption) {
            System.out.println(text);
        }
    }
}

public class EX7_Inheritance {
    public static void main(String[] args) {
        SmartTv smartTv = new SmartTv();
        smartTv.channel = 10; // 부모 클래스로부터 상속받은 멤버
        smartTv.channelUp();  // 부모 클래스로부터 상속받은 멤버
        System.out.println(smartTv.channel);

        smartTv.displayCaption("Hello, world");
        smartTv.caption = true;
        smartTv.displayCaption("Hello, world");

        System.out.println(smartTv.toString()); // Object 클래스의 메서드 사용하여 객체의 주소값을 나타내준다.
    }
}
