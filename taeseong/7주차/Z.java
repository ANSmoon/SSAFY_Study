import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(Z(N, r, c));
    } // main

    public static int Z(int n, int r, int c) {
        // 베이스 케이스
        if (n == 0) {
            return 0;
        }

        int half = (int) Math.pow(2, n - 1);
        int sectionSize = half * half;

        // 각 사분면의 시작 값을 더함
        if (r < half && c < half) {
            return Z(n - 1, r, c); // 1사분면
        } else if (r < half && c >= half) {
            return sectionSize + Z(n - 1, r, c - half); // 2사분면
        } else if (r >= half && c < half) {
            return 2 * sectionSize + Z(n - 1, r - half, c); // 3사분면
        } else {
            return 3 * sectionSize + Z(n - 1, r - half, c - half); // 4사분면
        }
    }
}