import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while(cheeze()) time++;

        System.out.println(time);
    }

    // 외부 공기인지 확인하는 메서드
    public static void air() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;
        map[0][0] = 2;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();

            for(int d=0; d<4; d++){
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];

                // 범위 내에 있고 외부 공기라면 2로 변환
                // map[nr][nc] == 0 조건 때문에 내부 공기는 건들지 않는다
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != 1) {
                    map[nr][nc] = 2;
                    queue.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    // 치즈 녹이는 메서드
    public static boolean cheeze() {
        Queue<int[]> queue = new LinkedList<>();
        int cnt = 0;

        air();
        // 치즈 녹이기
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                // 치즈를 만나면 2변 이상이 공기와 만났는지 확인
                if(map[r][c] == 1){
                    int tmp = 0;

                    for(int d=0; d<4; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(map[nr][nc] == 2) tmp++;
                    }
                    // 만약 2변 이상이 외부 공기라면 치즈 녹이기
                    if(tmp >= 2) queue.offer(new int[] {r, c});
                }
                // 외부 공기 개수 세기
                else if(map[r][c] == 2) cnt++;
            }
        }

        while(!queue.isEmpty()){
            int[] melt = queue.poll();
            map[melt[0]][melt[1]] = 2;
        }

        // 외부 공기의 개수에 따라 while문을 위한 true, false return
        if(cnt != N * M) return true;
        else return false;
    }
}