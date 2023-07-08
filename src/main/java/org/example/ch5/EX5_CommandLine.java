package org.example.ch5;

public class EX5_CommandLine {

    /**
     * 커맨드 라인을 통해 입력받기
     * : 커멘드 라인에 입력한 값이 문자열 배열에 담아서 전달된다.
     *   사용자가 다른 값을 넣으면 상황에 따른 프로그램을 바꾸었어야 했는데
     *   cmd 등에서 입력할 경우 사용자가 다른 값을 넣어도 프로그램을 바꾸지 않아도 된다.
     */
    public static void main(String[] args) {  // 커맨드 라인 입력한 내용을 String[] args에 담긴다

        /**
         *  ex1)                              클래스명       main(String[] args)에 들어갈 문자열
         *  C:\java\workspace\ch5\bin>java Ex5_CommandLine abc 123 "Hello world" 입력시
         *
         * 결과물 : 커맨드 라인 입력한 내용을 String[] args에 담긴다
         * 매개변수의 개수: 3
         * args[0] = "abc"
         * args[1] = "123"
         * args[2] = "Hello world"
         *
         *  ex2)                             클래스명
         *  C:\java\workspace\ch5\bin>java Ex5_CommandLine  입력시
         *
         * 결과물 : 아무것도 입력하지 않았으므로 main(String[] args)은 빈문자열이다.
         * 매개변수의 개수: 0
         */
        System.out.println("매개변수의 개수: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = \"" + args[i] + "\"");
        }



    }
}
