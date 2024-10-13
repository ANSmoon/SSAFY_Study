/*
 * Author : 문범수
 * Date : 2024-09-03
 * subject : 이진 탐색
 * page : https://www.acmicpc.net/problem/10504
 * main : 투 포인터 탐색
 * issue : 시간초과 => 길이를 이용한 등차수열 공식 활용 / 길이초과 => StringBuilder 사용해 출력값 관리 필요
 * name : 덧셈
 * duration : 60m
 * no : 10504
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Plus {
    static int N, number;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();  // StringBuilder 사용
        N = Integer.parseInt(reader.readLine());

        for (int i = 0; i < N; i++) {
            number = Integer.parseInt(reader.readLine());
            if (!findShortestContinuousSum(sb)) {
                sb.append("IMPOSSIBLE\n");
            }
        }
        System.out.print(sb);  // 모든 결과를 한 번에 출력

        // x 는 44720까지가 최대 가능한 자연수
//        int x = 10;
//
//        while((x * (x+1)/2) < 1000000000){
//            x++;
//        }
//        System.out.println(x);
    }

    static boolean findShortestContinuousSum(StringBuilder sb) {
        // 연속된 2개 이상의 합 구하기
        for (int n = 2; (n * (n + 1)) / 2 <= number; n++) {
            if (((number - (n * (n + 1) / 2))) % n == 0) {
                int sum = ((number - n * (n + 1) / 2)) / n + 1;
                printResult(sb, sum, sum + n - 1);
                return true;
            }
        }
        return false;
    }

    // StringBuilder로 사용해야 출력 초과 안남
    static void printResult(StringBuilder sb, int start, int end) {
        sb.append(number).append(" = ");
        // 끝 지점 도달전에 종료
        for (int i = start; i < end; i++) {
            sb.append(i).append(" + ");
        }
        sb.append(end).append("\n");
    }
}