package org.example.ch12;


import java.util.ArrayList;

/**
 * 와일드 카드 <\?>
 * : 하나의 참조 변수로 대입된 타입이 다른 객체를 잠조 가능
 *   ex)
 *   ArrayList<\? extends Product> list =  new ArrayList<Tv>();  // OK
 *   ArrayList<\? extends Product> list =  new ArrayList<Audio>();  // OK
 *   ArrayList<Product> list =  new ArrayList<Tv>();  // 에러 타입 불일치
 *
 * - 와일드 카드 종류
 * <\? extends T>  : 와일들 카드의 상한 제한, T와 그 자손들만 가능  (주로 사용)
 * <\? super T>    : 와일들 카드의 하한 제한, T와 그 조상들만 가능
 * <\?>            : 제한 없음, 모든 타입이 가능 <\? extends Object>와 동일
 *
 * 메서드의 매개변수에도 와일드 카드를 사용 가능하다.
 * ex)
 * static Juice makeJuice(FruitBox<\? extends Fruit> box?) {
 *     String tmp = "";
 *
 *     for(Fruit f : box.getList())
 *         tmp += f + " ";
 *
 *     return new Juice(tmp);
 * }
 *
 * // 만약 위 매개변수가 FruitBox<Fruit>였으면
 * Juicer.makeJuice(new FruitBox<Fruit>()); // 일치하는 이것만 되었을 텐데
 * Juicer.makeJuice(new FruitBox<Apple>()); // 와일드 카드로 이것도 가능해짐
 *
 */


/**
 * 지네릭 메서드
 * : 지네릭 타입이 선언된 메서드(타입 변수는 메서드 내에서만 유효)
 *   ex)
 *   static <T> void sort(List<T> list, comparator<\? super T> c)
 *
 *   클래스의 타입 매개변수<T>와 메서드의 타입 매개변수 <T>는 서로 별개이다.
 *   (iv와 lv와 같은 개념)
 *   ex)
 *   class FruitBox<T> { // 타입 문자가 서로 일치해보이지만 서로 다른 타입 변수!
 *       ...
 *       static <T> void sort(List<T> list, comparator<\? super T> c) {
 *           ...
 *       }
 *   }
 *
 *   메서드를 호출할 때마다 타입을 대입한다.(대부분 생략 가능)
 *   단, 생략할 수 없을 때는 생략하면 안됨
 *   ex) this.<Fruit>makeJuice(fruitBox);
 */


/**
 * 와일드 카드 vs 지네릭 메서드
 * : 와일드 카드는 하나의 참조변수로 서로 다른 타입이 대입된 여러 지네릭 객체를 다루기 위한것이고
 *   지네릭 메서드는 메서드를 호출할 때마다 다른 지네릭 타입을 대입할 수 있게 한 것
 *
 *   즉, 와일드 카드가 잘 안될 때 지네릭 메서드를 사용한다.
 */

public class EX12_WildCard {
    public static void main(String[] args) {
        FruitBox2<Fruit2> fruitBox = new FruitBox2<Fruit2>();

        FruitBox2<Apple2> appleBox = new FruitBox2<Apple2>();
        
//        하나의 참조변수에 모두 담을 수 있게된다.
//        FruitBox2<? extends Fruit2> appleBox = new FruitBox2<Apple2>();
//        appleBox = new FruitBox2<Fruit2>();
//        appleBox = new FruitBox2<Apple2>();
//        appleBox = new FruitBox2<Grape2>();

        fruitBox.add(new Apple2());
        fruitBox.add(new Grape2());

        appleBox.add(new Apple2());
        appleBox.add(new Apple2());

        System.out.println(Juicer.makeJuice(fruitBox));
        System.out.println(Juicer.makeJuice(appleBox));
    }

}

class Fruit2 {
    public String toString() {
        return "Fruit";
    }
}
class Apple2 extends Fruit2 {
    public String toString() {
        return "Apple";
    }
}
class Grape2 extends Fruit2  {
    public String toString() {
        return "Grape";
    }
}

class Juice {
    String name;

    public Juice(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

class Juicer {
    static Juice makeJuice(FruitBox2<? extends Fruit2> box) {
        String tmp = "";

        for (Fruit2 f : box.getList()) {
            tmp += f + " ";
        }

        return new Juice(tmp);
    }
}


class FruitBox2<T extends Fruit2> extends Box2<T> {

}

class Box2<T> {
    ArrayList<T> list = new ArrayList<T>();
    void add(T item) {
        list.add(item);
    }

    T get(int i) {
        return list.get(i);
    }

    ArrayList<T> getList() {
        return list;
    }

    int size() {
        return list.size();
    }

    public String toString() {
        return list.toString();
    }
}