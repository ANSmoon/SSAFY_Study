/*
 * Author : 문범수
 * Date : 2024-07-12
 * subject :
 * page : https://www.acmicpc.net/problem/17070
 * main : dfs
 * issue : 시간초과
 * name : 파이프옮기기 1
 * duration : 120m
 * no : 17070
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class movingPipeLine {
    static int N;
    static int[][] map;
    static int ans;
    static StringTokenizer token;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        ans = 0;
        dfs(1, 2, 0);

        System.out.println(ans);
    }

    // x는 세로, y는 가로
    // direction이 0일 때는 파이프가 가로 방향, 1일 때는 세로 방향, 2일 때는 대각선 방향.
    public static void dfs(int x, int y, int direction) {
        if (x == N && y == N) { // 도착했을 경우
            ans++;
            return;
        }

        // code clean 구현
        switch (direction) {
            case 0: // 파이프가 가로 방향일 경우, 갈 수 있는 경우는 동쪽과 대각선임.
                if (y + 1 <= N && map[x][y + 1] == 0) { // 동쪽
                    dfs(x, y + 1, 0);
                }
                break;
            case 1: // 파이프가 세로 방향일 경우, 갈 수 있는 경우는 남쪽과 대각선임.
                if (x + 1 <= N && map[x + 1][y] == 0) { // 남쪽
                    dfs(x + 1, y, 1);
                }
                break;
            case 2: // 파이프가 대각선일 경우, 갈 수 있는 경우는 동쪽과 남쪽, 대각선임.
                if (y + 1 <= N && map[x][y + 1] == 0) { // 동쪽
                    dfs(x, y + 1, 0);
                }

                if (x + 1 <= N && map[x + 1][y] == 0) { // 남쪽
                    dfs(x + 1, y, 1);
                }
                break;
        }

        // 파이프가 어떠한 방향이든 대각선은 검사해야하기 때문에 공통 코드로 설정
        if (y + 1 <= N && x + 1 <= N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
            dfs(x + 1, y + 1, 2);
        }
    }
}