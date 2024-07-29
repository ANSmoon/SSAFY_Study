/*
 * Author : 문범수
 * Date : 2024-07-21
 * subject :
 * page : https://www.acmicpc.net/problem/12100
 * main : simulation
 * issue :
 * name : 2048(Easy)
 * duration : 2h
 * no : 12100
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class game {
    static int N, sum, ans, movingIndex, value; // movingIndex : 비어있거나 합쳐질 위치 / value : 이동할 위치의 값
    static int[][][] matrix;
    static StringTokenizer token;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        matrix = new int[N][N][6]; // 0층은 original 버전
        sum = 0;
        ans = 0;

        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j][0] = Integer.parseInt(token.nextToken());
            }
        }

        solve(0);
        System.out.println(ans);

    }
    // 총 소요 시간 : 4 ^ 5 * N * N= O(N ^ 2)
    // 새로운 블록 추가 X
    // 벽과 가까운 쪽이 먼저 sum
    // dfs() 구현

    static void solve(int depth) {
        // base line
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ans = Math.max(ans, matrix[i][j][5]);
                }
            }
            return;
        }

        // recursive line
        // 4방 진행
        for (int i = 0; i < 4; i++) {
            // deep-copy
            for (int k = 0; k < N; k++) {
                for (int j = 0; j < N; j++) {
                    matrix[k][j][depth + 1] = matrix[k][j][depth];
                }
            }

            switch (i) {
                case 0: // 위쪽으로 이동
                    for (int c = 0; c < N; c++) {
                        movingIndex = 0;
                        value = 0;
                        for (int r = 0; r < N; r++) {
                            // 현재 위치가 0이 아닐경우
                            if (matrix[r][c][depth + 1] != 0) {
                                // 최근 위치 값이 지금 위치 값과 같을 경우
                                if (value == matrix[r][c][depth + 1]) {
                                    matrix[movingIndex - 1][c][depth + 1] *= 2;
                                    matrix[r][c][depth + 1] = 0;
                                    value = 0;
                                } else { // 최근 위치 값과 다를 경우
                                    value = matrix[r][c][depth + 1];
                                    matrix[r][c][depth + 1] = 0;
                                    matrix[movingIndex][c][depth + 1] = value;
                                    movingIndex++;
                                }
                            }
                        }
                    }
                    break;

                case 1: // 오른쪽으로 이동
                    for (int r = 0; r < N; r++) {
                        movingIndex = N - 1;
                        value = 0;
                        for (int c = N - 1; c >= 0; c--) {
                            // 현재 위치가 0이 아닐경우
                            if (matrix[r][c][depth + 1] != 0) {
                                // 최근 위치 값이 지금 위치 값과 같을 경우
                                if (value == matrix[r][c][depth + 1]) {
                                    matrix[r][movingIndex + 1][depth + 1] *= 2;
                                    matrix[r][c][depth + 1] = 0;
                                    value = 0;
                                } else { // 최근 위치 값과 다를 경우
                                    value = matrix[r][c][depth + 1];
                                    matrix[r][c][depth + 1] = 0;
                                    matrix[r][movingIndex][depth + 1] = value;
                                    movingIndex--;
                                }
                            }
                        }
                    }
                    break;

                case 2: // 아래쪽으로 이동
                    for (int c = 0; c < N; c++) {
                        movingIndex = N - 1;
                        value = 0;
                        for (int r = N - 1; r >= 0; r--) {
                            // 현재 위치가 0이 아닐경우
                            if (matrix[r][c][depth + 1] != 0) {
                                // 최근 위치 값이 지금 위치 값과 같을 경우
                                if (value == matrix[r][c][depth + 1]) {
                                    matrix[movingIndex + 1][c][depth + 1] *= 2;
                                    matrix[r][c][depth + 1] = 0;
                                    value = 0;
                                } else { // 최근 위치 값과 다를 경우
                                    value = matrix[r][c][depth + 1];
                                    matrix[r][c][depth + 1] = 0;
                                    matrix[movingIndex][c][depth + 1] = value;
                                    movingIndex--;
                                }
                            }
                        }
                    }
                    break;

                case 3: // 왼쪽으로 이동
                    for (int c = 0; c < N; c++) {
                        movingIndex = 0;
                        value = 0;
                        for (int r = 0; r < N; r++) {
                            // 현재 위치가 0이 아닐경우
                            if (matrix[r][c][depth + 1] != 0) {
                                // 최근 위치 값이 지금 위치 값과 같을 경우
                                if (value == matrix[r][c][depth + 1]) {
                                    matrix[r][movingIndex - 1][depth + 1] *= 2;
                                    matrix[r][c][depth + 1] = 0;
                                    value = 0;
                                } else { // 최근 위치 값과 다를 경우
                                    value = matrix[r][c][depth + 1];
                                    matrix[r][c][depth + 1] = 0;
                                    matrix[r][movingIndex][depth + 1] = value;
                                    movingIndex++;
                                }
                            }
                        }
                    }
                    break;
            }
        }
    }
}