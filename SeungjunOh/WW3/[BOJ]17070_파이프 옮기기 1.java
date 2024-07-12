import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int count, N;
    static int[] dr = { 0, 1, 1 }; // 가로, 세로, 대각선
    static int[] dc = { 1, 0, 1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        count = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int a = Integer.parseInt(st.nextToken());
                map[r][c] = a;
            }
        }

        for (int i = 0; i <= N; i++) {
            map[i][N] = 1;
            map[N][i] = 1;
        }

        DFS(0, 1, 0);

        System.out.println(count);

    }

    public static void DFS(int x, int y, int dir) {
        if (x==N-1&&y==N-1) {
            count++;
            return;
        }
        if (dir == 0) { // 가로면
            if (map[x + dr[0]][y + dc[0]] != 1) {
                DFS(x + dr[0], y + dc[0], 0);
                if (map[x + dr[1]][y + dc[1]] != 1 && map[x + dr[2]][y + dc[2]] != 1) {
                    DFS(x + dr[2], y + dc[2], 2);
                }
            }
        } else if (dir == 1) { // 세로면
            if (map[x + dr[1]][y + dc[1]] != 1) {
                DFS(x + dr[1], y + dc[1], 1);
                if (map[x + dr[0]][y + dc[0]] != 1 && map[x + dr[2]][y + dc[2]] != 1) {
                    DFS(x + dr[2], y + dc[2], 2);
                }
            }
        } else { // 대각선이면
            if (map[x + dr[0]][y + dc[0]] != 1) {
                DFS(x + dr[0], y + dc[0], 0);
            }
            if (map[x + dr[1]][y + dc[1]] != 1) {
            	DFS(x + dr[1], y + dc[1], 1);
            }
            if (map[x + dr[2]][y + dc[2]] != 1&&map[x + dr[0]][y + dc[0]] != 1 &&map[x + dr[1]][y + dc[1]] != 1 ) {
            	DFS(x + dr[2], y + dc[2], 2);
            }
        }

    }
}