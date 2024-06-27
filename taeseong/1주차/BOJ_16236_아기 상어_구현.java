import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static class Shark {
        int x;
        int y;
        int size;
        int grow;
        int time;

        Shark() {}

        Shark(int x, int y, int size, int grow, int time) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.grow = grow;
            this.time = time;
        }
    } // class Shark

    public static int N, answer;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1}; // 북 서 남 동
    public static Queue<Shark> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                // 아기 상어의 위치가 들어온다면
                if (map[r][c] == 9) {
                    // 상어 객체 생성, 맵에서 0으로 교체
                    queue.offer(new Shark(r, c, 2, 0, 0));
                    map[r][c] = 0;
                    visited[r][c] = true;
                }
            }
        }

        hunt();
        System.out.println(answer);
    } // main

    // 물고기 사냥
    public static void hunt() {
        while (!queue.isEmpty()) {
            int minDist = 987654321;
            int minX = -1;
            int minY = -1;

            // 같은 거리를 움직인 상어를 비교하기 위해 queue.size() 사용
            int size = queue.size();
            Shark currentShark = null;

            for (int i = 0; i < size; i++) {
                Shark shark = queue.poll();
                currentShark = shark;

                for (int d = 0; d < 4; d++) {
                    int nr = shark.x + dr[d];
                    int nc = shark.y + dc[d];

                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                        visited[nr][nc] = true;

                        // 0이거나 상어와 같은 크기의 물고기면 그냥 이동
                        if (map[nr][nc] == 0 || map[nr][nc] == shark.size) {
                            queue.offer(new Shark(nr, nc, shark.size, shark.grow, shark.time + 1));
                        }

                        // 상어보다 작은 물고기를 만나면
                        else if (map[nr][nc] < shark.size) {
                            // 먹을 수 있는 물고기 위치 갱신
                            if (shark.time + 1 < minDist || (shark.time + 1 == minDist && (nr < minX || (nr == minX && nc < minY)))) {
                                minDist = shark.time + 1;
                                minX = nr;
                                minY = nc;
                            }
                        }
                    }
                } // for dir
            } // for queue size

            // 먹을 수 있는 물고기를 찾았다면
            if (minX != -1 && minY != -1) {
                // 물고기 먹고
                map[minX][minY] = 0;

                // 걸린 시간 저장
                answer = minDist;

                // 방문배열 초기화 & 물고기 먹은 자리 방문 체크
                visited = new boolean[N][N];
                visited[minX][minY] = true;

                // queue 초기화
                queue = new LinkedList<>();

                // 성장이 된다면
                if (currentShark.grow + 1 == currentShark.size) {
                    // 상어 크기 키워주기
                    queue.offer(new Shark(minX, minY, currentShark.size + 1, 0, minDist));
                } else {
                    queue.offer(new Shark(minX, minY, currentShark.size, currentShark.grow + 1, minDist));
                }
            }
        } // while queue
    } // hunt()
}
