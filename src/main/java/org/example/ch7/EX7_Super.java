package org.example.ch7;


/**
 * 참조변수 super
 * : 객체 자신을 가리키는 참조 변수로 인스턴스 메서드(생성자)내에서만 존재한다.(즉, static 클래스 메서드 내에서는 사용 불가)
 *   조상의 멤버를 자신의 멤버와 구별할 때 사용한다.
 */

/**
 * super() - 조상의 생성자
 * : 조상의 생성자를 호출할 때 사용한다.
 *   조상의 멤버는 조상의 생성자를 호출해서 초기화한다.
 *
 * - 조건
 * 생성자의 첫 줄에는 반드시 생성자를 호출해야 한다.
 * 그렇지 않으면 컴파일러가 생성자의 첫 줄에 super();를 삽입한다.
 */
class Parent {
    int x = 10; // super.x
    int y;

    Parent() {
    }

    Parent(int x, int y) {
        // super();  // 컴파일러에서 자동으로 Object 클래스의 생성자를 호출해준 것이었다.
        this.x = x;
        this.y = y;
    }
}

class Child extends Parent {
    // 만약 자식에 같은 중복되는 iv가 없었다면 this, super 둘 다 같은 값이 나온다.
    int x = 20; // this.x
    int z;

    Child() {
    }

    Child(int x, int y, int z) {
        super(x, y);   // 조상의 iv이므로 super()로 조상의 생성자를 초기화해야한다.
                       // 해당 방법 말고도 this.x = x; this.y = y;일 때 해당 조상 클래스에 기본 생성자가 있으면 가능하다.
                       // 왜냐하면 기본적으로 super();로 조상 클래스의 기본생성자를 불러와주기 때문이다.
        this.z = z;
    }

    void method() {
        System.out.println("x = " + x);
        System.out.println("this.x = " + this.x);
        System.out.println("super.x = " + super.x);
    }
}

public class EX7_Super {

    public static void main(String[] args) {
        Child c = new Child();
        c.method();
    }
}
