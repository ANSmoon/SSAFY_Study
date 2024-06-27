/*
 * Author : 문범수
 * Date : 2024-06-20
 * subject : DP
 * page : https://www.acmicpc.net/problem/1149
 * main : 규칙 설정
 * issue : 비용 문제
 * name : no_1149.java
 * duration : 1day
 * no : 1149
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BOJ_1149_ansmoon_DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N][3];
        // 각 집의 비용을 입력받습니다.
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            cost[i][0] = Integer.parseInt(line[0]); // 빨강 비용
            cost[i][1] = Integer.parseInt(line[1]); // 초록 비용
            cost[i][2] = Integer.parseInt(line[2]); // 파랑 비용
        }

        // DP 배열 초기화
        int[][] dp = new int[N][3];
        dp[0][0] = cost[0][0]; // 1번 집을 빨강으로 칠할 때의 비용
        dp[0][1] = cost[0][1]; // 1번 집을 초록으로 칠할 때의 비용
        dp[0][2] = cost[0][2]; // 1번 집을 파랑으로 칠할 때의 비용

        // 점화식을 이용하여 DP 테이블을 채웁니다.
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0]; // 빨강으로 칠할 때
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1]; // 초록으로 칠할 때
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2]; // 파랑으로 칠할 때
        }

        int result = Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]);

        System.out.println(result);
    }
}
