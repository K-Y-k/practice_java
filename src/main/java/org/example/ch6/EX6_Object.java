package org.example.ch6;

public class EX6_Object {

    /**
     * 객체지향 언어 = 프로그래밍 언어 + 객체지향개념(규칙)
     *
     * - 배경)
     * 80년대에 빠른 변화를 소프트웨어가 따라 잡지 못하여
     * 해결책으로 객체지향 언어를 도입(절차적 -> 객체지향)
     *
     * - 장점)
     * 코드의 재사용성을 높이고 유지보수가 용이하다. 중복 코드를 제거할 수 있다.
     *
     * - 특징) OOP(Object oriented programming)
     * 1.캡슐화
     * 2.상속
     * 3.추상화
     * 4.다형성
     */
    public static void main(String[] args) {
        /**
         * 클래스와 객체
         * 
         * - 클래스 정의
         * 객체를 정의해 놓은 것
         * 
         * - 클래스 용도 및 필요성
         * 객체를 생성하는데 사용하기 위해 필요
         *
         * - 객체 정의
         * 실제로 존재하는 것, 사물 또는 개념
         *
         * - 객체 용도 및 필요성
         * 객체가 가진 속성(변수)과 기능(메서드)을 사용하기 위해 필요
         *
         * => 즉, 클래스는 제품 설계도
         *         객체는 제품
         */

        /**
         * 객체의 구성요소 - 속성과 기능
         * 객체 = 속성(변수) + 기능(메서드)
         *
         * ex) 하드 웨어(HW) TV를 소프트웨어(SW) TV 객체로 표현
         * 속성(변수)   : 크기, 길이, 높이 ,색상, 볼륨, 채널 등
         * 기능(메서드) : 켜기, 끄기, 볼륨 높이기, 볼륨 낮추기, 채널 변경하기 등
         */
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


        /**
         *  객체와 인스턴스 (서로 거의 같은 말)
         *  - 객체    : 모든 인스턴스를 대표하는 일반적 용어
         *  - 인스턴스 : 특정 클래스로부터 생성된 객체 (ex) Tv인스턴스)
         *  
         *  설계도    인스턴스화       제품
         *  클래스     ----->      인스턴스(객체)
         */


        /**
         * 하나의 소스파일에 여러 클래스 작성하기 (가능하면 하나의 소스파일에 하나의 클래스가 낫다)
         * 1. public class가 있는 경우 소스파일 이름은 반드시 public class의 이름과 일치해야한다.
         *    단, public이 붙은 class는 단 하나만이다.
         *
         *    ex) Hello2.java만 가능
         *        public class Hello2 { }
         *        class Hello3 { }
         *
         *
         * 2. public class가 없는 경우 소스파일 이름 둘 다 가능
         *    ex) Hello2.java 와 Hello3.java 둘 다 가능
         *        class Hello2 { }
         *        class Hello3 { }
         */


    }
}
