package org.example.ch12;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 지네릭스란?
 * : 컴파일시 타입을 체크해 주는 기능 - JDK1.5
 *   - 장점)
 *   1.객체의 타입 안정성을 제공하고 (ClassCastException 형변환 예외를 막을 수 있게 됨)
 *   2.형변환의 번거로움을 줄여주어 코드가 간력해 진다.
 *
 * ex) Tv객체만 저장할 수 있는 ArrayList 생성
 * ArrayList<Tv> tvList = new ArrayList<Tv>(); // 원래는 ArrayList는 Object[] 배열을 가지고 있어 모든 종류의 객체가 저장 가능했었다.
 * tvList.add(new Tv());     // OK
 * tvList.add(new Audio());  // 제네릭스 덕분에 컴파일에서 에러를 잡아준다.
 */


/**
 * 타입 변수
 * : 클래스를 작성할 때 Object타입(일반 클래스) 대신 타입 변수(E)(제네릭 클래스)를 선언해서 사용
 *
 *   타입 변수는 보통 한 글자로 쓰고
 *   타입 변수는 아무거나 상관 없지만 보통 타입은 T, 요소는 E를 사용
 *
 *   객체를 생성시 타입 변수 대신 실제 객체 타입을 지정한다.(이 덕분에 형변환이 불필요해짐)
 *
 * ex) JDK1.5이전 일반 Object 클래스
 * public class ArrayList extends AbstractList {
 *     private transient Object[] elementData;
 *     public boolean add(Object o) {
 *         ...
 *     }
 *     public Object get(int index) {
 *         ...
 *     }
 *     ...
 * }
 * ex) JDK1.5이후 제네릭 클래스 활용
 * public class ArrayList<E> extends AbstractList {
 *     private transient E[] elementData;
 *     public boolean add(E o) {
 *         ...
 *     }
 *     public E get(int index) {
 *         ...
 *     }
 *     ...
 * }
 */


/**
 * 지네릭스 용어
 * Box<T>  : 지네릭 클래스, 'T의 Box' 또는 'T Box'라고 읽음
 * T       : 타입 벼ㅑㄴ수 똔느 타입 매개 변수
 * Box     : 원시 타입(= 일반 클래스)
 *
 * ex)
 * Box<String> b = new Box<String>();   // < > < >은 서로 일치해야 한다.
 * 참조변수                  생성자
 */


/**
 * 제네릭 타입과 다형성
 * : 참조 변수와 생성자의 대입된 타입은 항상 일치해야한다.
 *   즉, 상속 관계 다형성이어도 불일치한 것이므로 안된다.
 *   ex) 
 *   List<Tv> list = new ArrayList<Tv>           // O : Tv가 Product의 자손 다형성으로 참조 변수 객체와 생성자 객체가 달라도 가능
 *   ArrayList<Product> list = new ArrayList<Tv> // X : Tv가 Product의 자손이어도 타입은 다르면 안됨
 *
 *
 *   단, 매개변수의 다형성은 성립
 *   조상의 타입으로 선언했으면 메서드 사용할 때 자손 객체도 사용가능하다! (자손일 경우 형변환 필요함)
 *   ex) ArrayList<Product> list = new ArrayList<Product>
 *       list.add(new Product());
 *       list.add(new Tv());     // 자손 가능
 *       list.add(new Audio());  // 자손 가능
 */


/**
 * 지네릭스 관련 클래스들
 *
 * Iterator<E>
 * : 클래스를 작성할 때 Object타입 대신 T와 같은 타입 변수를 사용
 *   이 덕분에 읽어올 때 형변환을 했었는데 형변환할 필요가 없어짐
 *
 *
 * HashMap<K,V>
 * : 여러 개의 타입 변수가 필요한 경우, 콤마(,)를 구분자로 선언
 *
 * ex)
 * HashMap<String, Student> map = new HashMap<String, Student>   // 생성
 * map.put("자바왕", new Student("자바왕", 1, 1, 100, 100, 100));  // 데이터 저장
 *           key         value
 */

/**
 * 제한된 지네릭 클래스
 * : extends로 대입할 수 있는 타입을 제한
 *   ex)
 *   class FruitBox <T extends Fruit> { // Fruit 자손만 타입으로 지정가능
 *       ArrayList<T> list = new ArrayList<T>();
 *       ...
 *   }
 *   FruitBox <Apple> appleBox = new FruitBox<Apple>(); // Apple이 자손이면 OK
 *   FruitBox <Toy> toyBox = new FruitBox<Toy>();       // Toy는 자손이 아니면 에러
 *
 *
 *   인터페이스인 경우에도 extends를 사용 (implements가 아니다!)
 *   interface Eatable {}
 *   class FruitBox<T extends Estable> {...}
 *
 *
 * 지네릭스의 제약
 * 1.타입 변수에 대입은 인스턴스 별로 다르게 가능하다.
 *   ex)
 *   Box<Apple>< appleBox = new Box<Apple>();  // Apple 객체만 저장가능
 *   Box<Grape>< appleBox = new Box<Grape>();  // Grape 객체만 저장가능
 *
 *   즉, static 멤버에 타입 변수 사용이 불가능하다. (static은 모든 인스턴스에 공통이기에)
 *   ex)
 *   class Box<T> {
 *       static T item; // 에러
 *       static int compare(T t1, T t2) { // 에러
 *           ...
 *       }
 *       ...
 *   }
 *
 * 2.객체/배열 생성할 때 타입 변수 사용불가, 타입 변수로 배열 선언은 가능
 *   즉, new T 같은 객체생성 및 배열생성에는 불가능하다!
 *   (new 연산자 뒤에는 확정된 타입이 와야하기 때문)
 *
 *   ex)
 *   class Box<T> {
 *       T[] itemArr; // OK T타입의 배열을 위한 참조변수 (타입 변수로 배열 선언은 가능)
 *       ...
 *       T[] toArray() {
 *           T[] tmpArr = new T[itemArr.length]; // 에러 지네릭 배열 생성불가
 *                                                  new T 같은 객체생성 및 배열생성에는 불가능하다!
 *       }
 *   }
 */


/**
 * 지네릭 타입의 형변환
 * : 지네릭 타입과 원시 타입 간의 형변환은 가능하나 바람직 하지 않다.(경고 발생)
 *   즉, JDK1.5이후부터는 지네릭 클래스들에는 꼭 <>안에 타입을 대입해야한다.
 *   ex) 지네릭 타입 참조변수: Box<Object> objBox  / 원시 타입 참조변수 : Box box
 *   Box<Object> objBox = null;
 *   Box box = (Box)objBox;           // Ok 지네릭 타입 -> 원시 타입 (경고 발생)
 *   objBox = (Box<Object>) box;      // Ok 원시 타입  -> 지네릭 타입 (경고 발생)
 *
 *   objBox = (Box<Object>) strBox;   // 에러 Box<String> -> Box<Object>는 안됨!
 *   strBox = (Box<String>) objBox;   // 에러 Box<Object> -> Box<String>는 안됨!
 *
 *
 *   와일드 카드가 사용된 지네릭 타입으로는 형변환 가능
 *   Box<Object> objBox = (Box<Object>)new Box<String>();                    // 에러 형변환 불가
 *   Box<\? extends Object> wBox = (Box<\? extendsObject>)new Box<String>(); // OK
 *   Box<\? extends Object> wBox = new Box<String>();                        // 위 문장과 동일, 생성자쪽에 형변환을 생략한 것
 *
 *   // 매개변수로 FruitBox<Fruit>, FruitBox<Apple>, FruitBox<Grape> 등이 가능
 *   static Juice makeJuice(FruitBox<\? extends Fruit> box) {...}
 *
 *   FruitBox<\? extends Fruit> box = new FruitBox<Fruit>();  // OK, new연산자 앞에 (FruitBox<\? extends Fruit>)이 생략된 것
 *   FruitBox<\? extends Fruit> box = new FruitBox<Apple>();  // OK
 *
 */


/**
 * 지네릭 타입의 제거
 * : 컴파일 할 때 컴파일러는 지네릭 타입을 제거하고 그 지네릭 타입의 객체로 들어가고 필요한 곳에 형변환을 넣는다.
 *   (C#에서는 컴파일 때 지네릭 타입을 유지하여 성능에 장점을 두었지만,
 *    자바에서는 하위호환성에 더 가치를 두었기 떄문에 지네릭 타입을 제거하고 형변환을 넣는다. = 안정성 향상)
 *
 *   1.지네릭 타입의 경계(bound)를 제거                       컴파일 된 상황
 *      class Bo<T extends Fruit> {                class Box {
 *          void add(T t) {                            void add(Fruit(<T extends Fruit>라서 Fruit로 바뀜) t) {
 *              ...                        ->              ...
 *          }                                          }
 *      }                                          }
 *
 *   2.지네릭 타입 제거 후에 타입이 불일치하면 형변환을 추가      컴파일 된 상황
 *      T get(int i) {                             Fruit get(int i) {
 *          return list.get(i);           ->           return (Fruit)list.get(i);
 *      }                                          }
 *
 *   3.와일드 카드가 포함된 경우, 적절한 타입으로 형변환 추가                                  컴파일 된 상황
 *     static Juice makeJuice(FruitBox<\? extends Fruit> box?) {              static Juice makeJuice(FruitBox box) {
 *         String tmp = "";                                                       String tmp = "";
 *                                                                                Iterator it = box.getList().iterator();
 *          for(Fruit f : box.getList())                             ->           while(it.hasNext()) {
 *              tmp += f + " ";                                                       tmp += (Fruit)it.next() + " ";
 *                                                                                }
 *          return new Juice(tmp);                                                return new Juice(tmp);
 *     }                                                                       }
 */

public class EX12_Generics {
    public static void main(String[] args) {
        // 예시 1) 제네릭스의 활용하여 컴파일러 체크 가능
//        ArrayList list = new ArrayList();
        ArrayList<Object> list = new ArrayList<Object>(); // JDK1.5이후 제네릭스가 도입하여 Object여도 생략하지 말고 꼭 이렇게 써여함
        list.add(10);
        list.add(20);
        list.add("30"); // String 클래스

//        Integer i = (Integer) list.get(2); // list.get(2)에서 꺼낼 때는 Object로 인식하여
                                             // Object -> Integer로의 형변환은 컴파일할 때 문제가 없지만
        System.out.println(list);            // 해당 값의 원래 클래스가 String이었으므로 실행 후 형변환 예외 에러가 발생
        // 실행 시 에러는 프로그램이 죽으므로 컴파일 할때 에러를 발견하는 것이 더 좋다.

        // => 제네릭스를 사용하여 해결
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(10);
        list2.add(20);
        list2.add(30);
//        list2.add("30"); // String 클래스이므로 컴파일 에러

        Integer i2 = list2.get(2); // 제네릭스에서 이미 Integer만 받아오게 설정했으므로 형변환 생략 가능
        System.out.println(i2);
        System.out.println(list);


        // 예시 2)
        ArrayList<Tv> list3 = new ArrayList<Tv>(); // Tv 타입의 객체만 저장 가능하게 됨
        list3.add(new Tv());
//        list3.add(new Audio()); 제네릭 클래스를 Tv 타입만 지정했으므로 다른 객체 못넣음

        Tv t = list3.get(0);     // 기존에는 Object여서 꼭 형변환을 해줘야했는데
                                 // 하나의 객체로 통일했으므로 형변환이 불필요해짐
        System.out.println(t);


        // 예시 3) 제네릭의 다형성
        ArrayList<Product> productList = new ArrayList<Product>();
//        ArrayList<Product> productList = new ArrayList<Tv>();  에러 상속관계여도 제네릭 타입의 불일치

        List<Product> productList2 = new ArrayList<Product>(); // 객체 다형성은 가능!


        productList.add(new Tv());
        productList.add(new Audio());

        printAll(productList);

        
        // 예시 4) Iterator<E> 사용
        ArrayList<Student> list4 = new ArrayList<>();
        list4.add(new Student("자바왕", 1, 1));
        list4.add(new Student("자바짱", 1, 2));
        list4.add(new Student("홍길동", 2, 1));

        Iterator<Student> it = list4.iterator();  // 지네릭스 덕분에
        while (it.hasNext()) {
            // 지네릭스를 사용하지 않았으면 (Student)it.next(); 처럼 형변환 필요
            Student s = it.next(); // 형변환 불필요
            System.out.println(s.name);
        }
        
        
        // 예시 5) HashMap<K,V> 사용
        HashMap<String , Student> map = new HashMap<>(); // JDK1.7부터 생성자에 타입 변수는 생략가능
        map.put("자바왕", new Student("자바왕",  1, 1, 100, 100, 200));

        Student s = map.get("자바왕"); // 제네릭스 덕분에 형변환 불필요

        System.out.println(map);
        System.out.println(map.get("자바왕").eng);
        System.out.println(s.math);


        // 예시 6) 제한된 지네릭 클래스
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        FruitBox<Grape> grapeBox = new FruitBox<Grape>();
//        FruitBox<Grape> grapeBox = new FruitBox<Apple>(); 타입 불일치 에러
//        FruitBox<Toy> toyBox = new FruitBox<Toy>();  에러


        fruitBox.add(new Fruit());
        fruitBox.add(new Apple()); // 자손이라 가능
        fruitBox.add(new Grape()); // 자손이라 가능

        appleBox.add(new Apple());
//        appleBox.add(new Grape()); 타입 불일치 에러

        grapeBox.add(new Grape());

        System.out.println("fruitBox = " + fruitBox);
        System.out.println("appleBox = " + appleBox);
        System.out.println("grapeBox = " + grapeBox);


        // 예시 7) 지네릭 타입 <-> 원시 타입 형변환
        Box b = null;                  // 원시 타입
        Box<String> bStr = null;       // 지네릭 타입
        // 즉, 아래 코드와 같다.
        // Box b2 = new Box<String>(); // 원시 타입과 지네릭 타입을 섞을 수는 있지만 바람직하지 않다.
        // b2.add(100);

        b = (Box) bStr;               // Box<String> -> Box 가능은 하지만 (경고 발생)
        bStr = (Box<String>)b;        // Box         -> Box<String> 가능은 하지만 (경고 발생)


        // 예시 8) 와일드 카드로 형변환
        FruitBox<? extends Fruit> fBox = new FruitBox<>();

        // Fruit의 자손이라면
        // FruitBox<Apple> -> FruitBox<? extends Fruit> 가능
        FruitBox<? extends Fruit> aBox = new FruitBox<Apple>(); // 타입 불일치이므로 new 앞에 (<? extends Fruit>)이 들어가야 하지만 생략된 것

        // 위와 반대로 FruitBox<? extends Fruit> -> FruitBox<Apple> 가능은 하지만 경고 발생
        FruitBox<Apple> appleBox2 = (FruitBox<Apple>)aBox;  // OK, 형변환을 넣어줘야함, 경고 발생


    }

    public static void printAll(ArrayList<Product> list) { // 매개 변수의 제네릭 타입도 일치해야한다!
        for (Product p : list) {
            System.out.println(p);
        }
    }
}

class Product {
}

class Tv extends Product{
}
class Audio extends Product {
}


class Student {
    String name = "";
    int ban;
    int no;
    int kor;
    int eng;
    int math;

    public Student(String name, int ban, int no) {
        this.name = name;
        this.ban = ban;
        this.no = no;
    }

    public Student(String name, int ban, int no, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.no = no;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
}


interface Eatable {}

class Fruit implements Eatable {
    public String toString() {
        return "Fruit";
    }
}

class Apple extends Fruit {
    public String toString() {
        return "Apple";
    }
}

class Grape extends Fruit {
    public String toString() {
        return "Grape";
    }
}

class Toy {
    public String toString() {
        return "Toy";
    }
}

// Fruit이거나 Fruit 자손이면서 Eatable 인터페이스를 구현한 클래스만 들어올 수 있다.
// 클래스와 인터페이스를 같이 하려면 ,가 아닌 &이다.
class FruitBox<T extends Fruit & Eatable> extends Box<T> {

}

class Box<T> {
    ArrayList<T> list = new ArrayList<T>();
    void add(T item) {
        list.add(item);
    }

    T get(int i) {
        return list.get(i);
    }

    int size() {
        return list.size();
    }

    public String toString() {
        return list.toString();
    }
}