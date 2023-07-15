package org.example.ch7;

/**
 * 접근 제어자를 사용하는 이유
 * : 1.외부로부터 데이터를 보호하기 위해서
 *   ex) 객체 클래스의 인스턴스 변수는 private로 외부 접근을 막고
 *                   메서드는 public으로 하여 여기서 값 적용을 하는 간접접근 방식을 해야한다.
 *   2.외부에 불필요하게 노출하지 않고 내부적으로만 사용하기 위해 감추는 것
 *
 * 캡슐화
 * : 직접 접근을 막고 메서드를 통한 간접 접근을 통해 데이터를 보호하는 것
 */
class Time {
    private int hour;   // 직접 접근하지 못하게 private
    private int minute;
    private int second;


    // public 메서드를 활용하여 간접 접근하게 한다.
    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        if(hour < 0 || hour > 23)
            return;

        this.hour = hour;
    }

}

public class EX7_Capcule {
    public static void main(String[] args) {
        Time t = new Time();
//        t.hour = 100;                 // private로 인해 직접 접근이 안된다.
        t.setHour(21);                  // public 메서드로 간접 접근으로 적용
        System.out.println(t.getHour());
    }
}
