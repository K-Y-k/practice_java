package org.example.ch7;


class Product {
    int price;
    int bonusPoint;

    Product(int price) {
        this.price = price;
        bonusPoint = (int)(price/10.0);
    }
}

class Tv1 extends Product {
    Tv1() {
        super(100);  // super()는 조상의 생성자를 생성하는 것
                           // Tv 가격을 100만원으로 고정
    }
    public String toString() {
        return "Tv";
    }
}
class Computer extends Product {
    Computer() {
        super(200);  // 컴퓨터의 가격을 200만원으로 고정
    }
    public String toString() {
        return "Computer";
    }
}

class Buyer {
    int money = 1000;
    int bonusPoint = 0;

    Product[] cart = new Product[10]; // 다형성으로 구입할 자손 물건들을 하나의 배열에 담을 수 있다.

    int i = 0;

    void buy(Product p) {
        if (money < p.price) {
            System.out.println("잔액이 부족하여 물건을 살 수 없습니다.");
            return;
        }

        money -= p.price;            // 가진 돈에서 구입한 제품의 가격을 뺀다.
        bonusPoint += p.bonusPoint;  // 제품의 보너스 점수를 추가한다.
        cart[i++] = p;               // 종류가 다른 자식 객체들을 하나의 배열에 담음
        
//        System.out.println(p.toString() + "을/를 구입하셨습니다.");
        System.out.println(p + "을/를 구입하셨습니다."); // p +연산자로 p.toString()와 같다.
    }

    void summary() {
        int sum = 0;
        String itemList = "";

        for (int i = 0; i < cart.length; i++) {
            if (cart[i] == null) // 배열은 연속되어있기에 비어있으면 끝인 것이므로 탈출
                break;
            sum += cart[i].price;
            itemList += cart[i].toString() + ", ";
        }
        System.out.println("구입하신 뭎룸의 총금액은 " + sum + "만원입니다.");
        System.out.println("구입하신 제품은 " + itemList + "입니다.");
    }
}

public class EX7_Polymorphism {
    public static void main(String[] args) {
        /**
         * 다형성
         * : 여러가지 형태를 가질 수 있는 능력
         *   조상 타입 참조 변수로 자손 타입 객체를 다루는 것 (이거로 외우기)
         *   (단, 1.조상 타입의 인스턴스 변수 및 메서드만 사용 가능, 자식에만 있는 것은 사용불가)\
         *   (    2.자식 타입 참조 변수로 부모 타입 객체를 다룰 수 없다.)
         *   ex)  참조변수 타입           인스턴스 타입(객체)
         *           Tv       t = new   SmartTv();  // 부모 참조변수 타입 -> 자식 인스턴스 타입은 허용
         *        SmartTv     t = new      Tv();    // 자식 참조변수 타입 -> 부모 인스턴스 타입은 불가능
         *      
         *
         * - 타입이 불일치하는 것에 대한 다형성의 장점
         * 1.다형적 매개변수
         *   : 참조형 매개변수는 메서드 호출시, 자신과 같은 타입 또는 자손 타입의 인스턴스를 넘겨줄 수 있다.
         *     ex) 부모 - Product / 자식 - Tv, Computer가 있으면
         *         종류가 많으면 void buy(Tv t)  void buy(Computer c) 이렇게 오버로딩해야하는 것이 많아지므로
         *         void buy(Product p) 하나로 통일 시킬 수 있게된다.
         * 
         * 2.하나의 배열로 여러 종류 객체를 저장하여 다루기 가능
         *   ex) Product p1 = new Tv1();
         *       Product p2 = new Computer(); 이렇게 일일히 생성해야했던 것을
         *
         *       Product[] p_array = new Product[2];
         *       p_array[0] = new Tv1();
         *       p_array[1] = new Computer(); 이렇게 하나의 배열에 담을 수 있게 된다.
         *
         */
        // 장점 1. 다형적 매개변수
        Buyer b = new Buyer();

//        b.buy(new Tv1());       // void buy(Product p)
        Product p = new Tv1();    // b.buy(new Tv1());와 같다.
        b.buy(p);                 // b.buy(new Tv1());는 참조변수가 필요 없을 때라 저렇게 가능했던 것이고 참조변수가 필요할 상황이 있으면 이 방식으로하기

        b.buy(new Computer());  // void buy(Product p)

        System.out.println("현재 남은 돈 = " + b.money + "만원");
        System.out.println("현재 보너스 점수 = " + b.bonusPoint + "점");

        System.out.println();

        // 장점 2. 하나의 배열로 여러 종류 객체 저장
        b.summary();


        /**
         * 참조변수의 형변환
         * : 사용할 수 있는 멤버의 갯수를 조절하는 것
         *   (기본형 형변환처럼 실제 값이 변하는 것이 아님)
         *
         *   조상 자손 관계의 참조변수는 서로 형변환이 가능하다.
         *   => 즉 각 멤버의 개수를 늘렸다 줄였다하려고 사용하는 것
         *      참조변수를 변경함으로써 사용할 수 있는 멤버의 개수를 조절하기 위해서 하는 것
         *   ex)
         *      SmartTv s = new SmartTv();
         *      Tv t = (Tv)s;               // 자손->조상 타입으로 형반환 가능
         *      SmartTv s2 = (SmartTv)t     // 부모->자손 타입으로 형변환 가능 
         *      Ambulance a = (Ambulance)s; // 상속관계가 아닌 클래스 간의 형변환 불가능
         *
         *      t  |0x100| 사용가능 멤버 수 2개   -> | 부모멤버 |
         *      s  |0x100| 사용가능 멤버 수 3개   -> | 부모멤버 |
         *      s2 |0x100| 사용가능 멤버 수 3개   -> | 자식멤버 |
         *
         *   단, 부모 -> 자식으로 형변환 한 것은
         *      이미 부모의 멤버로만 사용할 수 있는 참조변수이므로 자식에만 있는 멤버를 호출할 수 없다!
         *      ex)
         *      t.smartTvMethod             // 부모->자손 타입으로 형변환한 것이므로 자손 메서드 사용 불가능
         *      s2.smartTvMethod            // 부모->자손 형변환한 것을 자손으로 다시 형변환한 것을 새로운 자식 객체의 참조변수에 넣으면
         *                                     새로운 자식 참조변수가 자식 객체를 가리킨 것이므로 자식의 메서드가 사용가능한 것이다!
         *
         *  => 즉, 참조변수가 처음 가리킨 객체가 무엇인지가 중요하다.
         *     CASE1.조상->자손으로 형변환할 때 자손 객체이면
         *           조상->자손으로 형변환한 참조변수 해당한 것은 조상 멤버밖에 사용 못하기 때문이다.
         *     CASE2.처음 가리킨 객체가 조상인데
         *           참조변수를 자식 객체로 형변환한다고 처음 실제 가리킨 조상 객체에서 자식 메서드를 사용할 수가 없다.
         *
         *     ex) SmartTv s = new SmartTv(); (처음 부분 객체 생성한 것이 조상인지 자식인지에 따라 중요함!)
         */

    }
}
