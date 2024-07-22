/*
 * Author : 문범수
 * Date : 2024-07-21
 * subject : Simulation
 * page : https://www.acmicpc.net/problem/12100
 * main : simulation
 * issue : 이동 방식 구현(시간초과)
 * name : 2048(Easy)
 * duration : 2h
 * no : 12100
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gameAnother {
    static int N, ans;
    static int[][] matrix;
    static int[] dr = {-1, 0, 1, 0}; // 방향 배열: 위, 오른쪽, 아래, 왼쪽
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        matrix = new int[N][N];
        ans = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        solve(0);
        System.out.println(ans);
    }

    static void solve(int depth) {
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ans = Math.max(ans, matrix[i][j]);
                }
            }
            return;
        }

        int[][] backup = new int[N][N];
        for (int i = 0; i < 4; i++) {
            copyMatrix(matrix, backup);
            move(i);
            solve(depth + 1);
            copyMatrix(backup, matrix);
        }
    }

    static void move(int direction) {
        boolean[][] merged = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            int idx = (direction == 1 || direction == 2) ? N - 1 : 0;

            for (int j = 0; j < N; j++) {
                int r = direction == 0 ? j : direction == 2 ? N - 1 - j : i;
                int c = direction == 1 ? N - 1 - j : direction == 3 ? j : i;

                if (matrix[r][c] != 0) {
                    if (direction == 0 || direction == 3) { // 위 또는 왼쪽
                        if (idx > 0 && temp[idx - 1] == matrix[r][c] && !merged[idx - 1][i]) {
                            temp[idx - 1] *= 2;
                            merged[idx - 1][i] = true;
                        } else {
                            temp[idx] = matrix[r][c];
                            idx++;
                        }
                    } else { // 아래 또는 오른쪽
                        if (idx < N - 1 && temp[idx + 1] == matrix[r][c] && !merged[idx + 1][i]) {
                            temp[idx + 1] *= 2;
                            merged[idx + 1][i] = true;
                        } else {
                            temp[idx] = matrix[r][c];
                            idx--;
                        }
                    }
                }
            }

            for (int j = 0; j < N; j++) {
                int r = direction == 0 ? j : direction == 2 ? N - 1 - j : i;
                int c = direction == 1 ? N - 1 - j : direction == 3 ? j : i;

                matrix[r][c] = temp[j];
            }
        }
    }

    static void copyMatrix(int[][] src, int[][] dest) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, N);
        }
    }
}
