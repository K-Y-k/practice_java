package org.example.ch5;


public class EX5_Array2 {
    public static void main(String[] args) {
        /**
         * 2차원 배열
         * : 행과 열의 테이블 형태로 데이터를 저장하기 위한 배열
         */
        // 스타일 1
        int[][] score = new int[4][3]; // 4(행) x 3(열) = 12개의 저장공간
        score[0][0] = 100;
        System.out.println("score[0][0] = " + score[0][0]);
        System.out.println();

        // 스타일 2
        int[][] score2 = {
                {100, 100, 100},
                {20, 20, 20},
                {30, 30, 30},
                {40, 40, 40},
                {50, 50, 50}
        };

        int korTotal = 0, engTotal = 0, mathTotal = 0;

        for (int i = 0; i < score2.length; i++) {
            int sum = 0;
            float avg = 0.0f;

            korTotal += score2[i][0];
            engTotal += score2[i][1];
            mathTotal += score2[i][2];
            System.out.printf("%3d", i+1);


            for (int j = 0; j < score2[i].length; j++) {
//                System.out.printf("score[%d][%d]=%d%n", i, j, score2[i][j]);
                sum += score2[i][j];
                System.out.printf(" %3d", score2[i][j]);
            }

            avg = sum / (float)score2[i].length;
            System.out.printf(" %d", sum);
            System.out.printf(" %f", avg);

            System.out.println();
        }

        System.out.println("korTotal = " + korTotal);
        System.out.println("engTotal = " + engTotal);
        System.out.println("mathTotal = " + mathTotal);

    }
}
