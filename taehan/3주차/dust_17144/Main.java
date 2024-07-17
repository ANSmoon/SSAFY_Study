package dust_17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 가로 세로 시간
    static int R, C, T;
    // 메인 맵
    static int[][] map;
    // 퍼지는 미세먼지 저장 배열
    static int[][] plus;
    // 공기청정기 위치 저장
    static List<int[]> purifier;
    // 이동 배열
    static int[] dr = {0, -1, 0, 1}; // 우 상 좌 하
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];
        plus = new int[R+1][C+1];
        purifier = new ArrayList<>();

        for(int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= C ; j++) {
                // map 정보 받기
                map[i][j] = Integer.parseInt(st.nextToken());

                // 공기청정기 위치 저장
                if(map[i][j] == -1) {
                    purifier.add(new int[] {i, j});
                }
            }
        }

//		for(int i = 0; i <= R; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

        for(int i = 0; i < T; i++) {
//            for(int j = 0; j <= R; j++) {
//                System.out.println(Arrays.toString(map[j]));
//            }
//            System.out.println();
            spread();
            for(int j = 1; j <= R; j++) {
                // 먼지 퍼뜨린 결과 저장 배열 시간마다 초기화
                Arrays.fill(plus[j], 0);
            }
//            for(int j = 0; j <= R; j++) {
//                System.out.println(Arrays.toString(map[j]));
//            }
//            System.out.println();
            circulation_U();
            circulation_D();
        }

        int result = 0;
        // 남은 먼지 다 더하기
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                result += map[i][j];
            }
        }
        
        // 공기청정기로 인해 빠진 값 추가
        System.out.println(result+2);
    }

    // 먼지 퍼뜨리는 함수
    public static void spread() {
        // 이동 위치 저장 변수
        int nr, nc;
        // 퍼뜨린 먼지 횟수
        int cnt = 0;
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                // 먼지가 있는 경우에만 아래 실행
                if(map[i][j] > 0) {
                    cnt = 0;
                    // 사방탐색
                    for(int k = 0; k < 4; k++) {
                        nr = i + dr[k];
                        nc = j + dc[k];
                        // map 범위안에 들고, 공기청정기 위치가 아닌지 확인
                        if(0 < nr && nr <= R && 0 < nc && nc <= C && map[nr][nc] >= 0) {
                            // 퍼뜨리고, cnt++
                            plus[nr][nc] += map[i][j] / 5;
                            cnt++;
                        }
                    }

                    // 퍼뜨린 만큼 원래위치에서 제거
                    map[i][j] -= (map[i][j] / 5) * cnt;
                    // 음수가 되면 0으로
                    if(map[i][j] < 0) map[i][j] = 0;
                }
            }
        }

//        for(int j = 0; j <= R; j++) {
//            System.out.println(Arrays.toString(plus[j]));
//        }
//        System.out.println();

        // 완성된 plus 배열을 가지고 map에 더해줌
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                map[i][j] += plus[i][j];
            }
        }
    }

    // 공기청정기 위 쪽 순환
    public static void circulation_U() {
        // 이동 위치 저장
        int[] now = new int[2];

        // 공기청정기 오른쪽 위치에서 시작
        now[0] = purifier.get(0)[0];
        now[1] = purifier.get(0)[1]+1;

        // 임시값 저장 위치 저장배열
        // idx 0번 위치에 이전 위치 값이 저장됨
        int[] tmp= new int[2];
        // idx 1번 위치에 현재 위치 값 저장
        tmp[1] = map[now[0]][now[1]];
        // dr, dc 의 idx => 이동 방향
        int idx = 0;

        // 공기청정기 위치로 다시 돌아올때까지 반복
        while(now[0] != purifier.get(0)[0] || now[1] != purifier.get(0)[1]){
            // 이동한 위치(현재)의 값을 1번 idx에 저장
            tmp[1] = map[now[0]][now[1]];
            // 0번 idx의 값을 현재 위치로 바꿈
            map[now[0]][now[1]] = tmp[0];
            // 범위 안이면 이동, 아니면 방향 바꿈
            if(0 < now[0] + dr[idx] && now[0] + dr[idx] <= R
                    && 0 < now[1] + dc[idx] && now[1] + dc[idx] <= C) {
                now[0] += dr[idx];
                now[1] += dc[idx];
            }else {
                // idx가 4가 되면 0으로 바꿈
                if(idx + 1 != 4) {
                    idx++;
                }else {
                    idx = 0;
                }
                now[0] += dr[idx];
                now[1] += dc[idx];
            }
            // 임시 배열에서 1번 idx 위치의 값을 0번 값으로 이동
            tmp[0] = tmp[1];
        };
    }

    // 아래 방향 순환
    // 위치만 반대로 이동 (idx--)
    public static void circulation_D() {
        int[] now = new int[2];

        now[0] = purifier.get(1)[0];
        now[1] = purifier.get(1)[1]+1;

        int[] tmp = new int[2];
        tmp[1] = map[now[0]][now[1]];
        int idx = 0;

        while(now[0] != purifier.get(1)[0] || now[1] != purifier.get(1)[1]) {
            tmp[1] = map[now[0]][now[1]];
            map[now[0]][now[1]] = tmp[0];
            if(0 < now[0] + dr[idx] && now[0] + dr[idx] <= R
                    && 0 < now[1] + dc[idx] && now[1] + dc[idx] <= C) {
                now[0] += dr[idx];
                now[1] += dc[idx];
            }else {
                if(idx - 1 != -1) {
                    idx--;
                }else {
                    idx = 3;
                }
                now[0] += dr[idx];
                now[1] += dc[idx];
            }
            tmp[0] = tmp[1];
        };
    }
}
