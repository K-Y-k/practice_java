package org.example.ch9;

/**
 * Object 클래스
 * : 모든 클래스의 최고 조상이다.
 *   오직 11개의 메서드만을 가지고 있다.
 *
 *   Object 클래스의 메서드 종류                                  설명
 *  protected Object clone()                   객체 자신의 복사본을 반환한다.
 *
 *  public boolean equals(Object obj)          객체 자신과 obj가 같은 객체인지 알려준다.
 *
 *  protected void finalize()                  객체가 소멸될 때 GC에 의해 자동적으로 호출된다.
 *                                             이때 수행되어야하는 코드가 있을 때 오버라이딩한다. (거의 사용안함)
 *
 *  public Class getClass()                    객체 자신의 클래스 정보를 담고 있는 Class인스턴스를 반환한다.
 *
 *  public int hashCode()                      객체 자신의 해시코드를 반환한다.
 *
 *  public String toString                     객체 자신의 정보를 문자열로 반환한다.
 *
 *  pubic void notify()                        객체 자신을 사용하려고 기다리는 쓰레드를 하나만 깨운다.
 *
 *  public void notifyAll()                    객체 자신을 사용하려고 기다리는 모든 쓰레드를 깨운다.
 *
 *  public void wait()                         다른 쓰레드가 notify()나 notifyAll()을 호출할 때까지 현재 쓰레드를 무한히 또는 지정된 시간(timeout, nanos)동안 기다리게 한다.
 *  public void wait(long timeout)
 *  public void wait(long timeout, int nanos)
 *
 */


/**
 * equals(Object obj
 * : 객체 자신(this)과 주어진 객체(obj)를 비교한다. 같으면 true 다르면 false
 *
 *   Object 클래스의 equals()는 객체의 주소를 비교한다. (참조변수 값 비교)
 *   public boolean equals(Object obj) {
 *       return (this==obj);  // 주소를 비교한다. 주소가 같아야 true
 *   }
 *
 *   보통 객체 클래스를 생성하면 iv 값으로 비교하게 오버라이딩을 한다.
 */
class Value {
    int value;

    Value(int value) {
        this.value = value;
    }

    public boolean equals(Object obj) {
        // 참조변수의 형변환 전에는 무조건 instanceof로 확인해야함
        if (!(obj instanceof Value))
            return false;

        Value v = (Value) obj;        // Object인 obj를 Value로 형변환
        return this.value == v.value; // 주소비교 -> iv를 비교로 바꿈
    }
}

public class EX9_Object {
    public static void main(String[] args) {
        Value v1 = new Value(10);
        Value v2 = new Value(10);

        // 오버라이딩 전에는 객체의 주소를 비교하므로 다르다고 나오지만,
        // 오버라이딩하여 주소가 아닌 iv 값을 비교하기에 같다고 나온다.
        if (v1.equals(v2))
            System.out.println("same 같습니다.");
        else
            System.out.println("different 다릅니다.");

    }
}
