import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = -987654321;
        dfs(0);
        System.out.println(max);
    } // main

    static void dfs(int count) {
        if (count == 5) {
            int currentMax = -987654321;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > currentMax) currentMax = map[i][j];
                }
            }

            if (currentMax > max) {
                max = currentMax;
            }
            return;
        }

        int[][] backupMap = new int[N][N];
        for (int i = 0; i < 4; i++) {
            // 현재 맵 상태 백업
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    backupMap[x][y] = map[x][y];
                }
            }

            move(i);
            dfs(count + 1);

            // 맵 상태 복원
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    map[x][y] = backupMap[x][y];
                }
            }
        }
    } // dfs

    static void move(int dir) {
        switch (dir) {
            case 0: moveUp(); break;
            case 1: moveDown(); break;
            case 2: moveLeft(); break;
            case 3: moveRight(); break;
        }
    } // move

    static void moveUp() {
        for (int j = 0; j < N; j++) {
            int[] temp = new int[N];
            boolean[] merged = new boolean[N];
            int index = 0;
            for (int i = 0; i < N; i++) {
                if (map[i][j] != 0) {
                    if (index > 0 && temp[index - 1] == map[i][j] && !merged[index - 1]) {
                        temp[index - 1] *= 2;
                        merged[index - 1] = true;
                    } else {
                        temp[index++] = map[i][j];
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                map[i][j] = temp[i];
            }
        }
    }

    static void moveDown() {
        for (int j = 0; j < N; j++) {
            int[] temp = new int[N];
            boolean[] merged = new boolean[N];
            int index = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] != 0) {
                    if (index > 0 && temp[index - 1] == map[i][j] && !merged[index - 1]) {
                        temp[index - 1] *= 2;
                        merged[index - 1] = true;
                    } else {
                        temp[index++] = map[i][j];
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                map[N - 1 - i][j] = temp[i];
            }
        }
    }

    static void moveLeft() {
        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            boolean[] merged = new boolean[N];
            int index = 0;
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    if (index > 0 && temp[index - 1] == map[i][j] && !merged[index - 1]) {
                        temp[index - 1] *= 2;
                        merged[index - 1] = true;
                    } else {
                        temp[index++] = map[i][j];
                    }
                }
            }
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j];
            }
        }
    }

    static void moveRight() {
        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            boolean[] merged = new boolean[N];
            int index = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (map[i][j] != 0) {
                    if (index > 0 && temp[index - 1] == map[i][j] && !merged[index - 1]) {
                        temp[index - 1] *= 2;
                        merged[index - 1] = true;
                    } else {
                        temp[index++] = map[i][j];
                    }
                }
            }
            for (int j = 0; j < N; j++) {
                map[i][N - 1 - j] = temp[j];
            }
        }
    }
}
