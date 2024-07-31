package organic_1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            st = new StringTokenizer(br.readLine());

            // 가로, 세로, 배추 위치 정보 개수
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[N][M];

            // 위치 임시 저장 변수
            int r, c;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                r = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                // 배추 위치 1로 변경
                map[r][c] = 1;
            }

            // 벌레 마리 수 저장 변수
            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        result++;
                        dfs(i, j);
                    }
                }
            }

            System.out.println(result);
        }
    }

    public static void dfs(int r, int c) {
        int nr, nc;
        // 사방탐색 하면서 1인 부분 다시 dfs 탐색
        for (int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];

            if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && map[nr][nc] == 1) {
                // 방문 처리 후 dfs 탐색
                visited[nr][nc] = true;
                dfs(nr, nc);
            }
        }
    }
}
