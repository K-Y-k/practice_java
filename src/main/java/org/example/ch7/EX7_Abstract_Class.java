package org.example.ch7;

/**
 * 추상 클래스
 * : 미완성 설계도이다. 즉, 인스턴스(객체) 생성이 불가능하다.
 *                   ex) Player p = new Player(); // 추상 클래스의 인스턴스 생성 못하여 에러
 *   미완성 메서드(추상 메서드)를 갖고 있는 클래스를 뜻한다.
 *
 */

/**
 * - 추상 클래스의 작성
 * : 여러 클래스에 공통적으로 사용될 수 있는 추상 클래스를 바로 작성하거나
 *   기존 클래스의 공통 부분을 뽑아서 추상 클래스를 만든다.
 */

/**
 * - 추상 클래스를 활용한 장점
 * 1.중복되는 공통 기능을 추상 클래스에 넣었으므로
 *   여러 자손 클래스를 작성하기 편해진다.
 *
 * 2.공통된 메서드를 변경할 때 추상 클래스만 변경하면 되므로
 *   코드 변경이 용이하여 유지보수가 쉬워진다.
 *
 * 3.단계별로 추상 클래스 -> 자손 추상 클래스 -> 자손 클래스와 같은 의미있는 단계로 만들면
 *   필요한 단계에 선택해서 상속하여 완성하기 쉽다.
 *
 *     (상반관계)
 * 추상화 <-> 구체화
 * 추상화는 불명확하므로 구체화된 코드보다 유연하여 변경에 유리한 것이다!
 *
 * ex)
 * GregorianCalendar cal = new GregorianCalendar(); // 구체적
 * Calendar cal = Calendar.getInstance();           // 추상적
 *
 * class Calendar {
 *     ...
 *
 *     public static Calendar getInstance(Locale aLocale) {
 *         return createCalendar(Timezone.getDefault(), aLocale);
 *     }
 *     private static Calendar createCalendar(Timezone zone, Locale aLocale) {
 *         ...
 *         if caltype != null {   // 이렇게 코드를 유연하게 갈아 낄 수가 있어진다!
 *             switch (caltype) {
 *             case "buddhist":
 *                  cal = new BuddhistCalendar(zone, aLocale);
 *                  break;
 *             case "japanese":
 *                  cal = new JapaneseCalendar(zone, aLocale);
 *                  break;
 *             ...
 *         }
 *     }
 * }
 *
 */
abstract class Player { // 추상 클래스(미완성 클래스)
    boolean pause;
    int currentPos;

    Player() {
        pause = false;
        currentPos = 0;
    }

    abstract void move(int x, int y);

    abstract void play(int pos); // 추상 메서드(구현부(몸통 {})이 없는 미완성 메서드)
    abstract void stop();        // 추상 메서드가 있기에 추상 클래스인 것

    void play() {         // 인스턴스 메서드
        play(currentPos); // 추상 메서드(여기서는 abstract void play)를 호출할 수 있다.
                          // 추상 클래스 상태일 때는 사용 못하겠지만
                          // 자손이 추상 메서드를 완성 시켰을 때는 호출 가능하다.
    }
}

/**
 * 상속을 통해 추상 메서드를 완성해야 인스턴스 생성이 가능하다!
 * AudioPlayer ap = new AudioPlayer(); 가능해짐
 * Player ap = new AudioPlayer();      조상으로 다형성이라서 가능
 */
class AudioPlayer extends Player {

    // 추상 메서드에서 몸통을 만들어서 오버라이딩
    // 추상 클래스의 추상 메서드를 모두 구현해주어야 한다.
    // 하나라도 구현을 안하면 여전히 추상 클래스가 된다.
    // 즉, 공통된 필수 기능을 추상 클래스의 추상 메서드를 만들어 놓는다.
    @Override
    void move(int x, int y) {
        System.out.println("Audio move [x=" + x + ", y=" + y +"]");
    }

    @Override
     void play(int pos) {
        // 각 play하는 방식의 코드가 다 다르기에 이러한 방법을 사용한 것
        System.out.println(pos + "위치부터 play합니다.");
    }

    @Override
    void stop() {
        System.out.println("재생을 멈춥니다.");
    }
}
class AppPlayer extends Player {

    @Override
    void move(int x, int y) {
        System.out.println("App move [x=" + x + ", y=" + y +"]");
    }

    @Override
     void play(int pos) {
        System.out.println(pos + "위치부터 play합니다.");
    }

    @Override
    void stop() {
        System.out.println("재생을 멈춥니다.");
    }
}

public class EX7_Abstract_Class {
    public static void main(String[] args) {
//        Player p = new Player();          // 추상 클래스의 객체는 생성 불가능
        AudioPlayer ap = new AudioPlayer(); // 추상 클래스를 완성시킨 자손 객체 생성 가능
        Player ap2 = new AudioPlayer();     // 다형성으로 이미 AudioPlayer로 생성된 객체를 가리키기에 정상 작동한다.
                                            // 단, 참조변수가 Player이므로 Player의 멤버만 사용가능
        ap.play(100);
        ap.stop();

        System.out.println();

        // 추상화한 것을 묶어서
        Player[] group = {new AudioPlayer(), new AppPlayer()};

        // 공통된 기능을 한번에 반복문으로 실행하고 있다.
        for (int i = 0; i < group.length; i++) {
            group[i].move(100, 200);
            group[i].play(i);
            group[i].stop();
        }

    }
}
