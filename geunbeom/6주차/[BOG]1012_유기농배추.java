import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(st.nextToken());
        int M, N, K;
        int[] p;
        int[][] arr;
        boolean[] visitedP;

        for (int i=0; i<tc; i++) {
            // 재 할당
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            p = new int[K+1];
            arr = new int[N][M];
            visitedP = new boolean[K+1];
            
            // 입력
            for (int j=1; j<K+1; j++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                p[j] = j;
                arr[r][c] = j;
            }

            // merge
            for (int r=0; r<N; r++) {
                for (int c=0; c<M; c++) {
                    int x = arr[r][c];
                    if (arr[r][c] == 0) continue;

                    if (r < N-1 && arr[r+1][c] != 0) {
                        int y = arr[r+1][c];
                        merge(p, x, y);
                    }if (c < M-1 && arr[r][c+1] != 0) {
                        int y = arr[r][c+1];
                        merge(p, x, y);
                    }
                }
            }
            
            // merge 의 갯수
            int sum = 0;
            for (int j=1; j<K+1; j++) {
                int k = findSetVisi(p, visitedP, j);
                if (k != 0) {
                    sum += 1;
                }
            }
            sb.append(sum).append("\n");

        }
        bw.write(sb+"");
        bw.flush();
        bw.close();

    }

    static void merge(int[] p, int x, int y) {
        int fx = findSet(p, x);
        int fy = findSet(p, y);

        if (fx < fy) p[fy] = fx;
        else p[fx] = fy;
    }

    static int findSet(int[] p, int x) {
        if(p[x] != x) return p[x] = findSet(p, p[x]);
        return x;
    }

    static int findSetVisi(int[] p, boolean[] visited, int x) {
        if(visited[x]) return 0;

        if(p[x] != x) {
            visited[p[x]] = true;
            return p[x] = findSetVisi(p, visited, p[x]);
        }

        visited[x] = true;
        return x;
    }

}
