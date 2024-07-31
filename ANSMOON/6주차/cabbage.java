/*
 * Author : 문범수
 * Date : 2024-03-12
 * subject : 4방 탐색을 응용한 DFS
 * page : https://www.acmicpc.net/problem/1012
 * main : DFS
 * issue :
 * name : no_1012.java
 * duration : 23m
 * no : 1012
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class cabbage {
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int N, M, K, result, total, x, y;
    static StringTokenizer token;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        total = Integer.parseInt(reader.readLine());
        int index = 1;

        while (index <= total) {
            token = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(token.nextToken());
            N = Integer.parseInt(token.nextToken());
            K = Integer.parseInt(token.nextToken());

            matrix = new int[N][M];
            visited = new boolean[N][M];
            result = 0;

            // 현재 섬의 위치 표시
            for (int i = 0; i < K; i++) {
                token = new StringTokenizer(reader.readLine());
                x = Integer.parseInt(token.nextToken());
                y = Integer.parseInt(token.nextToken());
                matrix[y][x] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && matrix[i][j] != 0) {
                        dfs(i, j);
                        result++;
                    }
                }
            }
            System.out.println(result);

            index++;
        }

    }

    private static void dfs(int i, int j) {
        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int row = i + dr[k];
            int col = j + dc[k];
            if (row >= 0 && row < N && col >= 0 && col < M && !visited[row][col] && matrix[row][col] != 0) {
                dfs(row, col);
            }
        }
    }
}
