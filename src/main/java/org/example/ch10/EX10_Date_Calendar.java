package org.example.ch10;

import java.util.Calendar;


/**
 * 날짜와 시간
 *
 * - java.util.Date
 * : 날짜와 시간을 다룰 목적으로 만들어진 클래스(JDK 1.0)
 *   Date의 메서드는 거의 deprecated(사용 X)되었지만, 여전히 쓰이고 있다.
 *   1. Calendar를 Date로 변환
 *   Calendar cal = Calendar.getInstance();
 *   ...
 *   Date d = new Date(cal.getTimeInMillis()); // Date(long date)
 *
 *   2. Date를 Calendar로 변환
 *   Date d = new Date();
 *   ...
 *   Calendar cal = Calendar.getInstance();
 *   cal.setTime(d);
 *
 * - java.util.Calendar
 * : Date 클래스를 개선한 새로운 클래스(JDK1.1) 여전히 단점 존재
 *
 * - java.time 패키지
 * : Date와 Calendar의 단점(닐찌+시간 같이 다뤄야하는 문제)을 개선한 새로운 클래스들을 제공(JDK 1.8)
 *   날짜(LocalDate클래스) / 시간(LocalDateTime)을 따로 다룰 수 있게 되었다.
 *   날짜, 시간 같이 다룰 때(LocalDateTime)
 *   즉, 가능하면 time 패키지를 사용하자
 */


/**
 * Calendar 클래스
 * : 추상 클래스이므로 getInstance()를 통해 구현된 객체를 얻어야 한다.
 *
 *   Calendar cal = new Calendar()          // 에러: 추상 클래스는 인스턴스를 생성할 수 없다.
 *   Calendar cal = Calendar.getInstance(); // getInstatnce() 메서드는 Calendar 클래스를 구현한 클래스의 인스턴스를 반환한다.
 *                                             Calendar의 구현 클래스 1.서양-GregorianCalendar
 *                                                                  2.불교-BuddhistCalendar
 *                                                                  3.일본-JapaneseCalendar
 *  - getInstance로 사용하는 이유
 *  구현 클래스로 생성하면 구현 클래스가 변경되면 일일히 변경해줘야하므로
 *
 */

/** - Calendar의 get()메서드
 *  get()으로 날짜와 시간 필드를 가져온다. - int get(int field)
 *  ex)
 *  Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간으로 셋팅됨
 *  int thisYear = cal.get(Calendar.YEAR); // 올해가 몇년인지 알아낸다.
 *  int lastDayOfMonth = cal.getActualMaximum(Calendar.DATE); // 이 달의 마지막날
 *
 *  - Calendar에 정의된 필드
 *    날짜 필드명                            설명
 *  YEAR                          년
 *  MONTH                         월(0부터 시작)
 *  WEEK_OF_YEAR                  1월 1일 ~ 지금 몇번째 주인지
 *  WEEK_OF_MONTH                 그 달의 몇번째 주인지
 *  DATE                          일
 *  DAY_OF_MONTH                  그 달의 몇 번째 일인지
 *  DAY_OF_YEAR                   1월1일에서 지금까지 그 해의 몇 번째 일인지
 *  DAY_OF_WEEK                   요일(1~7로 표현되고 1이 일요일)
 *  DAY_OF_WEEK_IN_MONTH          그 달의 몇 번째 요일
 *
 *    시간 필드명                            설명
 *  HOUR                          시간(0~11)
 *  HOUR_OF_DAY                   시간(0~23)
 *  MINUTE                        분
 *  SECOND                        초
 *  MILLISECOND                   천분의 일초
 *  ZONE_OFFSET                   GMT기준 시차(천분의 일초 단위)
 *  AM_PM                         오전/오후
 */

/**
 * Calendar의 set()메서드
 * - set()으로 날짜와 시간 지정하기
 * void set(int field, int value)
 * void set(int year, int month, int date)
 * void set(int year, int month, int date, int hourOfDay, int minute)
 * void set(int year, int month, int date, int hourOfDay, int minute, int second)
 *
 * - 날짜 지정하는 방법 (월(MONTH)이 0부터 시작하는 점에 주의)
 * ex)
 * Calendar date1 = Calendar.getInstance();
 * 
 * // 한번에 설정
 * date1.set(2017, 7, 15); // 2017년 8월 15일(0부터 시작하므로 7월 아님)
 * // 각각 설정
 * date1.set(Calendar.YEAR, 2017);
 * date1.set(Calendar.MONTH, 2017);
 * date1.set(Calendar.DATE, 2017);
 *
 * - 시간 지정하는 방법
 * ex)
 * Calendat time1 = Calendar.getInstance();
 *
 *  // time1을 10시 20분 30초로 설정
 * time1.set(Calendar.HOUR_OF_DAY, 10);
 * time1.set(Calendar.HOUR_OF_DAY, 20);
 * time1.set(Calendar.HOUR_OF_DAY, 30);
 */

/**
 * Calendar의 clear()메서드
 * : clear()는 Calendar 객체의 모든 필드를 초기화
 *   특정 날짜(년/월/일)만 설정하고 싶은 경우 전체 필드를 clear한 후 설정 해야 잘 나온다.
 *
 * ex)
 * Calendar dt = Calenar.getInstance(); // 현재 시간 생성
 * dt.clear();                          // 모든 필드를 초기화
 * System.out.println(new Date(dt.getTimeInMillis())); // 1970년 1월 1일 00:00:00로 초기화됨
 *
 * clear(int field)는 Calendar 객체의 특정 필드를 초기화
 * dt.clear(Calendar.SECOND);      // 초를 초기화
 * dt.clear(Calendar.MINUTE);      // 분을 초기화
 * dt.clear(Calendar.HOUR_OF_DAY); // 시간을 초기화
 * dt.clear(Calendar.HOUR);        // 시간을 초기화
 */

/**
 * Calendar의 add()/roll()메서드
 * - add()는 특정 필드의 값을 증가 또는 감소(다른 필드에 영향 O)
 * ex)
 * Calendar date = Calendar.getInstance();
 * date.clear();          // 모든 필드를 초기화
 * date.set(2020, 7, 31); // 2020년 8월 31일로 설정
 *
 * date.add(Calendar.DATE, 1);  // 날짜(DATE)에 1을 더한다.
 * date.add(Calendar.DATE, -8); // 월(MONTH)에서 8을 뺀다.
 *
 * - roll()은 특정 필드의 값을 증가 또는 감소(변경된 필드만 적용되고 다른 필드에 영향 X)
 * ex)
 * date.set(2020, 7, 31); // 2020년 8월 31일로 설정
 *
 * // add()와 달리 roll()은 다른 필드에 영햐을 미치지 않는다.
 * date.roll(Calendar.DATE, 1);  // 날짜(DATE)에 1을 더하면 2020 9 1인데 다른 필드에는 영향을 안주므로 2020 8 1이다.
 * date.roll(Calendar.DATE, -8); // 월(MONTH)에서 8을 빼면 2019 12 31인데 다른 필드에는 영향을 안주므로 2020 12 31이다.
 */

public class EX10_Date_Calendar {
    public static void main(String[] args) {
        /**
         * 예제 1 - get()으로 가져오기
         */
        // Calendar 객체 필드 가져오기 get()활용
        // 생성할 때 기본적으로 현재 날짜와 시간으로 설정된다.
        Calendar today = Calendar.getInstance(); // Calendar 객체를 생성
        System.out.println("이 해의 년도 : " + today.get(Calendar.YEAR));
        System.out.println("월(0~11, 0:1월) : " + today.get(Calendar.MONDAY));
        System.out.println("이 해의 몇 째 주 : " + today.get(Calendar.WEEK_OF_YEAR));
        System.out.println("이 달의 몇 째 주 : " + today.get(Calendar.WEEK_OF_MONTH));

        // DATE와 DAY_OF_MONTH는 같다.
        System.out.println("이 달의 몇 일 : " + today.get(Calendar.DATE));
        System.out.println("이 달의 몇 일 : " + today.get(Calendar.DAY_OF_MONTH));
        System.out.println("이 해의 몇 일 : " + today.get(Calendar.DAY_OF_YEAR));
        System.out.println("요일(1~7, 1:일요일) : " + today.get(Calendar.DAY_OF_WEEK));
        System.out.println("오전_오후(0:오전, 1:오후) : " + today.get(Calendar.AM_PM));
        System.out.println("시간(0~11) : " + today.get(Calendar.HOUR));
        System.out.println("시간(0~23) : " + today.get(Calendar.HOUR_OF_DAY));
        System.out.println("분(0~59) : " + today.get(Calendar.MINUTE));
        System.out.println("초(0~59) : " + today.get(Calendar.SECOND));
        System.out.println("1000분의 1초(0~999) : " + today.get(Calendar.MILLISECOND));

        // 천분의 1초를 시간으로 표시하기 위해 3600000으로 나누었다.(1시간 = 60 * 60초)
        System.out.println("TimeZone(-12~+12) : " + today.get(Calendar.ZONE_OFFSET)/(60*60*1000));
        System.out.println("이 달의 마지막 날 : " + today.getActualMaximum(Calendar.DATE)); // 이 달의 마지막 일을 찾는다.


        /**
         * 예제 2 - 날짜 지정하기
         */
        final String[] DAY_OF_WEEK = {"", "일", "월", "화", "수", "목", "금", "토"};

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        // month의 경우 0부터 시작하기에 4월인 경우 3로 지정
        // date1.set(2022,Calendar.MAY, 15)와 같이 할 수도 있다.
        date1.set(2022, 4, 15); // 2022년 5월 15일로 날짜를 설정
        System.out.println("date1은 " + DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)]+ "요일이고,");
        System.out.println("오늘(date2)은 " + DAY_OF_WEEK[date2.get(Calendar.DAY_OF_WEEK)] + "요일입니다.");

        // 두 날짜 간의 차이를 얻으려면, getTimeInMillis() 천분의 일초 단위로 변환한 후 차이를 구하면 된다.
        long difference = (date2.getTimeInMillis() - date1.getTimeInMillis()) / 1000;
        System.out.println("그 날(date1)부터 지금(date2)까지 " + difference + "초가 지났습니다.");
        System.out.println("일(day)로 계산하면 " + difference/(24*60*60) + "일입니다."); // 1일 = 24 * 60 * 60


        /**
         * 예제 3 - 시간 지정하기
         */
        final int[] TIME_UNIT = {3600, 60, 1};
        final String[] TIME_UNIT_NAME = {"시간", "분", "초"};

        Calendar time1 = Calendar.getInstance();
        Calendar time2 = Calendar.getInstance();

        time1.set(Calendar.HOUR_OF_DAY, 10);  // 10시 20분 30초로 설정
        time1.set(Calendar.MINUTE, 20);
        time1.set(Calendar.SECOND, 30);

        time2.set(Calendar.HOUR_OF_DAY, 20);  // 20시 30분 10초로 설정
        time2.set(Calendar.MINUTE, 30);
        time2.set(Calendar.SECOND, 10);

        System.out.println("time1 : "+ time1.get(Calendar.HOUR_OF_DAY) + "시 " + time1.get(Calendar.MINUTE) + "분" + time1.get(Calendar.SECOND) + "초");
        System.out.println("time2 : "+ time2.get(Calendar.HOUR_OF_DAY) + "시 " + time2.get(Calendar.MINUTE) + "분" + time2.get(Calendar.SECOND) + "초");

        long difference2 = Math.abs(time2.getTimeInMillis() - time1.getTimeInMillis()) / 1000;
        System.out.println("time1과 time2의 차이는 " + difference2 + "초 입니다.");

        String tmp = "";
        for (int i = 0; i < TIME_UNIT.length; i++) {
            tmp += difference2/TIME_UNIT[i] + TIME_UNIT_NAME[i];
            difference2 %= TIME_UNIT[i];
        }
        System.out.println("시분초로 변환하면 " + tmp + "입니다.");


        /**
         * 예제 4 - add(), roll()
         */
        Calendar date = Calendar.getInstance();
        date.set(2019, Calendar.AUGUST, 31);

        System.out.println(toString(date));
        System.out.println("= 1일 후 =");
        date.add(Calendar.DATE, 1);
        System.out.println(toString(date));

        System.out.println("= 6달 전 =");
        date.add(Calendar.MONTH, -6);
        System.out.println(toString(date));

        System.out.println("= 31일 후(roll) ="); // 일만 변화하고 번화한 다른 필드는 무시하고 기존 값으로
        date.roll(Calendar.DATE, 31);
        System.out.println(toString(date));

        System.out.println("= 31일 후(add) =");
        date.add(Calendar.DATE, 31);
        System.out.println(toString(date));
    }

    public static String toString(Calendar date) {
        return date.get(Calendar.YEAR)+"년 " +  date.get(Calendar.MONTH)+"년 " +  date.get(Calendar.DATE)+"일";
    }
}
