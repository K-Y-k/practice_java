package org.example.ch6;

/**                                0x100
 *  객체 변수 |0x100|  --> color   |     null      |
 *                       power   |     false     |
 *                       channel |       0       |
 *                               |    power()    |
 *                               |  channelUp()  |
 *                               | channelDown() |
 */
class Tv {
    String color;
    boolean power;
    int channel;

    void power() {
        power = !power;
    }

    void channelUp() {
        channel++;
    }

    void channelDown() {
        channel--;
    }
}

public class EX6_Object2 {
    public static void main(String[] args) {
        /**
         * 객체의 생성과 사용
         *
         * - 객체의 생성
         * 1.클래스명 변수명;          // 클래스의 객체를 참조하기 위한 참조변수를 선언
         * 2.변수명 = new 클래스명();  // 클래스의 객체를 생성 후, 객체의 주소를 참조변수에 저장
         *
         * => 위를 합친 방식
         *    클래스명 변수명 = new 클래스명();
         *
         * ex1)
         * Tv t;                  // Tv클래스 타입의 참조변수 t를 선언
         * t = new Tv();          // Tv인스턴스를 생성한 후, 생성된 Tv인스턴스의 주소를 t에 저장
         *
         * ex2)
         * Tv t = new Tv();
         *
         *
         * - 객체 사용
         * t.chanel = 7;         // 변수 사용 예시로 Tv인스턴스의 멤버변수 channel의 값을 7로 한다.
         * t.channelDown();      // 기능 사용 예시로 Tv인스턴스의 메서드 channelDown()을 호출한다.
         */
        Tv t1 = new Tv();
        t1.channel = 7;
        t1.channelDown();

        Tv t2;
        t2 = new Tv();
        System.out.println("t1의 현재 채널은 " + t1.channel +" 입니다.");
        System.out.println("t2의 현재 채널은 " + t2.channel +" 입니다.");

        // 위 t1과 t2는 다른 별도의 객체이다.
        t2 = t1; // 이렇게 하게되면 t2의 참조변수가 t1의 참조변수가 되어
                 // 이제 t2의 값을 부를 때 t1의 값들이 되고 t2의 기존 참조변수의 값들을 사용 못하게 된다.
                 // 이렇게 사용 못하는 메모리는 GC(가비지 컬렉터)에서 주기적으로 불필요한 메모리를 확인하고 제거한다.
                 // 자바에서는 GC 덕분에 우리가 따로 메모리 정리를 하지 않는 장점이 있다.

        System.out.println("t2의 현재 채널은 " + t2.channel +" 입니다.");
        // 즉, 여러 객체가 같은 참조변수를 가리킬 수 있지만
        // ex) t1 0x100 ->
        //     t2 0x100 ->

        // 한 객체에 여러 참조 변수는 들어갈 수 없다.
        // ex) t1 0x100, 0x200 같이 못함!
    }
}

