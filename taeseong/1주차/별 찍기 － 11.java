import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        // map 생성 및 공백으로 초기화
        map = new char[N][2 * N - 1];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < 2 * N - 1; c++) {
                map[r][c] = ' ';
            }
        }

        star(0, (2 * N - 1) / 2, N);

        for (int r = 0; r < N; r++) {
            bw.write(map[r]);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    } // main

    public static void star(int row, int col, int N) {
        if (N == 3) {
            map[row][col] = '*';
            map[row + 1][col - 1] = '*';
            map[row + 1][col + 1] = '*';
            for (int i = 0; i < 5; i++) {
                map[row + 2][col - 2 + i] = '*';
            }
        } else {
            int half = N / 2;
            star(row, col, half);
            star(row + half, col - half, half);
            star(row + half, col + half, half);
        }
    } // star()
}
