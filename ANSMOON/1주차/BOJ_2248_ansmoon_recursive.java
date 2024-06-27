/*
 * Author : 문범수
 * Date : 2024-06-26
 * subject : 재귀
 * page : https://www.acmicpc.net/problem/2448
 * main : 반복 설정
 * issue : 규칙 문제
 * name : no_2248.java
 * duration : 2h
 * no : 2248
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BOJ_2248_ansmoon_recursive {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][2 * N - 1];
        
        // 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                map[i][j] = ' ';
            }
        }

        drawStars(map, N, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map[i]);
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    private static void drawStars(char[][] map, int n, int x, int y) {
        if (n == 3) {
            map[x][y] = '*';
            map[x + 1][y - 1] = '*';
            map[x + 1][y + 1] = '*';
            map[x + 2][y - 2] = '*';
            map[x + 2][y - 1] = '*';
            map[x + 2][y] = '*';
            map[x + 2][y + 1] = '*';
            map[x + 2][y + 2] = '*';
        } else {
            int newN = n / 2;
            drawStars(map, newN, x, y);               // 상단
            drawStars(map, newN, x + newN, y - newN); // 좌하단
            drawStars(map, newN, x + newN, y + newN); // 우하단
        }
    }
}
